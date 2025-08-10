package care.jarurat.hope.Userflow;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class NutritionCareService {

    private final UserService userService;

    public NutritionCareService(UserService userService) {
        this.userService = userService;
    }

    public String handle(User user, String input) {
        String response = switch (input) {
            case "1" -> {
                user.setCurrentIntent("nutrition_diet");
                yield "🥗 Here's some guidance on a balanced diet:\nEat more greens, fruits, whole grains, and stay hydrated!";
            }
            case "2" -> {
                user.setCurrentIntent("nutrition_tips");
                yield "💡 Nutrition Tip: Avoid processed food and eat fresh homemade meals.";
            }
            default -> "❓ Please select:\n1) Balanced Diet\n2) Nutrition Tips";
        };

        // update user 
        userService.updateUser(user);

        return response;
    }
}
