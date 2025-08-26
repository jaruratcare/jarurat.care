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

        return switch (input.toLowerCase()) {
            case "meal_plan", "भोजन योजना" -> {
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
            case "back_to_menu", "🔙 मेनू पर जाएँ" -> {
                user.setCurrentIntent("nutrition_step3");
                userService.updateUser(user);
                yield nutritionStep3Service.createSymptomsListMessage(lang);
            }
            default -> {
                yield lang.equals("hi")
                        ? "❌ अमान्य विकल्प। कृपया पुनः चुनें।"
                        : "❌ Invalid choice. Please pick again.";
            }
        };
    }
}