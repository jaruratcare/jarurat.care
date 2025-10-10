package care.jarurat.hope.model.financials;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Policy {
    @JsonProperty("Policy Name")
    private String policyName;
    @JsonProperty("Coverage %")
    private String coverage;
    @JsonProperty("Eligibility")
    private String eligibility;
    @JsonProperty("Income Range")
    private String incomeRange;
    @JsonProperty("Govt/Private")
    private String govtOrPrivate;
    @JsonProperty("Claim Steps")
    private String claimSteps;
    @JsonProperty("Application Link")
    private String applicationLink;
    @JsonProperty("Helpline")
    private String helpline;
    @JsonProperty("insurance type")
    private String insuranceType;
    @JsonProperty("Application Guide")
    private String applicationGuide;
    @JsonProperty("Required Docs")
    private String requiredDocs;
}
