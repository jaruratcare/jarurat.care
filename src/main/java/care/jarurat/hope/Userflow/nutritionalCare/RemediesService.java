package care.jarurat.hope.Userflow.nutritionalCare;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.OpenAiServiceWrapper;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemediesService {

    private final OpenAiServiceWrapper openAiServiceWrapper;
    private final UserService userService;

    public Object getRemedies(User user) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        String symptom = user.getNutritionSymptoms() != null && !user.getNutritionSymptoms().isEmpty()
                ? user.getNutritionSymptoms()
                : "general";
        String foodPreference = (user.getFoodPreference() != null) ? user.getFoodPreference() : "not specified";
        String dietType = (user.getDietType() != null) ? user.getDietType() : "not specified";
        String Diabetic=user.getDiabeticStatus() != null && !user.getFoodPreference().isEmpty() ? user.getDiabeticStatus() : "not specified";

        String systemPrompt = "You are a healthcare assistant specializing in safe, simple home remedies for cancer patients. Always keep responses concise.";

       String userPrompt = lang.equals("hi")
        ? String.format(
            "कैंसर रोगियों के लिए %s के सुरक्षित घरेलू उपचार सुझाएं। " +
            "आहार प्रकार: %s, खाने की पसंद: %s, डायबिटीज़: %s। अधिकतम 800 वर्ण।",
            symptom, dietType, foodPreference, Diabetic
        )
        : String.format(
            "Suggest safe home remedies for %s for cancer patients. " +
            "User diet type: %s, food preference: %s, diabetic: %s. Max 800 characters.",
            symptom, dietType, foodPreference, Diabetic
        );
        String remedies = openAiServiceWrapper.generateResponse(systemPrompt, userPrompt, 0.7, 300);

        if (remedies.length() > 1024) {
            log.warn("Remedies response too long ({} chars), truncating...", remedies.length());
            remedies = remedies.substring(0, 1020) + "...";
        }

        log.info("Generated remedies length: {} characters", remedies.length());

        user.setCurrentIntent("nutrition_step4");
        userService.updateUser(user);

        return InteractiveMessage.builder()
                .header(lang.equals("hi") ? "💊 घरेलू उपचार" : "💊 Home Remedies")
                .body(remedies)
                .footer(lang.equals("hi")
                        ? "कृपया आगे बढ़ने के लिए नीचे से विकल्प चुनें।"
                        : "Please choose an option below to continue.")
                .buttons(List.of(
                        InteractiveMessage.Button.builder()
                                .id("back_to_menu")
                                .title(lang.equals("hi") ? "🔙 पिछली मेनू" : "🔙 Back to Menu")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                .build()
                ))
                .build();
    }
}
