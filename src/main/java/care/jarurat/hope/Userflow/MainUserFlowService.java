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

        System.out.println("DEBUG: CurrentIntent before processing = " + user.getCurrentIntent());

        // Start conversation
        if ("hi".equalsIgnoreCase(input)) {
            user.setCurrentIntent("language_selection");
            saveOrUpdate(user);
            return "👋 Welcome to Hope, Your Caregiving Companion 💜!\n"
                    + "Please choose your language:\n"
                    + "1) English 🇺🇸\n"
                    + "2) हिंदी 🇮🇳";
        }

        // If no state, force language selection
        if (user.getCurrentIntent() == null || user.getCurrentIntent().isBlank()) {
            user.setCurrentIntent("language_selection");
            saveOrUpdate(user);
            return "Please choose your language:\n1) English 🇺🇸\n2) हिंदी 🇮🇳";
        }

        // Nutrition module routing
        if (user.getCurrentIntent().startsWith("nutrition_")) {
            String reply = nutritionCareService.handleNutrition(user, input);
            userService.updateUser(user);
            return reply;
        }

        // Main flow switch
        return switch (user.getCurrentIntent()) {
            case "language_selection" -> handleLanguageSelection(user, input);
            case "main_menu" -> handleMainMenu(user, input);
            default -> {
                user.setCurrentIntent("language_selection");
                saveOrUpdate(user);
                yield "🤖 I didn’t understand that. Please type 'Hi' to start again.";
            }
        };
    }

    private String handleLanguageSelection(User user, String input) {
        return switch (input) {
            case "1" -> {
                user.setLanguage("english");
                user.setCurrentIntent("main_menu");
                saveOrUpdate(user);
                yield getMainMenuEnglish();
            }
            case "2" -> {
                user.setLanguage("hindi");
                user.setCurrentIntent("main_menu");
                saveOrUpdate(user);
                yield getMainMenuHindi();
            }
            default -> "❌ Invalid choice, Please type 1 for English or 2 for हिंदी.";
        };
    }

    private String handleMainMenu(User user, String input) {
        return switch (input) {
            case "2" -> {
                user.setCurrentIntent("nutrition_step1");
                saveOrUpdate(user);
                yield "🍽️ Are you vegetarian or non-vegetarian?";
            }
            default -> "❌ Invalid choice. Please select an option from the menu.";
        };
    }

    private String getMainMenuEnglish() {
        return "How can I help you today?\n" +
                "1) Financial Guidance 💰\n" +
                "2) Nutritional Care 🥗\n" +
                "3) Emotional Support 💜\n" +
                "4) Nearby Hospitals 🏥\n" +
                "5) Free/Low-Cost Stay & Food 🛏️🍛\n" +
                "6) Diagnostic Labs 🧪\n" +
                "7) Palliative & Hospice Care 🕊️\n" +
                "8) Talk to a Volunteer 📞\n" +
                "9) Change Language 🌐";
    }

    private String getMainMenuHindi() {
        return "आप किस प्रकार की सहायता चाहते हैं?\n" +
                "1) वित्तीय मार्गदर्शन 💰\n" +
                "2) पोषण संबंधी देखभाल 🥗\n" +
                "3) भावनात्मक समर्थन 💜\n" +
                "4) नजदीकी अस्पताल 🏥\n" +
                "5) मुफ्त/कम लागत ठहराव और भोजन 🛏️🍛\n" +
                "6) डायग्नोस्टिक लैब 🧪\n" +
                "7) उपशामक और होस्पिस देखभाल 🕊️\n" +
                "8) स्वयंसेवक से बात करें 📞\n" +
                "9) भाषा बदलें 🌐";
    }

    private void saveOrUpdate(User user) {
        if (user.getLanguage() == null) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }
    }
}