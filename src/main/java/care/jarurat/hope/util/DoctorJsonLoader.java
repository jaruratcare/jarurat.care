package care.jarurat.hope.util;

import care.jarurat.hope.model.Doctor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;

//@Component
public class DoctorJsonLoader {

    private final List<Doctor> doctors = new ArrayList<>();

    @PostConstruct
    public void load() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("doctors.json");

            List<Map<String, Object>> rawList =
                    mapper.readValue(is, new TypeReference<>() {});

            int idCounter = 1;

            for (Map<String, Object> m : rawList) {

                Doctor d = new Doctor();

                d.setId(String.valueOf(idCounter++));
                d.setName((String) m.get("Doctor Name"));
                d.setSpecialty((String) m.get("Specialization"));
                d.setHospitalName((String) m.get("Hospital"));
                d.setCity((String) m.get("City"));
                d.setAddress((String) m.get("Address"));
                d.setOpdTimings((String) m.get("OPD Timings"));
                d.setDescription((String) m.get("Description"));
                d.setPhone((String) m.get("Contact"));
                d.setWebsite((String) m.get("Website"));

                Object exp = m.get("Experience (Years)");

                try {
                    if (exp instanceof Integer) {
                        d.setExperience((Integer) exp);

                    } else if (exp instanceof String) {
                        String cleaned = ((String) exp).replaceAll("[^0-9]", "");

                        if (!cleaned.isEmpty()) {
                            d.setExperience(Integer.parseInt(cleaned));
                        } else {
                            d.setExperience(0);
                        }

                    } else {
                        d.setExperience(0);
                    }

                } catch (Exception e) {
                    d.setExperience(0);
                }

                // ✅ You were missing this!
                doctors.add(d);
            }

            System.out.println("➡ Loaded doctors: " + doctors.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
