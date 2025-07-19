package care.jarurat.hope.service;

import care.jarurat.hope.model.User;
import care.jarurat.hope.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveOrUpdateUser(User user) {
        try {
            userRepository.saveUser(user);
        } catch (ExecutionException | InterruptedException e) {
            log.error("❌ Error saving user: {}", user.getUserId(), e);
            throw new RuntimeException("Failed to save user", e);
        }
    }

    public User getUser(String userId) {
        try {
            return userRepository.getUser(userId);
        } catch (ExecutionException | InterruptedException e) {
            log.error("❌ Error retrieving user: {}", userId, e);
            throw new RuntimeException("Failed to get user", e);
        }
    }

    public void updateField(String userId, String fieldName, Object value) {
        try {
            userRepository.updateField(userId, fieldName, value);
        } catch (Exception e) {
            log.error("❌ Failed to update field '{}' for user '{}'", fieldName, userId, e);
        }
    }

    public void updateLanguage(String userId, String language) {
        updateField(userId, "language", language);
    }

    public void updateCurrentIntent(String userId, String intent) {
        updateField(userId, "currentIntent", intent);
    }

    public void updateName(String userId, String name) {
        updateField(userId, "name", name);
    }

    public void updateLastSeen(String userId, String lastSeen) {
        updateField(userId, "lastSeen", lastSeen);
    }
}
