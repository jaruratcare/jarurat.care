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
public class NutritionStep2Service {

    private final UserService userService;

    public Object handle(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";

        boolean valid = switch (input.toLowerCase()) {
            case "soft", "liquid", "normal", "नरम", "तरल", "तरल आहार", "सामान्य", "सामान्य आहार" -> true;
            default -> false;
        };

        // Handle Back / Main Menu
        if (input.equalsIgnoreCase("back") || input.equals("🔙")) {
            user.setCurrentIntent("nutrition_step1");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "कृपया अपना भोजन प्रकार चुनें:" : "Please choose your food type:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("soft").title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                            InteractiveMessage.Button.builder().id("liquid").title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                            InteractiveMessage.Button.builder().id("normal").title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build()
                    ))
                    .build();
        }

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

        if (!valid) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "❌ अमान्य विकल्प। कृपया पुनः चुनें:" : "❌ Invalid choice. Please select again:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("soft").title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                            InteractiveMessage.Button.builder().id("liquid").title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                            InteractiveMessage.Button.builder().id("normal").title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build(),
                            InteractiveMessage.Button.builder().id("back").title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back").build(),
                            InteractiveMessage.Button.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                    ))
                    .build();
        }
        user.setEatingCondition(input);
        user.setDietType(input);
        user.setLastIntent(user.getCurrentIntent());
        user.setCurrentIntent("nutrition_step3");
        userService.updateUser(user);

        return ListMessage.builder()
                .header(lang.equals("hi") ? "क्या आपको कोई लक्षण हैं?" : "Do you have any symptoms?")
                .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
                .buttonText(lang.equals("hi") ? "लक्षण चुनें" : "Choose symptom")
                .sections(List.of(
                        ListMessage.Section.builder()
                                .title(lang.equals("hi") ? "लक्षण" : "Symptoms")
                               .rows(List.of(
                                         ListMessage.Row.builder().id("constipation").title(lang.equals("hi") ? "कब्ज़" : "Constipation").description(lang.equals("hi") ? "पाचन समस्या - कब्ज़" : "Digestive issue - constipation").build(),
                                         ListMessage.Row.builder().id("diarrhea").title(lang.equals("hi") ? "दस्त" : "Diarrhea").description(lang.equals("hi") ? "पाचन समस्या - दस्त" : "Digestive issue - diarrhea").build(),
                                         ListMessage.Row.builder().id("nausea").title(lang.equals("hi") ? "मतली / उल्टी" : "Nausea / Vomit").description(lang.equals("hi") ? "मतली या उल्टी" : "Nausea / vomiting").build(),
                                         ListMessage.Row.builder().id("fatigue").title(lang.equals("hi") ? "थकान / कमजोरी" : "Fatigue / Weak").description(lang.equals("hi") ? "थकान या कमजोरी" : "Fatigue / weakness").build(),
                                         ListMessage.Row.builder().id("loss_appetite").title(lang.equals("hi") ? "भूख न लगना" : "Loss of Appetite").description(lang.equals("hi") ? "भूख न लगना / वजन घटना" : "Loss of appetite / weight loss").build(),
                                         ListMessage.Row.builder().id("low_immunity").title(lang.equals("hi") ? "कमज़ोर प्रतिरक्षा" : "Low Immunity").description(lang.equals("hi") ? "कमज़ोर प्रतिरक्षा" : "Low immunity").build()
                                ))

                                .build()
                 ))
                .build();
    }
}
