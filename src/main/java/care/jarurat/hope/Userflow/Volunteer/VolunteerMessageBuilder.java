package care.jarurat.hope.Userflow.Volunteer;

import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.InteractiveMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class VolunteerMessageBuilder {

    public InteractiveMessage askCommunicationMode(boolean isEnglish) {
        String body = isEnglish
                ? "How would you prefer to talk to a volunteer?"
                : "आप स्वयंसेवक से कैसे बात करना पसंद करेंगे?";

        return InteractiveMessage.builder()
                .body(body)
                .buttons(List.of(
                        InteractiveMessage.Button.builder()
                                .id("volunteer_chat")
                                .title(isEnglish ? "💬 Chat" : "चैट")
                                .type("reply")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("volunteer_call")
                                .title(isEnglish ? "📞 Phone Call" : "फोन कॉल")
                                .type("reply")
                                .build()
                ))
                .build();
    }

    public ListMessage askDateTimeWithListMessage(boolean isEnglish) {
        String body = isEnglish
                ? "Please select your preferred date and time:"
                : "कृपया अपनी पसंदीदा तारीख और समय चुनें:";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Reduced time slots to stay within WhatsApp limits
        String[] slots = {"10:00", "14:00", "16:00"}; // Only 3 slots instead of 4
        
        List<ListMessage.Section> sections = new ArrayList<>();
        int totalRows = 0;

        // Only show 3 days instead of 7 to stay within 10 row limit (3 days × 3 slots = 9 rows)
        for (int i = 0; i < 3; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            String dateStr = date.format(dateFormatter);

            List<ListMessage.Row> rows = new ArrayList<>();
            for (String slot : slots) {
                if (totalRows >= 10) break; // Strict limit check
                
                String id = dateStr + " " + slot;
                rows.add(ListMessage.Row.builder()
                        .id(id)
                        .title(id)
                        .build());
                totalRows++;
            }

            if (!rows.isEmpty()) {
                sections.add(ListMessage.Section.builder()
                        .title(dateStr)
                        .rows(rows)
                        .build());
            }
            
            if (totalRows >= 10) break; // Don't exceed WhatsApp limit
        }

        return ListMessage.builder()
                .body(body)
                .buttonText(isEnglish ? "Select Time" : "समय चुनें")
                .sections(sections)
                .build();
    }

    // Alternative method with pagination for more options
    public ListMessage askDateTimeWithPagination(boolean isEnglish, int page) {
        String body = isEnglish
                ? "Please select your preferred date and time (Page " + (page + 1) + "):"
                : "कृपया अपनी पसंदीदा तारीख और समय चुनें (पृष्ठ " + (page + 1) + "):";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] slots = {"10:00", "11:00", "14:00", "16:00"};
        
        List<ListMessage.Section> sections = new ArrayList<>();
        int totalRows = 0;

        // Show 2 days per page with 4 slots each (total 8 rows)
        int daysPerPage = 2;
        int startDay = page * daysPerPage;
        
        for (int i = startDay; i < startDay + daysPerPage; i++) {
            if (totalRows >= 10) break;
            
            LocalDate date = LocalDate.now().plusDays(i);
            String dateStr = date.format(dateFormatter);

            List<ListMessage.Row> rows = new ArrayList<>();
            for (String slot : slots) {
                if (totalRows >= 10) break;
                
                String id = dateStr + " " + slot;
                rows.add(ListMessage.Row.builder()
                        .id(id)
                        .title(id)
                        .build());
                totalRows++;
            }

            if (!rows.isEmpty()) {
                sections.add(ListMessage.Section.builder()
                        .title(dateStr)
                        .rows(rows)
                        .build());
            }
        }

        return ListMessage.builder()
                .body(body)
                .buttonText(isEnglish ? "Select Time" : "समय चुनें")
                .sections(sections)
                .build();
    }

    public String defaultFallback(boolean isEnglish) {
        return isEnglish
                ? "Please choose a valid option."
                : "कृपया एक मान्य विकल्प चुनें।";
    }

    // Method to check if there are more pages available
    public boolean hasMorePages(int currentPage, int daysPerPage) {
        int totalDaysToShow = 7; // Total days you want to offer
        return (currentPage + 1) * daysPerPage < totalDaysToShow;
    }

    // Simple version with just today and tomorrow
    public ListMessage askDateTimeSimple(boolean isEnglish) {
        String body = isEnglish
                ? "Please select your preferred date and time:"
                : "कृपया अपनी पसंदीदा तारीख और समय चुनें:";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] slots = {"10:00", "14:00", "16:00"};
        
        List<ListMessage.Section> sections = new ArrayList<>();

        // Only show today and tomorrow (6 total rows)
        for (int i = 0; i < 2; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            String dateStr = date.format(dateFormatter);

            List<ListMessage.Row> rows = new ArrayList<>();
            for (String slot : slots) {
                String id = dateStr + " " + slot;
                rows.add(ListMessage.Row.builder()
                        .id(id)
                        .title(id)
                        .build());
            }

            sections.add(ListMessage.Section.builder()
                    .title(dateStr)
                    .rows(rows)
                    .build());
        }

        return ListMessage.builder()
                .body(body)
                .buttonText(isEnglish ? "Select Time" : "समय चुनें")
                .sections(sections)
                .build();
    }
}