package care.jarurat.hope.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import care.jarurat.hope.dto.UserRequestDto;
import care.jarurat.hope.dto.UserResponseDto;
import care.jarurat.hope.mapper.UserMapper;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;

@RestController
@RequestMapping("/api/test")
public class FirebaseTestController {

    private final UserService userService;

    public FirebaseTestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserResponseDto writeTestData(@RequestBody UserRequestDto userRequestDto) {
        User user = UserMapper.toUser(userRequestDto);
        userService.saveUser(user);
        return UserMapper.toResponseDto(user);
    }
}