package care.jarurat.hope.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import care.jarurat.hope.model.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppService {

    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;

    @Value("${whatsapp.phone.number.id}")
    private String phoneNumberId;

    @Value("${whatsapp.token}")
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UserService userService;

    public WhatsAppService(UserService userService) {
        this.userService = userService;
    }

    public void handleMessage(String phone, String message) {
        // Load or create user
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
            case "awaiting_language":
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
                break;

            case "awaiting_consent":
                if (message.equalsIgnoreCase("yes")) {
                    sendTextMessage(phone, "🙏 Thank you for your consent.\nPlease enter your *name*.");
                    userService.updateCurrentIntent(phone, "awaiting_name");
                } else if (message.equalsIgnoreCase("no")) {
                    sendTextMessage(phone, "❌ Without consent, we cannot proceed. Type YES anytime to continue.");
                } else {
                    sendTextMessage(phone, "Please reply YES or NO.");
                }
                break;

            case "awaiting_name":
                userService.updateName(phone, message);
                sendTextMessage(phone, "✅ Got it! Now, please tell us your *cancer type* (e.g., Breast, Lung, etc.)");
                userService.updateCurrentIntent(phone, "awaiting_cancer_type");
                break;

            case "awaiting_cancer_type":
                userService.updateField(phone, "cancerType", message);
                sendTextMessage(phone, "⏩ What stage is it? (e.g., Stage I, II, III, IV)");
                userService.updateCurrentIntent(phone, "awaiting_stage");
                break;

            case "awaiting_stage":
                userService.updateField(phone, "stage", message);
                sendTextMessage(phone, "🏥 Where are you getting treatment?");
                userService.updateCurrentIntent(phone, "awaiting_hospital");
                break;

            case "awaiting_hospital":
                userService.updateField(phone, "hospital", message);
                sendTextMessage(phone, "✅ Thank you! You're now fully onboarded. Please choose a service:\n1. Financial 💰\n2. Nutrition 🍱\n3. Emotional 💜\n4. Hospitals 🏥\n5. Stay & Food 🍲\n6. Labs 🧪\n7. Palliative ☘️\n8. Talk to Volunteer 🧑‍🤝‍🧑");
                userService.updateCurrentIntent(phone, "main_menu");
                break;

            case "main_menu":
                // Here you'd call different services based on menu choice
                sendTextMessage(phone, "🚧 This module is under development.");
                break;

            default:
                sendTextMessage(phone, "❓ I'm not sure what to do. Please reply with a menu number or type HELP.");
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
            restTemplate.postForEntity(url, request, String.class);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to send WhatsApp message to " + phone);
        }
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}