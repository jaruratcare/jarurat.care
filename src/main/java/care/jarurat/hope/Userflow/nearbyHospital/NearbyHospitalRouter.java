package care.jarurat.hope.Userflow.nearbyHospital;

import care.jarurat.hope.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NearbyHospitalRouter {

    private final NearbyHospitalHandler handler;

    public Object handle(User user, String input) {
        switch (user.getCurrentIntent()) {
            case "nearby_hospitals":
            case "awaiting_location":
            case "awaiting_hospital_type":
            case "hospital_pdf_offer":
                return handler.handle(user, input);
            default:
                return null;
        }
    }
}
