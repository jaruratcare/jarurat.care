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

    // Validate input
    boolean valid = switch (input.toLowerCase()) {
        case "yes_diabetic", "✅ हाँ", "no_diabetic", "❌ नहीं" -> true;
        default -> false;
    };

    if (!valid) {
        return InteractiveMessage.builder()
                .body(lang.equals("hi") ? "❌ कृपया हाँ या नहीं चुनें:" : "❌ Please choose Yes or No:")
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("yes_diabetic")
                                .title(lang.equals("hi") ? "✅ हाँ" : "✅ Yes").build(),
                        InteractiveMessage.Button.builder().id("no_diabetic")
                                .title(lang.equals("hi") ? "❌ नहीं" : "❌ No").build()
                ))
                .build();
    }

    // Save diabetic status
    if (input.equalsIgnoreCase("yes_diabetic") || input.equalsIgnoreCase("✅ हाँ")) {
        user.setDiabeticStatus("Diabetic");
    } else {
        user.setDiabeticStatus("not diabetic");
    }

    user.setCurrentIntent("nutrition_step2"); // Next step: Eating Condition
    userService.updateUser(user);

    return InteractiveMessage.builder()
            .body(lang.equals("hi") ? "🥗 खाने की स्थिति क्या है?" : "🥗 What is the eating condition?")
            .buttons(List.of(
                    InteractiveMessage.Button.builder().id("soft")
                            .title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                    InteractiveMessage.Button.builder().id("liquid")
                            .title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                    InteractiveMessage.Button.builder().id("normal")
                            .title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build()
            ))
            .build();
   }
}