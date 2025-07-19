package care.jarurat.hope.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;

@Data
public class User {
    @DocumentId
    private String userId;
    private String name;
    private String language;
    private String CurrentIntent;
    private String lastSeen;
    private String Phone;
}
