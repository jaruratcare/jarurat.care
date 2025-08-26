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

        if ("vegetarian".equalsIgnoreCase(input) || "non_vegetarian".equalsIgnoreCase(input)
                || "शाकाहारी".equalsIgnoreCase(input) || "मांसाहारी".equalsIgnoreCase(input)) {

            user.setFoodPreference(input);
            user.setCurrentIntent("nutrition_step2");
            userService.updateUser(user);

            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "🥗 खाने की स्थिति क्या है?" : "🥗 What is the eating condition?")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("soft").title(lang.equals("hi") ? "1) नरम" : "1) Soft").build(),
                            InteractiveMessage.Button.builder().id("liquid").title(lang.equals("hi") ? "2) तरल आहार" : "2) Liquid Diet").build(),
                            InteractiveMessage.Button.builder().id("normal").title(lang.equals("hi") ? "3) सामान्य आहार" : "3) Normal Diet").build()
                    ))
                    .build();
        }

        return InteractiveMessage.builder()
        .body(lang.equals("hi")
                ? "कृपया अपनी पसंद चुनें:"
                : "Please choose your preference:")
        .footer(lang.equals("hi")
                ? "शाकाहारी या मांसाहारी चुनें"
                : "Select Vegetarian or Non-Vegetarian")
        .buttons(Arrays.asList(
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
