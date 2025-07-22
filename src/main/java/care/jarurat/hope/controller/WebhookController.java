package care.jarurat.hope.controller;

import care.jarurat.hope.service.UserService;
import care.jarurat.hope.service.WhatsAppService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final UserService userService;
    private final WhatsAppService whatsappService;

    @Value("${whatsapp.verify.token}")
    private String VERIFY_TOKEN;

    public WebhookController(UserService userService, WhatsAppService whatsappService) {
        this.userService = userService;
        this.whatsappService = whatsappService;
    }

    // Webhook verification (GET)
    @GetMapping
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.token") String token,
                                                @RequestParam("hub.challenge") String challenge) {
        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            log.info("Webhook verified successfully");
            return ResponseEntity.ok(challenge);
        } else {
            log.warn("Webhook verification failed: mode={}, token={}", mode, token);
            return ResponseEntity.status(403).body("Verification failed");
        }
    }
    // Receive WhatsApp message (POST)
    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody JsonNode payload) {
        try {
            JsonNode entry = payload.get("entry").get(0);
            JsonNode changes = entry.get("changes").get(0);
            JsonNode value = changes.get("value");
            JsonNode messages = value.get("messages");

            if (messages == null || messages.isEmpty()) {
                log.info("Received webhook with no message.");
                return ResponseEntity.ok("No message found");
            }

            JsonNode message = messages.get(0);
            String from = message.get("from").asText(); //  (user ID)
            String messageBody = message.get("text").get("body").asText();

            log.info("Received message from {}: {}", from, messageBody);

            // Handle message
            whatsappService.handleMessage(from, messageBody);

            return ResponseEntity.ok("Message processed");

        } catch (Exception e) {
            log.error("Error processing webhook payload", e);
            return ResponseEntity.status(500).body("Webhook error");
        }
    }
}
