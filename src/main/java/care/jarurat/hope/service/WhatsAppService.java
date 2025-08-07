package care.jarurat.hope.service;

import care.jarurat.hope.Userflow.MainUserFlowService;
import care.jarurat.hope.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
public class WhatsAppService {

    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;

    @Value("${whatsapp.token}")
    private String apiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserService userService;

    @Autowired
    private MainUserFlowService mainUserFlowService;

    public void handleMessage(String from, String messageBody) {
        String input = messageBody.trim().toLowerCase();

        // Retrieve or create new user
        User user = userService.getUserById(from);
        if (user == null) {
            user = new User();
            user.setUserId(from);
        }

        // Update last seen timestamp
        user.setLastSeen(Instant.now().toString());

        // Use flow logic to get response and update user state
        String reply = mainUserFlowService.getResponse(user, input);

        // Save updated user state to Firestore
        userService.saveUser(user);

        // Send reply
        sendTextMessage(from, reply);
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
