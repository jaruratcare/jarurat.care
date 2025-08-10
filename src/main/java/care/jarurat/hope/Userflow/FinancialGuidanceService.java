package care.jarurat.hope.Userflow;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class FinancialGuidanceService {

    private final UserService userService;

    public FinancialGuidanceService(UserService userService) {
        this.userService = userService;
    }

    public String handle(User user, String input) {
        String response = switch (input) {
            case "1" -> {
                user.setCurrentIntent("budget_planning");
                yield "📊 Let's plan your budget:\n- Track expenses\n- Set limits\n- Save regularly.";
            }
            case "2" -> {
                user.setCurrentIntent("saving_tips");
                yield "💰 Saving Tip: Set aside 20% of your income every month!";
            }
            default -> "❓ Please select:\n1) Budget Planning\n2) Saving Tips";
        };

       //update user
        userService.updateUser(user);

        return response;
    }
}
