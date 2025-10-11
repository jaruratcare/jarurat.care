package care.jarurat.hope.Userflow.Volunteer;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final Calendar calendarService;
    private static final String CALENDAR_ID = "neerajkumarsharma345@gmail.com"; // Replace with your calendar ID

    public String bookAppointment(String userName, String phone, String mode, String dateTime) {
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
            return "✅ Your appointment is booked on " + dateTime + "\n" +
                   "You can view it here: " + event.getHtmlLink();
        } catch (IOException e) {
            log.error("Error booking volunteer appointment", e);
            return "⚠️ Failed to book appointment. Please try again later.";
        } catch (Exception e) {
            log.error("Invalid date/time format or error", e);
            return "⚠️ Invalid date/time format. Please provide date and time as yyyy-MM-dd HH:mm";
        }
    }
}
