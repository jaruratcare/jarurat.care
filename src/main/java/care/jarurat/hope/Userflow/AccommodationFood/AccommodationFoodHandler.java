package care.jarurat.hope.Userflow.AccommodationFood;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationFoodHandler {

    private final AccommodationFoodService afService;
    private final UserService userService;
    private final AccommodationFoodMessageBuilder messageBuilder;

    public Object handle(User user, String input) {
        boolean isHindi = "hi".equalsIgnoreCase(user.getLanguage()) || "hindi".equalsIgnoreCase(user.getLanguage());

        // --- Handle Navigation buttons first ---
        if ("back".equalsIgnoreCase(input)) {
            return handleBack(user, isHindi);
        } else if ("main_menu".equalsIgnoreCase(input)) {
            user.setCurrentIntent(null); // reset flow
            userService.updateUser(user);
            return messageBuilder.mainMenu(isHindi);
        }

        // --- Handle normal flow ---
        return switch (user.getCurrentIntent()) {
            case "accommodation_food" -> askHospital(user, isHindi);
            case "awaiting_af_hospital" -> handleHospitalInput(user, input, isHindi);
            case "awaiting_af_help_type" -> handleHelpType(user, input, isHindi);
            case "awaiting_af_income" -> handleIncome(user, input, isHindi);
            case "awaiting_af_service_selection" -> handleServiceSelection(user, input, isHindi);
            default -> null;
        };
    }

    // --- Back button logic ---
    private Object handleBack(User user, boolean isHindi) {
        String intent = user.getCurrentIntent();

        switch (intent) {
            case "awaiting_af_hospital":
                user.setCurrentIntent("accommodation_food"); // go back to start
                userService.updateUser(user);
                return messageBuilder.mainMenu(isHindi);

            case "awaiting_af_help_type":
                user.setCurrentIntent("awaiting_af_hospital"); // go back to hospital input
                userService.updateUser(user);
                return messageBuilder.askHospital(isHindi);

            case "awaiting_af_income":
                user.setCurrentIntent("awaiting_af_help_type"); // go back to help type
                userService.updateUser(user);
                return messageBuilder.askHelpType(isHindi);

            case "awaiting_af_service_selection":
                user.setCurrentIntent("awaiting_af_income"); // go back to income
                userService.updateUser(user);
                return messageBuilder.askIncomeRange(isHindi);

            default:
                user.setCurrentIntent(null); // fallback to main menu
                userService.updateUser(user);
                return messageBuilder.mainMenu(isHindi);
        }
    }

    private Object askHospital(User user, boolean isHindi) {
        user.setCurrentIntent("awaiting_af_hospital");
        userService.updateUser(user);
        return messageBuilder.askHospital(isHindi);
    }

    private Object handleHospitalInput(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidHospital(isHindi);
        }

        String hospitalName = input.trim();

        boolean exists = afService.hospitalExists(hospitalName);
        if (!exists) {
            return messageBuilder.hospitalNotFound(isHindi, hospitalName);
        }

        user.setCurrentIntent("awaiting_af_help_type");
        userService.updateUser(user);
        return messageBuilder.askHelpType(isHindi);
    }

    private Object handleHelpType(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidChoice(isHindi);
        }

        String trimmedInput = input.trim().toLowerCase();
        String helpTypeId;
        if (trimmedInput.contains("stay") && trimmedInput.contains("food")) {
            helpTypeId = "stay_food";
        } else if (trimmedInput.contains("stay")) {
            helpTypeId = "only_stay";
        } else if (trimmedInput.contains("food")) {
            helpTypeId = "only_food";
        } else {
            return messageBuilder.invalidChoice(isHindi);
        }

        user.setHelpType(helpTypeId);
        user.setCurrentIntent("awaiting_af_income");
        userService.updateUser(user);

        return messageBuilder.askIncomeRange(isHindi);
    }

    private Object handleIncome(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidChoice(isHindi);
        }

        user.setIncomeRange(input.trim());
        user.setCurrentIntent("awaiting_af_service_selection");
        userService.updateUser(user);

        return messageBuilder.askServiceOptionsForHelpType(isHindi, user.getHelpType());
    }

    private Object handleServiceSelection(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidChoice(isHindi);
        }

        List<AccommodationFoodFacility> facilities = afService.getFacilitiesByOption(input.trim().toLowerCase());
        if (facilities.isEmpty()) {
            return messageBuilder.comingSoonText(isHindi);
        }

        user.setCurrentIntent("awaiting_af_service_selection"); // keep the intent to allow back
        userService.updateUser(user);

        return messageBuilder.facilityServiceWithNavigation(facilities, isHindi, input.trim().toLowerCase());
    }
}
