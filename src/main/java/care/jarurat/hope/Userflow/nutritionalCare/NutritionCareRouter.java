package care.jarurat.hope.Userflow.nutritionalCare;

import care.jarurat.hope.Userflow.nutritionalCare.steps.*;
import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.MainMenuService;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class NutritionCareRouter {

    private final UserService userService;
    private final NutritionStep1Service step1Service;
    private final NutritionStep0Service step0Service;
    private final NutritionStep2Service step2Service;
    private final NutritionStep3Service step3Service;
    private final NutritionStep4Service step4Service;

    private final MealPlanService mealPlanService;
    private final RemediesService remediesService;
    private final ImmunityService immunityService;
    private final SupplementsService supplementsService;

    public Object handle(User user, String input) {
        String intent = user.getCurrentIntent() != null ? user.getCurrentIntent() : "";

        log.info("Handling input: '{}' with intent: '{}' for user: {}", input, intent, user.getPhone());


        if ("show_pdf_offer".equalsIgnoreCase(input)) {
            user.setLastIntent(user.getCurrentIntent());
            user.setCurrentIntent("nutrition_pdf_offer");
            userService.saveUser(user);
            return mealPlanService.handlePdfOffer(user, "show_pdf_init");
        }

        if ("nutrition_generate_confirm".equals(intent) && input.startsWith("day_")) {
            Object response = mealPlanService.handleNextDay(user, input);
            if (input.equals("day_6")) {
                user.setLastIntent(user.getCurrentIntent());
                user.setCurrentIntent("nutrition_pdf_offer");
                userService.saveUser(user);
            }
            return response;
        }

        if ("nutrition_pdf_offer".equals(intent)) {
            Object response = mealPlanService.handlePdfOffer(user, input);
            if ("yes_pdf".equalsIgnoreCase(input) || "no_pdf".equalsIgnoreCase(input)) {
                user.setLastIntent(user.getCurrentIntent());
                user.setCurrentIntent("nutrition_step4");
                userService.saveUser(user);
            }
            return response;
        }

        // ✅ Step-based routing
        switch (intent) {
            case "nutrition_step1" -> {
                return step1Service.handle(user, input);
            }
            case "nutrition_step0" -> {
                return step0Service.handle(user, input);
            }
            case "nutrition_step2" -> {
                return step2Service.handle(user, input);
            }
            case "nutrition_step3" -> {
                return step3Service.handle(user, input);
            }
            case "nutrition_step4" -> {
                return step4Service.handle(user, input, remediesService, immunityService, supplementsService);
            }
            case "nutrition_generate_confirm" -> {
                Object response = mealPlanService.handleGenerateConfirm(user, input);
                user.setLastIntent(user.getCurrentIntent());
                user.setCurrentIntent("nutrition_generate_confirm");
                userService.saveUser(user);
                return response;
            }
            default -> {
                log.warn("Unknown intent: {} or input: {} for user: {}", intent, input, user.getPhone());
                user.setCurrentIntent(null);
                userService.saveUser(user);
                return InteractiveMessage.builder()
                        .body(user.getLanguage() != null && user.getLanguage().equals("hi")
                                ? "❌ पोषण प्रवाह में कुछ गड़बड़ हो गई। पुनः प्रारंभ करने के लिए 'Hi' टाइप करें।"
                                : "❌ Something went wrong in Nutrition flow. Type 'Hi' to restart.")
                        .buttons(Collections.emptyList())
                        .build();
            }
        }
    }
}
