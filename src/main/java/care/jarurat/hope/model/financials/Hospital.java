package care.jarurat.hope.model.financials;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Hospital {
    @JsonProperty("Policy Name")
    private String policyName;
    @JsonProperty("Hospital Name")
    private String hospitalName;
    @JsonProperty("City")
    private String city;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("Contact")
    private String contact;
}
