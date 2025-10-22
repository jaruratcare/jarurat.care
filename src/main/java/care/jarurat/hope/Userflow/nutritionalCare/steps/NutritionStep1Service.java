package care.jarurat.hope.Userflow.nutritionalCare.steps;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionStep1Service {

    private final UserService userService;

    public InteractiveMessage handle(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";

        // 🧭 Handle Back Button (go back to main menu or restart)
        if ("back".equalsIgnoreCase(input)) {
            // Step1 is the first step — so 'back' means go to main menu
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

        // ✅ Handle valid food preference
        if ("vegetarian".equalsIgnoreCase(input) || "non_vegetarian".equalsIgnoreCase(input)
                || "शाकाहारी".equalsIgnoreCase(input) || "मांसाहारी".equalsIgnoreCase(input)) {

            user.setFoodPreference(input);
            user.setLastIntent(user.getCurrentIntent());
            user.setCurrentIntent("nutrition_step0"); // Go to next step
            userService.updateUser(user);

            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "❓ क्या आप मधुमेह के रोगी हैं?" : "❓ Are you diabetic?")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("yes_diabetic")
                                    .title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes").build(),
                            InteractiveMessage.Button.builder()
                                    .id("no_diabetic")
                                    .title(lang.equals("hi") ? "❌ नहीं" : "❌ No").build(),
                            // 👇 Added buttons
                            InteractiveMessage.Button.builder()
                                    .id("back")
                                    .title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back").build(),
                            InteractiveMessage.Button.builder()
                                    .id("main_menu")
                                    .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                    ))
                    .build();
        }

        // 🧩 Default response (initial question)
        return InteractiveMessage.builder()
                .body(lang.equals("hi") ? "कृपया अपनी पसंद चुनें:" : "Please choose your preference:")
                .footer(lang.equals("hi") ? "शाकाहारी या मांसाहारी चुनें" : "Select Vegetarian or Non-Vegetarian")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder()
                                .id("vegetarian")
                                .title(lang.equals("hi") ? "🥗 शाकाहारी" : "🥗 Vegetarian")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("non_vegetarian")
                                .title(lang.equals("hi") ? "🍗 मांसाहारी" : "🍗 Non-Vegetarian")
                                .build(),
                        // 👇 Added navigation buttons
                        InteractiveMessage.Button.builder()
                                .id("back")
                                .title(lang.equals("hi") ? "🔙 वापस" : "🔙 Back")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                .build()
                ))
                .build();
    }
}
