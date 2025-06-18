package care.jarurat.hope.controller;

import care.jarurat.hope.dto.UserRequestDto;
import care.jarurat.hope.dto.UserResponseDto;
import care.jarurat.hope.mapper.UserMapper;
import care.jarurat.hope.model.User;
import care.jarurat.hope.repository.UserRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class FirebaseTestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public UserResponseDto writeTestData(@RequestBody UserRequestDto userRequestDto) throws Exception{
        User user = UserMapper.toUser(userRequestDto);
        userRepository.saveUser(user);
        return UserMapper.toResponseDto(user);
    }

}
