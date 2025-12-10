package care.jarurat.hope.doctor;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorRouter {

  private final DoctorHandler handler;
  private final UserService userService;

  public Object handle(User user, String input) {
    return route(user, input);
  }

  public Object route(User user, String rawMsg) {

    String msg = rawMsg == null ? "" : rawMsg.trim().toLowerCase();
    String intent = user.getCurrentIntent();

    if (intent == null || intent.isBlank()) {
      user.setCurrentIntent("doctor_find_start");
      userService.updateUser(user);
      return handler.askCity();
    }

    switch (intent) {

      case "doctor_find_start":
        user.setCurrentIntent("doctor_awaiting_city");
        userService.updateUser(user);
        return handler.askCity();

      case "doctor_awaiting_city":
        user.setTempDoctorCity(rawMsg.trim());
        user.setCurrentIntent("doctor_awaiting_specialty");
        userService.updateUser(user);
        return handler.askSpecialty();

      case "doctor_awaiting_specialty":
        String specialty = parseSpecialty(msg);
        user.setTempDoctorSpecialty(specialty);
        user.setCurrentIntent("doctor_list");
        userService.updateUser(user);
        return handler.handleSearch(user.getTempDoctorCity(), specialty);

      case "doctor_list":
        if (msg.startsWith("doctor ") || msg.startsWith("doctor_")) {
          String id = msg.replaceFirst("^doctor[_ ]+", "").trim();
          user.setCurrentIntent("doctor_details");
          userService.updateUser(user);
          return handler.handleDetails(id);
        }
        if (msg.equals("back") || msg.equals("menu")) {
          user.setCurrentIntent("main_menu");
          userService.updateUser(user);
          return "Returning to main menu.";
        }
        return "To see details, reply: DOCTOR <ID>";

      case "doctor_details":
        if (msg.startsWith("doctor ") || msg.startsWith("doctor_")) {
          String id = msg.replaceFirst("^doctor[_ ]+", "").trim();
          return handler.handleDetails(id);
        }
        return "Reply 'back' to return to the doctor list or type DOCTOR <ID>.";

      default:
        user.setCurrentIntent("doctor_awaiting_city");
        userService.updateUser(user);
        return handler.askCity();
    }
  }

  private String parseSpecialty(String msg) {
    switch (msg) {
      case "1":
        return "Medical Oncologist";
      case "2":
        return "Surgical Oncologist";
      case "3":
        return "Radiation Oncologist";
      case "4":
        return "Gastroenterologist";
      case "5":
        return "Interventional Radiology";
      default:
        return msg;
    }
  }
}
