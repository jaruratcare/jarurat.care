package care.jarurat.hope.repository.financials;

import care.jarurat.hope.model.financials.GovtScheme;
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
public class GovtSchemeRepository {

    private static final String COLLECTION_NAME = "govt_schemes";

    public void save(GovtScheme scheme) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(scheme.getSchemeName()).set(scheme).get();
            log.info("Saved scheme: {}", scheme.getSchemeName());
        } catch (Exception e) {
            log.error("Error saving scheme {}: {}", scheme.getSchemeName(), e.getMessage());
        }
    }

    // TODO: Implement query methods
    public List<GovtScheme> findAll() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
            return future.get().toObjects(GovtScheme.class);
        } catch (Exception e) {
            log.error("Error finding all schemes: {}", e.getMessage());
            return new ArrayList<>(); // Return empty list on error
        }
    }

    public List<GovtScheme> findByIncomeAndCity(String income, String city) {
        // Placeholder for more complex query logic
        return null;
    }
}
