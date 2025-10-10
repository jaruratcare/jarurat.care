package care.jarurat.hope.repository.financials;

import care.jarurat.hope.model.financials.Hospital;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class HospitalRepository {

    private static final String COLLECTION_NAME = "hospitals";

    public void save(Hospital hospital) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            // Using name + city as a simple unique ID
            String docId = hospital.getHospitalName().replaceAll("[^a-zA-Z0-9]", "") + "_" + hospital.getCity();
            db.collection(COLLECTION_NAME).document(docId).set(hospital).get();
            log.info("Saved hospital: {}", hospital.getHospitalName());
        } catch (Exception e) {
            log.error("Error saving hospital {}: {}", hospital.getHospitalName(), e.getMessage());
        }
    }

    public long count() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
            return future.get().size();
        } catch (Exception e) {
            log.error("Error counting hospitals: {}", e.getMessage());
            return 0;
        }
    }

    public List<Hospital> findAll() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
            return future.get().toObjects(Hospital.class);
        } catch (Exception e) {
            log.error("Error finding all hospitals: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Hospital> findByPolicyNameAndCity(String policyName, String city) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                    .whereEqualTo("policyName", policyName)
                    .whereEqualTo("city", city)
                    .get();
            return future.get().toObjects(Hospital.class);
        } catch (Exception e) {
            log.error("Error finding hospitals by policy {} and city {}: {}", policyName, city, e.getMessage());
            return new ArrayList<>();
        }
    }
}
