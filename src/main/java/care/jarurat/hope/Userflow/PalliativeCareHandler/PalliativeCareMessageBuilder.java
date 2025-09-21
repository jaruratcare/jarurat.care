package care.jarurat.hope.Userflow.PalliativeCareHandler;

import care.jarurat.hope.model.ListMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PalliativeCareMessageBuilder {

    // Ask for city
    public String askCity(boolean isHindi) {
        return isHindi
                ? "🏙️ कृपया अपने शहर का नाम भेजें"
                : "🏙️ Please send your city name";
    }

    // Invalid city input
    public String invalidCity(boolean isHindi) {
        return isHindi
                ? "⚠️ कृपया सही शहर का नाम भेजें।"
                : "⚠️ Please send a valid city name.";
    }

    // City not found
    public String cityNotFound(boolean isHindi) {
        return isHindi
                ? "⚠️ शहर नहीं मिला। कृपया पहले शहर भेजें।"
                : "⚠️ City not found. Please send your city name first.";
    }

    // Invalid choice
    public String invalidChoice(boolean isHindi) {
        return isHindi
                ? "❌ अमान्य विकल्प। कृपया सही विकल्प चुनें।"
                : "❌ Invalid choice. Please select a valid option.";
    }

    // Ask hospital type: Government / Private
    public ListMessage askHospitalType(boolean isHindi) {
        List<ListMessage.Row> rows = List.of(
                ListMessage.Row.builder().id("1").title(isHindi ? "🏛️ सरकारी" : "🏛️ Govt").build(),
                ListMessage.Row.builder().id("2").title(isHindi ? "🏥 प्राइवेट" : "🏥 Private").build()
        );

        List<ListMessage.Section> sections = List.of(
                ListMessage.Section.builder()
                        .title(isHindi ? "अस्पताल प्रकार" : "Hospital Type")
                        .rows(rows)
                        .build()
        );

        return ListMessage.builder()
                .header(isHindi ? "अस्पताल प्रकार चुनें" : "Choose Hospital Type")
                .body(isHindi ? "कृपया विकल्प चुनें:" : "Please select an option below:")
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttonText(isHindi ? "विकल्प चुनें" : "Select")
                .sections(sections)
                .build();
    }

    // Ask care type: Home / Facility
    public ListMessage askCareType(boolean isHindi) {
        List<ListMessage.Row> rows = List.of(
                ListMessage.Row.builder().id("home")
                        .title(isHindi ? "🏠 होम केयर" : "🏠 Home care")
                        .build(),
                ListMessage.Row.builder().id("facility")
                        .title(isHindi ? "🏥 फेसीलिटी केयर" : "🏥 Facility care")
                        .build()
        );

        List<ListMessage.Section> sections = List.of(
                ListMessage.Section.builder()
                        .title(isHindi ? "देखभाल प्रकार" : "Type of Care")
                        .rows(rows)
                        .build()
        );

        return ListMessage.builder()
                .header(isHindi ? "देखभाल प्रकार चुनें" : "Choose Type of Care")
                .body(isHindi ? "कृपया विकल्प चुनें:" : "Please select an option below:")
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttonText(isHindi ? "विकल्प चुनें" : "Select")
                .sections(sections)
                .build();
    }

    // Coming soon text
    public String comingSoonText(String city, boolean isHindi) {
        return isHindi
                ? "🚧 " + city + " में पालीएटिव केयर सेवाएं जल्द ही जोड़ी जाएंगी।"
                : "🚧 Palliative care services for " + city + " will be added soon.";
    }

    // Build facility/service list text
    public String facilityServiceListText(List<PalliativeCareFacility> facilities, boolean isHindi) {
    StringBuilder sb = new StringBuilder();
    sb.append(isHindi ? "🩺 उपलब्ध सेवाएं:\n\n" : "🩺 Available Services:\n\n");

    for (PalliativeCareFacility f : facilities) {
        sb.append("🏥 ").append(f.getName());
        if (f.getType() != null && !f.getType().isEmpty()) {
            sb.append(" (").append(f.getType()).append(")");
        }
        sb.append("\n📍 ").append(f.getLocation());
        if (f.getPhone() != null && !f.getPhone().isEmpty()) {
            sb.append("\n📞 ").append(f.getPhone());
        }
        if (f.getMapsLink() != null && !f.getMapsLink().isEmpty()) {
            sb.append("\n🔗 ").append(f.getMapsLink());
        }
        if (f.getServices() != null && !f.getServices().isEmpty()) {
            sb.append("\n🛟 ").append(isHindi ? "सेवा प्रकार: " : "Service Type: ").append(f.getServices());
        }
        sb.append("\n\n");
    }

    return sb.toString().trim();
}
}
