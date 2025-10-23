package care.jarurat.hope.Userflow.nutritionalCare.steps;

import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.ListMessage.Row;
import care.jarurat.hope.model.ListMessage.Section;
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

        // 🔙 Back → Step0
        if ("back".equalsIgnoreCase(input) || input.equals("🔙")) {
            user.setCurrentIntent("nutrition_step0");
            userService.updateUser(user);
            return ListMessage.builder()
                    .header(lang.equals("hi") ? "क्या आप डायबिटिक हैं?" : "Are you diabetic?")
                    .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
                    .buttonText(lang.equals("hi") ? "चुनें" : "Choose")
                    .sections(List.of(
                            Section.builder()
                                    .title(lang.equals("hi") ? "विकल्प" : "Options")
                                    .rows(List.of(
                                            Row.builder().id("yes_diabetic")
                                                    .title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes").build(),
                                            Row.builder().id("no_diabetic")
                                                    .title(lang.equals("hi") ? "❌ नहीं" : "❌ No").build(),
                                            Row.builder().id("back")
                                                    .title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back").build(),
                                            Row.builder().id("main_menu")
                                                    .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                                    ))
                                    .build()
                    ))
                    .build();
        }

        // 🏠 Main Menu
        if ("main_menu".equalsIgnoreCase(input) || input.equals("🏠")) {
            user.setCurrentIntent(null);
            userService.updateUser(user);
            return ListMessage.builder()
                    .header(lang.equals("hi") ? "🏠 मुख्य मेनू में आपका स्वागत है!" : "Welcome to the main menu!")
                    .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
                    .buttonText(lang.equals("hi") ? "विकल्प चुनें" : "Choose option")
                    .sections(List.of(
                            Section.builder()
                                    .title(lang.equals("hi") ? "मुख्य मेनू" : "Main Menu")
                                    .rows(List.of(
                                            Row.builder().id("nutrition")
                                                    .title(lang.equals("hi") ? "🍎 पोषण देखभाल" : "🍎 Nutritional Care").build(),
                                            Row.builder().id("palliative")
                                                    .title(lang.equals("hi") ? "💊 उपशामक देखभाल" : "💊 Palliative Care").build()
                                    ))
                                    .build()
                    ))
                    .build();
        }

        // ❌ Invalid selection
        if (!valid) {
            return ListMessage.builder()
                    .header(lang.equals("hi") ? "❌ अमान्य विकल्प" : "❌ Invalid Choice")
                    .body(lang.equals("hi") ? "कृपया पुनः चुनें:" : "Please select again:")
                    .buttonText(lang.equals("hi") ? "स्थिति चुनें" : "Choose condition")
                    .sections(List.of(
                            Section.builder()
                                    .title(lang.equals("hi") ? "खाने की स्थिति" : "Eating Condition")
                                    .rows(List.of(
                                            Row.builder().id("soft").title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                                            Row.builder().id("liquid").title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                                            Row.builder().id("normal").title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build(),
                                            Row.builder().id("back").title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back").build(),
                                            Row.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                                    ))
                                    .build()
                    ))
                    .build();
        }

        // ✅ Save selection & go to Step3
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
                        Section.builder()
                                .title(lang.equals("hi") ? "लक्षण" : "Symptoms")
                                .rows(List.of(
                                        Row.builder().id("constipation").title(lang.equals("hi") ? "कब्ज़" : "Constipation").build(),
                                        Row.builder().id("diarrhea").title(lang.equals("hi") ? "दस्त" : "Diarrhea").build(),
                                        Row.builder().id("nausea").title(lang.equals("hi") ? "मतली / उल्टी" : "Nausea / Vomit").build(),
                                        Row.builder().id("fatigue").title(lang.equals("hi") ? "थकान / कमजोरी" : "Fatigue / Weak").build(),
                                        Row.builder().id("loss_appetite").title(lang.equals("hi") ? "भूख न लगना" : "Loss of Appetite").build(),
                                        Row.builder().id("low_immunity").title(lang.equals("hi") ? "कमज़ोर प्रतिरक्षा" : "Low Immunity").build(),
                                        Row.builder().id("back").title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back").build(),
                                        Row.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                                ))
                                .build()
                ))
                .build();
    }
}
