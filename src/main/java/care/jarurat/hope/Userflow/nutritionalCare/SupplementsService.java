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
public class SupplementsService {

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

    // ----- GET SUPPLEMENTS -----
    public Object getSupplements(User user) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        String symptom = user.getNutritionSymptoms() != null && !user.getNutritionSymptoms().isEmpty()
                ? user.getNutritionSymptoms()
                : "general";
        String dietType = user.getDietType() != null && !user.getDietType().isEmpty() ? user.getDietType() : "general";
        String preference = user.getFoodPreference() != null && !user.getFoodPreference().isEmpty() ? user.getFoodPreference() : "general";
        String Diabetic = user.getDiabeticStatus() != null && !user.getFoodPreference().isEmpty() ? user.getDiabeticStatus() : "not specified";

        String systemPrompt = "You are a healthcare assistant specializing in safe, affordable supplements for cancer patients. Always keep responses concise and practical.";

        String userPrompt = lang.equals("hi")
                ? String.format(
                "कैंसर रोगियों के लिए %s के आधार पर सुरक्षित और किफायती सप्लीमेंट्स सुझाएं। " +
                        "आहार प्रकार: %s, खाने की पसंद: %s, डायबिटीज़: %s। मांसाहारी आइटम शामिल न करें यदि शाकाहारी है। " +
                        "अधिकतम 800 वर्ण।",
                symptom, dietType, preference, Diabetic
        )
                : String.format(
                "Suggest safe and affordable supplements for cancer patients based on %s. " +
                        "User diet type: %s, food preference: %s, diabetic: %s. " +
                        "Do NOT include any non-vegetarian items if the preference is vegetarian. " +
                        "Max 800 characters.",
                symptom, dietType, preference, Diabetic
        );

        String supplements = openAiServiceWrapper.generateResponse(systemPrompt, userPrompt, 0.7, 300);

        if (supplements.length() > 1024) {
            log.warn("Supplements response too long ({} chars), truncating...", supplements.length());
            supplements = supplements.substring(0, 1020) + "...";
        }

        log.info("Generated supplements length: {} characters", supplements.length());

        user.setCurrentIntent("nutrition_step4");
        userService.updateUser(user);

        // Return as LIST MESSAGE with back option
        return ListMessage.builder()
                .header(lang.equals("hi") ? "📦 किफायती सप्लीमेंट्स" : "📦 Affordable Supplements")
                .body(supplements)
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
