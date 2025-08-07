package care.jarurat.hope.controller;

import care.jarurat.hope.model.User;
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

    @GetMapping
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.verify_token") String token,
                                                @RequestParam("hub.challenge") String challenge) {
        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            log.info("✅ Webhook verified");
            return ResponseEntity.ok(challenge);
        } else {
            log.warn("❌ Webhook verification failed: mode={}, token={}", mode, token);
            return ResponseEntity.status(403).body("Verification failed");
        }
    }

    @PostMapping
public ResponseEntity<String> receiveMessage(@RequestBody JsonNode payload) {
    try {
        log.debug("📩 Incoming payload: {}", payload.toPrettyString());

        JsonNode entry = payload.get("entry").get(0);
        JsonNode changes = entry.get("changes").get(0);
        JsonNode value = changes.get("value");

        if (!value.has("messages")) {
            log.info("📭 No messages in payload.");
            return ResponseEntity.ok("No message to process");
        }

        JsonNode messages = value.get("messages");
        JsonNode message = messages.get(0);

        String from = message.get("from").asText(); // user phone number
        String messageBody = message.get("text").get("body").asText();

        log.info("💬 Received message from {}: {}", from, messageBody);

        // ✅ Extract user name and save user
        if (value.has("contacts")) {
            JsonNode contact = value.get("contacts").get(0);
            String name = contact.get("profile").get("name").asText();
            String phone = contact.get("wa_id").asText();

            User user = new User();
            user.setUserId(from);
            user.setName(name);
            user.setPhone(phone);
            user.setLastSeen(java.time.Instant.now().toString());

            userService.saveUser(user);
        }

        whatsappService.handleMessage(from, messageBody);

        return ResponseEntity.ok("Message processed");

    } catch (Exception e) {
        log.error("❌ Error processing webhook payload", e);
        return ResponseEntity.status(500).body("Webhook error");
    }
}

}
