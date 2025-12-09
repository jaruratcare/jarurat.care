package care.jarurat.hope.doctor;

public class DoctorPrompts {

  public static String askCity() {
    return "🏙️ *Please type your city name*\n\nExample:\n• Mumbai\n• Pune\n• Nagpur\n• Delhi\n\n_Type your city:_";
  }

  public static String askSpecialty() {
    return "🩺 *Select the type of doctor you need:*\n\n" +
        "1️⃣ Medical Oncologist\n" +
        "2️⃣ Surgical Oncologist\n" +
        "3️⃣ Radiation Oncologist\n" +
        "4️⃣ Gastroenterologist\n" +
        "5️⃣ Interventional Radiologist\n" +
        "6️⃣ Other (type manually)\n\n" +
        "➡️ Type your specialty (e.g., *Medical Oncology* )";
  }

  public static String noDoctorsFound(String city, String specialty) {
    return "❌ *No doctors found*\n\n" +
        "City: *" + city + "*\n" +
        "Specialty: *" + specialty + "*\n\n" +
        "Try another city or specialty.";
  }
}
