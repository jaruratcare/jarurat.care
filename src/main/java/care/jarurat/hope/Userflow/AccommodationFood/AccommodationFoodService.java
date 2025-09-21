package care.jarurat.hope.Userflow.AccommodationFood;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
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

    public List<AccommodationFoodFacility> getFacilitiesByCityAndType(String city, String helpType) {
        List<AccommodationFoodFacility> facilities = new ArrayList<>();
        try {
            String normalizedCity = city.trim();
            String normalizedHelpType = helpType.trim().toLowerCase();

            CollectionReference facilitiesRef = firestore
                    .collection("accommodation_food_centers")
                    .document(normalizedCity)
                    .collection("centers");

            QuerySnapshot snapshotAll = facilitiesRef.get().get();

            for (QueryDocumentSnapshot doc : snapshotAll.getDocuments()) {
                Object serviceObj = doc.get("services");
                String docService = serviceObj != null ? serviceObj.toString().trim().toLowerCase() : null;

                if (docService != null && docService.contains(normalizedHelpType)) {
                    AccommodationFoodFacility facility = new AccommodationFoodFacility(
                            doc.getString("name"),
                            doc.getString("location"),
                            doc.getString("phone"),
                            doc.getString("map_link"),
                            doc.getString("type"),
                            docService,
                            doc.getString("price_range")
                    );
                    facilities.add(facility);
                }
            }

        } catch (Exception e) {
            log.error("❌ Error fetching accommodation/food facilities for city {}: {}", city, e.getMessage(), e);
        }
        return facilities;
    }
}
