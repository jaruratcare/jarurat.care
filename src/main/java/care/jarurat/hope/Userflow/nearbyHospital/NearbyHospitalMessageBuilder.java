package care.jarurat.hope.Userflow.nearbyHospital;

import care.jarurat.hope.model.InteractiveMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NearbyHospitalMessageBuilder {

    // Simple text messages (return String)
    public String askLocation(boolean isHindi) {
        return isHindi 
                ? "📍 कृपया अपनी लाइव लोकेशन शेयर करें"
                : "📍 Please share your live location";
    }

    public String invalidLocationFormat(boolean isHindi) {
        return isHindi 
                ? "⚠️ कृपया अपनी लाइव लोकेशन शेयर करें"
                : "⚠️ Please share your live location";
    }

    public String locationNotFound(boolean isHindi) {
        return isHindi 
                ? "⚠️ मुझे अभी आपकी लोकेशन नहीं मिली। कृपया पहले लोकेशन भेजें।"
                : "⚠️ I don't have your location yet. Please share location first.";
    }

    public String invalidChoice(boolean isHindi) {
        return isHindi 
                ? "❌ अमान्य विकल्प। कृपया 1, 2, या 3 में से चुनें।"
                : "❌ Invalid choice. Reply 1, 2, or 3.";
    }

    // Interactive messages with buttons
    public InteractiveMessage chooseHospitalType(boolean isHindi) {
        List<InteractiveMessage.Button> buttons = new ArrayList<>();
        buttons.add(InteractiveMessage.Button.builder().id("1").title(isHindi ? "🏥 सरकारी" : "🏥 Government").type("reply").build());
        buttons.add(InteractiveMessage.Button.builder().id("2").title(isHindi ? "🏥 निजी" : "🏥 Private").type("reply").build());
        buttons.add(InteractiveMessage.Button.builder().id("3").title(isHindi ? "🏥 कोई भी" : "🏥 Any").type("reply").build());

        return InteractiveMessage.builder()
                .header(isHindi ? "अस्पताल का प्रकार चुनें" : "Choose Hospital Type")
                .body(isHindi ? "क्या आप किसी प्रकार का अस्पताल पसंद करेंगे?" : "Would you prefer a hospital type?")
                .footer(isHindi ? "नीचे एक विकल्प चुनें 👇" : "Select one option below 👇")
                .buttons(buttons)
                .build();
    }

    public InteractiveMessage noHospitalsFound(String type, boolean isHindi) {
        List<InteractiveMessage.Button> buttons = new ArrayList<>();
        buttons.add(InteractiveMessage.Button.builder().id("1").title(isHindi ? "🏥 सरकारी" : "🏥 Government").type("reply").build());
        buttons.add(InteractiveMessage.Button.builder().id("2").title(isHindi ? "🏥 निजी" : "🏥 Private").type("reply").build());
        buttons.add(InteractiveMessage.Button.builder().id("3").title(isHindi ? "🏥 कोई भी" : "🏥 Any").type("reply").build());

        return InteractiveMessage.builder()
                .header(isHindi ? "कोई अस्पताल नहीं मिला" : "No hospitals found")
                .body(isHindi ? "❌ क्षमा करें, " + type + " अस्पताल आपके पास नहीं मिले। कृपया दूसरा प्रकार चुनें।"
                               : "❌ Sorry, I couldn't find " + type + " hospitals near your location. Please select another type.")
                .footer(isHindi ? "नीचे एक विकल्प चुनें 👇" : "Select one option below 👇")
                .buttons(buttons)
                .build();
    }

    public InteractiveMessage hospitalList(List<Hospital> hospitals, boolean isHindi) {
        StringBuilder sb = new StringBuilder();
        for (Hospital h : hospitals) {
            sb.append(String.format(
                    "➡ %s\n%s\n📞 %s\n📏 %.2f km away\n🌍 %s\n\n",
                    h.getName(), h.getAddress(), h.getPhone(), h.getDistance(), h.getMapsLink()
            ));
        }
        List<InteractiveMessage.Button> buttons = List.of(
                InteractiveMessage.Button.builder().id("yes_pdf").title(isHindi ? "📄 PDF देखें" : "📄 View as PDF").build(),
                InteractiveMessage.Button.builder().id("no_pdf").title(isHindi ? "❌ नहीं" : "❌ No, thanks").build()
        );

        return InteractiveMessage.builder()
                .body(sb.toString().trim())
                .buttons(buttons)
                .build();
    }

    public InteractiveMessage askPdfDownload(boolean isHindi) {
        List<InteractiveMessage.Button> buttons = List.of(
                InteractiveMessage.Button.builder().id("yes_pdf").title(isHindi ? "📄 PDF डाउनलोड करें" : "📄 Download PDF").build(),
                InteractiveMessage.Button.builder().id("no_pdf").title(isHindi ? "❌ नहीं" : "❌ No, thanks").build()
        );

        return InteractiveMessage.builder()
                .body(isHindi ? "📄 क्या आप पास के अस्पताल PDF डाउनलोड करना चाहते हैं?" 
                              : "📄 Do you want to download nearby hospitals as PDF?")
                .buttons(buttons)
                .build();
    }

    public InteractiveMessage pdfReady(String pdfUrl, boolean isHindi) {
        return InteractiveMessage.builder()
                .header(isHindi ? "📄 पास के अस्पताल PDF" : "📄 Nearby Hospitals PDF")
                .body((isHindi ? "आपका PDF तैयार है। इसे यहां डाउनलोड करें:\n" : "Your PDF is ready. Download here:\n") + pdfUrl)
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("back_to_menu")
                                .title(isHindi ? "🔙 मेन्यू पर वापस जाएँ" : "🔙 Back to Menu").build()
                ))
                .build();
    }

    public InteractiveMessage pdfDeclined(boolean isHindi) {
    return InteractiveMessage.builder()
            .body(isHindi ? "ठीक है, मेन्यू पर लौट रहे हैं।" : "Okay, returning to main menu.")
            .buttons(List.of(
                    InteractiveMessage.Button.builder()
                            .id("back_to_menu") // same ID used in pdfReady
                            .title(isHindi ? "🔙 मेन्यू पर वापस जाएँ" : "🔙 Back to Menu")
                            .build()
            ))
            .build();
}



    public InteractiveMessage pdfError(boolean isHindi) {
        return InteractiveMessage.builder()
                .header(isHindi ? "❌ त्रुटि" : "❌ Error")
                .body(isHindi ? "PDF बनाने में विफल। कृपया बाद में पुनः प्रयास करें।" 
                               : "Failed to generate PDF. Please try again later.")
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("back_to_menu")
                                .title(isHindi ? "🔙 मेन्यू पर वापस जाएँ" : "🔙 Back to Menu").build()
                ))
                .build();
    }

    // ✅ Method for generating PDF content
    public String buildHospitalText(List<Hospital> hospitals) {
        StringBuilder sb = new StringBuilder();
        for (Hospital h : hospitals) {
            sb.append(String.format(
                    "➡ %s\n%s\n📞 %s\n📏 %.2f km away\n🌍 %s\n\n",
                    h.getName(), h.getAddress(), h.getPhone(), h.getDistance(), h.getMapsLink()
            ));
        }
        return sb.toString().trim();
    }
}
