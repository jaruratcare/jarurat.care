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
    private String lastSeen;
    private String phone;
    private String nutritionSymptoms;    
    //  THESE ARE NUTRITION FIELDS:
    private String foodPreference; // vegetarian, non_vegetarian
    private String eatingCondition; // soft, liquid, normal
    private String dietType; // soft, liquid, normal

    // New fields for onboarding
    private String city;
    private String state;
    private String role; // PATIENT or CAREGIVER
    private String cancerType;
    private String cancerStage;
    private String incomeRange;
    private String tempPolicySelection;
    // Field for NearbyHospital
    private Double latitude;
    private Double longitude;

    // NEW FIELD: store user selected help type (stay_food, only_stay, only_food)
    private String helpType;
}
