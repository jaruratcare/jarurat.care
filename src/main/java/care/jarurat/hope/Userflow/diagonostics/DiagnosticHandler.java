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
        if ("6".equals(input)) {
            user.setCurrentIntent("diagnostic_lab_start");
            userService.updateUser(user);
        }
        String intent = user.getCurrentIntent() != null ? user.getCurrentIntent().toLowerCase() : "";

        return switch (intent) {
            case "diagnostic_lab_start" -> startFlow(user, isEnglish);
            case "diagnostic_awaiting_location_confirmation" -> handleLocationConfirmation(user, input, isEnglish);
            case "diagnostic_awaiting_location" -> handleNewCity(user, input, isEnglish);
            case "diagnostic_awaiting_test_type" -> handleTestType(user, input, isEnglish);
            case "diagnostic_awaiting_collection_type" -> handleCollectionType(user, input, isEnglish);
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
            user.setCurrentIntent("diagnostic_awaiting_test_type");
            userService.updateUser(user);
            return isEnglish 
                ? "Great! What type of test are you looking for (e.g., 'blood test', 'MRI', 'CT scan')?"
                : "बहुत खूब! आप किस प्रकार के टेस्ट की तलाश में हैं (उदाहरण: 'ब्लड टेस्ट', 'एमआरआई', 'सीटी स्कैन')?";
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
        user.setCurrentIntent("diagnostic_awaiting_test_type");
        userService.updateUser(user);

        return isEnglish 
            ? "Thanks! Now, what type of test are you looking for (e.g., 'blood test', 'MRI', 'CT scan')?"
            : "धन्यवाद! अब, आप किस प्रकार के टेस्ट की तलाश में हैं (उदाहरण: 'ब्लड टेस्ट', 'एमआरआई', 'सीटी स्कैन')?";
    }

    private Object handleTestType(User user, String input, boolean isEnglish) {
        if (input == null || input.trim().isEmpty()) {
            return messageBuilder.invalidChoice(isEnglish);
        }

        user.setTestType(input.trim());
        user.setCurrentIntent("diagnostic_awaiting_collection_type");
        userService.updateUser(user);

        String body = isEnglish 
            ? "Got it. Do you need a lab with in-centre testing or one with home collection?"
            : "समझ गया। क्या आपको इन-सेंटर टेस्टिंग वाली लैब चाहिए या होम कलेक्शन वाली?";

        String button1Title = isEnglish ? "In-Centre" : "इन-सेंटर";
        String button2Title = isEnglish ? "Home Collection" : "होम कलेक्शन";

        return care.jarurat.hope.model.InteractiveMessage.builder()
                .body(body)
                .buttons(java.util.Arrays.asList(
                        care.jarurat.hope.model.InteractiveMessage.Button.builder().id("in_centre").title(button1Title).build(),
                        care.jarurat.hope.model.InteractiveMessage.Button.builder().id("home_collection").title(button2Title).build()
                ))
                .build();
    }

    private Object handleCollectionType(User user, String input, boolean isEnglish) {
        if ("in_centre".equalsIgnoreCase(input) || "home_collection".equalsIgnoreCase(input)) {
            user.setCollectionType(input.toLowerCase());
            userService.updateUser(user);
            return diagnosticService.findNearbyLabs(user, user.getCity(), isEnglish);
        } else {
            String body = isEnglish ? "Please select one of the options." : "कृपया विकल्पों में से एक चुनें।";
            String button1Title = isEnglish ? "In-Centre" : "इन-सेंटर";
            String button2Title = isEnglish ? "Home Collection" : "होम कलेक्शन";

            return care.jarurat.hope.model.InteractiveMessage.builder()
                    .body(body)
                    .buttons(java.util.Arrays.asList(
                            care.jarurat.hope.model.InteractiveMessage.Button.builder().id("in_centre").title(button1Title).build(),
                            care.jarurat.hope.model.InteractiveMessage.Button.builder().id("home_collection").title(button2Title).build()
                    ))
                    .build();
        }
    }
}
