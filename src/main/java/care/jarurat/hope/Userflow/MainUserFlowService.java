package care.jarurat.hope.Userflow;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainUserFlowService {

    private final FinancialGuidanceService financialGuidanceService;
    private final NutritionCareService nutritionCareService;
    private final UserService userService;

    public String getResponse(User user, String input) {
        input = input.trim().toLowerCase();

        // Debug current intent
        System.out.println("DEBUG: Current intent before processing = " + user.getCurrentIntent());

        // Start conversation
        if ("hi".equalsIgnoreCase(input)) {
            user.setCurrentIntent("choose_language");

            // Save or update user depending on existence
            if (user.getLanguage() == null) {
                userService.saveUser(user);
            } else {
                userService.updateUser(user);
            }

            return "👋 Welcome to Hope, Your Caregiving Companion 💜!\n"
                + "Please choose your language:\n"
                + "1) English 🇺🇸\n"
                + "2) हिंदी 🇮🇳";
        }

        // If intent null or blank, force language selection
        if (user.getCurrentIntent() == null || user.getCurrentIntent().isBlank()) {
            user.setCurrentIntent("choose_language");
            userService.updateUser(user);
            return "Please choose your language:\n1) English 🇺🇸\n2) हिंदी 🇮🇳";
        }

        switch (user.getCurrentIntent().toLowerCase()) {
            case "choose_language":
                switch (input) {
                    case "1":
                        user.setLanguage("english");
                        user.setCurrentIntent("main_menu");
                        userService.updateUser(user);
                        return "You selected English. How can I help you today?\n"
                            + "1) Financial Guidance 💰\n"
                            + "2) Nutrition Care 🥗\n"
                            + "3) Emotional Support 💜";
                    case "2":
                        user.setLanguage("hindi");
                        user.setCurrentIntent("main_menu");
                        userService.updateUser(user);
                        return "आपने हिंदी चुना। आप किस प्रकार की सहायता चाहते हैं?\n"
                            + "1) वित्तीय मार्गदर्शन 💰\n"
                            + "2) पोषण संबंधी देखभाल 🥗\n"
                            + "3) भावनात्मक समर्थन 💜";
                    default:
                        return "❌ Invalid language choice. Type 'Hi' to start again.";
                }

            case "main_menu":
                switch (input) {
                    case "1":
                        user.setCurrentIntent("financial_guidance");
                        userService.updateUser(user);
                        return "📊 Financial Guidance:\n1) Budget Planning\n2) Saving Tips";
                    case "2":
                        user.setCurrentIntent("nutrition_care");
                        userService.updateUser(user);
                        return "🥗 Nutrition Care:\n1) Balanced Diet\n2) Nutrition Tips";
                    case "3":
                        user.setCurrentIntent("emotional_support");
                        userService.updateUser(user);
                        return "🧠 Emotional support module is coming soon. Type 'Hi' to restart.";
                    default:
                        return "❌ Invalid choice. Type 'Hi' to start again.";
                }

            case "financial_guidance":
                String replyFG = financialGuidanceService.handle(user, input);
                userService.updateUser(user);
                return replyFG;

            case "nutrition_care":
                String replyNC = nutritionCareService.handle(user, input);
                userService.updateUser(user);
                return replyNC;

            case "emotional_support":
                return "🧠 Emotional support module is coming soon. Type 'Hi' to restart.";

            default:
                user.setCurrentIntent("choose_language");
                userService.updateUser(user);
                return "Unknown state. Type 'Hi' to start again.";
        }
    }
}
