package care.jarurat.hope.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String userId;
    private String language;
    private String intent;

    private String city;
    private String selectedDoctorType; // or reuse currentIntent

}