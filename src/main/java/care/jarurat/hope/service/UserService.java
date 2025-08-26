package care.jarurat.hope.service;

import care.jarurat.hope.model.User;
import care.jarurat.hope.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(String userId) {
        userRepository.delete(userId);
    }

    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
}