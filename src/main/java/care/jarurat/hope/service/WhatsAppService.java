package care.jarurat.hope.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WhatsAppService {
    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;
    @Value("${whatsapp.token}")
    private String apiToken;
    private final RestTemplate restTemplate=new RestTemplate();

    public void handleMessage(String from, String messageBody) {
        String input = messageBody.trim().toLowerCase();
        if(input.equals("hi")){
            sendTextMessage(from,
            "welcome to hope! Please choose your language:\n"+
            "1) English\n"+
            "2)Hindi"
            );
        }else if(input.equals("1")){
            sendTextMessage(from, 
            "You selected English.How can i help you today?\n" +
                    "1) Financial Guidance\n" +
                    "2) Nutritional Care\n" +
                    "3) Emotional Support"

            );
        }else if(input.equals("2")){
            sendTextMessage(from, 
            "आपने हिंदी चुना। आज मैं आपकी किस प्रकार मदद कर सकता हूँ?\n" +
                    "1) वित्तीय मार्गदर्शन\n" +
                    "2) पोषण संबंधी देखभाल\n" +
                    "3) भावनात्मक समर्थन");
        }else if(input.equals("3")){
            sendTextMessage(from, "Emotional Support module is coming soon. Type 'Hi' to restart.");
        }else{
            sendTextMessage(from, "I didn’t understand that. Please type 'Hi' to start again.");
        }
    }
    private void sendTextMessage(String to,String message){
    Map<String,Object> body= Map.of(
       "messaging_product", "whatsapp",
                "to", to,
                "type", "text",
                "text", Map.of("body", message)
                );
         HttpHeaders headers = new HttpHeaders();
         headers.setBearerAuth(apiToken);
         headers.add("Content-type","application/json");
         HttpEntity<Map<String,Object>> request = new HttpEntity<>(body,headers);
         try{
            restTemplate.exchange(whatsappApiUrl,HttpMethod.POST,request,String.class );
            log.info("Sent message to {}: {}", to, message);
         }catch (Exception e) {
            log.error("Failed to send WhatsApp message to {}. Error: {}", to, e.getMessage());
        }
    }

}
