package care.jarurat.hope.Userflow;

import care.jarurat.hope.model.User;
import care.jarurat.hope.service.OpenAiServiceWrapper;
import care.jarurat.hope.service.UserService;
import care.jarurat.hope.util.PdfGeneratorUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class NutritionCareService {

    private final UserService userService;
    private final OpenAiServiceWrapper openAiServiceWrapper;

    public String handleNutrition(User user, String input) {
        return switch (user.getCurrentIntent()) {
            case "nutrition_step1" -> handleStep1(user, input);
            case "nutrition_step2" -> handleStep2(user, input);
            case "nutrition_step3" -> handleStep3(user, input);
            case "nutrition_step4" -> handleStep4(user, input);
            case "nutrition_pdf_offer" -> handlePdfOffer(user, input);
            default -> "❌ Something went wrong in Nutrition flow. Type 'Hi' to restart.";
        };
    }

    private String handleStep1(User user, String input) {
        if (input.equalsIgnoreCase("vegetarian") || input.equalsIgnoreCase("non-vegetarian")) {
            user.setPreference("diet_type", input);
            user.setCurrentIntent("nutrition_step2");
            userService.updateUser(user);
            return "🥗 What is the eating condition?\n1) Soft\n2) Liquid Diet\n3) Normal Diet";
        }
        return "❌ Please type 'vegetarian' or 'non-vegetarian'.";
    }

    private String handleStep2(User user, String input) {
        switch (input) {
            case "1" -> user.setPreference("eating_condition", "soft");
            case "2" -> user.setPreference("eating_condition", "liquid");
            case "3" -> user.setPreference("eating_condition", "normal");
            default -> {
                return "❌ Invalid choice. Please select 1, 2, or 3.";
            }
        }
        user.setCurrentIntent("nutrition_step3");
        userService.updateUser(user);
        return "Do you have any symptoms?\n" +
                "1) Gut health issues\n" +
                "2) Nausea / vomiting\n" +
                "3) Fatigue / weakness\n" +
                "4) Loss of appetite / weight loss\n" +
                "5) Low immunity";
    }

    private String handleStep3(User user, String input) {
        user.setPreference("symptoms", input);
        user.setCurrentIntent("nutrition_step4");
        userService.updateUser(user);
        return "💡 What would you like help with today?\n" +
                "1) Get a tailored daily meal plan 🗓️\n" +
                "2) Remedies for specific symptoms 💊\n" +
                "3) Immunity building tips 🛡️\n" +
                "4) List of free/affordable nutrition supplements 📦";
    }

    private String handleStep4(User user, String input) {
        String response;

        switch (input) {
            case "1": {
                String dietType = user.getPreference("diet_type");
                String eatingCondition = user.getPreference("eating_condition");
                String symptoms = user.getPreference("symptoms");

                log.info("[NutritionCareService] Preferences collected:");
                log.info("Diet type: {}", dietType);
                log.info("Eating condition: {}", eatingCondition);
                log.info("Symptoms: {}", symptoms);

                String preferences = String.format(
                        "You are a nutritionist. Create a healthy, tailored Indian vegetarian meal plan for weight loss. " +
                                "Diet type: %s; Eating condition: %s; Symptoms: %s.",
                        dietType != null ? dietType : "not specified",
                        eatingCondition != null ? eatingCondition : "not specified",
                        symptoms != null ? symptoms : "not specified"
                );

                try {
                    String mealPlan = openAiServiceWrapper.generateMealPlan(preferences);

                    // Generate PDF file
                    String pdfUrl = PdfGeneratorUploader.generateAndUploadPdf(mealPlan, user.getUserId());

                    // Save to Firestore (persistent)
                    Firestore db = FirestoreClient.getFirestore();
                    db.collection("users")
                      .document(user.getUserId())
                      .update("lastMealPlanPdfUrl", pdfUrl);

                    // Also save in user object
                    user.setPreference("lastMealPlanPdfUrl", pdfUrl);
                    user.setCurrentIntent("nutrition_pdf_offer");
                    userService.updateUser(user);

                    response = "📅 Here’s your tailored daily meal plan:\n" + mealPlan +
                               "\n\nWould you like to download this meal plan as a PDF? Reply 'yes' or 'no'.";

                } catch (Exception e) {
                    log.error("Error while generating meal plan or PDF", e);
                    response = "⚠️ Sorry, AI service error: " + e.getMessage() +
                               "\nPlease try again later or type 'Hi' to return to main menu.";
                }
                break;
            }

            case "2":
                response = "💊 Remedies for your selected symptoms:\n" +
                        "- Gut health: Include curd, bananas, isabgol\n" +
                        "- Nausea: Ginger tea, small frequent meals\n" +
                        "- Fatigue: Iron-rich foods like spinach, jaggery\n" +
                        "Type 'Hi' to return to the main menu.";
                user.setCurrentIntent("main_menu");
                userService.updateUser(user);
                break;

            case "3":
                response = "🛡️ Immunity building tips:\n" +
                        "- Seasonal fruits like oranges, guava\n" +
                        "- Turmeric milk at night\n" +
                        "- Soaked almonds in the morning\n" +
                        "Type 'Hi' to return to the main menu.";
                user.setCurrentIntent("main_menu");
                userService.updateUser(user);
                break;

            case "4":
                response = "📦 Affordable supplements near you:\n" +
                        "1) NGO XYZ – Free nutrition packs\n" +
                        "2) ABC Hospital – Subsidised protein powder\n" +
                        "3) HealthOrg – Vitamin packs at low cost\n" +
                        "Type 'Hi' to return to the main menu.";
                user.setCurrentIntent("main_menu");
                userService.updateUser(user);
                break;

            default:
                response = "❌ Invalid choice. Please select 1, 2, 3, or 4.";
        }

        return response;
    }

    public String handlePdfOffer(User user, String userMessage) {
        if ("yes".equalsIgnoreCase(userMessage.trim())) {
            try {
                Firestore db = FirestoreClient.getFirestore();
                DocumentSnapshot doc = db.collection("users")
                                         .document(user.getUserId())
                                         .get()
                                         .get();

                String pdfUrl = doc.getString("lastMealPlanPdfUrl");

                if (pdfUrl != null && !pdfUrl.isEmpty()) {
                    user.setCurrentIntent("main_menu");
                    userService.updateUser(user);
                    return "✅ Your PDF is ready: " + pdfUrl;
                } else {
                    return "❌ Sorry, PDF not found. Please generate the meal plan again.";
                }
            } catch (Exception e) {
                log.error("Error fetching PDF URL from Firestore", e);
                return "⚠ An error occurred while fetching your PDF. Please try again later.";
            }
        } else {
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return "Okay! Returning to main menu.";
        }
    }
}
