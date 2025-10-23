package care.jarurat.hope.Userflow.nutritionalCare.steps;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

// ================== Nutrition Step 0 ==================
@Service
@RequiredArgsConstructor
public class NutritionStep0Service {

    private final UserService userService;

    public Object handle(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";

        // 🔙 Back to Step1
        if ("back".equalsIgnoreCase(input)) {
            user.setCurrentIntent("nutrition_step1");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "कृपया अपनी पसंद चुनें:" : "Please choose your preference:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("vegetarian")
                                    .title(lang.equals("hi") ? "🥗 शाकाहारी" : "🥗 Vegetarian").build(),
                            InteractiveMessage.Button.builder().id("non_vegetarian")
                                    .title(lang.equals("hi") ? "🍗 मांसाहारी" : "🍗 Non-Vegetarian").build(),
                            InteractiveMessage.Button.builder().id("main_menu")
                                    .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                    )).build();
        }

        // 🏠 Main Menu
        if ("main_menu".equalsIgnoreCase(input)) {
            user.setCurrentIntent(null);
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "🏠 मुख्य मेनू पर वापस आ गए।" : "🏠 You are back to the main menu.")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("main_menu")
                                    .title(lang.equals("hi") ? "🔙 मुख्य मेनू" : "🔙 Main Menu").build()
                    )).build();
        }

        boolean valid = switch (input.toLowerCase()) {
            case "yes_diabetic", "no_diabetic", "✅ हाँ", "❌ नहीं" -> true;
            default -> false;
        };

       if (!valid) {
    return ListMessage.builder()
            .header(lang.equals("hi") ? "❌ कृपया हाँ या नहीं चुनें:" : "❌ Please choose Yes or No:")
            .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
            .buttonText(lang.equals("hi") ? "चुनें" : "Choose")
            .sections(List.of(
                    ListMessage.Section.builder()
                            .title(lang.equals("hi") ? "विकल्प" : "Options")
                            .rows(List.of(
                                    ListMessage.Row.builder().id("yes_diabetic")
                                            .title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes").build(),
                                    ListMessage.Row.builder().id("no_diabetic")
                                            .title(lang.equals("hi") ? "❌ नहीं" : "❌ No").build(),
                                    ListMessage.Row.builder().id("back")
                                            .title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back").build(),
                                    ListMessage.Row.builder().id("main_menu")
                                            .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                            ))
                            .build()
            ))
            .build();
}

        // ✅ Store answer and move to Step2
        user.setDiabeticStatus(input.equalsIgnoreCase("yes_diabetic") || input.equalsIgnoreCase("✅ हाँ") ? "Diabetic" : "Not Diabetic");
        user.setLastIntent(user.getCurrentIntent());
        user.setCurrentIntent("nutrition_step2");
        userService.updateUser(user);

        return ListMessage.builder()
                .header(lang.equals("hi") ? "🥗 खाने की स्थिति क्या है?" : "🥗 What is the eating condition?")
                .body(lang.equals("hi") ? "एक चुनें:" : "Select one:")
                .buttonText(lang.equals("hi") ? "स्थिति चुनें" : "Choose condition")
                .sections(List.of(
                        ListMessage.Section.builder()
                                .title(lang.equals("hi") ? "खाने की स्थिति" : "Eating Condition")
                                .rows(List.of(
                                        ListMessage.Row.builder().id("soft").title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                                        ListMessage.Row.builder().id("liquid").title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                                        ListMessage.Row.builder().id("normal").title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build(),
                                        ListMessage.Row.builder().id("back").title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back").build(),
                                        ListMessage.Row.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                                )).build()
                )).build();
    }
}