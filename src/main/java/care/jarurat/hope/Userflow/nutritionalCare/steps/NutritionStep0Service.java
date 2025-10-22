package care.jarurat.hope.Userflow.nutritionalCare.steps;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionStep0Service {

    private final UserService userService;

    public Object handle(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";

        // 🧭 Handle Back Button
        if ("back".equalsIgnoreCase(input)) {
            user.setCurrentIntent("nutrition_step1");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "कृपया अपनी पसंद चुनें:" : "Please choose your preference:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("vegetarian")
                                    .title(lang.equals("hi") ? "🥗 शाकाहारी" : "🥗 Vegetarian").build(),
                            InteractiveMessage.Button.builder()
                                    .id("non_vegetarian")
                                    .title(lang.equals("hi") ? "🍗 मांसाहारी" : "🍗 Non-Vegetarian").build(),
                            InteractiveMessage.Button.builder()
                                    .id("main_menu")
                                    .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                    ))
                    .build();
        }

        // 🏠 Handle Main Menu
        if ("main_menu".equalsIgnoreCase(input)) {
            user.setCurrentIntent(null);
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi")
                            ? "🏠 मुख्य मेनू पर वापस आ गए।"
                            : "🏠 You are back to the main menu.")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("main_menu")
                                    .title(lang.equals("hi") ? "🔙 मुख्य मेनू" : "🔙 Main Menu").build()
                    ))
                    .build();
        }

        boolean valid = switch (input.toLowerCase()) {
            case "yes_diabetic", "no_diabetic", "✅ हाँ", "❌ नहीं" -> true;
            default -> false;
        };

        if (!valid) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "❌ कृपया हाँ या नहीं चुनें:" : "❌ Please choose Yes or No:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("yes_diabetic")
                                    .title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes").build(),
                            InteractiveMessage.Button.builder()
                                    .id("no_diabetic")
                                    .title(lang.equals("hi") ? "❌ नहीं" : "❌ No").build(),
                            InteractiveMessage.Button.builder()
                                    .id("back")
                                    .title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back").build(),
                            InteractiveMessage.Button.builder()
                                    .id("main_menu")
                                    .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                    ))
                    .build();
        }

        // ✅ Store answer and move to next step
        if (input.equalsIgnoreCase("yes_diabetic") || input.equalsIgnoreCase("✅ हाँ")) {
            user.setDiabeticStatus("Diabetic");
        } else {
            user.setDiabeticStatus("Not Diabetic");
        }

        user.setLastIntent(user.getCurrentIntent());
        user.setCurrentIntent("nutrition_step2"); // Next: Eating Condition
        userService.updateUser(user);

        return InteractiveMessage.builder()
                .body(lang.equals("hi") ? "🥗 खाने की स्थिति क्या है?" : "🥗 What is the eating condition?")
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("soft")
                                .title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                        InteractiveMessage.Button.builder().id("liquid")
                                .title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                        InteractiveMessage.Button.builder().id("normal")
                                .title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build(),
                        // 👇 Navigation
                        InteractiveMessage.Button.builder()
                                .id("back")
                                .title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back").build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                ))
                .build();
    }
}
