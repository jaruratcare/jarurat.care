package care.jarurat.hope.Userflow.Volunteer;

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

        return switch (intent) {
            case "volunteer_start" -> {
                user.setCurrentIntent("volunteer_choose_mode");
                userService.updateUser(user);
                yield messageBuilder.askCommunicationMode(isEnglish);
            }

            case "volunteer_choose_mode" -> {
                if ("volunteer_chat".equals(input) || "volunteer_call".equals(input)) {
                    String mode = input.equals("volunteer_chat") ? "Chat" : "Phone Call";
                    user.setTempMode(mode); // store selected mode
                    user.setCurrentIntent("volunteer_ask_datetime");
                    userService.updateUser(user);

                    // Return the ListMessage with proper sections
                    ListMessage listMessage = messageBuilder.askDateTimeWithListMessage(isEnglish);
                    yield listMessage;
                } else {
                    yield messageBuilder.defaultFallback(isEnglish);
                }
            }

            case "volunteer_ask_datetime" -> {
                String mode = user.getTempMode();
                String response = volunteerService.bookAppointment(user.getName(), user.getPhone(), mode, input);
                user.setCurrentIntent("main_menu");
                user.setTempMode(null); // clear tempMode
                userService.updateUser(user);
                yield response;
            }

            default -> messageBuilder.defaultFallback(isEnglish);
        };
    }
}
