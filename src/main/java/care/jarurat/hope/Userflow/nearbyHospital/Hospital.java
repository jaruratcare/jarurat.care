package care.jarurat.hope.Userflow.nearbyHospital;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hospital {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double distance;   
    private String phone;
    private String mapsLink;
}
