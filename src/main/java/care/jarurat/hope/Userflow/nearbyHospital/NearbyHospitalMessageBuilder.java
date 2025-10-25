package care.jarurat.hope.Userflow.nearbyHospital;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NearbyHospitalMessageBuilder {

    // ===================== SIMPLE TEXTS =====================
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

    // ===================== CHOOSE TYPE (ListMessage) =====================
    public ListMessage chooseHospitalType(boolean isHindi) {
        List<ListMessage.Row> rows = new ArrayList<>();
        rows.add(ListMessage.Row.builder().id("1").title(isHindi ? "🏥 सरकारी" : "🏥 Government")
                .description(isHindi ? "सरकारी अस्पताल" : "Government hospitals").build());
        rows.add(ListMessage.Row.builder().id("2").title(isHindi ? "🏥 निजी" : "🏥 Private")
                .description(isHindi ? "निजी अस्पताल" : "Private hospitals").build());
        rows.add(ListMessage.Row.builder().id("3").title(isHindi ? "🏥 कोई भी" : "🏥 Any")
                .description(isHindi ? "कोई भी अस्पताल" : "Any hospital type").build());
        rows.add(ListMessage.Row.builder().id("back_to_menu").title(isHindi ? "🏠 मुख्य मेन्यू" : "🏠 Main Menu")
                .description(isHindi ? "मुख्य मेन्यू पर जाएँ" : "Return to main menu").build());

        List<ListMessage.Section> sections = List.of(
                ListMessage.Section.builder()
                        .title(isHindi ? "अस्पताल का प्रकार चुनें" : "Choose Hospital Type")
                        .rows(rows)
                        .build()
        );

        return ListMessage.builder()
                .header(isHindi ? "अस्पताल का प्रकार चुनें" : "Choose Hospital Type")
                .body(isHindi ? "कृपया नीचे से एक प्रकार चुनें 👇" : "Please select a type below 👇")
                .footer(isHindi ? "मुख्य मेन्यू पर वापस जाने के लिए नीचे स्क्रॉल करें।" : "Scroll down to go back to main menu.")
                .buttonText(isHindi ? "प्रकार देखें" : "View Types")
                .sections(sections)
                .build();
    }

    // ===================== NO HOSPITAL FOUND =====================
    public InteractiveMessage noHospitalsFound(String type, boolean isHindi) {
        return InteractiveMessage.builder()
                .header(isHindi ? "कोई अस्पताल नहीं मिला" : "No hospitals found")
                .body(isHindi ? "❌ क्षमा करें, " + type + " अस्पताल आपके पास नहीं मिले।"
                        : "❌ Sorry, no " + type + " hospitals were found near your location.")
                .footer(isHindi ? "मुख्य मेन्यू पर वापस जाएँ 👇" : "Go back to main menu 👇")
                .buttons(List.of(InteractiveMessage.Button.builder()
                        .id("back_to_menu")
                        .title(isHindi ? "🏠 मुख्य मेन्यू" : "🏠 Main Menu")
                        .build()))
                .build();
    }

    // ===================== PDF / Back buttons helper =====================
    public List<InteractiveMessage.Button> pdfButtons(boolean isHindi) {
        List<InteractiveMessage.Button> buttons = new ArrayList<>();
        buttons.add(InteractiveMessage.Button.builder()
                .id("yes_pdf")
                .title(isHindi ? "📄 PDF देखें" : "📄 View PDF")
                .build());
        buttons.add(InteractiveMessage.Button.builder()
                .id("no_pdf")
                .title(isHindi ? "❌ नहीं, धन्यवाद" : "❌ No, thanks")
                .build());
        buttons.add(InteractiveMessage.Button.builder()
                .id("back_to_menu")
                .title(isHindi ? "🏠 मुख्य मेन्यू" : "🏠 Main Menu")
                .build());
        return buttons;
    }

    // ===================== PDF and text builders =====================
    public InteractiveMessage pdfReady(String pdfUrl, boolean isHindi) {
        return InteractiveMessage.builder()
                .header(isHindi ? "📄 पास के अस्पताल PDF" : "📄 Nearby Hospitals PDF")
                .body((isHindi ? "आपका PDF तैयार है। इसे यहां डाउनलोड करें:\n" : "Your PDF is ready. Download here:\n") + pdfUrl)
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("back_to_menu")
                                .title(isHindi ? "🏠 मुख्य मेन्यू" : "🏠 Main Menu").build()
                ))
                .build();
    }

    public InteractiveMessage pdfDeclined(boolean isHindi) {
        return InteractiveMessage.builder()
                .body(isHindi ? "ठीक है, मेन्यू पर लौट रहे हैं।" : "Okay, returning to main menu.")
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("back_to_menu")
                                .title(isHindi ? "🏠 मुख्य मेन्यू" : "🏠 Main Menu").build()
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
                                .title(isHindi ? "🏠 मुख्य मेन्यू" : "🏠 Main Menu").build()
                ))
                .build();
    }

    // ===================== Build hospital text =====================
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

    // ===================== Split text into <=1024 chunks =====================
    public List<String> splitMessage(String fullText, int maxLength) {
        List<String> chunks = new ArrayList<>();
        int start = 0;
        while (start < fullText.length()) {
            int end = Math.min(fullText.length(), start + maxLength);
            if (end < fullText.length()) {
                int lastNewLine = fullText.lastIndexOf('\n', end);
                if (lastNewLine > start) end = lastNewLine + 1;
            }
            chunks.add(fullText.substring(start, end).trim());
            start = end;
        }
        return chunks;
    }
}
