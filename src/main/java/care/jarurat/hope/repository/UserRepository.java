package care.jarurat.hope.repository;

import care.jarurat.hope.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
public class UserRepository {
    
    private static final String COLLECTION_NAME = "users";

    public void save(User user) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(user.getUserId()).set(user).get();
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
                User user = snapshot.toObject(User.class);
                log.info("User found: {}", userId);
                return user;
            } else {
                log.info("User not found: {}", userId);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error fetching user {}: {}", userId, e.getMessage());
            return null;
        }
    }

    public void updateUser(User user) {
    try {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> updates = new HashMap<>();
        if (user.getName() != null) updates.put("name", user.getName());
        if (user.getLanguage() != null) updates.put("language", user.getLanguage());
        if (user.getCurrentIntent() != null) updates.put("currentIntent", user.getCurrentIntent());
        if (user.getLastSeen() != null) updates.put("lastSeen", user.getLastSeen());
        if (user.getPhone() != null) updates.put("phone", user.getPhone());

        // add preferences if not null
        if (user.getPreference() != null && !user.getPreference().isEmpty()) {
            updates.put("preference", user.getPreference());
        }

        // add PDF URL if not null
        if (user.getMealPlanPdfUrl() != null) {
            updates.put("mealPlanPdfUrl", user.getMealPlanPdfUrl());
        }

        if (!updates.isEmpty()) {
            db.collection(COLLECTION_NAME)
              .document(user.getUserId())
              .update(updates)
              .get();

            log.info("User updated: {}", user.getUserId());
        } else {
            log.info("No fields to update for user: {}", user.getUserId());
        }
    } catch (Exception e) {
        log.error("Error updating user {}: {}", user.getUserId(), e.getMessage());
    }
}


    public void delete(String userId) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(userId).delete().get();
            log.info("User deleted: {}", userId);
        } catch (Exception e) {
            log.error("Error deleting user {}: {}", userId, e.getMessage());
        }
    }
    
}
