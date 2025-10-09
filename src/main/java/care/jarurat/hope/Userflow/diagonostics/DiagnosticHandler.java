package care.jarurat.hope.Userflow.diagonostics;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiagnosticHandler {

    private final DiagnosticService diagnosticService;
    private final DiagnosticMessageBuilder messageBuilder;
    private final UserService userService;

    public Object handle(User user, String input) {
        boolean isEnglish = "en".equalsIgnoreCase(user.getLanguage());

        // Start flow if user selects "6" from menu directly
        if ("6".equals(input)) {
            user.setCurrentIntent("diagnostic_lab_start");
            userService.updateUser(user);
        }

        // Normalize intent to lowercase
        String intent = user.getCurrentIntent() != null ? user.getCurrentIntent().toLowerCase() : "";

        return switch (intent) {
            case "diagnostic_lab_start" -> startFlow(user, isEnglish);
            case "diagnostic_awaiting_location_confirmation" -> handleLocationConfirmation(user, input, isEnglish);
            case "diagnostic_awaiting_location" -> handleNewCity(user, input, isEnglish);
            default -> messageBuilder.defaultFallback(isEnglish);
        };
    }

    private Object startFlow(User user, boolean isEnglish) {
        if (user.getCity() != null && !user.getCity().isEmpty()) {
            user.setCurrentIntent("diagnostic_awaiting_location_confirmation");
            userService.updateUser(user);
            return messageBuilder.askLocationConfirmation(user.getCity(), user.getState(), isEnglish);
        } else {
            user.setCurrentIntent("diagnostic_awaiting_location");
            userService.updateUser(user);
            return messageBuilder.askCity(isEnglish);
        }
    }

    private Object handleLocationConfirmation(User user, String input, boolean isEnglish) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidChoice(isEnglish);
        }

        String normalized = input.trim().toLowerCase();
        if ("confirm_city".equals(normalized)) {
            return diagnosticService.findNearbyLabs(user, user.getCity(), isEnglish);
        } else if ("different_city".equals(normalized)) {
            user.setCurrentIntent("diagnostic_awaiting_location");
            userService.updateUser(user);
            return messageBuilder.askDifferentCity(isEnglish);
        } else {
            return messageBuilder.invalidChoice(isEnglish);
        }
    }

    private Object handleNewCity(User user, String input, boolean isEnglish) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidCity(isEnglish);
        }

        user.setCity(input.trim());
        user.setCurrentIntent(null); // flow ends, reset intent
        userService.updateUser(user);
        return diagnosticService.findNearbyLabs(user, input.trim(), isEnglish);
    }
}
