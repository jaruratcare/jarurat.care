package care.jarurat.hope.Userflow.nutritionalCare;

import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.ListMessage.Row;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.OpenAiServiceWrapper;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemediesService {

    private final OpenAiServiceWrapper openAiServiceWrapper;
    private final UserService userService;

    // ----- BACK BUTTON HANDLER -----
    public ListMessage handleBack(User user) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        user.setCurrentIntent("nutrition_step4");
        userService.updateUser(user);

        return ListMessage.builder()
                .header(lang.equals("hi") ? "आपको किस मदद की आवश्यकता है?" : "What would you like help with today?")
                .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
                .buttonText(lang.equals("hi") ? "विकल्प चुनें" : "Choose option")
                .sections(List.of(
                        ListMessage.Section.builder()
                                .title(lang.equals("hi") ? "सहायता विकल्प" : "Help Options")
                                .rows(List.of(
                                        Row.builder().id("meal_plan").title(lang.equals("hi") ? "भोजन योजना" : "Daily Meal").build(),
                                        Row.builder().id("remedies").title(lang.equals("hi") ? "उपचार" : "Remedies").build(),
                                        Row.builder().id("immunity").title(lang.equals("hi") ? "प्रतिरक्षा" : "Immunity Tips").build(),
                                        Row.builder().id("supplements").title(lang.equals("hi") ? "सप्लीमेंट्स" : "Supplements").build(),
                                        Row.builder().id("back").title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back").build(),
                                        Row.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                                ))
                                .build()
                ))
                .build();
    }

    // ----- GET REMEDIES -----
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

        // Return as LIST MESSAGE with back option
        return ListMessage.builder()
                .header(lang.equals("hi") ? "💊 घरेलू उपचार" : "💊 Home Remedies")
                .body(remedies)
                .buttonText(lang.equals("hi") ? "विकल्प चुनें" : "Choose option")
                .sections(List.of(
                        ListMessage.Section.builder()
                                .title(lang.equals("hi") ? "क्रियाएँ" : "Actions")
                                .rows(List.of(
                                        Row.builder().id("back").title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back").build(),
                                        Row.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                                ))
                                .build()
                ))
                .build();
    }
}
