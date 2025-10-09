package care.jarurat.hope.Userflow.diagonostics.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesApiResponse {
    private List<Place> results;
    private String status;
}
