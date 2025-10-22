package care.jarurat.hope.Userflow.nutritionalCare;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.OpenAiServiceWrapper;
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
public class MealPlanService {

    private final OpenAiServiceWrapper openAiServiceWrapper;
    private final UserService userService;

    private final Map<String, List<String>> userWeeklyPlans = new ConcurrentHashMap<>();
    private final Map<String, Integer> userCurrentDay = new ConcurrentHashMap<>();

    // Step 1: Generate weekly plan
    public InteractiveMessage handleGenerateConfirm(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        String dietType = (user.getDietType() != null) ? user.getDietType() : "not specified";
        String eatingCondition = (user.getEatingCondition() != null) ? user.getEatingCondition() : "not specified";
        String foodPreference = (user.getFoodPreference() != null) ? user.getFoodPreference() : "not specified";
        String Diabetic=user.getDiabeticStatus() != null && !user.getFoodPreference().isEmpty() ? user.getDiabeticStatus() : "not specified";
        String symptoms = (user.getNutritionSymptoms() != null && !user.getNutritionSymptoms().isEmpty())
                ? user.getNutritionSymptoms()
                : "none";

        if (!"continue_mealplan".equalsIgnoreCase(input)) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "❌ अमान्य विकल्प। कृपया जारी रखें।"
                            : "❌ Invalid choice. Please click Continue to generate meal plan.")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("continue_mealplan")
                                    .title(lang.equals("hi") ? "➡️ जारी रखें" : "➡️ Continue")
                                    .build()
                    ))
                    .build();
        }

        String systemPrompt = "You are a nutritionist who creates healthy Indian meal plans.";
       String userPrompt = lang.equals("hi")
        ? String.format("""
            आप एक पोषण विशेषज्ञ हैं। कृपया कैंसर मरीज के लिए पूरे सप्ताह की व्यक्तिगत भारतीय भोजन योजना तैयार करें।
            आहार प्रकार: %s
            खाने की पसंद: %s
            लक्षण: %s
            डायबिटीज़: %s
            आउटपुट प्रारूप: सोमवार से रविवार (नाश्ता, दोपहर का भोजन, रात का खाना, स्नैक्स)।
            """, dietType, foodPreference, symptoms, Diabetic)
        : String.format("""
            You are a nutritionist. Create a personalized Indian meal plan for a cancer patient for the whole week.
            Diet Type: %s
            Food Preference: %s
            Symptoms: %s
            Diabetic: %s
            Output format: Monday to Sunday (Breakfast, Lunch, Dinner, Snacks).
            """, dietType, foodPreference, symptoms, Diabetic);
        String aiMealPlan = openAiServiceWrapper.generateResponse(systemPrompt, userPrompt, 0.7, 2000);
        log.info("Generated weekly plan for {}: {}", user.getPhone(), aiMealPlan);

        String[] dayMarkers = lang.equals("hi")
                ? new String[]{"सोमवार", "मंगलवार", "बुधवार", "गुरुवार", "शुक्रवार", "शनिवार", "रविवार"}
                : new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        List<String> dayPlans = new ArrayList<>();
        if (aiMealPlan == null || aiMealPlan.trim().isEmpty()) {
            for (String dayMarker : dayMarkers) {
                dayPlans.add(dayMarker + ": " + (lang.equals("hi") ? "कोई योजना उपलब्ध नहीं" : "No plan available"));
            }
        } else {
            String remainingText = aiMealPlan;
            for (int i = 0; i < dayMarkers.length; i++) {
                String currentDayMarker = dayMarkers[i];
                String nextDayMarker = (i < dayMarkers.length - 1) ? dayMarkers[i + 1] : null;

                int currentIndex = remainingText.indexOf(currentDayMarker);

                if (currentIndex >= 0) {
                    int nextIndex = (nextDayMarker != null)
                            ? remainingText.indexOf(nextDayMarker, currentIndex + currentDayMarker.length())
                            : -1;

                    String dayContent = (nextIndex >= 0)
                            ? remainingText.substring(currentIndex, nextIndex).trim()
                            : remainingText.substring(currentIndex).trim();

                    dayPlans.add(dayContent);

                    if (nextIndex >= 0) remainingText = remainingText.substring(nextIndex);
                    else break;
                } else {
                    dayPlans.add(currentDayMarker + ": " + (lang.equals("hi") ? "कोई योजना उपलब्ध नहीं" : "No plan available"));
                }
            }

            while (dayPlans.size() < dayMarkers.length) {
                dayPlans.add(dayMarkers[dayPlans.size()] + ": " + (lang.equals("hi") ? "कोई योजना उपलब्ध नहीं" : "No plan available"));
            }
        }

        userWeeklyPlans.put(user.getPhone(), dayPlans);
        userCurrentDay.put(user.getPhone(), 0);

        return createDayMessage(user.getPhone(), 0, lang);
    }

    // Step 2: Show next day's plan
    public InteractiveMessage handleNextDay(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        List<String> dayPlans = userWeeklyPlans.get(user.getPhone());

        if (dayPlans == null || dayPlans.isEmpty()) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "❌ कोई योजना नहीं मिली। कृपया 'Continue' क्लिक करें।"
                            : "❌ No plan found. Please click 'Continue'.")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("continue_mealplan")
                                    .title(lang.equals("hi") ? "➡️ जारी रखें" : "➡️ Continue")
                                    .build()
                    ))
                    .build();
        }

        int currentDay = userCurrentDay.getOrDefault(user.getPhone(), 0) + 1;
        if (currentDay >= dayPlans.size()) currentDay = dayPlans.size() - 1;
        userCurrentDay.put(user.getPhone(), currentDay);

        return createDayMessage(user.getPhone(), currentDay, lang);
    }

    // Step 3: Create day message with Back and Main Menu
    private InteractiveMessage createDayMessage(String phoneNumber, int dayIndex, String lang) {
        List<String> dayPlans = userWeeklyPlans.get(phoneNumber);

        if (dayPlans == null || dayIndex >= dayPlans.size()) {
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "❌ कोई योजना नहीं मिली।" : "❌ No plan found.")
                    .buttons(Collections.emptyList())
                    .build();
        }

        String dayText = dayPlans.get(dayIndex);
        String prefix = "📝 ";
        int maxLength = 1024 - prefix.length();
        if (dayText.length() > maxLength) dayText = dayText.substring(0, maxLength - 3) + "...";

        boolean isLastDay = (dayIndex == dayPlans.size() - 1);
        String nextButtonId = isLastDay ? "show_pdf_offer" : "day_" + (dayIndex + 1);
        String nextButtonTitle = isLastDay
                ? (lang.equals("hi") ? "➡️ जारी रखें" : "➡️ Continue")
                : (lang.equals("hi") ? "अगला दिन" : "Next day");

        return InteractiveMessage.builder()
                .body(prefix + dayText)
                .buttons(List.of(
                        InteractiveMessage.Button.builder()
                                .id(nextButtonId)
                                .title(nextButtonTitle)
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("back")
                                .title(lang.equals("hi") ? "🔙 पिछला दिन" : "🔙 Back")
                                .build(),
                        InteractiveMessage.Button.builder()
                                .id("main_menu")
                                .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                .build()
                ))
                .build();
    }

    // Step 4: Handle PDF offer with Back and Main Menu
    public InteractiveMessage handlePdfOffer(User user, String input) {
        String lang = user.getLanguage() != null ? user.getLanguage() : "en";
        List<String> weekPlan = userWeeklyPlans.get(user.getPhone());

        try {
            if (("yes_pdf".equalsIgnoreCase(input) || "pdf_download".equalsIgnoreCase(input)) && weekPlan != null && !weekPlan.isEmpty()) {
                StringBuilder content = new StringBuilder();
                String[] dayMarkers = lang.equals("hi")
                        ? new String[]{"सोमवार", "मंगलवार", "बुधवार", "गुरुवार", "शुक्रवार", "शनिवार", "रविवार"}
                        : new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

                for (int i = 0; i < dayMarkers.length; i++) {
                    content.append((i < weekPlan.size() && weekPlan.get(i) != null)
                            ? weekPlan.get(i)
                            : dayMarkers[i] + ": " + (lang.equals("hi") ? "कोई योजना उपलब्ध नहीं" : "No plan available"))
                            .append("\n\n");
                }

                String pdfUrl = PdfGeneratorUploader.generateAndUploadPdf(content.toString(), user.getPhone());
                userWeeklyPlans.remove(user.getPhone());
                userCurrentDay.remove(user.getPhone());

                user.setCurrentIntent("nutrition_step4");
                userService.saveUser(user);

                return InteractiveMessage.builder()
                        .header(lang.equals("hi") ? "📄 भोजन योजना पीडीएफ" : "📄 Meal Plan PDF")
                        .body(lang.equals("hi")
                                ? "आपकी व्यक्तिगत भोजन योजना पीडीएफ तैयार है।\nडाउनलोड करें:\n" + pdfUrl
                                : "Your personalized meal plan PDF is ready.\nDownload:\n" + pdfUrl)
                        .buttons(List.of(
                                InteractiveMessage.Button.builder()
                                        .id("back")
                                        .title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back")
                                        .build(),
                                InteractiveMessage.Button.builder()
                                        .id("main_menu")
                                        .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                        .build()
                        ))
                        .build();

            } else if ("no_pdf".equalsIgnoreCase(input)) {
                userWeeklyPlans.remove(user.getPhone());
                userCurrentDay.remove(user.getPhone());

                user.setCurrentIntent("nutrition_step4");
                userService.saveUser(user);

                return InteractiveMessage.builder()
                        .header(lang.equals("hi") ? "✅ पूर्ण" : "✅ Done")
                        .body(lang.equals("hi")
                                ? "ठीक है, नया सत्र शुरू करने के लिए 'Hi' टाइप करें।"
                                : "Okay, no problem. Type 'Hi' to start a new session.")
                        .buttons(List.of(
                                InteractiveMessage.Button.builder()
                                        .id("back")
                                        .title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back")
                                        .build(),
                                InteractiveMessage.Button.builder()
                                        .id("main_menu")
                                        .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                        .build()
                        ))
                        .build();

            } else {
                return InteractiveMessage.builder()
                        .body(lang.equals("hi")
                                ? "📄 आपकी व्यक्तिगत साप्ताहिक भोजन योजना तैयार है। पीडीएफ डाउनलोड करना चाहेंगे?"
                                : "📄 Your personalized weekly meal plan is ready. Would you like to download as PDF?")
                        .buttons(List.of(
                                InteractiveMessage.Button.builder()
                                        .id("yes_pdf")
                                        .title(lang.equals("hi") ? "📄 पीडीएफ डाउनलोड करें" : "📄 Download PDF")
                                        .build(),
                                InteractiveMessage.Button.builder()
                                        .id("no_pdf")
                                        .title(lang.equals("hi") ? "❌ नहीं, धन्यवाद" : "❌ No, thanks")
                                        .build(),
                                InteractiveMessage.Button.builder()
                                        .id("back")
                                        .title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back")
                                        .build(),
                                InteractiveMessage.Button.builder()
                                        .id("main_menu")
                                        .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                        .build()
                        ))
                        .build();
            }

        } catch (Exception e) {
            log.error("Error handling PDF offer for user {}: {}", user.getPhone(), e.getMessage(), e);

            return InteractiveMessage.builder()
                    .header(lang.equals("hi") ? "❌ त्रुटि" : "❌ Error")
                    .body(lang.equals("hi")
                            ? "पीडीएफ तैयार करने में त्रुटि हुई। कृपया बाद में पुनः प्रयास करें। नया सत्र शुरू करने के लिए 'Hi' टाइप करें।"
                            : "Error generating PDF. Please try again later. Type 'Hi' to start a new session.")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder()
                                    .id("back")
                                    .title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back")
                                    .build(),
                            InteractiveMessage.Button.builder()
                                    .id("main_menu")
                                    .title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu")
                                    .build()
                    ))
                    .build();
        }
    }
}
