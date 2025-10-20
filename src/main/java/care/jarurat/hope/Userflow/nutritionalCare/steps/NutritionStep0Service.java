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

    public InteractiveMessage handle(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";

        // If no input yet, ask user if they are diabetic
        if (input == null || input.isEmpty()) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi")
                            ? "🩸 क्या आप डायबिटिक हैं?"
                            : "🩸 Are you diabetic?")
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

        // Save response and move to next step
        if (input.equalsIgnoreCase("yes_diabetic") || input.equalsIgnoreCase("✅ हाँ")) {
            user.setDiabeticStatus("Diabetic");
        } else if (input.equalsIgnoreCase("no_diabetic") || input.equalsIgnoreCase("❌ नहीं")) {
            user.setDiabeticStatus("not diabetic");
        } else {
            // Invalid input — ask again
            return InteractiveMessage.builder()
                    .body(lang.equals("hi")
                            ? "❌ कृपया हाँ या नहीं चुनें:"
                            : "❌ Please choose Yes or No:")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("yes_diabetic").title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes").build(),
                            InteractiveMessage.Button.builder().id("no_diabetic").title(lang.equals("hi") ? "❌ नहीं" : "❌ No").build()
                    ))
                    .build();
        }

        // Update user and go to next intent
        user.setCurrentIntent("nutrition_step1");
        userService.updateUser(user);

        return InteractiveMessage.builder()
                .body(lang.equals("hi")
                        ? "🍽️ कृपया अपनी भोजन की पसंद चुनें:"
                        : "🍽️ Please select your food preference:")
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("vegetarian").title(lang.equals("hi") ? "🥗 शाकाहारी" : "🥗 Vegetarian").build(),
                        InteractiveMessage.Button.builder().id("non_vegetarian").title(lang.equals("hi") ? "🍗 मांसाहारी" : "🍗 Non-Vegetarian").build()
                ))
                .build();
    }
}
