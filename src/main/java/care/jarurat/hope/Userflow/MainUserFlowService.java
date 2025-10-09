package care.jarurat.hope.Userflow;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.*;
import care.jarurat.hope.Userflow.financialguidance.*;
import care.jarurat.hope.Userflow.nutritionalCare.NutritionCareRouter;
import care.jarurat.hope.Userflow.nearbyHospital.NearbyHospitalRouter;
import care.jarurat.hope.Userflow.PalliativeCareHandler.PalliativeCareHandler; // ✅ import PalliativeCareHandler
import care.jarurat.hope.Userflow.diagonostics.DiagnosticHandler;
import care.jarurat.hope.Userflow.AccommodationFood.AccommodationFoodHandler; // ✅ import AccommodationFoodHandler
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainUserFlowService {

    private final UserService userService;
    private final GlobalCommandService globalCommandService;
    private final LanguageService languageService;
    private final OnboardingService onboardingService;
    private final MainMenuService mainMenuService;
    private final FinancialGuidanceService financialGuidanceService;
    private final GovernmentSchemesService governmentSchemesService;
    private final NgosAndTrustsService ngosAndTrustsService;
    private final CrowdfundingService crowdfundingService;
    private final InsuranceService insuranceService;
    private final NutritionCareRouter nutritionCareRouter;
    private final NearbyHospitalRouter nearbyHospitalRouter; 
    private final PalliativeCareHandler palliativeCareHandler;
    private final AccommodationFoodHandler accommodationFoodHandler; 
    private final DiagnosticHandler diagnosticHandler;

    


    public Object getResponse(User user, String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Please send a message. Type 'Hi' to start.";
        }

        input = input.trim();
        String intent = user.getCurrentIntent();

        //  handle global commands (like 'hi', 'help', 'menu')
        Object globalResponse = globalCommandService.handleGlobalCommand(user, input);
        if (globalResponse != null) {
            return globalResponse;
        }

        // allow main_menu command anytime
        if ("back_to_menu".equalsIgnoreCase(input) || "main_menu".equalsIgnoreCase(input)) {
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
        }

        if ("back_to_financial_menu".equalsIgnoreCase(input)) {
            return financialGuidanceService.sendMainFinancialGuidanceMenu(user);
        }

        //  onboarding start
        if ("onboarding_start".equalsIgnoreCase(input)) {
            user.setCurrentIntent("onboarding_handle_name");
            userService.updateUser(user);

            boolean isEnglish = "en".equals(user.getLanguage());
            return isEnglish
                    ? "To personalize support, please tell me your name."
                    : "आपका समर्थन व्यक्तिगत बनाने के लिए, कृपया अपना नाम बताएं।";
        }

        log.debug("Processing message '{}' for user {} with intent: {}", input, user.getUserId(), intent);

        //  If no intent yet, ask language
        if (intent == null || intent.isBlank()) {
            return InteractiveMessage.builder()
                    .body("Please choose your preferred language:")
                    .footer("Choose your language / अपनी भाषा चुनें")
                    .buttons(Arrays.asList(
                            InteractiveMessage.Button.builder().id("english").title("🇬🇧 English").build(),
                            InteractiveMessage.Button.builder().id("hindi").title("🇮🇳 Hindi").build()
                    ))
                    .build();
        }

        //  handle financial guidance menus
        if ("financial_guidance_menu".equals(intent)) {
            switch (input) {
                case "fg_govt_schemes_start":
                    user.setCurrentIntent("fg_govt_schemes");
                    userService.updateUser(user);
                    return governmentSchemesService.handleGovernmentSchemes(user, input);

                case "fg_ngos_and_trusts_start":
                    user.setCurrentIntent("fg_ngos_and_trusts");
                    userService.updateUser(user);
                    return ngosAndTrustsService.handleNgosAndTrusts(user, input);

                case "fg_crowdfunding_start":
                    user.setCurrentIntent("fg_crowdfunding");
                    userService.updateUser(user);
                    return crowdfundingService.handleCrowdfunding(user, input);

                case "fg_insurance_start":
                    user.setCurrentIntent("fg_insurance");
                    userService.updateUser(user);
                    return insuranceService.handleInsurance(user, input);
            }
        }

        //  route to correct FG service
        if (intent.startsWith("fg_")) {
            if (intent.startsWith("fg_govt_schemes")) {
                return governmentSchemesService.handleGovernmentSchemes(user, input);
            } else if (intent.startsWith("fg_ngos_and_trusts")) {
                return ngosAndTrustsService.handleNgosAndTrusts(user, input);
            } else if (intent.startsWith("fg_crowdfunding")) {
                return crowdfundingService.handleCrowdfunding(user, input);
            } else if (intent.startsWith("fg_insurance")) {
                return insuranceService.handleInsurance(user, input);
            }
        }

        //  main flow handling
        switch (intent.toLowerCase()) {
            case "choose_language":
                return languageService.handleLanguageSelection(user, input);

            case "onboarding_handle_name":
            case "onboarding_handle_location":
            case "onboarding_handle_role":
            case "onboarding_handle_medical":
                Object onboardingResp = onboardingService.handleOnboardingStep(user, input);
                if ("onboarding_complete".equals(user.getCurrentIntent())) {
                    user.setCurrentIntent("main_menu");
                    userService.updateUser(user);
                    return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
                }
                return onboardingResp;

            case "main_menu":
                Object menuResponse = mainMenuService.handleMainMenu(user, input);
                if (menuResponse == null) {

                    //  Nutrition care
                    if ("nutrition_care_start".equals(user.getCurrentIntent())) {
                        user.setCurrentIntent("nutrition_step1");
                        userService.updateUser(user);
                        return nutritionCareRouter.handle(user, input);

                    //  Financial guidance
                    } else if ("financial_guidance_start".equals(user.getCurrentIntent())) {
                        return financialGuidanceService.sendMainFinancialGuidanceMenu(user);

                    //  Nearby hospitals
                    } else if ("nearby_hospitals".equals(user.getCurrentIntent())
                            || "awaiting_location".equals(user.getCurrentIntent())
                            || "awaiting_hospital_type".equals(user.getCurrentIntent())) {
                        return nearbyHospitalRouter.handle(user, input);

                    //  Palliative care
                    } else if ("palliative_care".equals(user.getCurrentIntent()) ||
                            "awaiting_palliative_city".equals(user.getCurrentIntent()) ||
                            "awaiting_palliative_type".equals(user.getCurrentIntent()) ||
                            "awaiting_palliative_care_type".equals(user.getCurrentIntent())) {

                        return palliativeCareHandler.handle(user, input);

                    //  Accommodation & Food
                    } else if ("accommodation_food".equals(user.getCurrentIntent()) ||
                               "awaiting_af_hospital".equals(user.getCurrentIntent()) ||
                               "awaiting_af_help_type".equals(user.getCurrentIntent()) ||
                               "awaiting_af_city".equals(user.getCurrentIntent()) ||
                               "awaiting_af_type".equals(user.getCurrentIntent()) ||
                               "awaiting_af_income".equals(user.getCurrentIntent()) ||
                               "awaiting_af_service_selection".equals(user.getCurrentIntent()) ) {

                        return accommodationFoodHandler.handle(user, input);
                    //diagonostics
                    }else if ("diagnostic_lab_start".equals(user.getCurrentIntent()) ||
                   "diagnostic_awaiting_location_confirmation".equals(user.getCurrentIntent()) ||
                   "diagnostic_awaiting_location".equals(user.getCurrentIntent())) {

                        return diagnosticHandler.handle(user, input);
        }

                }
                return menuResponse;

            //  nutrition flow
            case "nutrition_step1":
            case "nutrition_step2":
            case "nutrition_step3":
            case "nutrition_step4":
            case "nutrition_generate_confirm":
            case "nutrition_pdf_offer":
                return nutritionCareRouter.handle(user, input);

            //  hospital flow
            case "nearby_hospitals":
            case "awaiting_location":
            case "awaiting_hospital_type":
            case "hospital_pdf_offer":
                return nearbyHospitalRouter.handle(user, input);

            //  palliative care flow
            case "palliative_care":
            case "awaiting_palliative_city":
            case "awaiting_palliative_type":
            case "awaiting_palliative_care_type":
                return palliativeCareHandler.handle(user, input);

            //  accommodation food flow
            case "accommodation_food":
            case "awaiting_af_hospital":
            case "awaiting_af_help_type":
            case "awaiting_af_city":
            case "awaiting_af_income":
            case "awaiting_af_service_selection":
            return accommodationFoodHandler.handle(user, input);
            // diagnostics flow
            case "diagnostic_lab_start":
            case "diagnostic_awaiting_location_confirmation":
            case "diagnostic_awaiting_location":
            return diagnosticHandler.handle(user, input);
        }

        log.warn("Unknown intent: {} for user: {}. Defaulting to main menu.", intent, user.getUserId());
        return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
    }
}
