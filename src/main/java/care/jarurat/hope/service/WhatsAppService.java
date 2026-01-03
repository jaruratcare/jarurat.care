package care.jarurat.hope.service;

import care.jarurat.hope.Userflow.MainUserFlowService;
import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhatsAppService {
    
    @Lazy
    private MainUserFlowService mainUserFlowService;
    private final UserService userService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${whatsapp.token}")
    private String accessToken;

    @Value("${whatsapp.phone.number.id}")
    private String phoneNumberId;

    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;

    public String handleMessage(String userId, String name, String phone, String messageBody) {
        try {
            // Get or create user
            User user = userService.getUserById(userId);
            if (user == null) {
                user = new User();
                user.setUserId(userId);
                user.setName(name);
                user.setPhone(phone);
                user.setLastSeen(java.time.Instant.now().toString());
                userService.saveUser(user);
            } else {
                // Update user info
                if (name != null && !name.equals(user.getName())) {
                    user.setName(name);
                }
                if (phone != null && !phone.equals(user.getPhone())) {
                    user.setPhone(phone);
                }
                user.setLastSeen(java.time.Instant.now().toString());
                userService.updateUser(user);
            }

            // Get response from flow service
            Object response = mainUserFlowService.getResponse(user, messageBody);

            // Send response to WhatsApp
          if (response instanceof ListMessage) {
                sendListMessage(userId, (ListMessage) response);
            } 
            else if (response instanceof InteractiveMessage) {
                sendInteractiveMessage(userId, (InteractiveMessage) response);
            } 
            else if (response instanceof String) {
                sendTextMessage(userId, (String) response);
            }



            return "Message processed successfully";

        } catch (Exception e) {
            log.error("Error handling message for user {}: {}", userId, e.getMessage(), e);
            // Send error message to user
            sendTextMessage(userId, "Sorry, I encountered an error. Please try again by typing 'Hi'.");
            return "Error processing message";
        }
    }

    public void sendTextMessage(String to, String message) {
        try {
            String url = whatsappApiUrl;

            Map<String, Object> payload = new HashMap<>();
            payload.put("messaging_product", "whatsapp");
            payload.put("to", to);
            payload.put("type", "text");

            Map<String, String> text = new HashMap<>();
            text.put("body", message);
            payload.put("text", text);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            log.info("Attempting to send message to URL: {}. Payload: {}", url, payload); // Gemini-added log

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("✅ Text message sent successfully to {}", to);
            } else {
                log.error("❌ Failed to send text message. Status: {}, Response: {}",
                        response.getStatusCode(), response.getBody());
            }

        } catch (Exception e) {
            log.error("❌ Error sending text message to {}: {}", to, e.getMessage(), e);
        }
    }

 public void sendInteractiveMessage(String to, InteractiveMessage interactiveMessage) {
    try {
        String url = whatsappApiUrl;

        // 🚨 If no buttons → this is NOT an interactive message
        if (interactiveMessage.getButtons() == null || interactiveMessage.getButtons().isEmpty()) {
            sendTextMessage(to, interactiveMessage.getBody());
            return;
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("messaging_product", "whatsapp");
        payload.put("to", to);
        payload.put("type", "interactive");

        Map<String, Object> interactive = new HashMap<>();
        interactive.put("type", "button"); // ✅ ALWAYS button

        // Body
        interactive.put("body", Map.of(
                "text", interactiveMessage.getBody()
        ));

        // Header (optional)
        if (interactiveMessage.getHeader() != null && !interactiveMessage.getHeader().isBlank()) {
            interactive.put("header", Map.of(
                    "type", "text",
                    "text", interactiveMessage.getHeader()
            ));
        }

        // Footer (optional)
        if (interactiveMessage.getFooter() != null && !interactiveMessage.getFooter().isBlank()) {
            interactive.put("footer", Map.of(
                    "text", interactiveMessage.getFooter()
            ));
        }

        // ✅ ACTION IS MANDATORY
        Map<String, Object> action = new HashMap<>();

        List<Map<String, Object>> buttons = interactiveMessage.getButtons()
                .stream()
                .map(b -> Map.of(
                        "type", "reply",
                        "reply", Map.of(
                                "id", b.getId(),
                                "title", b.getTitle()
                        )
                ))
                .toList();

        action.put("buttons", buttons);
        interactive.put("action", action);

        payload.put("interactive", interactive);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        restTemplate.postForEntity(
                url,
                new HttpEntity<>(payload, headers),
                String.class
        );

        log.info("✅ Interactive message sent successfully to {}", to);

    } catch (Exception e) {
        log.error("❌ Error sending interactive message", e);
    }
}




    // NEW METHOD for List Messages
    public void sendListMessage(String to, ListMessage listMessage) {
        try {
            String url = whatsappApiUrl;

            Map<String, Object> payload = new HashMap<>();
            payload.put("messaging_product", "whatsapp");
            payload.put("to", to);
            payload.put("type", "interactive");

            Map<String, Object> interactive = new HashMap<>();
            interactive.put("type", "list");

            // Body
            Map<String, String> body = new HashMap<>();
            body.put("text", listMessage.getBody());
            interactive.put("body", body);

            // Header (optional)
            if (listMessage.getHeader() != null && !listMessage.getHeader().isEmpty()) {
                Map<String, String> header = new HashMap<>();
                header.put("type", "text");
                header.put("text", listMessage.getHeader());
                interactive.put("header", header);
            }

            // Footer (optional)
            if (listMessage.getFooter() != null && !listMessage.getFooter().isEmpty()) {
                Map<String, String> footer = new HashMap<>();
                footer.put("text", listMessage.getFooter());
                interactive.put("footer", footer);
            }

            // Action for List
            Map<String, Object> action = new HashMap<>();
            action.put("button", listMessage.getButtonText());

            List<Map<String, Object>> sections = new ArrayList<>();
            for (ListMessage.Section section : listMessage.getSections()) {
                Map<String, Object> sectionMap = new HashMap<>();
                if (section.getTitle() != null && !section.getTitle().isEmpty()) {
                    sectionMap.put("title", section.getTitle());
                }

                List<Map<String, Object>> rows = new ArrayList<>();
                for (ListMessage.Row row : section.getRows()) {
                    Map<String, Object> rowMap = new HashMap<>();
                    rowMap.put("id", row.getId());
                    rowMap.put("title", row.getTitle());
                    if (row.getDescription() != null && !row.getDescription().isEmpty()) {
                        rowMap.put("description", row.getDescription());
                    }
                    rows.add(rowMap);
                }
                sectionMap.put("rows", rows);
                sections.add(sectionMap);
            }
            action.put("sections", sections);
            interactive.put("action", action);
            payload.put("interactive", interactive);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            log.info("Attempting to send list message to URL: {}. Payload: {}", url, payload); 

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("✅ List message sent successfully to {}", to);
            } else {
                log.error("❌ Failed to send list message. Status: {}, Response: {}",
                        response.getStatusCode(), response.getBody());
            }

        } catch (Exception e) {
            log.error("❌ Error sending list message to {}: {}", to, e.getMessage(), e);
        }
    }
}