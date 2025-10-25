package care.jarurat.hope.Userflow.nearbyHospital;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import care.jarurat.hope.util.PdfGeneratorUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class NearbyHospitalHandler {

    private final NearbyHospitalService hospitalService;
    private final UserService userService;
    private final NearbyHospitalMessageBuilder messageBuilder;

    private final Map<String, List<InteractiveMessage>> userHospitalChunks = new ConcurrentHashMap<>();
    private final Map<String, Integer> userCurrentChunkIndex = new ConcurrentHashMap<>();

    public Object handle(User user, String input) {
        boolean isHindi = "hi".equalsIgnoreCase(user.getLanguage()) || "hindi".equalsIgnoreCase(user.getLanguage());

        return switch (user.getCurrentIntent()) {
            case "nearby_hospitals" -> handleNearbyHospitals(user, isHindi);
            case "awaiting_location" -> handleLocation(user, input, isHindi);
            case "awaiting_hospital_type" -> handleHospitalType(user, input, isHindi);
            case "hospital_pdf_offer",
                 "next_hospital_chunk" -> handlePdfOfferOrNext(user, input, isHindi);
            default -> null;
        };
    }

    private Object handleNearbyHospitals(User user, boolean isHindi) {
        user.setCurrentIntent("awaiting_location");
        userService.updateUser(user);
        return messageBuilder.askLocation(isHindi);
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

            return messageBuilder.chooseHospitalType(isHindi);
        } catch (Exception e) {
            log.error("Invalid location input: {}", input, e);
            return messageBuilder.invalidLocationFormat(isHindi);
        }
    }

    private Object handleHospitalType(User user, String input, boolean isHindi) {
        if (user.getLatitude() == null || user.getLongitude() == null) {
            user.setCurrentIntent("nearby_hospitals");
            userService.updateUser(user);
            return messageBuilder.locationNotFound(isHindi);
        }

        String type;
        switch (input.trim()) {
            case "1", "Government" -> type = "government";
            case "2", "Private" -> type = "private";
            case "3", "Any" -> type = "any";
            default -> {
                return messageBuilder.invalidChoice(isHindi);
            }
        }

        List<Hospital> hospitals = hospitalService.getTopHospitals(user.getLatitude(), user.getLongitude(), type, 20);
        if (hospitals.isEmpty()) return messageBuilder.noHospitalsFound(type, isHindi);

        String fullText = messageBuilder.buildHospitalText(hospitals);
        List<String> chunks = messageBuilder.splitMessage(fullText, 1024);

        List<InteractiveMessage> messages = new ArrayList<>();
        for (int i = 0; i < chunks.size(); i++) {
            List<InteractiveMessage.Button> buttons;
            if (i < chunks.size() - 1) {
                buttons = List.of(
                    InteractiveMessage.Button.builder()
                            .id("next_hospital_chunk")
                            .title(isHindi ? "अगला" : "Next") // WhatsApp safe
                            .build()
                );
            } else {
                buttons = messageBuilder.pdfButtons(isHindi);
            }

            messages.add(InteractiveMessage.builder()
                    .body(chunks.get(i))
                    .buttons(buttons)
                    .build());
        }

        userHospitalChunks.put(user.getPhone(), messages);
        userCurrentChunkIndex.put(user.getPhone(), 0);

        // ✅ set intent so next click is routed
        user.setCurrentIntent("next_hospital_chunk");
        userService.updateUser(user);

        return messages.get(0);
    }

    private Object handlePdfOfferOrNext(User user, String input, boolean isHindi) {
        List<InteractiveMessage> messages = userHospitalChunks.get(user.getPhone());
        if (messages == null || messages.isEmpty()) return messageBuilder.noHospitalsFound("any", isHindi);

        // If user clicks Next
        if ("next_hospital_chunk".equalsIgnoreCase(input)) {
            int idx = userCurrentChunkIndex.getOrDefault(user.getPhone(), 0) + 1;
            if (idx >= messages.size()) idx = messages.size() - 1;
            userCurrentChunkIndex.put(user.getPhone(), idx);

            return messages.get(idx);
        }

        // Else handle PDF offer
        try {
            if ("yes_pdf".equalsIgnoreCase(input)) {
                StringBuilder sb = new StringBuilder();
                for (InteractiveMessage msg : messages) sb.append(msg.getBody()).append("\n\n");
                String pdfUrl = PdfGeneratorUploader.generateAndUploadPdf(sb.toString(), user.getPhone());
                userHospitalChunks.remove(user.getPhone());
                userCurrentChunkIndex.remove(user.getPhone());
                user.setCurrentIntent("nearby_hospitals");
                userService.updateUser(user);
                return messageBuilder.pdfReady(pdfUrl, isHindi);

            } else if ("no_pdf".equalsIgnoreCase(input)) {
                userHospitalChunks.remove(user.getPhone());
                userCurrentChunkIndex.remove(user.getPhone());
                user.setCurrentIntent("nearby_hospitals");
                userService.updateUser(user);
                return messageBuilder.pdfDeclined(isHindi);

            } else {
                return messages.get(userCurrentChunkIndex.getOrDefault(user.getPhone(), 0));
            }
        } catch (Exception e) {
            log.error("PDF generation error for user {}: {}", user.getPhone(), e.getMessage(), e);
            return messageBuilder.pdfError(isHindi);
        }
    }
}
