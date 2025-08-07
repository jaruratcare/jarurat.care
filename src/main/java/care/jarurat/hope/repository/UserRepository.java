package care.jarurat.hope.repository;

import care.jarurat.hope.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
public class UserRepository {

    private static final String COLLECTION_NAME = "users";

    public void save(User user) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(user.getUserId()).set(user);
            log.info("User saved: {}", user.getUserId());
        } catch (Exception e) {
            log.error("Error saving user {}: {}", user.getUserId(), e.getMessage());
        }
    }

    public User findById(String userId) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot snapshot = future.get();
            if (snapshot.exists()) {
                return snapshot.toObject(User.class);
            } else {
                log.info("User not found: {}", userId);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error fetching user {}: {}", userId, e.getMessage());
            return null;
        }
    }

    public void delete(String userId) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(userId).delete();
            log.info("User deleted: {}", userId);
        } catch (Exception e) {
            log.error("Error deleting user {}: {}", userId, e.getMessage());
        }
    }
}
