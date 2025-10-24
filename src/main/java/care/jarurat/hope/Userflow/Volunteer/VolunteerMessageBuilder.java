package care.jarurat.hope.Userflow.Volunteer;

import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class VolunteerMessageBuilder {

    private final VolunteerService volunteerService;
    private final UserService userService;

    public Object handle(User user, String input) {
        String intent = user.getCurrentIntent();
        boolean isEnglish = "en".equalsIgnoreCase(user.getLanguage());
        if ("back".equalsIgnoreCase(input) || "🔙".equals(input)) {
            user.setCurrentIntent("volunteer_start");
            userService.updateUser(user);
            return askCommunicationMode(isEnglish);
        }

        if ("main_menu".equalsIgnoreCase(input) || "🏠".equals(input)) {
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return isEnglish ? "🏠 Returning to Main Menu..." : "🏠 मुख्य मेनू पर लौट रहे हैं...";
        }

        // Flow handling
        return switch (intent) {
            case "volunteer_start" -> {
                user.setCurrentIntent("volunteer_choose_mode");
                userService.updateUser(user);
                yield askCommunicationMode(isEnglish);
            }

            case "volunteer_choose_mode" -> {
                if ("volunteer_chat".equals(input) || "volunteer_call".equals(input)) {
                    String mode = input.equals("volunteer_chat") ? "Chat" : "Phone Call";
                    user.setTempMode(mode);
                    user.setCurrentIntent("volunteer_ask_datetime");
                    userService.updateUser(user);
                    yield askDateTimeWithListMessage(isEnglish);
                } else {
                    yield defaultFallback(isEnglish);
                }
            }

            case "volunteer_ask_datetime" -> {
                String mode = user.getTempMode();
                InteractiveMessage response = volunteerService.bookAppointment(
                        user.getName(),
                        user.getPhone(),
                        mode,
                        input,
                        user.getLanguage()
                );
                user.setCurrentIntent("main_menu");
                user.setTempMode(null);
                userService.updateUser(user);
                yield response;
            }

            default -> defaultFallback(isEnglish);
        };
    }

    public InteractiveMessage askCommunicationMode(boolean isEnglish) {
        String body = isEnglish
                ? "How would you prefer to talk to a volunteer?"
                : "आप स्वयंसेवक से कैसे बात करना पसंद करेंगे?";

        return InteractiveMessage.builder()
                .body(body)
                .buttons(List.of(
                        InteractiveMessage.Button.builder()
                                .id("volunteer_chat")
                                .title(isEnglish ? "💬 Chat" : "💬 चैट")
                                .type("reply")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("volunteer_call")
                                .title(isEnglish ? "📞 Phone Call" : "📞 फोन कॉल")
                                .type("reply")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(isEnglish ? "🏠 Main Menu" : "🏠 मुख्य मेनू")
                                .type("reply")
                                .build()
                ))
                .build();
    }

    public ListMessage askDateTimeWithListMessage(boolean isEnglish) {
        String body = isEnglish
                ? "Please select your preferred time for today:"
                : "कृपया आज के लिए अपनी पसंदीदा समय स्लॉट चुनें:";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] slots = {"10:00", "14:00", "16:00"};
        List<ListMessage.Section> sections = new ArrayList<>();

        LocalDate today = LocalDate.now();
        String dateStr = today.format(dateFormatter);

        List<ListMessage.Row> rows = new ArrayList<>();
        for (String slot : slots) {
            String id = dateStr + " " + slot; 
            rows.add(ListMessage.Row.builder()
                    .id(id)
                    .title(slot) 
                    .build());
        }

        sections.add(ListMessage.Section.builder()
                .title(isEnglish ? "Today (" + dateStr + ")" : "आज (" + dateStr + ")")
                .rows(rows)
                .build());

        // Navigation
        sections.add(ListMessage.Section.builder()
                .title(isEnglish ? "Navigation" : "नेविगेशन")
                .rows(List.of(
                        ListMessage.Row.builder().id("back").title(isEnglish ? "🔙 Back" : "🔙 पिछला चरण").build(),
                        ListMessage.Row.builder().id("main_menu").title(isEnglish ? "🏠 Main Menu" : "🏠 मुख्य मेनू").build()
                ))
                .build());

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
}
