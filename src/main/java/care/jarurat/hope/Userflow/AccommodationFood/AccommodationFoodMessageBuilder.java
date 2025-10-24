package care.jarurat.hope.Userflow.AccommodationFood;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccommodationFoodMessageBuilder {

   public InteractiveMessage askHospital(boolean isHindi) {
    String text = isHindi
            ? "🏥 कृपया उस अस्पताल का नाम भेजें जहाँ आप जा रहे हैं:"
            : "🏥 Please send the name of the hospital you are visiting:";

    List<InteractiveMessage.Button> buttons = List.of(
            InteractiveMessage.Button.builder()
                    .id("main_menu")
                    .title(isHindi ? "🔙 Back" : "🔙 Back")
                    .build()
    );

    return InteractiveMessage.builder()
            .header(isHindi ? "🏥 अस्पताल चुनें" : "🏥 Enter Hospital Name")
            .body(text)
            .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
            .buttons(buttons)
            .build();
}

    public String invalidHospital(boolean isHindi) {
        return isHindi ? "⚠️ कृपया सही अस्पताल का नाम भेजें।" :
                "⚠️ Please send a valid hospital name.";
    }

    public String hospitalNotFound(boolean isHindi, String hospital) {
        return isHindi ? "⚠️ " + hospital + " अस्पताल डेटाबेस में नहीं मिला।" :
                "⚠️ Hospital " + hospital + " not found in our database.";
    }

    public String invalidChoice(boolean isHindi) {
        return isHindi
                ? "❌ अमान्य विकल्प। कृपया सही विकल्प चुनें।"
                : "❌ Invalid choice. Please select a valid option.";
    }

    public ListMessage askHelpType(boolean isHindi) {
        List<ListMessage.Row> rows = List.of(
                ListMessage.Row.builder().id("stay_food").title(isHindi ? "🏨 Stay + Food" : "🏨 Stay + Food").build(),
                ListMessage.Row.builder().id("only_stay").title(isHindi ? "🏠 Only Stay" : "🏠 Only Stay").build(),
                ListMessage.Row.builder().id("only_food").title(isHindi ? "🍽️ Only Food" : "🍽️ Only Food").build()
        );

        List<ListMessage.Section> sections = new ArrayList<>();
        sections.add(ListMessage.Section.builder()
                .title(isHindi ? "मदद का प्रकार" : "Help Type")
                .rows(rows)
                .build());
        sections.add(getNavigationSection(isHindi));

        return ListMessage.builder()
                .header(isHindi ? "कृपया विकल्प चुनें" : "Please choose an option")
                .body(isHindi ? "नीचे दिए गए विकल्पों में से चुनें:" : "Select an option from the list below:")
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttonText(isHindi ? "विकल्प चुनें" : "Select")
                .sections(sections)
                .build();
    }

    public InteractiveMessage askIncomeRange(boolean isHindi) {
    String text = isHindi
            ? "💰 कृपया अपनी औसत आय सीमा लिखें:"
            : "💰 Please type your average income range:";

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
            .header(isHindi ? "💰 आय सीमा" : "💰 Income Range")
            .body(text)
            .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
            .buttons(buttons)
            .build();
}

    public ListMessage askServiceOptionsForHelpType(boolean isHindi, String helpType) {
        List<ListMessage.Row> rows = new ArrayList<>();
        if (helpType == null) helpType = "";
        helpType = helpType.trim().toLowerCase();

        switch (helpType) {
            case "stay_food":
                rows.add(ListMessage.Row.builder()
                        .id("stay")
                        .title(isHindi ? "Nearby Stays" : "Nearby Stays")
                        .description(isHindi ? "Nearby free or ₹50/night stays (NGO run)" : "Nearby free or ₹50/night stays (NGO run)")
                        .build());
                rows.add(ListMessage.Row.builder()
                        .id("food")
                        .title(isHindi ? "Free Meals" : "Free Meals")
                        .description(isHindi ? "Free meal services (open hours + location)" : "Free meal services (open hours + location)")
                        .build());
                break;
            case "only_stay":
                rows.add(ListMessage.Row.builder()
                        .id("stay")
                        .title(isHindi ? "Nearby Stays" : "Nearby Stays")
                        .description(isHindi ? "Nearby free or ₹50/night stays (NGO run)" : "Nearby free or ₹50/night stays (NGO run)")
                        .build());
                break;
            case "only_food":
                rows.add(ListMessage.Row.builder()
                        .id("food")
                        .title(isHindi ? "Free Meals" : "Free Meals")
                        .description(isHindi ? "Free meal services (open hours + location)" : "Free meal services (open hours + location)")
                        .build());
                break;
        }

        rows.add(ListMessage.Row.builder()
                .id("ngo")
                .title(isHindi ? "NGO Info" : "NGO Info")
                .description(isHindi ? "NGO contact info, map pins, price range" : "NGO contact info, map pins, price range")
                .build());

        List<ListMessage.Section> sections = new ArrayList<>();
        sections.add(ListMessage.Section.builder()
                .title(isHindi ? "सेवा विकल्प" : "Service Options")
                .rows(rows)
                .build());
        sections.add(getNavigationSection(isHindi));

        return ListMessage.builder()
                .header(isHindi ? "नीचे विकल्प चुनें:" : "Select from the options below:")
                .body(isHindi ? "कृपया किसी एक सेवा विकल्प का चयन करें:" : "Please select one of the service options:")
                .footer(isHindi ? "आप किसी भी विकल्प का चयन कर सकते हैं" : "You can select any option")
                .buttonText(isHindi ? "विकल्प चुनें" : "Select")
                .sections(sections)
                .build();
    }

    public String comingSoonText(boolean isHindi) {
        return isHindi ? "🚧 सुविधाएँ जल्द ही जोड़ी जाएंगी।" : "🚧 Facilities will be added soon.";
    }

    public String facilityServiceListText(List<AccommodationFoodFacility> facilities, boolean isHindi, String choice) {
        StringBuilder sb = new StringBuilder();
        sb.append(isHindi ? "🩺 उपलब्ध सेवाएं:\n\n" : "🩺 Available Services:\n\n");

        for (AccommodationFoodFacility f : facilities) {
            sb.append("🏠 ").append(f.getName()).append("\n");
            sb.append("📍 ").append(f.getLocation()).append("\n");

            switch (choice) {
                case "stay":
                    if (f.getPhone() != null) sb.append("📞 ").append(f.getPhone()).append("\n");
                    if (f.getPriceRange() != null) sb.append("💰 ").append(f.getPriceRange()).append("\n");
                    break;
                case "food":
                    if (f.getOpen_hour() != null) sb.append("🕒 ").append(f.getOpen_hour()).append("\n");
                    if (f.getMapsLink() != null) sb.append("🔗 ").append(f.getMapsLink()).append("\n");
                    break;
                case "ngo":
                    if (f.getPhone() != null) sb.append("📞 ").append(f.getPhone()).append("\n");
                    if (f.getMapsLink() != null) sb.append("🔗 ").append(f.getMapsLink()).append("\n");
                    if (f.getPriceRange() != null) sb.append("💰 ").append(f.getPriceRange()).append("\n");
                    break;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    // --- Back & Main Menu Buttons ---
    public InteractiveMessage facilityServiceWithNavigation(List<AccommodationFoodFacility> facilities, boolean isHindi, String choice) {
        String text = facilityServiceListText(facilities, isHindi, choice);

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
                .header(isHindi ? "🩺 उपलब्ध सेवाएं" : "🩺 Available Services")
                .body(text)
                .footer(isHindi ? "विकल्प चुनें 👇" : "Select an option below 👇")
                .buttons(buttons)
                .build();
    }

    public String mainMenu(boolean isHindi) {
        return isHindi
                ? "🏠 मुख्य मेन्यू:\n1. आवास और भोजन\n2. अन्य विकल्प"
                : "🏠 Main Menu:\n1. Accommodation & Food\n2. Other Options";
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
