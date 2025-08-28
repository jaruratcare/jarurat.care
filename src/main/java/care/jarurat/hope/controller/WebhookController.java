package care.jarurat.hope.controller;

import care.jarurat.hope.model.User;
import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.Userflow.MainUserFlowService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import care.jarurat.hope.service.UserService;
import care.jarurat.hope.service.WhatsAppService;
import care.jarurat.hope.model.ListMessage;

@Slf4j
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final MainUserFlowService mainUserFlowService;
    private final UserService userService;
    private final WhatsAppService whatsAppService;

    @Value("${whatsapp.verify.token}")
    private String VERIFY_TOKEN;

    public WebhookController(MainUserFlowService mainUserFlowService, UserService userService, WhatsAppService
            whatsAppService) {
        this.mainUserFlowService = mainUserFlowService;
        this.userService = userService;
        this.whatsAppService = whatsAppService;
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
            String messageBody;

            if (message.has("text")) {
                messageBody = message.get("text").get("body").asText();

            } else if (message.has("interactive")) {
                JsonNode interactive = message.get("interactive");
                if (interactive.has("button_reply")) {
                    messageBody = interactive.get("button_reply").get("id").asText();
                } else if (interactive.has("list_reply")) {
                    messageBody = interactive.get("list_reply").get("id").asText();
                } else {
                    log.warn("Received unhandled interactive message type.");
                    messageBody = "";
                }

            } else if ("location".equals(message.get("type").asText()) && message.has("location")) {
                JsonNode location = message.get("location");
                double lat = location.get("latitude").asDouble();
                double lon = location.get("longitude").asDouble();
                String address = location.has("address") ? location.get("address").asText() : "";
                log.info("📍 Received location from {}: {}, {} ({})", from, lat, lon, address);

                // Convert into a string input that your NearbyHospitalRouter can parse
                messageBody = lat + "," + lon;

            } else {
                log.warn("Received message with no text, interactive, or location content.");
                messageBody = "";
            }

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

            // Process message through main user flow service
            Object response = mainUserFlowService.getResponse(user, messageBody);

            log.info("📤 Response generated for user {}: {}", from, response);

            // Send the response back to the user
            if (response instanceof InteractiveMessage) {
                whatsAppService.sendInteractiveMessage(from, (InteractiveMessage) response);
            } else if (response instanceof String) {
                whatsAppService.sendTextMessage(from, (String) response);
            } else if (response instanceof ListMessage) {
                whatsAppService.sendListMessage(from, (ListMessage) response);
            }

            return ResponseEntity.ok("Message processed");

        } catch (Exception e) {
            log.error("❌ Error processing webhook payload", e);
            return ResponseEntity.status(500).body("Webhook error");
        }
    }
}
