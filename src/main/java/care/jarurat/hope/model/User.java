package care.jarurat.hope.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @DocumentId
    private String userId;
    private String name;
    private String language;

    @PropertyName("currentIntent")
    private String currentIntent;
    private String lastIntent; // 👈 Added for back button tracking

    private String lastSeen;
    private String phone;

    private String nutritionSymptoms;    

    //  NUTRITION FIELDS
    private String foodPreference; // vegetarian, non_vegetarian
    private String eatingCondition; // soft, liquid, normal
    private String dietType; // soft, liquid, normal
    private String DiabeticStatus;

    // ONBOARDING FIELDS
    private String city;
    private String state;
    private String role; // PATIENT or CAREGIVER
    private String cancerType;
    private String cancerStage;
    private String incomeRange;
    private String tempPolicySelection;

    // NEARBY HOSPITAL
    private Double latitude;
    private Double longitude;

    // HELP TYPE (stay_food, only_stay, only_food)
    private String helpType;

    // TEMPORARY FIELD for volunteer mode (Chat / Phone Call)
    private String tempMode;
}
