package care.jarurat.hope.Userflow.doctors;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

  private final Firestore firestore;

  public DoctorService(Firestore firestore) {
    this.firestore = firestore;
  }

  public List<Doctor> getDoctors(String city, String specialization) throws Exception {
    return firestore.collection("doctors")
        .whereEqualTo("city", city)
        .whereEqualTo("specialization", specialization)
        .get().get()
        .getDocuments()
        .stream()
        .map(this::mapToDoctor)
        .collect(Collectors.toList());
  }

  private Doctor mapToDoctor(QueryDocumentSnapshot doc) {
    Long exp = doc.getLong("experienceYears");
    return Doctor.builder()
        .name(doc.getString("name"))
        .hospital(doc.getString("hospital"))
        .specialization(doc.getString("specialization"))
        .opdTimings(doc.getString("opdTimings"))
        .experienceYears(exp != null ? exp.intValue() : 0)
        .description(doc.getString("description"))
        .contact(doc.getString("contact"))
        .city(doc.getString("city"))
        .website(doc.getString("website"))
        .build();
  }
}
