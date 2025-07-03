package care.jarurat.hope.controller;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import care.jarurat.hope.service.WhatsAppService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final UserService userService;
    private final WhatsAppService WhatAppService;
    public WebhookController(UserService userService, WhatsAppService WhatAppService) {
        this.userService = userService;
        this.WhatAppService = WhatAppService;
    }
    //webhook verification
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.token") String token,
                                                @RequestParam("hub.challenge") String challenge) {
        if("subscribe".equals(mode) && "VERIFY_TOKEN".equals(token)){
           return ResponseEntity.ok(challenge);
        }else{
     return ResponseEntity.status(403).body("VERFICATION FAILED");
        }

    }
    public ResponseEntity<String> recieveMessage(@RequestBody JsonNode payload) {
        try {
            JsonNode messageNode = payload.at("/entry/0/messaging/0/messages/0");
            JsonNode contactNode = messageNode.at("/entry/0/messaging/0/contacts/0");
            if (messageNode.isMissingNode()) {
                return ResponseEntity.ok("No message");
            }
            String from = messageNode.get("from").asText();
            String messageBody = messageNode.get("text").get("body").asText();
            userService.getUser(from, user -> {
                if (user == null) {
                    User newUser = new User();
                    newUser.setPhone(from);
                    userService.saveOrUpdateUser(newUser);
                }
                whatsAppService.handleMessage(from, messageBody);
            });
            return ResponseEntity.ok("Message processed");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("webhook error");

        }
    }


}
