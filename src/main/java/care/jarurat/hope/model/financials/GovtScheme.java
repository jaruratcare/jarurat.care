package care.jarurat.hope.model.financials;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GovtScheme {
    @JsonProperty("Scheme Name")
    private String schemeName;
    @JsonProperty("Coverage")
    private String coverage;
    @JsonProperty("Eligibility")
    private String eligibility;
    @JsonProperty("Income Range")
    private String incomeRange;
    @JsonProperty("Claim Steps")
    private String claimSteps;
    @JsonProperty("Application Link")
    private String applicationLink;
    @JsonProperty("Helpline")
    private String helpline;
    @JsonProperty("scheme type")
    private String schemeType;
    @JsonProperty("Hospitals")
    private List<Map<String, Object>> hospitals;
}
