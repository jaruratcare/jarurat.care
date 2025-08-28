package care.jarurat.hope.Userflow.nearbyHospital;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import care.jarurat.hope.util.PdfGeneratorUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NearbyHospitalHandler {

    private final NearbyHospitalService hospitalService;
    private final UserService userService;
    private final NearbyHospitalMessageBuilder messageBuilder;

    public Object handle(User user, String input) {
        boolean isHindi = "hi".equalsIgnoreCase(user.getLanguage()) || "hindi".equalsIgnoreCase(user.getLanguage());

        return switch (user.getCurrentIntent()) {
            case "nearby_hospitals" -> handleNearbyHospitals(user, isHindi);
            case "awaiting_location" -> handleLocation(user, input, isHindi);
            case "awaiting_hospital_type" -> handleHospitalType(user, input, isHindi);
            case "hospital_pdf_offer" -> handlePdfOffer(user, input, isHindi);
            default -> null;
        };
    }

    private Object handleNearbyHospitals(User user, boolean isHindi) {
        user.setCurrentIntent("awaiting_location");
        userService.updateUser(user);
        return messageBuilder.askLocation(isHindi); // Returns String
    }

    private Object handleLocation(User user, String input, boolean isHindi) {
        try {
            String[] parts = input.split(",");
            double lat = Double.parseDouble(parts[0].trim());
            double lng = Double.parseDouble(parts[1].trim());

            user.setLatitude(lat);
            user.setLongitude(lng);
            user.setCurrentIntent("awaiting_hospital_type");
            userService.updateUser(user);

            return messageBuilder.chooseHospitalType(isHindi); // Returns InteractiveMessage
        } catch (Exception e) {
            log.error("Invalid location input: {}", input, e);
            return messageBuilder.invalidLocationFormat(isHindi); // Returns String
        }
    }

    private Object handleHospitalType(User user, String input, boolean isHindi) {
        if (user.getLatitude() == null || user.getLongitude() == null) {
            user.setCurrentIntent("nearby_hospitals");
            userService.updateUser(user);
            return messageBuilder.locationNotFound(isHindi); // Returns String
        }

        String type;
        switch (input.trim()) {
            case "1": case "Government": type = "government"; break;
            case "2": case "Private": type = "private"; break;
            case "3": case "Any": type = "any"; break;
            default:
                return messageBuilder.invalidChoice(isHindi); // Returns String
        }

        List<Hospital> hospitals = hospitalService.getTopHospitals(user.getLatitude(), user.getLongitude(), type, 5);

        if (hospitals.isEmpty()) return messageBuilder.noHospitalsFound(type, isHindi); // Returns InteractiveMessage

        user.setCurrentIntent("hospital_pdf_offer");
        userService.updateUser(user);

        return messageBuilder.hospitalList(hospitals, isHindi); // Returns InteractiveMessage
    }

    private Object handlePdfOffer(User user, String input, boolean isHindi) {
        try {
            if ("yes_pdf".equalsIgnoreCase(input)) {
                List<Hospital> hospitals = hospitalService.getTopHospitals(user.getLatitude(), user.getLongitude(), "any", 5);
                String pdfUrl = PdfGeneratorUploader.generateAndUploadPdf(messageBuilder.buildHospitalText(hospitals), user.getPhone());

                user.setCurrentIntent("main_menu");
                userService.updateUser(user);

                return messageBuilder.pdfReady(pdfUrl, isHindi); // Returns InteractiveMessage
            } else if ("no_pdf".equalsIgnoreCase(input)) {
                user.setCurrentIntent("main_menu");
                userService.updateUser(user);
                return messageBuilder.pdfDeclined(isHindi); // Returns InteractiveMessage
            }

            return messageBuilder.askPdfDownload(isHindi); // Returns InteractiveMessage
        } catch (Exception e) {
            log.error("Error generating PDF for user {}: {}", user.getPhone(), e.getMessage(), e);
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return messageBuilder.pdfError(isHindi); // Returns InteractiveMessage
        }
    }
}
