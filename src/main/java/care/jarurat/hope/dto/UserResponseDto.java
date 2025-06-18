package care.jarurat.hope.dto;

import lombok.Data;

@Data
public class UserResponseDto {
 private String userId;
 private String name;
 private String language;
 private String currentIntent;
 private String status;
}
