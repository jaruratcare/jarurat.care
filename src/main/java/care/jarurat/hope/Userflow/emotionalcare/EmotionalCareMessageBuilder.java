package care.jarurat.hope.Userflow.emotionalcare;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Component
public class EmotionalCareMessageBuilder {
    public ListMessage buildSupportTypeMenu(boolean isHindi) {
        return ListMessage.builder()
                .header(isHindi ? "सहयोग विकल्प" : "Support Options")
                .body(isHindi
                        ? "🤝 *आप अभी किस प्रकार का सहयोग चाहते हैं?*"
                        : "🤝 *What kind of support would you like right now?*")
                .buttonText(isHindi ? "विकल्प देखें" : "View Options")
                .sections(Collections.singletonList(
                        ListMessage.Section.builder()
                                .title(isHindi ? "विकल्प चुनें" : "Choose an option")
                                .rows(Arrays.asList(
                                        ListMessage.Row.builder().id("talk_to_volunteer")
                                                .title(isHindi ? "स्वयंसेवक से बात करें" : "Talk to Volunteer").build(),
                                        ListMessage.Row.builder().id("mindfulness_audio")
                                                .title(isHindi ? "माइंडफुलनेस ऑडियो" : "Mindfulness Audio").build(),
                                        ListMessage.Row.builder().id("caregiving_tips")
                                                .title(isHindi ? "देखभाल गाइड" : "Care Guide").build(),
                                        ListMessage.Row.builder().id("emotional_helplines")
                                                .title(isHindi ? "हेल्पलाइन" : "Helplines").build(),
                                        ListMessage.Row.builder().id("back")
                                                .title(isHindi ? "वापस" : "Back").build(),
                                        ListMessage.Row.builder().id("main_menu")
                                                .title(isHindi ? "मुख्य मेनू" : "Main Menu").build()
                                ))
                                .build()
                ))
                .build();
    }
    public ListMessage buildVolunteerPrompt(boolean isHindi) {

        String body = isHindi
                ? "🙋 *हम आपको एक प्रशिक्षित स्वयंसेवक से जोड़ सकते हैं।*\n\nआप किस माध्यम से बात करना चाहेंगे?"
                : "🙋 *We can connect you with a trained volunteer.*\n\nHow would you prefer to talk?";

        return ListMessage.builder()
                .header(isHindi ? "संपर्क विकल्प" : "Contact Options")
                .body(body)
                .buttonText(isHindi ? "विकल्प देखें" : "View Options")
                .sections(Collections.singletonList(
                        ListMessage.Section.builder()
                                .title(isHindi ? "एक विकल्प चुनें" : "Choose an option")
                                .rows(Arrays.asList(
                                        ListMessage.Row.builder().id("text_volunteer")
                                                .title(isHindi ? "टेक्स्ट चैट" : "Text Chat").build(),
                                        ListMessage.Row.builder().id("call_volunteer")
                                                .title(isHindi ? "कॉल बुक करें" : "Book a Call").build(),
                                        ListMessage.Row.builder().id("callback")
                                                .title(isHindi ? "कॉल बैक शेड्यूल" : "Schedule Callback").build(),
                                        ListMessage.Row.builder().id("back")
                                                .title(isHindi ? "वापस" : "Back").build(),
                                        ListMessage.Row.builder().id("main_menu")
                                                .title(isHindi ? "मुख्य मेनू" : "Main Menu").build()
                                ))
                                .build()
                ))
                .build();
    }
    public InteractiveMessage buildMindfulnessAudio(boolean isHindi, String text) {
        return InteractiveMessage.builder()
                .body(text)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder()
                                .id("back")
                                .title(isHindi ? "⬅️ वापस" : "⬅️ Back").build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(isHindi ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                ))
                .build();
    }
    public InteractiveMessage buildCaregivingTips(boolean isHindi, String text) {
        return InteractiveMessage.builder()
                .body(text)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder()
                                .id("yes_pdf")
                                .title(isHindi ? "📄 पीडीएफ डाउनलोड" : "📄 Download PDF").build(),
                        InteractiveMessage.Button.builder()
                                .id("back")
                                .title(isHindi ? "⬅️ वापस" : "⬅️ Back").build(),
                                InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(isHindi ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                ))
                .build();
    }
    public InteractiveMessage buildHelplines(boolean isHindi, String text) {
        return InteractiveMessage.builder()
                .body(text)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder()
                                .id("back")
                                .title(isHindi ? "⬅️ वापस" : "⬅️ Back").build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(isHindi ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                ))
                .build();
    }
    public InteractiveMessage buildVolunteerTimeChoices(boolean isHindi) {

        String body = isHindi
                ? "⏰ *कृपया आज के लिए एक समय चुनें:*\n\nहम आपका कॉल/कॉल बैक निर्धारित कर देंगे।"
                : "⏰ *Please choose a time for today:*\n\nWe will schedule your call/callback.";

        return InteractiveMessage.builder()
                .body(body)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder()
                                .id("today_10am")
                                .title(isHindi ? "🕙 सुबह 10 बजे" : "🕙 10:00 AM").build(),
                        InteractiveMessage.Button.builder()
                                .id("today_2pm")
                                .title(isHindi ? "🕑 दोपहर 2 बजे" : "🕑 2:00 PM").build(),
                        InteractiveMessage.Button.builder()
                                .id("today_6pm")
                                .title(isHindi ? "🕕 शाम 6 बजे" : "🕕 6:00 PM").build()
                ))
                .build();
    }
    public InteractiveMessage buildCheckInPrompt(boolean isHindi) {
        return InteractiveMessage.builder()
                .body(isHindi
                        ? "📅 *क्या आप चाहते हैं कि हम हर 3 दिन में आपसे संपर्क करें?*"
                        : "📅 *Would you like us to check in every 3 days?*")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder()
                                .id("yes_checkin")
                                .title(isHindi ? "✅ हाँ" : "✅ Yes").build(),
                        InteractiveMessage.Button.builder()
                                .id("no_checkin")
                                .title(isHindi ? "❌ नहीं धन्यवाद" : "❌ No, thanks").build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(isHindi ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                ))
                .build();
    }
  public ListMessage buildFeelingCheckInList(boolean isHindi) {

    return ListMessage.builder()
            .header(isHindi ? "कैसा महसूस कर रहे हैं?" : "How are you feeling?")
            .body(isHindi
                    ? "💛 *कृपया चुनें कि आप अभी कैसा महसूस कर रहे हैं:*"
                    : "💛 *Please choose how you're feeling right now:*")
            .buttonText(isHindi ? "विकल्प देखें" : "View Options")
            .sections(Collections.singletonList(
                    ListMessage.Section.builder()
                            .title(isHindi ? "एक विकल्प चुनें" : "Choose one option")
                            .rows(Arrays.asList(

                                    ListMessage.Row.builder()
                                            .id("feeling_anxious")
                                            .title(isHindi ? "😟 चिंतित" : "😟 Anxious")
                                            .build(),

                                    ListMessage.Row.builder()
                                            .id("feeling_sad")
                                            .title(isHindi ? "😔 उदास" : "😔 Sad")
                                            .build(),

                                    ListMessage.Row.builder()
                                            .id("feeling_exhausted")
                                            .title(isHindi ? "🥱 थका हुआ" : "🥱 Tired")
                                            .build(),

                                    ListMessage.Row.builder()
                                            .id("just_checking_resources")
                                            .title(isHindi ? "📚 संसाधन देख रहा" : "📚 Resources Only")
                                            .build(),
                                           ListMessage.Row.builder()
                                            .id("main_menu")
                                            .title(isHindi ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                            .build()
                            ))
                            .build()
            ))
            .build();
}

}
