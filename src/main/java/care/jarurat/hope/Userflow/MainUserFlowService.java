package care.jarurat.hope.Userflow;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.*;
import care.jarurat.hope.Userflow.financialguidance.*;
import care.jarurat.hope.Userflow.nutritionalCare.NutritionCareRouter;
import care.jarurat.hope.Userflow.nearbyHospital.NearbyHospitalRouter;
import care.jarurat.hope.Userflow.PalliativeCareHandler.PalliativeCareHandler;
import care.jarurat.hope.Userflow.diagonostics.DiagnosticHandler;
import org.springframework.context.annotation.Lazy;
import care.jarurat.hope.Userflow.doctors.DoctorRouter;
import care.jarurat.hope.Userflow.AccommodationFood.AccommodationFoodHandler;
import care.jarurat.hope.Userflow.Volunteer.VolunteerHandler;
import care.jarurat.hope.Userflow.emotionalcare.EmotionalCareRouter;
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
    private final VolunteerHandler volunteerHandler;
    private final EmotionalCareRouter emotionalCareRouter;
    @Lazy
    private final DoctorRouter doctorRouter;

    public Object getResponse(User user, String input) {


        if (input == null || input.trim().isEmpty()) {
            return "Please send a message. Type 'Hi' to start.";
        }

        input = input.trim();
        String intent = user.getCurrentIntent();
        // 🚀 FIX: Doctors flow must be handled BEFORE switch-case
        if ("10".equals(input) && "main_menu".equals(intent)) {
            user.setCurrentIntent("doctor_find_start");
            userService.updateUser(user);
            return doctorRouter.handle(user, input);
        }


        // Global commands
        Object globalResponse = globalCommandService.handleGlobalCommand(user, input);
        if (globalResponse != null) {
            return globalResponse;
        }

        // 🔥 CHANGE LANGUAGE HANDLER (User pressed 9)
        if ("9".equals(input)) {
            user.setCurrentIntent("choose_language");
            userService.updateUser(user);

            return InteractiveMessage.builder()
                    .body("Please choose your preferred language:")
                    .footer("Choose your language / अपनी भाषा चुनें").buttons(Arrays.asList(
                            InteractiveMessage.Button.builder().id("english").title("🇬🇧 English").build(),
                            InteractiveMessage.Button.builder().id("hindi").title("🇮🇳 Hindi").build()))
                    .build();
        }

        // MAIN MENU redirect
        if ("back_to_menu".equalsIgnoreCase(input) || "main_menu".equalsIgnoreCase(input)) {
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
        }

        if ("back_to_financial_menu".equalsIgnoreCase(input)) {
            return financialGuidanceService.sendMainFinancialGuidanceMenu(user);
        }

        // Onboarding start
        if ("onboarding_start".equalsIgnoreCase(input)) {
            user.setCurrentIntent("onboarding_handle_name");
            userService.updateUser(user);
            boolean isEnglish = "en".equals(user.getLanguage());
            return isEnglish
                    ? "To personalize support, please tell me your name."
                    : "आपका समर्थन व्यक्तिगत बनाने के लिए, कृपया अपना नाम बताएं।";
        }

        log.debug("Processing message '{}' for user {} with intent: {}", input, user.getUserId(), intent);

        // First-time language selection
        if (intent == null || intent.isBlank()) {
            return InteractiveMessage.builder()
                    .body("Please choose your preferred language:")
                    .footer("Choose your language / अपनी भाषा चुनें").buttons(Arrays.asList(
                            InteractiveMessage.Button.builder().id("english").title("🇬🇧 English").build(),
                            InteractiveMessage.Button.builder().id("hindi").title("🇮🇳 Hindi").build()))
                    .build();
        }

        // FINANCIAL MAIN MENU
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

        // FINANCIAL SUBFLOWS
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

        // MAIN MENU & NEW MODULE ROUTING
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

            // MAIN MENU HANDLING
            case "main_menu":
                Object menuResponse = mainMenuService.handleMainMenu(user, input);

                if (menuResponse == null) {
                            
                    if ("10".equals(input)) {
                        user.setCurrentIntent("doctor_find_start");
                        userService.updateUser(user);
                        return doctorRouter.handle(user, input);
                    }


                    if ("nutrition_step1".equals(user.getCurrentIntent())) {
                        return nutritionCareRouter.handle(user, input);
                    }

                    if ("financial_guidance_start".equals(user.getCurrentIntent())) {
                        return financialGuidanceService.sendMainFinancialGuidanceMenu(user);
                    }

                    if (user.getCurrentIntent().startsWith("nearby_hospitals")
                            || user.getCurrentIntent().startsWith("awaiting_location")
                            || user.getCurrentIntent().startsWith("awaiting_hospital_type")) {
                        return nearbyHospitalRouter.handle(user, input);
                    }

                    if (user.getCurrentIntent().startsWith("palliative_care")
                            || user.getCurrentIntent().startsWith("awaiting_palliative")) {
                        return palliativeCareHandler.handle(user, input);
                    }

                    if (user.getCurrentIntent().startsWith("accommodation_food")
                            || user.getCurrentIntent().startsWith("awaiting_af_")) {
                        return accommodationFoodHandler.handle(user, input);
                    }

                    if (user.getCurrentIntent().startsWith("diagnostic")) {
                        return diagnosticHandler.handle(user, input);
                    }

                    if (user.getCurrentIntent().startsWith("volunteer")) {
                        return volunteerHandler.handle(user, input);
                    }

                    if (user.getCurrentIntent().startsWith("emotional")) {
                        return emotionalCareRouter.handle(user, input);
                    }
                      return menuResponse;
                }


            case "nutrition_step1":
            case "nutrition_step0":
            case "nutrition_step2":
            case "nutrition_step3":
            case "nutrition_step4":
            case "nutrition_generate_confirm":
            case "nutrition_pdf_offer":
                return nutritionCareRouter.handle(user, input);

            // Hospital flow
            case "nearby_hospitals":
            case "awaiting_location":
            case "awaiting_hospital_type":
            case "hospital_pdf_offer":
            case "next_hospital_chunk":
                return nearbyHospitalRouter.handle(user, input);

            // Palliative flows
            case "palliative_care":
            case "awaiting_palliative_city":
            case "awaiting_palliative_type":
            case "awaiting_palliative_care_type":
                return palliativeCareHandler.handle(user, input);

            // Accommodation & food
            case "accommodation_food":
            case "awaiting_af_hospital":
            case "awaiting_af_help_type":
            case "awaiting_af_city":
            case "awaiting_af_income":
            case "awaiting_af_service_selection":
                return accommodationFoodHandler.handle(user, input);

            // Diagnostics
            case "diagnostic_lab_start":
            case "diagnostic_awaiting_location_confirmation":
            case "diagnostic_awaiting_location":
            case "diagnostic_awaiting_test_type":
            case "diagnostic_awaiting_collection_type":
                return diagnosticHandler.handle(user, input);

            // Volunteer
            case "volunteer_start":
            case "volunteer_choose_mode":
            case "volunteer_ask_datetime":
                return volunteerHandler.handle(user, input);

            // Emotional
            case "emotional_care":
            case "feeling_checkin":
            case "feeling_anxious":
            case "feeling_sad":
            case "feeling_exhausted":
            case "just_checking_resources":
            case "support_type_menu":
            case "mindfulness_audio":
            case "emotional_helplines":
            case "talk_to_volunteer":
            case "volunteer_call_time":
            case "volunteer_callback_time":
            case "volunteer_textchat_time":
            case "caregiving_tips":
            case "caregiving_tips_pdf_offer":
                return emotionalCareRouter.handle(user, input);

            // --- Doctor Flow Cases ---
            case "doctor_find_start":
            case "doctor_awaiting_specialty":
            case "doctor_awaiting_location":
            case "doctor_list":
            case "doctor_pdf_offer":
                return doctorRouter.handle(user, input);
        }

        log.warn("Unknown intent: {} for user: {}. Defaulting to main menu.", intent, user.getUserId());
        return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
    }
}
