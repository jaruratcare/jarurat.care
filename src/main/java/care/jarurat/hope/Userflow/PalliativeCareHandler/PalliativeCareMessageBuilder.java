package care.jarurat.hope.Userflow.PalliativeCareHandler;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PalliativeCareMessageBuilder {

    public String askCity(boolean isHindi) {
        return isHindi
                ? "🏙️ कृपया अपने शहर का नाम भेजें"
                : "🏙️ Please send your city name";
    }

    public String invalidCity(boolean isHindi) {
        return isHindi
                ? "⚠️ कृपया सही शहर का नाम भेजें।"
                : "⚠️ Please send a valid city name.";
    }

    public String cityNotFound(boolean isHindi) {
        return isHindi
                ? "⚠️ शहर नहीं मिला। कृपया पहले शहर भेजें।"
                : "⚠️ City not found. Please send your city name first.";
    }

    public String invalidChoice(boolean isHindi) {
        return isHindi
                ? "❌ अमान्य विकल्प। कृपया सही विकल्प चुनें।"
                : "❌ Invalid choice. Please select a valid option.";
    }

    public ListMessage askHospitalType(boolean isHindi) {
        List<ListMessage.Row> rows = List.of(
                ListMessage.Row.builder().id("1").title(isHindi ? "🏛️ सरकारी" : "🏛️ Govt").build(),
                ListMessage.Row.builder().id("2").title(isHindi ? "🏥 प्राइवेट" : "🏥 Private").build()
        );

        List<ListMessage.Section> sections = new ArrayList<>();
        sections.add(ListMessage.Section.builder()
                .title(isHindi ? "अस्पताल प्रकार" : "Hospital Type")
                .rows(rows)
                .build());

        sections.add(getNavigationSection(isHindi));

        return ListMessage.builder()
                .header(isHindi ? "अस्पताल प्रकार चुनें" : "Choose Hospital Type")
                .body(isHindi ? "कृपया विकल्प चुनें:" : "Please select an option below:")
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttonText(isHindi ? "विकल्प चुनें" : "Select")
                .sections(sections)
                .build();
    }

    public ListMessage askCareType(boolean isHindi) {
        List<ListMessage.Row> rows = List.of(
                ListMessage.Row.builder().id("home")
                        .title(isHindi ? "🏠 होम केयर" : "🏠 Home care")
                        .build(),
                ListMessage.Row.builder().id("facility")
                        .title(isHindi ? "🏥 फेसीलिटी केयर" : "🏥 Facility care")
                        .build()
        );

        List<ListMessage.Section> sections = new ArrayList<>();
        sections.add(ListMessage.Section.builder()
                .title(isHindi ? "देखभाल प्रकार" : "Type of Care")
                .rows(rows)
                .build());

        sections.add(getNavigationSection(isHindi));

        return ListMessage.builder()
                .header(isHindi ? "देखभाल प्रकार चुनें" : "Choose Type of Care")
                .body(isHindi ? "कृपया विकल्प चुनें:" : "Please select an option below:")
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttonText(isHindi ? "विकल्प चुनें" : "Select")
                .sections(sections)
                .build();
    }

    public String comingSoonText(String city, boolean isHindi) {
        return isHindi
                ? "🚧 " + city + " में पालीएटिव केयर सेवाएं जल्द ही जोड़ी जाएंगी।"
                : "🚧 Palliative care services for " + city + " will be added soon.";
    }

    public InteractiveMessage facilityService(List<PalliativeCareFacility> facilities, boolean isHindi) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < facilities.size(); i++) {
            PalliativeCareFacility f = facilities.get(i);
            message.append(i + 1).append(". ").append(f.getName()).append("\n");
            if (f.getLocation() != null) message.append("📍 ").append(f.getLocation()).append("\n");
            if (f.getPhone() != null) message.append("📞 ").append(f.getPhone()).append("\n");
            if (f.getMapsLink() != null) message.append("🔗 ").append(f.getMapsLink()).append("\n");
            if (f.getServices() != null && !f.getServices().isEmpty())
                message.append(isHindi ? "🛟 सेवा प्रकार: " : "🛟 Service Type: ").append(f.getServices()).append("\n");
            message.append("\n");
        }

        List<InteractiveMessage.Button> buttons = List.of(
                InteractiveMessage.Button.builder()
                        .id("back")
                        .title(isHindi ? "🔙 Back" : "🔙 Back")
                        .build(),
                InteractiveMessage.Button.builder()
                        .id("main_menu")
                        .title(isHindi ? "🏠 Main Menu" : "🏠 Main Menu")
                        .build()
        );

        return InteractiveMessage.builder()
                .header(isHindi ? "उपलब्ध सेवाएं" : "Available Services")
                .body(message.toString())
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttons(buttons)
                .build();
    }

    // --- NEW method for Main Menu ---
    public String mainMenu(boolean isHindi) {
        return isHindi
                ? "🏠 मुख्य मेन्यू:\n1. पालीएटिव केयर\n2. अन्य विकल्प"
                : "🏠 Main Menu:\n1. Palliative Care\n2. Other Options";
    }

    private ListMessage.Section getNavigationSection(boolean isHindi) {
        return ListMessage.Section.builder()
                .title(isHindi ? "नेविगेशन" : "Navigation")
                .rows(List.of(
                        ListMessage.Row.builder().id("back").title(isHindi ? "🔙 Back" : "🔙 Back").build(),
                        ListMessage.Row.builder().id("main_menu").title(isHindi ? "🏠 Main Menu" : "🏠 Main Menu").build()
                ))
                .build();
    }
}
