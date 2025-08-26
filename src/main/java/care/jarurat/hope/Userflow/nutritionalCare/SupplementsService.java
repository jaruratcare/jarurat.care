package care.jarurat.hope.Userflow.nutritionalCare;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.OpenAiServiceWrapper;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplementsService {

    private final OpenAiServiceWrapper openAiServiceWrapper;
    private final UserService userService;

    public Object getSupplements(User user) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        String symptom = user.getNutritionSymptoms() != null && !user.getNutritionSymptoms().isEmpty()
                ? user.getNutritionSymptoms()
                : "general";
        String diat_type=user.getDietType() !=null && !user.getDietType().isEmpty() ? user.getDietType() : "general";
        String prefrence=user.getFoodPreference() !=null && !user.getFoodPreference().isEmpty() ? user.getFoodPreference() :"general";

        String systemPrompt = "You are a healthcare assistant specializing in safe, affordable supplements for cancer patients. Always keep responses concise and practical.";

        String userPrompt = lang.equals("hi")
                ? String.format("कैंसर रोगियों के लिए %s के आधार पर सुरक्षित और किफायती सप्लीमेंट्स सुझाएं। अधिकतम 800 वर्ण।", symptom , diat_type,prefrence)
                : String.format("Suggest safe and affordable supplements for cancer patients based on %s. Max 800 characters.", symptom,diat_type,prefrence);

        String supplements = openAiServiceWrapper.generateResponse(systemPrompt, userPrompt, 0.7, 300);

        if (supplements.length() > 1024) {
            log.warn("Supplements response too long ({} chars), truncating...", supplements.length());
            supplements = supplements.substring(0, 1020) + "...";
        }

        log.info("Generated supplements length: {} characters", supplements.length());

        return InteractiveMessage.builder()
                .header(lang.equals("hi") ? "📦 किफायती सप्लीमेंट्स" : "📦 Affordable Supplements")
                .body(supplements)
                .footer(lang.equals("hi")
                        ? "कृपया आगे बढ़ने के लिए नीचे से विकल्प चुनें।"
                        : "Please choose an option below to continue.")
                .buttons(List.of(
                        InteractiveMessage.Button.builder()
                                .id("back_to_menu")
                                .title(lang.equals("hi") ? "🔙 मेनू पर जाएँ" : "🔙 Back to Menu")
                                .build()
                ))
                .build();
    }
}
