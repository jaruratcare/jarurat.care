package care.jarurat.hope.Userflow.nearbyHospital;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NearbyHospitalService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${google.places.api.key}")
    private String googleApiKey;

    // Return only top 5 hospitals
    public List<Hospital> getTopHospitals(double latitude, double longitude, String type, int limit) {
        List<Hospital> hospitals = getNearbyHospitals(latitude, longitude, type);
        return hospitals.stream().limit(5).toList(); // Always return only 5
    }

    public List<Hospital> getNearbyHospitals(double latitude, double longitude, String type) {
        List<Hospital> hospitals = new ArrayList<>();

        try {
            String url = String.format(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=80000&type=hospital&keyword=cancer&key=%s",
                latitude, longitude, googleApiKey
            );

            log.info("Google API Key: {}", googleApiKey);
            log.info("Google Places Query URL: {}", url);

            String response = restTemplate.getForObject(url, String.class);
            JsonNode results = objectMapper.readTree(response).get("results");

            if (results == null || !results.isArray()) {
                log.warn("⚠️ No results returned from Google API.");
                return hospitals;
            }

            for (JsonNode result : results) {

                String placeId = result.has("place_id") ? result.get("place_id").asText() : null;
                String name = result.has("name") ? result.get("name").asText() : "Unknown Hospital";
                String address = result.has("vicinity") ? result.get("vicinity").asText() : "No address available";

                double lat = result.get("geometry").get("location").get("lat").asDouble();
                double lon = result.get("geometry").get("location").get("lng").asDouble();

                // Type filter
                if ("government".equalsIgnoreCase(type) && !name.toLowerCase().contains("government")) continue;
                if ("private".equalsIgnoreCase(type) && name.toLowerCase().contains("government")) continue;

                String phone = placeId != null ? fetchPhoneNumber(placeId) : "Not available";
                double distance = haversine(latitude, longitude, lat, lon);

                String mapsLink = String.format("https://www.google.com/maps/search/?api=1&query=%f,%f", lat, lon);

                hospitals.add(new Hospital(name, address, lat, lon, distance, phone, mapsLink));
            }

            // Sort by distance
            hospitals.sort(Comparator.comparingDouble(Hospital::getDistance));

        } catch (Exception e) {
            log.error("❌ Error fetching hospitals: {}", e.getMessage());
        }

        return hospitals;
    }

    private String fetchPhoneNumber(String placeId) {
        try {
            String url = String.format(
                "https://maps.googleapis.com/maps/api/place/details/json?place_id=%s&fields=formatted_phone_number&key=%s",
                placeId, googleApiKey
            );

            JsonNode result = objectMapper.readTree(restTemplate.getForObject(url, String.class)).get("result");

            if (result != null && result.has("formatted_phone_number")) {
                return result.get("formatted_phone_number").asText();
            }
        } catch (Exception ex) {
            log.warn("⚠️ Could not fetch phone number for placeId {}: {}", placeId, ex.getMessage());
        }
        return "Not available";
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}
