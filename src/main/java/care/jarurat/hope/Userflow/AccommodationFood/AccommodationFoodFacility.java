package care.jarurat.hope.Userflow.AccommodationFood;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccommodationFoodFacility {
    private String name;
    private String location;   
    private String phone;
    private String mapsLink;   
    private String type;    
    private String services;  
    private String priceRange; 
}
