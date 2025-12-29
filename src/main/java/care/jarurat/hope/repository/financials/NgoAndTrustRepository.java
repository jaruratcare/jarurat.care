// package care.jarurat.hope.repository.financials;

// import care.jarurat.hope.model.financials.NgoAndTrust;
// import com.google.cloud.firestore.Firestore;
// import com.google.firebase.cloud.FirestoreClient;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Repository;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.QuerySnapshot;

// import java.util.ArrayList;
// import java.util.List;

// @Slf4j
// @Repository
// public class NgoAndTrustRepository {

//     private static final String COLLECTION_NAME = "ngos_and_trusts";

//     public void save(NgoAndTrust ngo) {
//         // Ensure we have a valid name to use as part of the ID
//         if (ngo.getName() == null || ngo.getName().isBlank()) {
//             log.warn("Skipping save for NGO with no name.");
//             return;
//         }

//         try {
//             Firestore db = FirestoreClient.getFirestore();
//             // Using name + city as a simple unique ID
//             String docId = ngo.getName().replaceAll("[^a-zA-Z0-9]", "") + "_" + ngo.getCity();
//             db.collection(COLLECTION_NAME).document(docId).set(ngo).get();
//             log.info("Saved NGO: {}", ngo.getName());
//         } catch (Exception e) {
//             log.error("Error saving NGO {}: {}", ngo.getName(), e.getMessage());
//         }
//     }

//     // TODO: Implement query methods
//     public List<NgoAndTrust> findAll() {
//         try {
//             Firestore db = FirestoreClient.getFirestore();
//             ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
//             return future.get().toObjects(NgoAndTrust.class);
//         } catch (Exception e) {
//             log.error("Error finding all NGOs: {}", e.getMessage());
//             return new ArrayList<>(); // Return empty list on error
//         }
//     }

//     public List<NgoAndTrust> findByCity(String city) {
//         try {
//             Firestore db = FirestoreClient.getFirestore();
//             ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).whereEqualTo("city", city).get();
//             return future.get().toObjects(NgoAndTrust.class);
//         } catch (Exception e) {
//             log.error("Error finding NGOs by city {}: {}", city, e.getMessage());
//             return new ArrayList<>();
//         }
//     }
// }
package care.jarurat.hope.repository.financials;

import care.jarurat.hope.model.financials.NgoAndTrust;
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
public class NgoAndTrustRepository {

    private static final String COLLECTION_NAME = "ngos-and-trusts";

    public void save(NgoAndTrust ngo) {
        // Use the correct getter
        if (ngo.getNgoName() == null || ngo.getNgoName().isBlank()) {
            log.warn("Skipping save for NGO with no name.");
            return;
        }

        try {
            Firestore db = FirestoreClient.getFirestore();
            // Unique ID based on ngoName + city
            String docId = ngo.getNgoName().replaceAll("[^a-zA-Z0-9]", "") + "_" + ngo.getCity();
            db.collection(COLLECTION_NAME).document(docId).set(ngo).get();
            log.info("Saved NGO: {}", ngo.getNgoName());
        } catch (Exception e) {
            log.error("Error saving NGO {}: {}", ngo.getNgoName(), e.getMessage());
        }
    }

    public List<NgoAndTrust> findAll() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
            return future.get().toObjects(NgoAndTrust.class);
        } catch (Exception e) {
            log.error("Error finding all NGOs: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<NgoAndTrust> findByCity(String city) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                    .whereEqualTo("city", city.trim().toLowerCase())
                    .get();
            return future.get().toObjects(NgoAndTrust.class);
        } catch (Exception e) {
            log.error("Error finding NGOs by city {}: {}", city, e.getMessage());
            return new ArrayList<>();
        }
    }
}

