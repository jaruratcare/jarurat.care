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

            if (response != null && "OK".equals(response.getStatus()) && response.getResults() != null && !response.getResults().isEmpty()) {
                // Short body text for WhatsApp
                String body = isEnglish
                        ? "Here are some labs I found near " + locationQuery + ":"
                        : "यहाँ " + locationQuery + " के पास कुछ लैब हैं जो मुझे मिलीं:";

                // Prepare up to 3 buttons for WhatsApp
                List<InteractiveMessage.Button> buttons = response.getResults().stream()
                        .limit(3) // WhatsApp allows max 3 buttons
                        .map(place -> {
                            String name = place.getName() != null ? place.getName() : "Lab";
                            String address = (place.getFormattedAddress() != null && !place.getFormattedAddress().isEmpty())
                                    ? place.getFormattedAddress()
                                    : (place.getVicinity() != null ? place.getVicinity() : "Address not available");

                            String encodedAddress = URLEncoder.encode(name + ", " + address, StandardCharsets.UTF_8);
                            String googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=" + encodedAddress;

                            return InteractiveMessage.Button.builder()
                                    .type("url")
                                    .title(name.length() > 20 ? name.substring(0, 17) + "..." : name) 
                                    .url(googleMapsUrl)
                                    .build();
                        })
                        .collect(Collectors.toList());

                user.setCurrentIntent("main_menu");
                userService.updateUser(user);

                return InteractiveMessage.builder()
                        .body(body)
                        .buttons(buttons)
                        .build();
            } else {
                user.setCurrentIntent("main_menu");
                userService.updateUser(user);
                return isEnglish
                        ? "Sorry, I couldn't find any diagnostic labs near that location."
                        : "क्षमा करें, मुझे उस स्थान के पास कोई डायग्नोस्टिक लैब नहीं मिली।";
            }

        } catch (Exception e) {
            log.error("Error calling Google Places API", e);
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return isEnglish
                    ? "Sorry, something went wrong while searching for labs."
                    : "क्षमा करें, लैब खोजते समय कुछ गड़बड़ हो गई।";
        }
    }
}
