package care.jarurat.hope.Userflow.nutritionalCare.steps;

import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionStep3Service {

    private final UserService userService;

    public ListMessage createSymptomsListMessage(String lang) {
        return ListMessage.builder()
                .header(lang.equals("hi") ? "क्या आपको कोई लक्षण हैं?" : "Do you have any symptoms?")
                .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
                .buttonText(lang.equals("hi") ? "लक्षण चुनें" : "Choose symptom")
                .sections(List.of(
                        ListMessage.Section.builder()
                                .title(lang.equals("hi") ? "लक्षण" : "Symptoms")
                                .rows(List.of(
                                        ListMessage.Row.builder().id("gut_health").title(lang.equals("hi") ? "पाचन समस्या" : "Gut issues").description(lang.equals("hi") ? "पाचन तंत्र की समस्या" : "Gut health issues").build(),
                                        ListMessage.Row.builder().id("nausea").title(lang.equals("hi") ? "मतली / उल्टी" : "Nausea / Vomit").description(lang.equals("hi") ? "मतली या उल्टी" : "Nausea / vomiting").build(),
                                        ListMessage.Row.builder().id("fatigue").title(lang.equals("hi") ? "थकान / कमजोरी" : "Fatigue / Weak").description(lang.equals("hi") ? "थकान या कमजोरी" : "Fatigue / weakness").build(),
                                        ListMessage.Row.builder().id("loss_appetite").title(lang.equals("hi") ? "भूख न लगना" : "Loss of Appetite").description(lang.equals("hi") ? "भूख न लगना / वजन घटना" : "Loss of appetite / weight loss").build(),
                                        ListMessage.Row.builder().id("low_immunity").title(lang.equals("hi") ? "कमज़ोर प्रतिरक्षा" : "Low Immunity").description(lang.equals("hi") ? "कमज़ोर प्रतिरक्षा" : "Low immunity").build()
                                ))
                                .build()
                ))
                .build();
    }

    public Object handle(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";

        // 🟡 Back button handling
        if (input.equalsIgnoreCase("back") || input.equals("🔙")) {
            user.setCurrentIntent("nutrition_step2");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "कृपया अपना आहार प्रकार चुनें:" : "Please select your diet type:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("soft").title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                            InteractiveMessage.Button.builder().id("liquid").title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                            InteractiveMessage.Button.builder().id("normal").title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build(),
                            InteractiveMessage.Button.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                    ))
                    .build();
        }

        // 🏠 Main Menu handling
        if (input.equalsIgnoreCase("main_menu") || input.equals("🏠")) {
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "मुख्य मेनू में आपका स्वागत है!" : "Welcome to the main menu!")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("nutrition").title(lang.equals("hi") ? "🍎 पोषण देखभाल" : "🍎 Nutritional Care").build(),
                            InteractiveMessage.Button.builder().id("palliative").title(lang.equals("hi") ? "💊 उपशामक देखभाल" : "💊 Palliative Care").build()
                    ))
                    .build();
        }

        // 🧩 Ensure correct intent flow
        if (user.getDietType() == null || !"nutrition_step3".equals(user.getCurrentIntent())) {
            return "❌ " + (lang.equals("hi") ? "कृपया पहले अपना आहार प्रकार चुनें" : "Please select your diet type first");
        }

        List<String> validSymptoms = List.of("gut_health", "nausea", "fatigue", "loss_appetite", "low_immunity");
        if (!validSymptoms.contains(input.toLowerCase())) {
            // Add Back + Main Menu buttons to the invalid input message
            ListMessage message = createSymptomsListMessage(lang);
            message.getSections().get(0).getRows().addAll(List.of(
                    ListMessage.Row.builder().id("back").title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back").build(),
                    ListMessage.Row.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
            ));
            return message;
        }

        // ✅ Valid symptom → go to Step 4
        user.setNutritionSymptoms(input.toLowerCase());
        user.setLastIntent(user.getCurrentIntent());
        user.setCurrentIntent("nutrition_step4");
        userService.updateUser(user);

        return createNextOptionsMessage(lang);
    }

    private ListMessage createNextOptionsMessage(String lang) {
        return ListMessage.builder()
                .header(lang.equals("hi") ? "💡 आज आप किस चीज़ में मदद चाहते हैं?" : "💡 What would you like help with today?")
                .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
                .buttonText(lang.equals("hi") ? "विकल्प चुनें" : "Choose option")
                .sections(List.of(
                        ListMessage.Section.builder()
                                .title(lang.equals("hi") ? "विकल्प" : "Options")
                                .rows(List.of(
                                        ListMessage.Row.builder().id("meal_plan").title(lang.equals("hi") ? "दैनिक भोजन योजना" : "Daily Meal Plan").description(lang.equals("hi") ? "व्यक्तिगत दैनिक भोजन योजना प्राप्त करें" : "Get a tailored daily meal plan").build(),
                                        ListMessage.Row.builder().id("remedies").title(lang.equals("hi") ? "उपचार" : "Remedies").description(lang.equals("hi") ? "लक्षणों के उपचार" : "Remedies for symptoms").build(),
                                        ListMessage.Row.builder().id("immunity").title(lang.equals("hi") ? "प्रतिरक्षा सुझाव" : "Immunity Tips").description(lang.equals("hi") ? "प्रतिरक्षा बढ़ाने के सुझाव" : "Immunity building tips").build(),
                                        ListMessage.Row.builder().id("supplements").title(lang.equals("hi") ? "सप्लीमेंट्स" : "Supplements").description(lang.equals("hi") ? "किफायती सप्लीमेंट्स" : "Affordable supplements").build(),
                                        ListMessage.Row.builder().id("back").title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back").build(),
                                        ListMessage.Row.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                                ))
                                .build()
                ))
                .build();
    }
}
