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
public class  ImmunityService {

    private final OpenAiServiceWrapper openAiServiceWrapper;
    private final UserService userService;

    public Object getImmunityTips(User user) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        String symptom = user.getNutritionSymptoms() != null && !user.getNutritionSymptoms().isEmpty()
                ? user.getNutritionSymptoms()
                : "general";
        String diat_type=user.getDietType() !=null && !user.getDietType().isEmpty() ? user.getDietType() : "general";
        String prefrence=user.getFoodPreference() !=null && !user.getFoodPreference().isEmpty() ? user.getFoodPreference() :"general";

       String systemPrompt = "You are a healthcare assistant specializing in boosting immunity for cancer patients through safe, natural, and practical methods. Always keep responses concise and easy to follow.";

        String userPrompt = lang.equals("hi")
        ? String.format(
            "कैंसर रोगियों के लिए %s के आधार पर सुरक्षित और किफायती इम्यूनिटी बढ़ाने के उपाय सुझाएं। " +
            "आहार प्रकार: %s, खाने की पसंद: %s। यदि शाकाहारी है तो मांसाहारी आइटम शामिल न करें। अधिकतम 800 वर्ण।",
            symptom, diat_type, prefrence
        )
        : String.format(
            "Suggest safe and affordable immunity-boosting tips for cancer patients based on %s. " +
            "User diet type: %s, food preference: %s. " +
            "Do NOT include any non-vegetarian items if the preference is vegetarian. Max 800 characters.",
            symptom, diat_type, prefrence
        );


        String supplements = openAiServiceWrapper.generateResponse(systemPrompt, userPrompt, 0.7, 300);

        if (supplements.length() > 1024) {
            log.warn("Supplements response too long ({} chars), truncating...", supplements.length());
            supplements = supplements.substring(0, 1020) + "...";
        }

        log.info("Generated supplements length: {} characters", supplements.length());

        return InteractiveMessage.builder()
        .header(lang.equals("hi") ?  "🛡️ इम्यूनिटी टिप्स" : "🛡️ Immunity Tips")
        .body(supplements)
        .footer(lang.equals("hi")
                ? "कृपया आगे बढ़ने के लिए नीचे से विकल्प चुनें।"
                : "Please choose an option below to continue.")
        .buttons(List.of(
                InteractiveMessage.Button.builder()
                        .id("back")
                        .title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back")
                        .build(),
                InteractiveMessage.Button.builder()
                        .id("main_menu")
                        .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                        .build()
        ))
        .build();
    }
}
