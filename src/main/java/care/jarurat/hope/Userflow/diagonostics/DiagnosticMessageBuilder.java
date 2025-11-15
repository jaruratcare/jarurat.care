package care.jarurat.hope.Userflow.diagonostics;
import care.jarurat.hope.model.InteractiveMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DiagnosticMessageBuilder {

    public Object askCity(boolean isEnglish) {
        return isEnglish
                ? "Please tell me your city or nearby address so I can find diagnostic labs for you."
                : "कृपया मुझे अपना शहर या आस-पास का पता बताएं ताकि मैं आपके लिए डायग्नोस्टिक लैब ढूँढ सकूँ।";
    }

    public InteractiveMessage askLocationConfirmation(String city, String state, boolean isEnglish) {
        String location = city;
        if (state != null && !state.isEmpty()) location += ", " + state;

        String body = isEnglish
                ? "I see you're in " + location + ". Should I find diagnostic labs there?"
                : "मैं देख रहा हूं कि आप " + location + " में हैं। क्या मुझे वहाँ डायग्नोस्टिक लैब ढूँढनी चाहिए?";

        return InteractiveMessage.builder()
                .body(body)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("confirm_city").title(isEnglish ? "Yes" : "हां").build(),
                        InteractiveMessage.Button.builder().id("different_city").title(isEnglish ? "Different city" : "दूसरा शहर").build()
                ))
                .build();
    }

    public Object askDifferentCity(boolean isEnglish) {
        return isEnglish
                ? "No problem. Please tell me the new city or address you'd like to search in."
                : "कोई बात नहीं। कृपया मुझे नया शहर या पता बताएं जहाँ आप खोजना चाहते हैं।";
    }

    public Object askTestType(boolean isEnglish) {
        return isEnglish
                ? "Great! What type of test are you looking for (e.g., 'blood test', 'MRI', 'CT scan')?"
                : "बहुत खूब! आप किस प्रकार के टेस्ट की तलाश में हैं (उदाहरण के लिए, 'ब्लड टेस्ट', 'एमआरआई', 'सीटी स्कैन')?";
    }

    public InteractiveMessage askCollectionType(boolean isEnglish) {
        String body = isEnglish
                ? "Got it. Do you need a lab with in-centre testing or one with home collection?"
                : "समझ गया। क्या आपको इन-सेंटर टेस्टिंग वाली लैब चाहिए या होम कलेक्शन वाली?";

        return InteractiveMessage.builder()
                .body(body)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("in_centre").title(isEnglish ? "In-Centre" : "इन-सेंटर").build(),
                        InteractiveMessage.Button.builder().id("home_collection").title(isEnglish ? "Home Collection" : "होम कलेक्शन").build()
                ))
                .build();
    }

    public Object invalidChoice(boolean isEnglish) {
        return isEnglish ? "❌ Invalid choice. Please select one of the options." : "❌ अमान्य विकल्प। कृपया विकल्पों में से एक चुनें।";
    }

    public Object invalidCity(boolean isEnglish) {
        return isEnglish ? "⚠️ Please send a valid city name." : "⚠️ कृपया सही शहर का नाम भेजें।";
    }

    public Object defaultFallback(boolean isEnglish) {
        return isEnglish
                ? "Sorry, I didn't understand that. Please tell me your city or address."
                : "क्षमा करें, मुझे समझ नहीं आया। कृपया मुझे अपना शहर या पता बताएं।";
    }
}
