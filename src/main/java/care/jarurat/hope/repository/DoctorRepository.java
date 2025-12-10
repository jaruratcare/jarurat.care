package care.jarurat.hope.repository;

import care.jarurat.hope.model.Doctor;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class DoctorRepository {

  private final Firestore db;

  public DoctorRepository(Firestore firestore) {
    this.db = firestore;
  }

  private CollectionReference doctors() {
    return db.collection("doctors");
  }

  public List<Doctor> findByCity(String city, int limit)
      throws ExecutionException, InterruptedException {

    Query q = doctors()
        .whereEqualTo("city", city)
        .limit(limit);

    return map(q.get().get());
  }

  public Optional<Doctor> findById(String id)
      throws ExecutionException, InterruptedException {

    DocumentSnapshot s = doctors().document(id).get().get();
    if (!s.exists())
      return Optional.empty();

    Doctor d = s.toObject(Doctor.class);
    d.setId(s.getId());
    return Optional.of(d);
  }

  private List<Doctor> map(QuerySnapshot snapshot) {
    return snapshot.getDocuments().stream()
        .map(doc -> {
          Doctor d = doc.toObject(Doctor.class);
          d.setId(doc.getId());
          return d;
        })
        .collect(Collectors.toList());
  }
}
