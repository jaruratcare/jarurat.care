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

        return switch (user.getCurrentIntent()) {
            case "accommodation_food" -> askCity(user, isHindi);
            case "awaiting_af_city" -> saveCity(user, input, isHindi);
            case "awaiting_af_type" -> handleHelpType(user, input, isHindi);
            case "awaiting_af_income" -> handleIncome(user, input, isHindi);
            default -> null;
        };
    }

    private Object askCity(User user, boolean isHindi) {
        user.setCurrentIntent("awaiting_af_city");
        userService.updateUser(user);
        return messageBuilder.askCity(isHindi);
    }

    private Object saveCity(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidCity(isHindi);
        }
        user.setCity(input.trim());
        user.setCurrentIntent("awaiting_af_type");
        userService.updateUser(user);
        return messageBuilder.askHelpType(isHindi);
    }

    private Object handleHelpType(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidChoice(isHindi);
        }
        // Help type input passed to service later
        user.setCurrentIntent("awaiting_af_income");
        userService.updateUser(user);
        return messageBuilder.askIncomeRange(isHindi);
    }

    private Object handleIncome(User user, String input, boolean isHindi) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidChoice(isHindi);
        }

        // Fetch facilities using service, input contains help type / income criteria
        List<AccommodationFoodFacility> facilities = afService.getFacilitiesByCityAndType(
                user.getCity(), input.trim()
        );

        if (facilities.isEmpty()) {
            return messageBuilder.comingSoonText(user.getCity(), isHindi);
        }

        return messageBuilder.facilityServiceListText(facilities, isHindi);
    }
}
