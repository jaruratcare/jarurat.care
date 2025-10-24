package care.jarurat.hope.Userflow.Volunteer;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VolunteerHandler {

private final VolunteerMessageBuilder messageBuilder;
private final VolunteerService volunteerService;
private final UserService userService;

public Object handle(User user, String input) {
    String intent = user.getCurrentIntent();
    boolean isEnglish = "en".equalsIgnoreCase(user.getLanguage());
    if ("back".equalsIgnoreCase(input) || "🔙".equals(input)) {
        user.setCurrentIntent("volunteer_start");
        userService.updateUser(user);
        return messageBuilder.askCommunicationMode(isEnglish);
    }

    if ("main_menu".equalsIgnoreCase(input) || "🏠".equals(input)) {
        user.setCurrentIntent("main_menu");
        userService.updateUser(user);
        return isEnglish
                ? "🏠 Returning to Main Menu..."
                : "🏠 मुख्य मेनू पर लौट रहे हैं...";
    }
    return switch (intent) {
        case "volunteer_start" -> {
            user.setCurrentIntent("volunteer_choose_mode");
            userService.updateUser(user);
            yield messageBuilder.askCommunicationMode(isEnglish);
        }

        case "volunteer_choose_mode" -> {
            if ("volunteer_chat".equals(input) || "volunteer_call".equals(input)) {
                String mode = input.equals("volunteer_chat") ? "Chat" : "Phone Call";
                user.setTempMode(mode);
                user.setCurrentIntent("volunteer_ask_datetime");
                userService.updateUser(user);
                yield messageBuilder.askDateTimeWithListMessage(isEnglish);
            } else {
                yield messageBuilder.defaultFallback(isEnglish);
            }
        }

        case "volunteer_ask_datetime" -> {
    String mode = user.getTempMode();
    // Pass language as well
    InteractiveMessage response = volunteerService.bookAppointment(
            user.getName(),
            user.getPhone(),
            mode,
            input,
            user.getLanguage()
    );
    user.setCurrentIntent("main_menu");
    user.setTempMode(null);
    userService.updateUser(user);
    yield response;
}

        default -> messageBuilder.defaultFallback(isEnglish);
    };
}


}
