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
}
