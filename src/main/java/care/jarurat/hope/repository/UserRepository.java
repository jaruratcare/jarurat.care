package care.jarurat.hope.repository;

import care.jarurat.hope.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME = "users";

    // ✅ Save or update user
    public void saveUser(User user) throws ExecutionException, InterruptedException {
        firestore.collection(COLLECTION_NAME).document(user.getUserId()).set(user).get();
    }

    // ✅ Get user by ID (phone number = userId)
    public User getUser(String userId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(User.class);
        } else {
            return null;
        }
    }

    // ✅ Update a single field in the user document
    public void updateField(String userId, String field, Object value) {
        firestore.collection(COLLECTION_NAME).document(userId).update(field, value);
    }

    // ✅ Optionally: Delete a user (for testing or reset)
    public void deleteUser(String userId) {
        firestore.collection(COLLECTION_NAME).document(userId).delete();
    }
}

