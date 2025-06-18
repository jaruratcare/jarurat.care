package care.jarurat.hope.mapper;

import care.jarurat.hope.dto.UserRequestDto;
import care.jarurat.hope.dto.UserResponseDto;
import care.jarurat.hope.model.User;

import java.time.Instant;

public class UserMapper {
    public static User toUser(UserRequestDto dto){
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setLanguage(dto.getLanguage());
        user.setCurrentIntent(dto.getIntent());
        user.setLastSeen(Instant.now().toString());
        return user;
    }

    public static UserResponseDto toResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setLanguage(user.getLanguage());
        dto.setCurrentIntent(user.getCurrentIntent());
        dto.setStatus("success");
        return dto;
    }


}
