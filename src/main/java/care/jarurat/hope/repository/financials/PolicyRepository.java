package care.jarurat.hope.repository.financials;

import care.jarurat.hope.model.financials.Policy;
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
public class PolicyRepository {

    private static final String COLLECTION_NAME = "policies";

    public void save(Policy policy) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(policy.getPolicyName()).set(policy).get();
            log.info("Saved policy: {}", policy.getPolicyName());
        } catch (Exception e) {
            log.error("Error saving policy {}: {}", policy.getPolicyName(), e.getMessage());
        }
    }

    public List<Policy> findByGovtOrPrivate(String type) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).whereEqualTo("govtOrPrivate", type).get();
            return future.get().toObjects(Policy.class);
        } catch (Exception e) {
            log.error("Error finding policies by type {}: {}", type, e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Policy> findAll() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
            return future.get().toObjects(Policy.class);
        } catch (Exception e) {
            log.error("Error finding all policies: {}", e.getMessage());
            return new ArrayList<>();
        }
    }
}
