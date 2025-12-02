package care.jarurat.hope.Userflow.doctors;

import care.jarurat.hope.model.ListMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMessageBuilder {

  public ListMessage buildDoctorListMessage(List<Doctor> doctors, String header) {
    List<ListMessage.Row> rows = doctors.stream()
        .map(d -> ListMessage.Row.builder()
            .id(d.getName() + "|" + d.getHospital())
            .title(d.getName())
            .description(d.getSpecialization() + " — " + d.getHospital() +
                "\nOPD: " + safe(d.getOpdTimings()))
            .build())
        .collect(Collectors.toList());

    ListMessage.Section section = ListMessage.Section.builder()
        .title("Doctors Found")
        .rows(rows)
        .build();

    return ListMessage.builder()
        .body(header != null ? header : "Here are doctors we found:")
        .footer("Jarurat Care - HopeBot")
        .sections(List.of(section))
        .build();
  }

  private String safe(String s) {
    return s == null ? "" : s;
  }
}
