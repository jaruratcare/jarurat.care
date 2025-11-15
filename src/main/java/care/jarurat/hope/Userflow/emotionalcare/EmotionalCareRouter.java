package care.jarurat.hope.Userflow.emotionalcare;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionalCareRouter {

    private final EmotionalCareMessageBuilder messageBuilder;
    private final EmotionalCareService emotionalService;
    private final UserService userService;

    public Object handle(User user, String input) {

        String lang = user.getLanguage() == null ? "" : user.getLanguage();
        boolean isHindi = lang.equalsIgnoreCase("hi") || lang.equalsIgnoreCase("hindi");

        String intent = user.getCurrentIntent() == null ? "" : user.getCurrentIntent();
        if (input != null) {
            String lower = input.trim().toLowerCase();

            if (lower.equals("back") || lower.equals("⬅️ वापस")) {
                user.setCurrentIntent("support_type_menu");
                userService.updateUser(user);
                return messageBuilder.buildSupportTypeMenu(isHindi);
            }

            if (lower.equals("main_menu") || lower.equals("🏠 मुख्य मेनू")) {
                user.setCurrentIntent("main_menu");
                userService.updateUser(user);
                return "main_menu";
            }
        }
        return switch (intent) {
            case "emotional_care" -> showFeelingCheckin(user, isHindi);

            case "feeling_checkin" -> handleFeelingSelection(user, input, isHindi);

            case "support_type_menu" ->
                    handleSupportSelection(user, input, isHindi);
            case "talk_to_volunteer",
                    "volunteer_call_time",
                    "volunteer_callback_time",
                    "volunteer_textchat_time" ->
                    emotionalService.handleVolunteer(user, input, isHindi);

            case "mindfulness_audio" ->
                    emotionalService.handleMindfulness(user, isHindi);

            case "emotional_helplines" ->
                    emotionalService.handleHelplines(user, isHindi);

            case "caregiving_tips" ->
                    emotionalService.handleCaregivingTips(user, input, isHindi);

            case "caregiving_tips_pdf_offer" ->
                    emotionalService.handlePdfOffer(user, input, isHindi, null);

            default ->
                    showSupportTypeMenu(user, isHindi);
        };
    }
    private Object showFeelingCheckin(User user, boolean isHindi) {
        user.setCurrentIntent("feeling_checkin");
        userService.updateUser(user);
        return messageBuilder.buildFeelingCheckInList(isHindi);
    }
    private Object handleFeelingSelection(User user, String input, boolean isHindi) {

    if (input == null)
        return messageBuilder.buildFeelingCheckInList(isHindi);
    switch (input.trim()) {
        case "feeling_anxious",
             "feeling_sad",
             "feeling_exhausted" -> {

         
            user.setMood(input);
            user.setCurrentIntent("support_type_menu");
            userService.updateUser(user);

            
            return messageBuilder.buildSupportTypeMenu(isHindi);
        }

        case "just_checking_resources" -> {
            user.setMood(null);
            user.setCurrentIntent("support_type_menu");
            userService.updateUser(user);

            return messageBuilder.buildSupportTypeMenu(isHindi);
        }

        default -> {
            return messageBuilder.buildFeelingCheckInList(isHindi);
        }
    }
}
    private Object showSupportTypeMenu(User user, boolean isHindi) {
        user.setCurrentIntent("support_type_menu");
        userService.updateUser(user);
        return messageBuilder.buildSupportTypeMenu(isHindi);
    }

    private Object handleSupportSelection(User user, String input, boolean isHindi) {

        if (input == null)
            return messageBuilder.buildSupportTypeMenu(isHindi);

        switch (input.trim()) {

            case "talk_to_volunteer" -> {
                user.setCurrentIntent("talk_to_volunteer");
                userService.updateUser(user);
                return messageBuilder.buildVolunteerPrompt(isHindi);
            }

            case "mindfulness_audio" -> {
                user.setCurrentIntent("mindfulness_audio");
                userService.updateUser(user);
                return emotionalService.handleMindfulness(user, isHindi);
            }

            case "caregiving_tips" -> {
                user.setCurrentIntent("caregiving_tips");
                userService.updateUser(user);
                return emotionalService.handleCaregivingTips(user, input, isHindi);
            }

            case "emotional_helplines" -> {
                user.setCurrentIntent("emotional_helplines");
                userService.updateUser(user);
                return emotionalService.handleHelplines(user, isHindi);
            }

            default -> {
                return messageBuilder.buildSupportTypeMenu(isHindi);
            }
        }
    }
}
