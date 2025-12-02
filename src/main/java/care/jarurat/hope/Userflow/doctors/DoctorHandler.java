package care.jarurat.hope.Userflow.doctors;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.WhatsAppService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorHandler {

  private final DoctorService doctorService;
  private final DoctorMessageBuilder messageBuilder;
  private final WhatsAppService whatsAppService;

  public DoctorHandler(DoctorService doctorService,
      DoctorMessageBuilder messageBuilder,
      WhatsAppService whatsAppService) {
    this.doctorService = doctorService;
    this.messageBuilder = messageBuilder;
    this.whatsAppService = whatsAppService;
  }

  public Object handle(User user, String input) {

    try {
      String intent = user.getCurrentIntent();

      // Step 1: Ask for city
      if ("doctor_start".equals(intent)) {
        user.setCurrentIntent("awaiting_doctor_city");
        whatsAppService.sendTextMessage(user.getPhone(),
            "Which city are you looking for a doctor in?");
        return null; // ✅ fixed
      }

      // Step 2: Ask for specialization
      if ("awaiting_doctor_city".equals(intent)) {
        user.setCurrentIntent("awaiting_doctor_specialization");
        user.setCity(input.trim());
        whatsAppService.sendTextMessage(user.getPhone(),
            "Which type of doctor are you looking for?\nOptions: Medical Oncologist, Surgical Oncologist, Radiation Oncologist, Gastroenterologist, Interventional Radiologist");
        return null; // ✅ fixed
      }

      // Step 3: Fetch doctors and send ListMessage
      if ("awaiting_doctor_specialization".equals(intent)) {
        user.setCurrentIntent("doctor_list");
        String city = user.getCity();
        String specialization = input.trim();

        List<Doctor> doctors = doctorService.getDoctors(city, specialization);

        if (doctors == null || doctors.isEmpty()) {
          whatsAppService.sendTextMessage(user.getPhone(),
              "Sorry, we couldn't find any " + specialization + " in " + city + ". Try another city?");
          return null; // ✅ fixed
        }

        whatsAppService.sendListMessage(user.getPhone(),
            messageBuilder.buildDoctorListMessage(doctors, null));
        return null; // ✅ fixed
      }

    } catch (Exception e) {
      e.printStackTrace();
      whatsAppService.sendTextMessage(user.getPhone(),
          "Sorry, there was an error fetching doctors. Please try again later.");
      return null; // ✅ fixed
    }

    return null;
  }
}
