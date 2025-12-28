package care.jarurat.hope.service;

import care.jarurat.hope.dto.DoctorDto;
import care.jarurat.hope.mapper.DoctorMapper;
import care.jarurat.hope.model.Doctor;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final Firestore firestore;

  public List<DoctorDto> search(String city, String specialty) {
    try {

        System.out.println("📥 Doctor search called");
        System.out.println("📥 Input city = '" + city + "'");
        System.out.println("📥 Input specialty = '" + specialty + "'");

        if (city == null || city.trim().isEmpty()) {
            System.out.println("⚠️ City is null or empty. Returning empty list.");
            return List.of();
        }

        String userCity = city.trim().toLowerCase(Locale.ROOT);
        System.out.println("🔎 Normalized userCity = '" + userCity + "'");

        // 1️⃣ Fetch ALL doctors from Firestore
        QuerySnapshot snap = firestore.collection("doctors")
                .get()
                .get();

        System.out.println("📊 Total doctors fetched from DB = "
                + snap.getDocuments().size());

        List<Doctor> rawList = snap.getDocuments().stream()
                .map(doc -> {
                    Doctor d = new Doctor();
                    d.setId(doc.getId());
                    d.setName(doc.getString("doctorName"));
                    d.setCity(doc.getString("city"));
                    d.setHospitalName(doc.getString("hospital"));
                    d.setSpecialty(doc.getString("specialization"));
                    d.setAddress(doc.getString("address"));
                    d.setDescription(doc.getString("description"));
                    d.setOpdTimings(doc.getString("opdTimings"));
                    d.setPhone(doc.getString("contact"));
                    d.setWebsite(doc.getString("website"));

                    Object exp = doc.get("experienceYears");
                        int experience = 0;

                        if (exp != null) {
                            try {
                                experience = Integer.parseInt(exp.toString().replaceAll("[^0-9]", ""));
                            } catch (Exception e) {
                                experience = 0;
                            }
                        }

                        d.setExperience(experience);


                    System.out.println("🗂️ Doctor loaded → name='"
                            + d.getName()
                            + "', city='"
                            + d.getCity()
                            + "', specialty='"
                            + d.getSpecialty()
                            + "'");

                    return d;
                })
                // 2️⃣ Case-insensitive CITY filter
                .filter(d -> {
                    if (d.getCity() == null) {
                        System.out.println("⚠️ Skipping doctor with NULL city");
                        return false;
                    }

                    System.out.println("🏙️ City match check → DB='"
                            + d.getCity()
                            + "', Input='"
                            + userCity
                            + "'");

                    boolean match = d.getCity().trim().equalsIgnoreCase(userCity);
                    System.out.println("✅ City match result = " + match);

                    return match;
                })
                .collect(Collectors.toList());

        System.out.println("📊 Doctors after city filter = " + rawList.size());

        if (rawList.isEmpty()) {
            System.out.println("⚠️ No doctors found after city filter.");
            return List.of();
        }

        // 3️⃣ Specialization
        String spec = specialty.trim().toLowerCase(Locale.ROOT);
        System.out.println("🔎 Normalized specialty = '" + spec + "'");

        List<DoctorDto> finalList = rawList.stream()
                .filter(d -> {
                    if (spec.isBlank()) {
                        System.out.println("ℹ️ Specialty blank → allowing all");
                        return true;
                    }

                    if (spec.equals("other")) {
                        System.out.println("ℹ️ Specialty is 'other' → allowing all");
                        return true;
                    }

                    if (d.getSpecialty() == null) {
                        System.out.println("⚠️ Doctor '"
                                + d.getName()
                                + "' has NULL specialty");
                        return false;
                    }

                    System.out.println("🩺 Specialty match check → DB='"
                            + d.getSpecialty()
                            + "', Input='"
                            + spec
                            + "'");

                    boolean match = d.getSpecialty()
                            .toLowerCase(Locale.ROOT)
                            .contains(spec);

                    System.out.println("✅ Specialty match result = " + match);

                    return match;
                })
                .map(DoctorMapper::toDto)
                .collect(Collectors.toList());

        System.out.println("📊 Final doctors returned = " + finalList.size());

        return finalList;

    } catch (Exception e) {
        System.out.println("❌ Exception occurred in DoctorService.search()");
        e.printStackTrace();
        return List.of();
    }
}


    public DoctorDto details(String id) {
    try {
        var doc = firestore.collection("doctors")
                .document(id)
                .get()
                .get();

        if (!doc.exists()) return null;

        Doctor d = new Doctor();
        d.setId(doc.getId());
        d.setName(doc.getString("doctorName"));
        d.setSpecialty(doc.getString("specialization"));
        d.setHospitalName(doc.getString("hospital"));
        d.setCity(doc.getString("city"));
        d.setAddress(doc.getString("address"));
        d.setOpdTimings(doc.getString("opdTimings"));
        d.setPhone(doc.getString("contact"));
        d.setWebsite(doc.getString("website"));
        d.setDescription(doc.getString("description"));

        Object exp = doc.get("experienceYears");
        if (exp != null) {
            d.setExperience(
                Integer.parseInt(exp.toString().replaceAll("[^0-9]", ""))
            );
        }

        return DoctorMapper.toDto(d);

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}
