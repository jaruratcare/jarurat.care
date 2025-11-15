package care.jarurat.hope.service;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final UserService userService;
    private final MainMenuService mainMenuService; // inject here

    public Object handleOnboardingStep(User user, String input) {
        switch (user.getCurrentIntent().toLowerCase()) {
            case "onboarding_handle_name":
                return handleOnboardingName(user, input);

            case "onboarding_handle_location":
                return handleOnboardingLocation(user, input);

            case "onboarding_handle_role":
                return handleOnboardingRole(user, input);

            case "onboarding_handle_medical":
                return handleOnboardingMedical(user, input);

            default:
                log.warn("Unknown onboarding intent: {} for user: {}", user.getCurrentIntent(), user.getUserId());
                user.setCurrentIntent("main_menu");
                userService.updateUser(user);
                return "Something went wrong during onboarding. Please type 'Hi' to restart.";
        }
    }

    /**
     * Step 1: Handle user's name
     */
    private String handleOnboardingName(User user, String input) {
        boolean isEnglish = "en".equals(user.getLanguage());

        // Save user name
        user.setName(input.trim());
        user.setCurrentIntent("onboarding_handle_location");
        userService.updateUser(user);

        return askLocation(isEnglish, user.getName());
    }

    /**
     * Step 2: Ask for City/State
     */
    private String askLocation(boolean isEnglish, String name) {
        if (isEnglish) {
            return "Thank you, " + name + ".\n\nTo provide the best local support, please tell me your city and state (e.g., Mumbai, Maharashtra ).";
        } else {
            return "धन्यवाद, " + name + "।\n\nसबसे अच्छी स्थानीय सहायता प्रदान करने के लिए, कृपया मुझे अपना शहर और राज्य बताएं (उदाहरण: मुंबई, महाराष्ट्र)।";
        }
    }

    private Object handleOnboardingLocation(User user, String location) {
        String[] parts = location.split(",");
        if (parts.length > 0) user.setCity(parts[0].trim());
        if (parts.length > 1) user.setState(parts[1].trim());

        user.setCurrentIntent("onboarding_handle_role");
        userService.updateUser(user);

        boolean isEnglish = "en".equals(user.getLanguage());

        String body = isEnglish
                ? "Thank you. Are you seeking help for yourself or for someone else?"
                : "धन्यवाद। क्या आप अपने लिए या किसी और के लिए मदद चाहते हैं?";

        // ✅ Shortened button titles to fit WhatsApp API rules (max 20 chars)
        String button1Title = isEnglish ? "Patient" : "मरीज";
        String button2Title = isEnglish ? "Caregiver" : "देखभालकर्ता";

        return InteractiveMessage.builder()
                .body(body)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("patient").title(button1Title).build(),
                        InteractiveMessage.Button.builder().id("caregiver").title(button2Title).build()
                ))
                .build();
    }

    /**
     * Step 3: Handle role (patient/caregiver)
     */
    private String handleOnboardingRole(User user, String role) {
        user.setRole(role);
        user.setCurrentIntent("onboarding_handle_medical");
        userService.updateUser(user);

        boolean isEnglish = "en".equals(user.getLanguage());
        if (isEnglish) {
            return "Thank you. If you are comfortable sharing, could you please provide the cancer type and stage? (e.g., \"Breast cancer, stage 2\").\n\nYou can also type \"Skip\".";
        } else {
            return "धन्यवाद। यदि आप साझा करने में सहज हैं, तो क्या आप कृपया कैंसर का प्रकार और चरण प्रदान कर सकते हैं? (उदाहरण के लिए, \"स्तन कैंसर, चरण 2\")।\n\nआप \"Skip\" भी टाइप कर सकते हैं।";
        }
    }

    /**
     * Step 4: Handle medical info (cancer type/stage)
     */
    private Object handleOnboardingMedical(User user, String medicalInfo) {
        if (!"skip".equalsIgnoreCase(medicalInfo.trim())) {
            String[] parts = medicalInfo.split(",");
            if (parts.length > 0) user.setCancerType(parts[0].trim());
            if (parts.length > 1) user.setCancerStage(parts[1].trim());
        }

        user.setCurrentIntent("main_menu");
        userService.updateUser(user);

        boolean isEnglish = "en".equals(user.getLanguage());
        return mainMenuService.getMainMenuMessage(isEnglish);
    }
}
