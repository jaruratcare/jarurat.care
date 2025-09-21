package care.jarurat.hope.Userflow.AccommodationFood;

import care.jarurat.hope.model.ListMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccommodationFoodMessageBuilder {

    // Ask for city
    public String askCity(boolean isHindi) {
        return isHindi
                ? "🏙️ कृपया अपने शहर का नाम भेजें"
                : "🏙️ Please send your city name";
    }

    // Invalid city input
    public String invalidCity(boolean isHindi) {
        return isHindi ? "⚠️ कृपया सही शहर का नाम भेजें।" : "⚠️ Please send a valid city name.";
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

    // Ask Help Type: Stay+Food / Only Stay / Only Food
    public ListMessage askHelpType(boolean isHindi) {
        List<ListMessage.Row> rows = List.of(
                ListMessage.Row.builder().id("stay_food").title(isHindi ? "🏨 Stay + Food" : "🏨 Stay + Food").build(),
                ListMessage.Row.builder().id("only_stay").title(isHindi ? "🏠 Only Stay" : "🏠 Only Stay").build(),
                ListMessage.Row.builder().id("only_food").title(isHindi ? "🍽️ Only Food" : "🍽️ Only Food").build()
        );

        // Section title must be ≤24 chars
        List<ListMessage.Section> sections = List.of(
                ListMessage.Section.builder()
                        .title(isHindi ? "मदद का प्रकार" : "Help Type")
                        .rows(rows)
                        .build()
        );

        return ListMessage.builder()
                .header(isHindi ? "कृपया विकल्प चुनें" : "Please choose an option")
                .body(isHindi ? "नीचे विकल्प चुनें:" : "Select an option below:")
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttonText(isHindi ? "विकल्प चुनें" : "Select")
                .sections(sections)
                .build();
    }

    // Ask Income Range
    public ListMessage askIncomeRange(boolean isHindi) {
    List<ListMessage.Row> rows = List.of(
            ListMessage.Row.builder().id("free_50")
                    .title(isHindi ? "नि:शुल्क/₹50 रात" : "Free/₹50 Night Stay").build(),
            ListMessage.Row.builder().id("free_meal")
                    .title(isHindi ? "नि:शुल्क भोजन" : "Free Meal Services").build(),
            ListMessage.Row.builder().id("ngo_contact")
                    .title(isHindi ? "एनजीओ संपर्क" : "NGO Contact Info").build()
    );

    List<ListMessage.Section> sections = List.of(
            ListMessage.Section.builder()
                    .title(isHindi ? "आय सीमा" : "Income Range") // ≤24 chars
                    .rows(rows)
                    .build()
    );

    return ListMessage.builder()
            .header(isHindi ? "आय सीमा चुनें" : "Choose income range")
            .body(isHindi ? "नीचे विकल्प चुनें:" : "Select an option below:")
            .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
            .buttonText(isHindi ? "विकल्प चुनें" : "Select")
            .sections(sections)
            .build();
}

    // Coming soon text
    public String comingSoonText(String city, boolean isHindi) {
        return isHindi
                ? "🚧 " + city + " में सुविधाएँ जल्द ही जोड़ी जाएंगी।"
                : "🚧 Facilities in " + city + " will be added soon.";
    }

    // Facility list
    public String facilityServiceListText(List<AccommodationFoodFacility> facilities, boolean isHindi) {
        StringBuilder sb = new StringBuilder();
        sb.append(isHindi ? "🩺 उपलब्ध सेवाएं:\n\n" : "🩺 Available Services:\n\n");

        for (AccommodationFoodFacility f : facilities) {
            sb.append("🏠 ").append(f.getName());
            if (f.getType() != null && !f.getType().isEmpty()) sb.append(" (").append(f.getType()).append(")");
            sb.append("\n📍 ").append(f.getLocation());
            if (f.getPhone() != null && !f.getPhone().isEmpty()) sb.append("\n📞 ").append(f.getPhone());
            if (f.getMapsLink() != null && !f.getMapsLink().isEmpty()) sb.append("\n🔗 ").append(f.getMapsLink());
            if (f.getServices() != null && !f.getServices().isEmpty())
                sb.append("\n🛟 ").append(isHindi ? "सेवा प्रकार: " : "Service Type: ").append(f.getServices());
            if (f.getPriceRange() != null && !f.getPriceRange().isEmpty())
                sb.append("\n💰 ").append(isHindi ? "मूल्य सीमा: " : "Price Range: ").append(f.getPriceRange());
            sb.append("\n\n");
        }

        return sb.toString().trim();
    }
}
