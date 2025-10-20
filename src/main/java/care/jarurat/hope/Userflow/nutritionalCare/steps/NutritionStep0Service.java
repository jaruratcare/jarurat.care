package care.jarurat.hope.Userflow.nutritionalCare.steps;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
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

        // If no input yet, ask user if they are diabetic
        if (input == null || input.isEmpty()) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "🩸 क्या आप डायबिटिक हैं?" : "🩸 Are you diabetic?")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("yes_diabetic")
                                    .title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes")
                                    .build(),
                            InteractiveMessage.Button.builder()
                                    .id("no_diabetic")
                                    .title(lang.equals("hi") ? "❌ नहीं" : "❌ No")
                                    .build()
                    ))
                    .build();
        }

        // Validate input
        boolean valid = switch (input.toLowerCase()) {
            case "yes_diabetic", "✅ हाँ", "no_diabetic", "❌ नहीं" -> true;
            default -> false;
        };

        if (!valid) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "❌ कृपया हाँ या नहीं चुनें:" : "❌ Please choose Yes or No:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("yes_diabetic")
                                    .title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes")
                                    .build(),
                            InteractiveMessage.Button.builder()
                                    .id("no_diabetic")
                                    .title(lang.equals("hi") ? "❌ नहीं" : "❌ No")
                                    .build()
                    ))
                    .build();
        }

        // Save response
        if (input.equalsIgnoreCase("yes_diabetic") || input.equalsIgnoreCase("✅ हाँ")) {
            user.setDiabeticStatus("Diabetic");
        } else {
            user.setDiabeticStatus("not diabetic");
        }

        user.setCurrentIntent("nutrition_step2");
        userService.updateUser(user);

        // Return next step as InteractiveMessage
        return InteractiveMessage.builder()
                .body(lang.equals("hi") ? "🍽️ कृपया अपनी भोजन की पसंद चुनें:" : "🍽️ Please select your food preference:")
                .buttons(List.of(
                        InteractiveMessage.Button.builder()
                                .id("vegetarian")
                                .title(lang.equals("hi") ? "🥗 शाकाहारी" : "🥗 Vegetarian")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("non_vegetarian")
                                .title(lang.equals("hi") ? "🍗 मांसाहारी" : "🍗 Non-Vegetarian")
                                .build()
                ))
                .build();
    }
}
