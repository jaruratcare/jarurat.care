package care.jarurat.hope.service;

import care.jarurat.hope.model.InteractiveMessage; // NEW IMPORT
import care.jarurat.hope.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays; // NEW IMPORT

@Slf4j
@Service
@RequiredArgsConstructor
public class GlobalCommandService {

    private final UserService userService;

    public Object handleGlobalCommand(User user, String input) {
        if (input.equalsIgnoreCase("DELETE")) {
            userService.deleteUser(user.getUserId());
            boolean isEnglish = "en".equals(user.getLanguage());
            if (isEnglish) {
                return "Your data has been permanently deleted. Thank you for using Hope. You can start again by typing \"Hi\".";
            } else {
                return "आपका डेटा स्थायी रूप से हटा दिया गया है। होप का उपयोग करने के लिए धन्यवाद। आप \"Hi\" टाइप करके फिर से शुरू कर सकते हैं।";
            }
        }

        // Handle greeting - always reset to language selection
        if (input.equalsIgnoreCase("hi") || input.equalsIgnoreCase("hello") ||
                input.equalsIgnoreCase("start") || input.equalsIgnoreCase("restart")) {
            user.setCurrentIntent("choose_language");
            userService.saveUser(user); // Save user state after reset

            // This is the welcome message and language selection
            String header = "Welcome to Jarurat Care! / जरूरत केयर में आपका स्वागत है!";
            String body = "Welcome to Hope, your caregiving companion 🌸 / होप में आपका स्वागत है, आपका देखभाल साथी 🌸";
            String footer = "Choose your language / भाषा चुनें:";

            return InteractiveMessage.builder()
                    .header(header)
                    .body(body)
                    .footer(footer)
                    .buttons(Arrays.asList(
                            InteractiveMessage.Button.builder().id("english").title("🇬🇧 English").build(),
                            InteractiveMessage.Button.builder().id("hindi").title("🇮🇳 Hindi").build()
                    ))
                    .build();
        }

        return null; // Indicates that this service did not handle the command
    }
}