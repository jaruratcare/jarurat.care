package care.jarurat.hope.Userflow.doctors;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepository {

  private final Firestore firestore;

  public DoctorRepository(Firestore firestore) {
    this.firestore = firestore;
  }

  public ApiFuture<QuerySnapshot> findByCityAndSpecialization(String city, String specialization) {
    
    return firestore.collection("doctors")
        .whereEqualTo("city", city)
        .whereEqualTo("specialization", specialization)
        .get();
  }
}
