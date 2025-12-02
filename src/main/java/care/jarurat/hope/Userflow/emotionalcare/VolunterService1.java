package care.jarurat.hope.Userflow.emotionalcare;

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
public class VolunterService1 {

    private final Calendar calendarService;
    private static final String CALENDAR_ID = "carejarurat@gmail.com";

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

    public InteractiveMessage bookAppointment(
            String userName,
            String phone,
            String mode,
            String dateTime,
            String language
    ) {
        boolean isEnglish = "en".equalsIgnoreCase(language);
        String message;

        try {
            if (dateTime.contains("T")) {
                dateTime = dateTime.replace("T", " ");
            }

            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, INPUT_FORMAT);
            ZonedDateTime start = localDateTime.atZone(ZoneId.of("Asia/Kolkata"));
            ZonedDateTime end = start.plusMinutes(30);
            String displayTime = localDateTime.format(DISPLAY_FORMAT);

            String description =
                    "User: " + userName +
                    "\nPhone: " + phone +
                    "\nMode: " + mode +
                    "\nFor: Emotional Support";

            Event event = new Event()
                    .setSummary("Volunteer Appointment (" + mode + ")")
                    .setDescription(description);

            EventDateTime startTime = new EventDateTime()
                    .setDateTime(new DateTime(start.toInstant().toEpochMilli()))
                    .setTimeZone("Asia/Kolkata");

            EventDateTime endTime = new EventDateTime()
                    .setDateTime(new DateTime(end.toInstant().toEpochMilli()))
                    .setTimeZone("Asia/Kolkata");

            event.setStart(startTime);
            event.setEnd(endTime);

            event = calendarService.events().insert(CALENDAR_ID, event).execute();

            log.info("Appointment created successfully. Event ID: {}", event.getId());

            if (isEnglish) {
                message =
                        "✅ Your appointment has been successfully booked.\n"
                        + "📅 **Scheduled Time:** " + displayTime + "\n\n"
                        + "💬 A volunteer will contact you at the scheduled time.";
            } else {
                message =
                        "✅ आपकी अपॉइंटमेंट सफलतापूर्वक बुक हो गई है।\n"
                        + "📅 **निर्धारित समय:** " + displayTime + "\n\n"
                        + "💬 निर्धारित समय पर एक स्वयंसेवक आपसे संपर्क करेगा।";
            }

        } catch (IOException e) {
            log.error("Calendar API error", e);
            message = isEnglish
                    ? "⚠️ Unable to connect to the booking system. Please try again later."
                    : "⚠️ बुकिंग सिस्टम से कनेक्ट नहीं हो पाया। कृपया बाद में पुनः प्रयास करें।";

        } catch (Exception e) {
            log.error("Date parsing error", e);
            message = isEnglish
                    ? "⚠️ Invalid date/time format. Please use: yyyy-MM-dd HH:mm"
                    : "⚠️ गलत समय प्रारूप। कृपया इस तरह लिखें: yyyy-MM-dd HH:mm";
        }

        return InteractiveMessage.builder()
                .body(message)
                .buttons(
                        List.of(
                                InteractiveMessage.Button.builder()
                                        .id("main_menu")
                                        .title(isEnglish ? "🏠 Main Menu" : "🏠 मुख्य मेनू")
                                        .type("reply")
                                        .build()
                        )
                )
                .build();
    }
}
