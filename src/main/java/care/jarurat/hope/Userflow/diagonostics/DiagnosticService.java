package care.jarurat.hope.Userflow.diagonostics;

import care.jarurat.hope.Userflow.diagonostics.model.PlacesApiResponse;
import care.jarurat.hope.model.InteractiveMessage;
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

    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";

    public Object findNearbyLabs(User user, String locationQuery, boolean isEnglish) {
        try {
            String query = "diagnostic labs in " + locationQuery;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PLACES_API_URL)
                    .queryParam("query", query)
                    .queryParam("key", apiKey);

            PlacesApiResponse response = restTemplate.getForObject(builder.toUriString(), PlacesApiResponse.class);

            user.setCurrentIntent("main_menu");
            userService.updateUser(user);

            if (response != null && "OK".equals(response.getStatus())
                    && response.getResults() != null && !response.getResults().isEmpty()) {
                String labsText = response.getResults()
                        .stream()
                        .limit(3)
                        .map(place -> {
                            String name = place.getName() != null ? place.getName() : "Unnamed Lab";
                            String address = (place.getFormattedAddress() != null && !place.getFormattedAddress().isEmpty())
                                    ? place.getFormattedAddress()
                                    : (place.getVicinity() != null ? place.getVicinity() : "Address not available");

                            String encodedAddress = URLEncoder.encode(name + ", " + address, StandardCharsets.UTF_8);
                            String mapsUrl = "https://www.google.com/maps/search/?api=1&query=" + encodedAddress;

                            return "🏥 " + name + "\n"
                                    + "📍 " + address + "\n"
                                    + "🔗 " + mapsUrl;
                        })
                        .collect(Collectors.joining("\n\n"));

                String header = isEnglish
                        ? "Here are some labs I found near " + locationQuery + ":\n\n"
                        : locationQuery + " के पास मिली लैब्स:\n\n";

                String body = header + labsText;

                return InteractiveMessage.builder()
                        .body(body)
                        .buttons(List.of(
                                InteractiveMessage.Button.builder()
                                        .id("main_menu")
                                        .title(isEnglish ? "🏠 Main Menu" : "🏠 मुख्य मेनू")
                                        .build()
                        ))
                        .build();
            }
            return InteractiveMessage.builder()
                    .body(isEnglish 
                            ? "Sorry, I couldn't find any diagnostic labs near that location."
                            : "क्षमा करें, वहाँ पास कोई लैब नहीं मिली।")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("main_menu")
                                    .title(isEnglish ? "🏠 Main Menu" : "🏠 मुख्य मेनू")
                                    .build()
                    ))
                    .build();

        } catch (Exception e) {

            log.error("Diagnostic API error", e);

            return InteractiveMessage.builder()
                    .body(isEnglish 
                            ? "Something went wrong while searching. Please try again later."
                            : "कुछ त्रुटि हुई। कृपया बाद में प्रयास करें।")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("main_menu")
                                    .title(isEnglish ? "🏠 Main Menu" : "🏠 मुख्य मेनू")
                                    .build()
                    ))
                    .build();
        }
    }
}
