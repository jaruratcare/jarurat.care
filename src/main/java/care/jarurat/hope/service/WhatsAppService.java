package care.jarurat.hope.service;

import care.jarurat.hope.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WhatsAppService {

    private final UserService userService;
    private final SupportModuleService supportModuleService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;

    @Value("${whatsapp.phone.number.id}")
    private String phoneNumberId;

    @Value("${whatsapp.token}")
    private String token;

    public WhatsAppService(UserService userService, SupportModuleService supportModuleService) {
        this.userService = userService;
        this.supportModuleService = supportModuleService;
    }

    public void handleMessage(String phone, String message) {
        User user = userService.getUser(phone);

        if (user == null) {
            user = new User();
            user.setUserId(phone);
            user.setPhone(phone);
            user.setLastSeen(now());
            user.setCurrentIntent("awaiting_language");
            userService.saveOrUpdateUser(user);
            sendTextMessage(phone, "👋 Welcome! Please select your preferred language:\n1. English\n2. Hindi");
            return;
        }

        userService.updateLastSeen(phone, now());
        String intent = user.getCurrentIntent();

        switch (intent) {
            case "awaiting_language" -> {
                if (message.equals("1")) {
                    userService.updateLanguage(phone, "English");
                    sendTextMessage(phone, "✅ Language set to English.\nDo you consent to share information to receive support?\nReply YES or NO.");
                    userService.updateCurrentIntent(phone, "awaiting_consent");
                } else if (message.equals("2")) {
                    userService.updateLanguage(phone, "Hindi");
                    sendTextMessage(phone, "✅ भाषा हिंदी सेट कर दी गई है।\nक्या आप जानकारी साझा करने के लिए सहमत हैं?\nYES या NO भेजें।");
                    userService.updateCurrentIntent(phone, "awaiting_consent");
                } else {
                    sendTextMessage(phone, "❌ Invalid selection. Please reply with 1 or 2.");
                }
            }

            case "awaiting_consent" -> {
                if (message.equalsIgnoreCase("yes")) {
                    sendTextMessage(phone, "🙏 Thank you for your consent.\nPlease enter your *name*.");
                    userService.updateCurrentIntent(phone, "awaiting_name");
                } else if (message.equalsIgnoreCase("no")) {
                    sendTextMessage(phone, "❌ Without consent, we cannot proceed. Type YES anytime to continue.");
                } else {
                    sendTextMessage(phone, "Please reply YES or NO.");
                }
            }

            case "awaiting_name" -> {
                userService.updateName(phone, message);
                sendTextMessage(phone, "✅ Got it! Now, please tell us your *cancer type* (e.g., Breast, Lung, etc.)");
                userService.updateCurrentIntent(phone, "awaiting_cancer_type");
            }

            case "awaiting_cancer_type" -> {
                userService.updateField(phone, "cancerType", message);
                sendTextMessage(phone, "⏩ What stage is it? (e.g., Stage I, II, III, IV)");
                userService.updateCurrentIntent(phone, "awaiting_stage");
            }

            case "awaiting_stage" -> {
                userService.updateField(phone, "stage", message);
                sendTextMessage(phone, "🏥 Where are you getting treatment?");
                userService.updateCurrentIntent(phone, "awaiting_hospital");
            }

            case "awaiting_hospital" -> {
                userService.updateField(phone, "hospital", message);
                sendTextMessage(phone, """
                        ✅ Thank you! You're now fully onboarded. Please choose a service:
                        1. Financial 💰
                        2. Nutrition 🍱
                        3. Emotional 💜
                        4. Hospitals 🏥
                        5. Stay & Food 🍲
                        6. Labs 🧪
                        7. Palliative ☘️
                        8. Talk to Volunteer 🧑‍🤝‍🧑
                        """);
                userService.updateCurrentIntent(phone, "main_menu");
            }

            case "main_menu" -> {
                supportModuleService.handleModule(phone, message);
            }

            default -> sendTextMessage(phone, "❓ I'm not sure what to do. Please type HELP or choose a menu number.");
        }
    }

    public void sendTextMessage(String phone, String text) {
        try {
            String url = whatsappApiUrl + "/" + phoneNumberId + "/messages";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);

            Map<String, Object> payload = new HashMap<>();
            payload.put("messaging_product", "whatsapp");
            payload.put("to", phone);
            payload.put("type", "text");

            Map<String, String> textObj = new HashMap<>();
            textObj.put("body", text);
            payload.put("text", textObj);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            log.info("✅ Message sent to {}. Response: {}", phone, response.getBody());
        } catch (Exception e) {
            log.error("❌ Failed to send WhatsApp message to {}", phone, e);
        }
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
