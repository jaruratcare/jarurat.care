package care.jarurat.hope.Userflow.PalliativeCareHandler;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PalliativeCareHandler {

    private final PalliativeCareService careService;
    private final UserService userService;
    private final PalliativeCareMessageBuilder messageBuilder;

    public Object handle(User user, String input) {
        boolean isHindi = "hi".equalsIgnoreCase(user.getLanguage()) || "hindi".equalsIgnoreCase(user.getLanguage());

        // Handle Back button globally
        if ("back".equalsIgnoreCase(input)) {
            return handleBack(user, isHindi);
        }

        return switch (user.getCurrentIntent()) {
            case "palliative_care" -> askCity(user, isHindi);
            case "awaiting_palliative_city" -> saveCity(user, input, isHindi);
            case "awaiting_palliative_type" -> handleHospitalType(user, input, isHindi);
            case "awaiting_palliative_care_type" -> handleCareType(user, input, isHindi);
            default -> null;
        };
    }

    // Step 1: Ask for city
    private Object askCity(User user, boolean isHindi) {
        user.setCurrentIntent("awaiting_palliative_city");
        userService.updateUser(user);
        return messageBuilder.askCity(isHindi);
    }

    // Step 2: Save city and ask hospital type
    private Object saveCity(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidCity(isHindi);
        }

        user.setCity(input.trim());
        user.setCurrentIntent("awaiting_palliative_type");
        userService.updateUser(user);

        return messageBuilder.askHospitalType(isHindi);
    }

    // Step 3: Handle hospital type and ask care type
    private Object handleHospitalType(User user, String input, boolean isHindi) {
        if (user.getCity() == null || user.getCity().isEmpty()) {
            user.setCurrentIntent("palliative_care");
            userService.updateUser(user);
            return messageBuilder.cityNotFound(isHindi);
        }

        String hospitalType;
        switch (input.trim()) {
            case "1": hospitalType = "government"; break;
            case "2": hospitalType = "private"; break;
            default:
                return messageBuilder.invalidChoice(isHindi);
        }

        user.setCurrentIntent("awaiting_palliative_care_type"); // Next step: care type
        userService.updateUser(user);

        return messageBuilder.askCareType(isHindi); // Ask Home vs Facility
    }

    // Step 4: Handle care type and show facility/services list
    private Object handleCareType(User user, String input, boolean isHindi) {
        if (user.getCity() == null || user.getCity().isEmpty()) {
            user.setCurrentIntent("palliative_care");
            userService.updateUser(user);
            return messageBuilder.cityNotFound(isHindi);
        }

        String careType;
        switch (input.trim().toLowerCase()) {
            case "home": careType = "home"; break;
            case "facility": careType = "facility"; break;
            default:
                return messageBuilder.invalidChoice(isHindi);
        }

        // Fetch facilities from service using city + careType
        List<PalliativeCareFacility> facilities = careService.getFacilitiesByCityAndService(
                user.getCity(),
                careType
        );

        if (facilities.isEmpty()) {
            return messageBuilder.comingSoonText(user.getCity(), isHindi);
        }

        return messageBuilder.facilityService(facilities, isHindi);
    }

    // Back button logic
    private Object handleBack(User user, boolean isHindi) {
        String currentIntent = user.getCurrentIntent();

        switch (currentIntent) {
            case "awaiting_palliative_city":
                // From city input, go back to palliative start
                user.setCurrentIntent("palliative_care");
                userService.updateUser(user);
                return messageBuilder.askCity(isHindi);

            case "awaiting_palliative_type":
                // From hospital type selection, go back to city input
                user.setCurrentIntent("awaiting_palliative_city");
                userService.updateUser(user);
                return messageBuilder.askCity(isHindi);

            case "awaiting_palliative_care_type":
                // From care type selection, go back to hospital type
                user.setCurrentIntent("awaiting_palliative_type");
                userService.updateUser(user);
                return messageBuilder.askHospitalType(isHindi);

            default:
                // For any other case, show main menu
                return messageBuilder.mainMenu(isHindi);
        }
    }
}
