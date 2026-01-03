package care.jarurat.hope.Userflow.emotionalcare;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.OpenAiServiceWrapper;
import care.jarurat.hope.service.UserService;
import care.jarurat.hope.util.PdfGeneratorUploader1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionalCareService {

    private final OpenAiServiceWrapper openAiService;
    private final EmotionalCareMessageBuilder messageBuilder;
    private final UserService userService;
    private final VolunterService1 volunteerService;
    private final Map<String, String> caregivingContentStore = new ConcurrentHashMap<>();

    public Object handleHelplines(User user, boolean isHindi) {
        String text = isHindi
                ? "📞 *भारत में प्रमुख मानसिक स्वास्थ्य हेल्पलाइन नंबर*\n\n🩵 iCall – 9152987821 (सोम–शनि, 10AM–8PM)\n💚 AASRA – 91-9820466726 (24x7)\n💛 वंदेर्वाला – 9999 666 555 (24x7)\n💙 NIMHANS – 080-46110007 (24x7)\n💜 Snehi – 9582208181 (10AM–10PM)"
                : "📞 *Top Mental Health Helplines (India)*\n\n🩵 iCall – 9152987821 (Mon–Sat, 10AM–8PM)\n💚 AASRA – 91-9820466726 (24x7)\n💛 Vandrevala – 9999 666 555 (24x7)\n💙 NIMHANS – 080-46110007 (24x7)\n💜 Snehi – 9582208181 (10AM–10PM)";
        return messageBuilder.buildHelplines(isHindi, text);
    }

public Object handleMindfulness(User user, boolean isHindi) {
    log.info("🧘 MINDFULNESS CALLED - Hindi: {}, User: {}", isHindi, user.getPhone());
    
    // 10 Verified Hindi Meditation Videos
    String[] hindiVideos = {
        " 10 मिनट ध्यान\nhttps://youtu.be/8iaKLg114DQ",
        " शांति ध्यान\nhttps://youtu.be/YWDRFZFCrGE", 
        " विश्राम\nhttps://youtu.be/MHx0rwHE7gg",
        " बौद्ध ध्यान\nhttps://youtu.be/p8p380NmVak",
        " फोकस ध्यान\nhttps://youtu.be/uDuPL6wfWvQ",
        " तनाव मुक्ति\nhttps://youtu.be/dD63eGlJd2A",
        " BK शिवानी\nhttps://youtu.be/XnT_cOq_Ba8",
        " चिंता मुक्ति\nhttps://youtu.be/KNcA2PpmZ0I",
        " ऊर्जा ध्यान\nhttps://youtu.be/6WQJOxSViGM",
        " हीलिंग\nhttps://youtu.be/rb5z7EKFOTc"
    };
    
    String[] englishVideos = {
        " 10min Hindi Calm\nhttps://youtu.be/8iaKLg114DQ",
        " Peace Meditation\nhttps://youtu.be/YWDRFZFCrGE",
        " Relaxation Hindi\nhttps://youtu.be/MHx0rwHE7gg", 
        " Vipassana Hindi\nhttps://youtu.be/p8p380NmVak",
        " Focus Meditation\nhttps://youtu.be/uDuPL6wfWvQ",
        " Stress Relief\nhttps://youtu.be/dD63eGlJd2A",
        " BK Shivani\nhttps://youtu.be/XnT_cOq_Ba8",
        " Anxiety Relief\nhttps://youtu.be/KNcA2PpmZ0I",
        " Energy Boost\nhttps://youtu.be/6WQJOxSViGM",
        " Healing Music\nhttps://youtu.be/rb5z7EKFOTc"
    };
    
    // Randomly select 5 from 10
    String[] allVideos = isHindi ? hindiVideos : englishVideos;
    java.util.Collections.shuffle(java.util.Arrays.asList(allVideos));
    String[] selected = java.util.Arrays.copyOfRange(allVideos, 0, 5);
    
    String result = (isHindi ? "🎥 *5 रैंडम ध्यान वीडियो*\n\n" : "🎥 *5 Random Meditation Videos*\n\n") 
        + String.join("\n\n", selected);
    
    log.info("🧘 SENDING ({} chars): {}", result.length(), result);
    return messageBuilder.buildMindfulnessAudio(isHindi, result);
}



    public Object handleVolunteer(User user, String input, boolean isHindi) {
        String intent = user.getCurrentIntent() == null ? "" : user.getCurrentIntent();
        if (input == null || input.isBlank()) {
            user.setCurrentIntent("talk_to_volunteer");
            userService.updateUser(user);
            return messageBuilder.buildVolunteerPrompt(isHindi);
        }
        switch (input.trim()) {
            case "text_volunteer" -> {
                user.setCurrentIntent("volunteer_textchat_time");
                userService.updateUser(user);
                return messageBuilder.buildVolunteerTimeChoices(isHindi);
            }
            case "call_volunteer" -> {
                user.setCurrentIntent("volunteer_call_time");
                userService.updateUser(user);
                return messageBuilder.buildVolunteerTimeChoices(isHindi);
            }
            case "callback" -> {
                user.setCurrentIntent("volunteer_callback_time");
                userService.updateUser(user);
                return messageBuilder.buildVolunteerTimeChoices(isHindi);
            }
        }
        if (intent.equals("volunteer_call_time")
                || intent.equals("volunteer_callback_time")
                || intent.equals("volunteer_textchat_time")) {
            LocalDate today = LocalDate.now();
            LocalTime selectedTime;
            switch (input) {
                case "today_10am" -> selectedTime = LocalTime.of(10, 0);
                case "today_2pm" -> selectedTime = LocalTime.of(14, 0);
                case "today_6pm" -> selectedTime = LocalTime.of(18, 0);
                default -> {
                    return messageBuilder.buildVolunteerTimeChoices(isHindi);
                }
            }
            LocalDateTime finalDateTime = LocalDateTime.of(today, selectedTime);
            String mode;
            if (intent.equals("volunteer_call_time")) mode = "Call";
            else if (intent.equals("volunteer_callback_time")) mode = "Callback";
            else mode = "TextChat";
            user.setCurrentIntent("emotional_care");
            userService.updateUser(user);
            return volunteerService.bookAppointment(
                    user.getName() == null ? "User" : user.getName(),
                    user.getPhone(),
                    mode,
                    finalDateTime.toString(),
                    user.getLanguage()
            );
        }
        return messageBuilder.buildVolunteerPrompt(isHindi);
    }

    public Object handleCaregivingTips(User user, String input, boolean isHindi) {
        try {
            String mood = user.getMood() != null ? user.getMood() : (isHindi ? "सामान्य" : "neutral");
            String systemPrompt = """
                    You are an emotional care expert.
                    Provide 5–7 short caregiving & self-compassion tips.
                    Keep each tip concise (1–2 lines) for WhatsApp.
                    Tailor the tips according to the user's mood.
                    """;
            String userPrompt = isHindi
                    ? "यूजर का मूड: " + mood + ". उसके अनुसार हिंदी में सुझाव दें। उदाहरण: अगर मूड उदास है तो हल्की गतिविधियाँ और आत्म-सहानुभूति के टिप्स दें।"
                    : "User mood: " + mood + ". Provide English caregiving tips according to this mood. For example, if the mood is sad, suggest light activities and self-compassion tips.";
            String response = openAiService.generateResponse(systemPrompt, userPrompt, 0.8, 450);
            caregivingContentStore.put(getUserKey(user), response);
            user.setCurrentIntent("caregiving_tips_pdf_offer");
            userService.updateUser(user);
            return messageBuilder.buildCaregivingTips(isHindi, response);
        } catch (Exception e) {
            log.error("Caregiving error: {}", e.getMessage());
            return messageBuilder.buildCaregivingTips(isHindi,
                    isHindi ? "⚠️ सुझाव लोड नहीं किए जा सके।" : "⚠️ Unable to load tips.");
        }
    }

    public Object handlePdfOffer(User user, String input, boolean isHindi, String content) {
        try {
            String key = getUserKey(user);
            if (content == null || content.isBlank()) content = caregivingContentStore.get(key);
            if (content == null) return isHindi ? "⚠️ कोई सामग्री उपलब्ध नहीं है।" : "⚠️ No content available.";
            if ("yes_pdf".equalsIgnoreCase(input)) {
    String pdf = PdfGeneratorUploader1.generateAndUploadPdf(content, user.getPhone());
    caregivingContentStore.remove(key);
    user.setCurrentIntent("emotional_care"); // Reset to main flow
    userService.updateUser(user);
    return messageBuilder.buildPdfReadyMessage(isHindi, pdf); // Use new method
}

            if ("no_pdf".equalsIgnoreCase(input)) return isHindi ? "ठीक है, बिना PDF जारी रख रहे हैं।" : "Alright, continuing.";
            return messageBuilder.buildCaregivingTips(isHindi, content);
        } catch (Exception e) {
            log.error("PDF error: {}", e.getMessage());
            return isHindi ? "⚠️ PDF नहीं बन सका।" : "⚠️ Could not generate PDF.";
        }
    }

    private String getUserKey(User user) {
        return user.getPhone() != null ? user.getPhone() : String.valueOf(user.hashCode());
    }
}
