package care.jarurat.hope.Userflow.doctor;

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
    
    // 🔥 GLOBAL WhatsApp doctor list click handler
    if (rawMsg != null && rawMsg.startsWith("DOCTOR_")) {

        String id = rawMsg.replace("DOCTOR_", "").trim();

        user.setCurrentIntent("doctor_details");
        userService.updateUser(user);

        return handler.handleDetails(id);
    }

    switch (intent) {

      case "doctor_find_start":
        user.setCurrentIntent("doctor_awaiting_city");
        userService.updateUser(user);
        return handler.askCity();

      case "doctor_awaiting_city":
        String city = rawMsg.trim();
        user.setTempDoctorCity(rawMsg.trim());
        user.setCurrentIntent("doctor_awaiting_specialty");
        userService.updateUser(user);
        return handler.askSpecialty();

      case "doctor_awaiting_specialty":
    System.out.println("🧠 Stored tempDoctorCity = " + user.getTempDoctorCity());
    System.out.println("🧠 Raw specialty input = " + rawMsg);

    city = user.getTempDoctorCity();

    if (city == null || city.isBlank()) {
        System.out.println("⚠️ tempDoctorCity missing, restoring from user city");
        city = user.getCity();
    }

    String specialty = parseSpecialty(msg);
    user.setTempDoctorSpecialty(specialty);

    // ✅ END FLOW HERE
    user.setCurrentIntent("main_menu"); // or null if you prefer
    userService.updateUser(user);

    // ✅ Use resolved city
    return handler.handleSearch(city, specialty);


      case "doctor_list": {
         

    // IMPORTANT: use rawMsg, not msg (msg is lowercased)
    if (rawMsg != null && rawMsg.startsWith("DOCTOR_")) {

        String id = rawMsg.replace("DOCTOR_", "").trim();
         System.out.println("🧠 Doctor selected ID = " + id);
        user.setCurrentIntent("doctor_details");
        userService.updateUser(user);

        return handler.handleDetails(id);
    }

    return "❌ Invalid choice. Please select a doctor from the list.";
}


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
