package care.jarurat.hope.service;

import care.jarurat.hope.Userflow.MainUserFlowService;
import care.jarurat.hope.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhatsAppService {

    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;

    @Value("${whatsapp.token}")
    private String apiToken;

    private final UserService userService;
    private final MainUserFlowService mainUserFlowService;
    private final RestTemplate restTemplate = new RestTemplate();

    public String handleMessage(String userId, String name, String phone, String messageBody) {
        User user = userService.getUserById(userId);

        if (user == null) {
            user = new User();
            user.setUserId(userId);
            if (name != null) user.setName(name);
            if (phone != null) user.setPhone(phone);
            user.setCurrentIntent(null); // explicitly set to null for new user
            log.info("🆕 Created new user: {}", userId);
            userService.saveUser(user);
        } else {
            if (name != null && !name.equals(user.getName())) user.setName(name);
            if (phone != null && !phone.equals(user.getPhone())) user.setPhone(phone);
        }

        user.setLastSeen(Instant.now().toString());

        log.debug("WhatsAppService: currentIntent before processing = {}", user.getCurrentIntent());

        // Process message and get reply
        String reply = mainUserFlowService.getResponse(user, messageBody);

        log.debug("WhatsAppService: currentIntent after processing = {}", user.getCurrentIntent());

        // Save updated user
        userService.updateUser(user);

        // Send reply to WhatsApp user
        sendTextMessage(user.getPhone(), reply);

        return reply;
    }

    private void sendTextMessage(String to, String message) {
        Map<String, Object> body = Map.of(
                "messaging_product", "whatsapp",
                "to", to,
                "type", "text",
                "text", Map.of("body", message)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    whatsappApiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );
            log.info("✅ Sent message to {}: {}\nResponse: {}", to, message, response.getBody());
        } catch (Exception e) {
            log.error("❌ Failed to send WhatsApp message to {}. Error: {}", to, e.getMessage());
        }
    }
}
