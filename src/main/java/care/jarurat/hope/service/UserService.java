package care.jarurat.hope.service;

import care.jarurat.hope.model.User;
import care.jarurat.hope.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveOrUpdateUser(User user) {
        userRepository.saveUser(user);
    }

    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }

    public void updateLanguage(String userId, String language) {
        userRepository.updateField(userId, "language", language);
    }

    public void updateCurrentIntent(String userId, String intent) {
        userRepository.updateField(userId, "currentIntent", intent);
    }

    public void updateName(String userId, String name) {
        userRepository.updateField(userId, "name", name);
    }

    public void updateLastSeen(String userId, String lastSeen) {
        userRepository.updateField(userId, "lastSeen", lastSeen);
    }
}