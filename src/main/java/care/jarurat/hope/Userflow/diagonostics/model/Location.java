package  care.jarurat.hope.Userflow.diagonostics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private double lat;
    private double lng;
}
