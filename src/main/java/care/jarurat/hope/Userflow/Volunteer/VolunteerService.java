package care.jarurat.hope.Userflow.Volunteer;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import care.jarurat.hope.model.InteractiveMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final Calendar calendarService;
    private static final String CALENDAR_ID = "carejarurat@gmail.com";

    public InteractiveMessage bookAppointment(String userName, String phone, String mode, String dateTime, String language) {
        boolean isEnglish = "en".equalsIgnoreCase(language);
        String message;

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ZonedDateTime start = localDateTime.atZone(ZoneId.of("Asia/Kolkata"));
            ZonedDateTime end = start.plusMinutes(30);

            Event event = new Event()
                    .setSummary("Volunteer Appointment (" + mode + ")")
                    .setDescription("User: " + userName + "\nPhone: " + phone + "\nMode: " + mode);

            EventDateTime startTime = new EventDateTime()
                    .setDateTime(new DateTime(start.toInstant().toEpochMilli()))
                    .setTimeZone("Asia/Kolkata");

            EventDateTime endTime = new EventDateTime()
                    .setDateTime(new DateTime(end.toInstant().toEpochMilli()))
                    .setTimeZone("Asia/Kolkata");

            event.setStart(startTime);
            event.setEnd(endTime);

            event = calendarService.events().insert(CALENDAR_ID, event).execute();
            log.info("Appointment created: {}", event.getHtmlLink());

            message = (isEnglish ? "✅ Your appointment is booked on " : "✅ आपकी अपॉइंटमेंट बुक हो गई है: ")
                    + dateTime + "\n"
                    + (isEnglish ? "You can view it here: " : "इसे यहाँ देखें: ")
                    + event.getHtmlLink();

        } catch (IOException e) {
            log.error("Error booking volunteer appointment", e);
            message = isEnglish
                    ? "⚠️ Failed to book appointment. Please try again later."
                    : "⚠️ अपॉइंटमेंट बुक करने में त्रुटि। कृपया बाद में पुनः प्रयास करें।";
        } catch (Exception e) {
            log.error("Invalid date/time format or error", e);
            message = isEnglish
                    ? "⚠️ Invalid date/time format. Please provide date and time as yyyy-MM-dd HH:mm"
                    : "⚠️ अमान्य तारीख/समय प्रारूप। कृपया yyyy-MM-dd HH:mm प्रारूप में दें।";
        }

        return InteractiveMessage.builder()
                .body(message)
                .buttons(List.of(
                        InteractiveMessage.Button.builder().id("main_menu").title(isEnglish ? "🏠 Main Menu" : "🏠 मुख्य मेनू").type("reply").build()
                ))
                .build();
    }
}
