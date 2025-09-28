package care.jarurat.hope.Userflow.AccommodationFood;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationFoodService {

    private final Firestore firestore;

    // Check if hospital exists
    public boolean hospitalExists(String hospitalName) {
        try {
            CollectionReference ref = firestore.collection("accommodation_food_centers")
                    .document(hospitalName)
                    .collection("centers");
            QuerySnapshot snapshot = ref.get().get();
            return !snapshot.isEmpty();
        } catch (Exception e) {
            log.error("Error checking hospital {}: {}", hospitalName, e.getMessage());
            return false;
        }
    }

    // Fetch facilities based on selected service option
    public List<AccommodationFoodFacility> getFacilitiesByOption(String option) {
    List<AccommodationFoodFacility> facilities = new ArrayList<>();
    try {
        CollectionReference hospitalsRef = firestore.collection("accommodation_food_centers");
        QuerySnapshot hospitals = hospitalsRef.get().get();

        for (QueryDocumentSnapshot hospitalDoc : hospitals.getDocuments()) {
            CollectionReference centersRef = hospitalDoc.getReference().collection("centers");
            QuerySnapshot centersSnapshot = centersRef.get().get();

            for (QueryDocumentSnapshot doc : centersSnapshot.getDocuments()) {
                String category = doc.getString("category");
                String services = doc.getString("services");
                String food =doc.getString("food");

                boolean match = false;
                switch(option.toLowerCase()) {
                    case "stay":
                        if (services != null && services.toLowerCase().contains("stay")) match = true;
                        break;
                    case "food":
                        if (food != null && food.equalsIgnoreCase("free")) match = true;
                        break;
                    case "ngo":
                        if (category != null && category.equalsIgnoreCase("ngo")) match = true;
                        break;
                }

                if (match) {
                    facilities.add(new AccommodationFoodFacility(
                            doc.getString("name"),
                            doc.getString("location"),
                            doc.getString("phone"),
                            doc.getString("map_link"),
                            doc.getString("type"),
                            services,
                            doc.getString("price_range"),
                            category,
                            doc.getString("food"),
                            doc.getString("open_hour")
                    ));
                }
            }
        }
    } catch (Exception e) {
        log.error("Error fetching facilities: {}", e.getMessage());
    }
    return facilities;
}

}
