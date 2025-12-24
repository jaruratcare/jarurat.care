package care.jarurat.hope.Userflow.doctor;

import care.jarurat.hope.dto.DoctorDto;
import care.jarurat.hope.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorHandler {

  private final DoctorService service;

  public String askCity() {
    return "🩺 *Find Doctors*\n\nPlease enter the *city* where you want to search for doctors.\n\nExample: Mumbai, Pune, Delhi";
  }

  public String askSpecialty() {
    return "Please select the type of doctor:\n\n"
        + "1️⃣ Medical Oncologist\n"
        + "2️⃣ Surgical Oncologist\n"
        + "3️⃣ Radiation Oncologist\n"
        + "4️⃣ Gastroenterologist\n"
        + "5️⃣ Interventional Radiologist\n"
        + "6️⃣ Other (type manually)";
  }

  public Object handleSearch(String city, String specialty) {
    List<DoctorDto> list = service.search(city, specialty);
    return DoctorMessageBuilder.doctorList(list);
}


  public String handleDetails(String id) {
    DoctorDto d = service.details(id);
    return DoctorMessageBuilder.doctorDetails(d);
  }
}
