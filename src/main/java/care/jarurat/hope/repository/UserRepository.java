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

    // Save new user
    public void save(User user) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(user.getUserId()).set(user).get();
            log.info("User saved: {}", user.getUserId());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error saving user {}: {}", user.getUserId(), e.getMessage());
        }
    }

    // Find by userId
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

    // Find by phone
    public User findByPhone(String phone) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference usersRef = db.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = usersRef.whereEqualTo("phone", phone).get();

        try {
            QuerySnapshot snapshot = future.get();
            if (!snapshot.isEmpty()) {
                User user = snapshot.getDocuments().get(0).toObject(User.class);
                log.info("User found by phone: {}", phone);
                return user;
            } else {
                log.info("No user found with phone: {}", phone);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error fetching user by phone {}: {}", phone, e.getMessage());
            return null;
        }
    }

    // Update user
    public void updateUser(User user) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            Map<String, Object> updates = new HashMap<>();

            if (user.getName() != null) updates.put("name", user.getName());
            if (user.getLanguage() != null) updates.put("language", user.getLanguage());
            if (user.getCurrentIntent() != null) updates.put("currentIntent", user.getCurrentIntent());
            if (user.getLastSeen() != null) updates.put("lastSeen", user.getLastSeen());
            if (user.getPhone() != null) updates.put("phone", user.getPhone());
            if (user.getCity() != null) updates.put("city", user.getCity());
            if (user.getState() != null) updates.put("state", user.getState());
            if (user.getRole() != null) updates.put("role", user.getRole());
            if (user.getCancerType() != null) updates.put("cancerType", user.getCancerType());
            if (user.getCancerStage() != null) updates.put("cancerStage", user.getCancerStage());
            if (user.getIncomeRange() != null) updates.put("incomeRange", user.getIncomeRange());

            // Nutrition fields (now single string for symptoms)
            if (user.getFoodPreference() != null) updates.put("foodPreference", user.getFoodPreference());
            if (user.getDietType() != null) updates.put("dietType", user.getDietType());
            if (user.getEatingCondition() != null) updates.put("eatingCondition", user.getEatingCondition());
            if (user.getNutritionSymptoms() != null) updates.put("nutritionSymptoms", user.getNutritionSymptoms());

            if (!updates.isEmpty()) {
                db.collection(COLLECTION_NAME).document(user.getUserId()).update(updates).get();
                log.info("User updated: {}", user.getUserId());
            } else {
                log.info("No fields to update for user: {}", user.getUserId());
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error updating user {}: {}", user.getUserId(), e.getMessage());
        }
    }

    // Delete user
    public void delete(String userId) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection(COLLECTION_NAME).document(userId).delete().get();
            log.info("User deleted: {}", userId);
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error deleting user {}: {}", userId, e.getMessage());
        }
    }
}
