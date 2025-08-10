package care.jarurat.hope.controller;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.WhatsAppService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import care.jarurat.hope.service.UserService;

@Slf4j
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final WhatsAppService whatsappService;
    private final UserService userService;

    @Value("${whatsapp.verify.token}")
    private String VERIFY_TOKEN;

    public WebhookController(WhatsAppService whatsappService,UserService userService) {
        this.whatsappService = whatsappService;
        this.userService=userService;
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

        JsonNode message = value.get("messages").get(0);
        String from = message.get("from").asText(); // user phone number
        String messageBody = message.get("text").get("body").asText();

        log.info("💬 Received message from {}: {}", from, messageBody);

        String name = null, phone = null;
        if (value.has("contacts")) {
            JsonNode contact = value.get("contacts").get(0);
            name = contact.get("profile").get("name").asText();
            phone = contact.get("wa_id").asText();
        }

        // Load user from DB (could be null)
        User user = userService.getUserById(from);

        if (user == null) {
            user = new User();
            user.setUserId(from);
            user.setName(name);
            user.setPhone(phone);
            user.setCurrentIntent(null);
            log.info("🆕 New user created: {}", from);
            // Do NOT save here to avoid premature overwrite
        } else {
            // Update name/phone only if changed
            if (name != null && !name.equals(user.getName())) {
                user.setName(name);
            }
            if (phone != null && !phone.equals(user.getPhone())) {
                user.setPhone(phone);
            }
        }

        // Update lastSeen timestamp
        user.setLastSeen(java.time.Instant.now().toString());

        // Now pass user and message to flow service which will update user state and save the user
        String responseMessage = whatsappService.handleMessage(
            user.getUserId(),
            user.getName(),
            user.getPhone(),
            messageBody
        );

        return ResponseEntity.ok("Message processed");

    } catch (Exception e) {
        log.error("❌ Error processing webhook payload", e);
        return ResponseEntity.status(500).body("Webhook error");
    }
}


}
