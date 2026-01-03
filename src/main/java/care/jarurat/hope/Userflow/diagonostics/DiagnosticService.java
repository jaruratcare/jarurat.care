package care.jarurat.hope.Userflow.diagonostics;

import care.jarurat.hope.Userflow.diagonostics.model.Place;
import care.jarurat.hope.Userflow.diagonostics.model.PlacesApiResponse;
import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiagnosticService {

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Value("${google.places.api.key}")
    private String apiKey;

    private static final String PLACES_API_URL =
            "https://maps.googleapis.com/maps/api/place/textsearch/json";

public ListMessage findNearbyLabsList(User user, String city) {

    String query = user.getTestType() + " diagnostic lab in " + city;

    UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(PLACES_API_URL)
            .queryParam("query", query)
            .queryParam("key", apiKey);

   if (user.getLabsNextPageToken() != null) {
    try {
        Thread.sleep(2000); // REQUIRED by Google Places API
    } catch (InterruptedException ignored) {}

    builder.queryParam("pagetoken", user.getLabsNextPageToken());
}


    PlacesApiResponse response =
            restTemplate.getForObject(builder.toUriString(), PlacesApiResponse.class);

    if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
        return ListMessage.builder()
                .header("No Labs Found")
                .body("Sorry, I couldn’t find any diagnostic labs nearby.")
                .buttonText("Back")
                .build();
    }

    // Save pagination + labs
    user.setLabsNextPageToken(response.getNextPageToken());
    user.setLastLabs(response.getResults());
    userService.updateUser(user);

    // Build list rows (LIMIT 5)
   List<ListMessage.Row> rows = response.getResults().stream()
        .limit(5)
        .map(p -> {

            String title = p.getName();
            if (title.length() > 24) {
                title = title.substring(0, 21) + "...";
            }

            String description = p.getVicinity() != null ? p.getVicinity() : "Nearby";
            if (description.length() > 72) {
                description = description.substring(0, 69) + "...";
            }

            return ListMessage.Row.builder()
                    .id("LAB_" + p.getName().hashCode())
                    .title(title)
                    .description(description)
                    .build();
        })
        .toList();

    return ListMessage.builder()
            .header("Diagnostic Labs Near You")
            .body("Tap a lab to view details")
            .buttonText("View Labs")
            .sections(List.of(
                    ListMessage.Section.builder()
                            .title("Available Labs")
                            .rows(rows)
                            .build()
            ))
            .build();
}
public String buildLabDetailsText(Place place) {

    StringBuilder sb = new StringBuilder();

    sb.append("🏥 *").append(place.getName()).append("*\n\n");

    if (place.getVicinity() != null) {
        sb.append("📍 Address:\n")
          .append(place.getVicinity())
          .append("\n\n");
    }

    sb.append("🗺 Open in Google Maps:\n");
    sb.append("https://www.google.com/maps/search/?api=1&query=");
    sb.append(encode(place.getName() + " " + place.getVicinity()));

    sb.append("\n\n");
    sb.append("Type BACK to return to the lab list.");

    return sb.toString();
}

private String encode(String value) {
    return java.net.URLEncoder.encode(value, java.nio.charset.StandardCharsets.UTF_8);
}


}
