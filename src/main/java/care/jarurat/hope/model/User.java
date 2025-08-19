package care.jarurat.hope.model;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, String> preference = new HashMap<>();
    private String mealPlanPdfUrl;
    // custom methods
    public void setPreference(String key, String value) {
        if (preference == null) {
            preference = new HashMap<>();
        }
        preference.put(key, value);
    }
    public String getPreference(String key) {
        return preference != null ? preference.get(key) : null;
    }

}
