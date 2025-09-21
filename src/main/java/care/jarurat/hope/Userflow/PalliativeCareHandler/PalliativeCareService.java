package care.jarurat.hope.Userflow.PalliativeCareHandler;

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
public class PalliativeCareService {

    private final Firestore firestore;

    // Fetch by city + care type
    public List<PalliativeCareFacility> getFacilitiesByCityAndService(String city, String serviceType) {
        List<PalliativeCareFacility> facilities = new ArrayList<>();
        try {
            String normalizedCity = city.trim();
            String normalizedService = serviceType.trim().toLowerCase();

            log.info("Fetching palliative care facilities for city: '{}' and service: '{}'", normalizedCity, normalizedService);

            CollectionReference centersRef = firestore
                    .collection("palliative_centers")
                    .document(normalizedCity)
                    .collection("centers");

            QuerySnapshot snapshotAll = centersRef.get().get();
            log.info("Total documents fetched: {}", snapshotAll.size());

            for (QueryDocumentSnapshot doc : snapshotAll.getDocuments()) {
                log.info("Document ID: {}", doc.getId());
                doc.getData().forEach((k, v) -> log.info(" - {} = {}", k, v));

                String docType = doc.getString("type");
                if (docType != null) docType = docType.trim();

                // SAFELY fetch services field
                Object serviceObj = doc.get("services");
                String docService = serviceObj != null ? serviceObj.toString().trim().toLowerCase() : null;

                if (docService == null) {
                    log.warn("?? Document '{}' has null services field, skipping.", doc.getId());
                    continue;
                }

                // Filter by serviceType
                if (docService.contains(normalizedService)) {
                    PalliativeCareFacility facility = new PalliativeCareFacility(
                            doc.getString("name"),
                            doc.getString("location"),
                            doc.getString("phone"),
                            doc.getString("map_link"),
                            docType,
                            docService // store service
                    );
                    facilities.add(facility);
                    log.info("✅ Added facility: {}", facility);
                }
            }

            log.info("Total facilities after filtering: {}", facilities.size());

        } catch (Exception e) {
            log.error("❌ Error fetching palliative facilities for city {}: {}", city, e.getMessage(), e);
        }

        return facilities;
    }
}
