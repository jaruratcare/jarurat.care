package care.jarurat.hope.service;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class LanguageService {

    private final UserService userService;

    public Object handleLanguageSelection(User user, String input) {
        String normalizedInput = input.toLowerCase();

        boolean isEnglish;

        // Handle button responses
        if ("english".equals(normalizedInput) || "1".equals(input)) {
            user.setLanguage("en");
            isEnglish = true;
        }
        else if ("hindi".equals(normalizedInput) || "2".equals(input)) {
            user.setLanguage("hi");
            isEnglish = false;
        }
        else {
            // Invalid selection, re-prompt
            return InteractiveMessage.builder()
                    .header("Please choose your language")
                    .body("Please select your preferred language:\nकृपया अपनी पसंदीदा भाषा चुनें:")
                    .buttons(Arrays.asList(
                            InteractiveMessage.Button.builder().id("english").title("🇬🇧 English").build(),
                            InteractiveMessage.Button.builder().id("hindi").title("🇮🇳 Hindi").build()
                    ))
                    .build();
        }

        user.setCurrentIntent("onboarding_handle_name");
        userService.updateUser(user);
        return getConsentMessage(isEnglish);
    }

    private InteractiveMessage getConsentMessage(boolean isEnglish) {
        String body;
        String buttonTitle;

        if (isEnglish) {
            body = "*Consent & Data collection*\n" +
                    "Your data is only used for personalized support. Type DELETE anytime to erase all information.\n\n" +
                    "*Privacy commitments*\n" +
                    "• All data is encrypted and kept secure.\n" +
                    "• You can type DELETE anytime to erase your data permanently.\n" +
                    "• By continuing, you agree to share your information for caregiving assistance.";
            buttonTitle = "Continue";
        } else {
            body = "*सहमति और डेटा संग्रह*\n" +
                    "आपके डेटा का उपयोग केवल व्यक्तिगत सहायता के लिए किया जाता है। किसी भी समय सभी जानकारी मिटाने के लिए DELETE टाइप करें।\n\n" +
                    "*गोपनीयता प्रतिबद्धताएं*\n" +
                    "• सभी डेटा एन्क्रिप्टेड और सुरक्षित रखा जाता है।\n" +
                    "• आप अपना डेटा स्थायी रूप से मिटाने के लिए किसी भी समय DELETE टाइप कर सकते हैं।\n" +
                    "• जारी रखकर, आप देखभाल सहायता के लिए अपनी जानकारी साझा करने के लिए सहमत हैं।";
            buttonTitle = "आगे बढ़ें";
        }

        return InteractiveMessage.builder()
                .body(body)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder()
                                .id("onboarding_start")  // ✅ FIX: use onboarding_start, not onboarding_handle_name
                                .title(buttonTitle)
                                .build()
                ))
                .build();

    }
}