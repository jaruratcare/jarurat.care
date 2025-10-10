package care.jarurat.hope.Userflow.financialguidance;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.model.financials.NgoAndTrust;
import care.jarurat.hope.repository.financials.NgoAndTrustRepository;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NgosAndTrustsService {

    private final UserService userService;
    private final NgoAndTrustRepository ngoAndTrustRepository;

    public Object handleNgosAndTrusts(User user, String input) {
        String intent = user.getCurrentIntent() != null ? user.getCurrentIntent() : "fg_ngos_and_trusts_start";

        switch (intent) {
            case "fg_ngos_and_trusts_start":
                if (user.getCity() != null && !user.getCity().isEmpty()) {
                    return sendNgoList(user, user.getCity());
                } else {
                    return askForCity(user);
                }

            case "fg_ngos_and_trusts_city":
                return sendNgoList(user, input);

            default:
                return askForCity(user);
        }
    }

    private Object askForCity(User user) {
        user.setCurrentIntent("fg_ngos_and_trusts_city");
        userService.updateUser(user);
        boolean isEnglish = "en".equals(user.getLanguage());
        return isEnglish ? "To find relevant NGOs and Trusts, please tell me your city." : "प्रासंगिक गैर सरकारी संगठनों और ट्रस्टों को खोजने के लिए, कृपया मुझे अपना शहर बताएं।";
    }

    private Object sendNgoList(User user, String city) {
        List<NgoAndTrust> ngos = ngoAndTrustRepository.findByCity(city);

        boolean isEnglish = "en".equals(user.getLanguage());

        user.setCurrentIntent("financial_guidance_menu");
        userService.updateUser(user);

        if (ngos == null || ngos.isEmpty()) {
            String body = String.format(isEnglish ? "Sorry, I couldn't find any NGOs or Trusts in %s." : "क्षमा करें, मुझे %s में कोई गैर सरकारी संगठन या ट्रस्ट नहीं मिला।", city);
            return InteractiveMessage.builder()
                    .body(body)
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }

        String header = String.format(isEnglish ? "Here are some NGOs & Trusts in %s:\n\n" : "%s में कुछ गैर सरकारी संगठन और ट्रस्ट यहां दिए गए हैं:\n\n", city);
        StringBuilder message = new StringBuilder(header);
        for (NgoAndTrust ngo : ngos) {
            message.append("*Name:* ").append(ngo.getName()).append("\n");
            if (ngo.getServices() != null) message.append("*Services:* ").append(ngo.getServices()).append("\n");
            if (ngo.getAddress() != null) message.append("*Address:* ").append(ngo.getAddress()).append("\n");
            if (ngo.getPhone() != null) message.append("*Phone:* ").append(ngo.getPhone()).append("\n");
            if (ngo.getWebsite() != null) message.append("*Website:* ").append(ngo.getWebsite()).append("\n");
            message.append("\n---\n\n");
        }

        String finalMessage = message.toString();
        if (finalMessage.length() > 1024) { // WhatsApp body character limit
            finalMessage = finalMessage.substring(0, 1020) + "...";
        }

        return InteractiveMessage.builder()
                .body(finalMessage)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                ))
                .build();
    }
}