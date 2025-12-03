package care.jarurat.hope.Userflow.doctors;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import care.jarurat.hope.service.WhatsAppService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorHandler {

  private final DoctorService doctorService;
  private final DoctorMessageBuilder messageBuilder;
  private final WhatsAppService whatsAppService;
  private final UserService userService;


 public DoctorHandler(DoctorService doctorService,
                     DoctorMessageBuilder messageBuilder,
                     WhatsAppService whatsAppService,
                     UserService userService) {
    this.doctorService = doctorService;
    this.messageBuilder = messageBuilder;
    this.whatsAppService = whatsAppService;
    this.userService = userService;   // ✅ FIXED
}

  public Object handle(User user, String input) {

    try {
      String intent = user.getCurrentIntent();

      // Step 1: Ask for city
      if ("doctor_find_start".equals(intent)) {
            user.setCurrentIntent("doctor_awaiting_location");
            userService.updateUser(user);   
            whatsAppService.sendTextMessage(user.getPhone(),
                    "Which city are you looking for a doctor in?");
            return null;
        }

      // Step 2: Ask for specialization
      if ("doctor_awaiting_location".equals(intent)) {
            user.setCity(input.trim());
            user.setCurrentIntent("doctor_awaiting_specialty");
            userService.updateUser(user);   // ✅ IMPORTANT
            whatsAppService.sendTextMessage(user.getPhone(),
                    "Which type of doctor are you looking for?\nOptions: Medical Oncologist, Surgical Oncologist, Radiation Oncologist, Gastroenterologist, Interventional Radiologist");
            return null;
        }

      // Step 3: Fetch doctors and send ListMessage
      if ("doctor_awaiting_specialty".equals(intent)) {
            String city = user.getCity();
            String specialization = input.trim();

            user.setCurrentIntent("doctor_list");
            userService.updateUser(user);   // ✅ IMPORTANT

            List<Doctor> doctors = doctorService.getDoctors(city, specialization);

            if (doctors == null || doctors.isEmpty()) {
                whatsAppService.sendTextMessage(user.getPhone(),
                        "Sorry, we couldn't find any " + specialization + " in " + city + ". Try another city?");
                return null;
            }

          whatsAppService.sendListMessage(
                    user.getPhone(),
                    messageBuilder.buildDoctorListMessage(doctors, null)
            );
            return null;
          }
        } catch (Exception e) {
        e.printStackTrace();
        whatsAppService.sendTextMessage(user.getPhone(),
                "Sorry, there was an error fetching doctors. Please try again later.");
        return null;
    }

    return null;
}
}