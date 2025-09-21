package care.jarurat.hope.Userflow.PalliativeCareHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PalliativeCareFacility {
    private String name;
    private String location;   
    private String phone;
    private String mapsLink;   
    private String type;
    private String services;
}
