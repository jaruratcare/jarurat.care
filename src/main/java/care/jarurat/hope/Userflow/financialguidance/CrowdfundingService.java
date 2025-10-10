package care.jarurat.hope.Userflow.financialguidance;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CrowdfundingService {

    private final UserService userService;

    public Object handleCrowdfunding(User user, String input) {
        user.setCurrentIntent("financial_guidance_menu");
        userService.updateUser(user);

        boolean isEnglish = "en".equals(user.getLanguage());

        String messageBody = isEnglish
                ? "Crowdfunding can be a powerful way to raise funds for treatment. Here are some of the most popular platforms in India:\n\n"
                + "*1. Milaap:* milaap.org\n"
                + "*2. Ketto:* ketto.org\n"
                + "*3. ImpactGuru:* impactguru.com\n\n"
                + "*Tips for a Successful Campaign:*\n"
                + "- *Tell a clear story:* Explain the situation honestly and clearly.\n"
                + "- *Use photos/videos:* Visuals help people connect with your story.\n"
                + "- *Share widely:* Use social media like WhatsApp, Facebook, and Instagram to share the campaign link with friends and family."
                
                : "उपचार के लिए धन जुटाने के लिए क्राउडफंडिंग एक शक्तिशाली तरीका हो सकता है। भारत में कुछ सबसे लोकप्रिय प्लेटफॉर्म यहां दिए गए हैं:\n\n"
                + "*1. मिलाप:* milaap.org\n"
                + "*2. केटो:* ketto.org\n"
                + "*3. इम्पैक्टगुरु:* impactguru.com\n\n"
                + "*एक सफल अभियान के लिए सुझाव:*\n"
                + "- *एक स्पष्ट कहानी बताएं:* स्थिति को ईमानदारी और स्पष्ट रूप से समझाएं।\n"
                + "- *तस्वीरों/वीडियो का उपयोग करें:* दृश्य लोगों को आपकी कहानी से जुड़ने में मदद करते हैं।\n"
                + "- *व्यापक रूप से साझा करें:* मित्रों और परिवार के साथ अभियान लिंक साझा करने के लिए व्हाट्सएप, फेसबुक और इंस्टाग्राम जैसे सोशल मीडिया का उपयोग करें।";

        return InteractiveMessage.builder()
                .body(messageBody)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                ))
                .build();
    }
}
