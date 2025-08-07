package care.jarurat.hope.Userflow;
import care.jarurat.hope.model.User;
import org.springframework.stereotype.Service;

@Service
public class MainUserFlowService {

    public String getResponse(User user, String input) {
        input = input.trim().toLowerCase();

        return switch (input) {
            case "hi" -> {
                user.setCurrentIntent("greet");
                yield "👋 Welcome to Hope, Your Caregiving Companion 💜!\nPlease choose your language:\n1) English 🇺🇸\n2) हिंदी 🇮🇳";
            }
            case "1" -> {
                user.setLanguage("english");
                user.setCurrentIntent("language_selected");
                yield "You selected English. How can I help you today?\n1) Financial Guidance 💰\n2) Nutrition Care 🥗\n3) Emotional Support 💜";
            }
            case "2" -> {
                user.setLanguage("hindi");
                user.setCurrentIntent("language_selected");
                yield "आपने हिंदी चुना। आप किस प्रकार की सहायता चाहते हैं?\n1) वित्तीय मार्गदर्शन 💰\n2) पोषण संबंधी देखभाल 🥗\n3) भावनात्मक समर्थन 💜";
            }
            case "3" -> {
                user.setCurrentIntent("emotional_support");
                yield "🧠 Emotional support module is coming soon. Type 'Hi' to restart.";
            }
            default -> {
                user.setCurrentIntent("unknown");
                yield "🤖 I didn’t understand that. Please type 'Hi' to start again.";
            }
        };
    }
}