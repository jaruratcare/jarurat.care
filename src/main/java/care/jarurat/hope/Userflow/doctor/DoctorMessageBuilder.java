package care.jarurat.hope.Userflow.doctor;

import care.jarurat.hope.dto.DoctorDto;
import care.jarurat.hope.model.ListMessage;

import java.util.ArrayList;
import java.util.List;

public class DoctorMessageBuilder {

  // --------------------------------------------------
  // 🔥 WHATSAPP LIST FORMAT (City & Specialty Results)
  // --------------------------------------------------
  public static Object doctorList(List<DoctorDto> doctors) {

    if (doctors == null || doctors.isEmpty()) {
      return "❌ No doctors found for this search.\n\nPlease try:\n• Another city\n• Another specialty";
    }

    List<ListMessage.Row> rows = new ArrayList<>();

    for (DoctorDto d : doctors) {

      String title = d.name;

      // WhatsApp limit: 24 chars max
      if (title.length() > 24) {
        title = title.substring(0, 21) + "...";
      }


      String desc = "";

if (d.specialty != null)
  desc += d.specialty;

if (d.hospitalName != null)
  desc += " | " + d.hospitalName;

if (d.city != null)
  desc += " | " + d.city;

// WhatsApp limit: 72 chars max
if (desc.length() > 72) {
  desc = desc.substring(0, 69) + "...";
}


      rows.add(
          ListMessage.Row.builder()
              .id("DOCTOR_" + d.id) // Reply code
              .title(title)
              .description(desc)
              .build());
    }

    return ListMessage.builder()
        .header("Top Doctors Near You")
        .body("Select a doctor from the list below to view full details.")
        .footer("JaruratCare • Verified Doctors")
        .buttonText("Show Doctors")
        .sections(List.of(
            ListMessage.Section.builder()
                .title("Available Doctors")
                .rows(rows)
                .build()))
        .build();
  }

  // --------------------------------------------------
  // 🔥 DOCTOR FULL DETAILS MESSAGE (Text Output)
  // --------------------------------------------------
  public static String doctorDetails(DoctorDto d) {

    if (d == null)
      return "❌ Doctor not found.";

    StringBuilder sb = new StringBuilder();

    sb.append("👨‍⚕️ *Doctor Details*\n");
    sb.append("──────────────────────\n\n");

    sb.append("🧑‍⚕️ *Name:* ").append(d.name).append("\n");
    sb.append("📌 *Specialty:* ").append(d.specialty).append("\n");
    sb.append("🏥 *Hospital:* ").append(d.hospitalName).append("\n");
    sb.append("🌍 *City:* ").append(d.city).append("\n\n");

    if (d.address != null)
      sb.append("📍 *Address:* ").append(d.address).append("\n\n");

    sb.append("⏳ *Experience:* ").append(d.experience != null ? d.experience : "-").append(" years\n");
    sb.append("🕒 *OPD Timings:* ").append(d.opdTimings).append("\n");
    sb.append("📞 *Contact:* ").append(d.phone).append("\n");
    sb.append("🌐 *Website:* ").append(d.website).append("\n\n");

    if (d.description != null)
      sb.append("📝 *About:* ").append(d.description).append("\n\n");

    sb.append("🔙 Reply *back* to go to doctor list.");

    return sb.toString();
  }
}
