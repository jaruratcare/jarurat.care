package care.jarurat.hope.Userflow.nutritionalCare.steps;

import java.util.List;

import org.springframework.stereotype.Service;

import care.jarurat.hope.Userflow.nutritionalCare.ImmunityService;
import care.jarurat.hope.Userflow.nutritionalCare.RemediesService;
import care.jarurat.hope.Userflow.nutritionalCare.SupplementsService;
import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NutritionStep4Service {

    private final UserService userService;
    private final NutritionStep3Service nutritionStep3Service;

    public Object handle(User user, String input,
                         RemediesService remediesService,
                         ImmunityService immunityService,
                         SupplementsService supplementsService) {

        String lang = user.getLanguage() != null ? user.getLanguage() : "en";

        // 🔙 Back button handling
        if (input.equalsIgnoreCase("back") || input.equals("🔙")) {
            String last = user.getLastIntent() != null ? user.getLastIntent() : "nutrition_step3";
            user.setCurrentIntent(last);
            userService.updateUser(user);

            if ("nutrition_step3".equals(last)) {
                return nutritionStep3Service.createSymptomsListMessage(lang);
            }
            // You can add more steps if needed
        }

        // 🏠 Main Menu handling
        if (input.equalsIgnoreCase("main_menu") || input.equals("🏠")) {
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(lang.equals("hi") ? "मुख्य मेनू में आपका स्वागत है!" : "Welcome to the main menu!")
                    .buttons(List.of(
                            InteractiveMessage.Button.builder().id("nutrition").title(lang.equals("hi") ? "🍎 पोषण देखभाल" : "🍎 Nutritional Care").build(),
                            InteractiveMessage.Button.builder().id("palliative").title(lang.equals("hi") ? "💊 उपशामक देखभाल" : "💊 Palliative Care").build()
                    ))
                    .build();
        }

        return switch (input.toLowerCase()) {
            case "meal_plan", "भोजन योजना" -> {
                user.setLastIntent(user.getCurrentIntent());
                user.setCurrentIntent("nutrition_generate_confirm");
                userService.updateUser(user);
                yield InteractiveMessage.builder()
                        .body(lang.equals("hi")
                                ? "📝 मैं आपकी व्यक्तिगत भोजन योजना तैयार करूंगा। कृपया आगे बढ़ने के लिए 'जारी रखें' पर क्लिक करें।"
                                : "📝 I will prepare your personalized meal plan. Please click Continue to proceed.")
                        .buttons(List.of(
                                InteractiveMessage.Button.builder()
                                        .id("continue_mealplan")
                                        .title(lang.equals("hi") ? "➡️ जारी रखें" : "➡️ Continue")
                                        .build()
                        ))
                        .build();
            }
            case "remedies", "उपचार" -> remediesService.getRemedies(user);
            case "immunity", "प्रतिरक्षा" -> immunityService.getImmunityTips(user);
            case "supplements", "सप्लीमेंट्स" -> supplementsService.getSupplements(user);
            default -> {
                // Add Back + Main Menu buttons to invalid input
                yield InteractiveMessage.builder()
                        .body(lang.equals("hi") ? "❌ अमान्य विकल्प। कृपया पुनः चुनें।" : "❌ Invalid choice. Please pick again.")
                        .buttons(List.of(
                                InteractiveMessage.Button.builder().id("back").title(lang.equals("hi") ? "🔙 पिछला चरण" : "🔙 Back").build(),
                                InteractiveMessage.Button.builder().id("main_menu").title(lang.equals("hi") ? "🏠 मुख्य मेनू" : "🏠 Main Menu").build()
                        ))
                        .build();
            }
        };
    }
}
