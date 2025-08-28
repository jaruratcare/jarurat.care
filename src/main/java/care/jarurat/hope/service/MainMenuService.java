package care.jarurat.hope.service;

import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainMenuService {

    private final UserService userService;

    public Object handleMainMenu(User user, String input) {
        if (input.equalsIgnoreCase("main_menu")) {
            return getMainMenuMessage("en".equals(user.getLanguage()));
        }

        switch (input.trim()) {
            case "1": // Financial Guidance
                user.setCurrentIntent("financial_guidance_start");
                userService.updateUser(user);
                return null;

           case "2": // Nutrition Care
            user.setCurrentIntent("nutrition_care_start");
            userService.updateUser(user);
            return null;
            
            case "4": // Nearby Hospitals
            user.setCurrentIntent("nearby_hospitals");
            userService.updateUser(user);
            return null;

            // ... other cases
            default:
                boolean isEnglish = "en".equals(user.getLanguage());
                if (isEnglish) {
                    return "❌ Invalid choice. Please select an option from the menu.";
                } else {
                    return "❌ गलत विकल्प। कृपया मेनू से एक विकल्प चुनें।";
                }
        }
    }

    public ListMessage getMainMenuMessage(boolean isEnglish) {
        String body;
        String buttonText;
        String header;

        if (isEnglish) {
            body = "How can I help you today?";
            buttonText = "View Options";
            header = "Main Menu";
        } else {
            body = "आज मैं आपकी कैसे मदद कर सकता हूँ?";
            buttonText = "विकल्प देखें";
            header = "मुख्य मेनू";
        }

        return ListMessage.builder()
                .header(header)
                .body(body)
                .buttonText(buttonText)
                .sections(Collections.singletonList(
                        ListMessage.Section.builder()
                                .title("Our Services")
                                .rows(Arrays.asList(
                                        ListMessage.Row.builder().id("1").title("Financial Guidance").build(),
                                        ListMessage.Row.builder().id("2").title("Nutrition Care").build(),
                                        ListMessage.Row.builder().id("3").title("Emotional Support").build(),
                                        ListMessage.Row.builder().id("4").title("Nearby Hospitals").build(),
                                        ListMessage.Row.builder().id("5").title("Free Stay & Food").build(),
                                        ListMessage.Row.builder().id("6").title("Diagnostic Labs").build(),
                                        ListMessage.Row.builder().id("7").title("Palliative Care").build(),
                                        ListMessage.Row.builder().id("8").title("Talk to a Volunteer").build(),
                                        ListMessage.Row.builder().id("9").title("Change Language").build()
                                ))
                                .build()
                ))
                .build();
    }
}