package care.jarurat.hope.Userflow.diagonostics.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    private String name;
    private String vicinity;
    private Geometry geometry;
      private Double rating;
    private Integer userRatingsTotal;

    @JsonProperty("formatted_address")
private String formattedAddress;
}
