package care.jarurat.hope.model.financials;

import lombok.Data;

@Data
public class NgoAndTrust {
     private String ngoId;
    private String ngoName;
    private String city;
    private String address;

    private String contactPhone;
    private String contactEmail;

    private String servicesOffered;
    private String state;
    private String type;
    private String website;
}
