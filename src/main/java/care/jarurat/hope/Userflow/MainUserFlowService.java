package care.jarurat.hope.Userflow;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.*;
import care.jarurat.hope.Userflow.financialguidance.*;
import care.jarurat.hope.Userflow.nutritionalCare.NutritionCareRouter;
import care.jarurat.hope.Userflow.nearbyHospital.NearbyHospitalRouter;
import care.jarurat.hope.Userflow.PalliativeCareHandler.PalliativeCareHandler;
import care.jarurat.hope.Userflow.diagonostics.DiagnosticHandler;
import care.jarurat.hope.Userflow.doctor.DoctorRouter;

import org.springframework.context.annotation.Lazy;

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
          
        // 🔥 GLOBAL DOCTOR LIST CLICK HANDLER (WhatsApp List Reply)
            if (input.startsWith("DOCTOR_")) {

                log.info("🧠 GLOBAL DOCTOR CLICK intercepted: {}", input);

                String doctorId = input.replace("DOCTOR_", "").trim();

                user.setCurrentIntent("doctor_details");
                userService.updateUser(user);

                return doctorRouter.handle(user, input);
            }

        // 🔹 GLOBAL COMMANDS
        Object globalResponse = globalCommandService.handleGlobalCommand(user, input);
        if (globalResponse != null)
            return globalResponse;

        // 🔹 LANGUAGE CHANGE
        if (input.equals("9")) {
            user.setCurrentIntent("choose_language");
            userService.updateUser(user);

            return InteractiveMessage.builder()
                    .body("Please choose your preferred language:")
                    .footer("Choose your language / अपनी भाषा चुनें")
                    .buttons(Arrays.asList(
                            InteractiveMessage.Button.builder().id("english").title("🇬🇧 English").build(),
                            InteractiveMessage.Button.builder().id("hindi").title("🇮🇳 Hindi").build()))
                    .build();
        }

        // 🔹 RETURN TO MAIN MENU
        if (input.equalsIgnoreCase("back_to_menu") || input.equalsIgnoreCase("main_menu")) {
            user.setCurrentIntent("main_menu");
            userService.updateUser(user);
            return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
        }

        // 🔹 RETURN FINANCIAL MENU
        if (input.equalsIgnoreCase("back_to_financial_menu"))
            return financialGuidanceService.sendMainFinancialGuidanceMenu(user);

        // 🔹 ONBOARDING START
        if (input.equalsIgnoreCase("onboarding_start")) {
            user.setCurrentIntent("onboarding_handle_name");
            userService.updateUser(user);

            return "en".equals(user.getLanguage())
                    ? "To personalize support, please tell me your name."
                    : "आपका समर्थन व्यक्तिगत बनाने के लिए, कृपया अपना नाम बताएं।";
        }

        log.debug("Processing message '{}' for user {} with intent: {}", input, user.getUserId(), intent);

        // 🔹 FIRST TIME USER
        if (intent == null || intent.isBlank()) {
            return languageSelect();
        }

        // 🔹 FINANCIAL FLOW HANDLING
        if (intent.equals("financial_guidance_menu")) {
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

        if (intent.startsWith("fg_")) {
            if (intent.startsWith("fg_govt_schemes"))
                return governmentSchemesService.handleGovernmentSchemes(user, input);

            if (intent.startsWith("fg_ngos_and_trusts"))
                return ngosAndTrustsService.handleNgosAndTrusts(user, input);

            if (intent.startsWith("fg_crowdfunding"))
                return crowdfundingService.handleCrowdfunding(user, input);

            if (intent.startsWith("fg_insurance"))
                return insuranceService.handleInsurance(user, input);
        }

        // 🔹 MAIN MENU ROUTING
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

                // USER SELECTED DOCTORS = OPTION 10
                if (input.equals("10")) {
                    user.setCurrentIntent("doctor_find_start");
                    userService.updateUser(user);
                    return doctorRouter.handle(user, input);
                }

                // IF NULL = ROUTE TO THEIR CURRENT MODULE
                if (menuResponse == null) {
                    return routeToModule(user, input);
                }

                return menuResponse;

            // ------- MODULE ROUTING BELOW -------

            case "nutrition_step1":
            case "nutrition_step0":
            case "nutrition_step2":
            case "nutrition_step3":
            case "nutrition_step4":
            case "nutrition_generate_confirm":
            case "nutrition_pdf_offer":
                return nutritionCareRouter.handle(user, input);

            case "nearby_hospitals":
            case "awaiting_location":
            case "awaiting_hospital_type":
            case "hospital_pdf_offer":
            case "next_hospital_chunk":
                return nearbyHospitalRouter.handle(user, input);

            case "palliative_care":
            case "awaiting_palliative_city":
            case "awaiting_palliative_type":
            case "awaiting_palliative_care_type":
                return palliativeCareHandler.handle(user, input);

            case "accommodation_food":
            case "awaiting_af_hospital":
            case "awaiting_af_help_type":
            case "awaiting_af_city":
            case "awaiting_af_income":
            case "awaiting_af_service_selection":
                return accommodationFoodHandler.handle(user, input);

            case "diagnostic_lab_start":
            case "diagnostic_awaiting_location_confirmation":
            case "diagnostic_awaiting_location":
            case "diagnostic_awaiting_test_type":
            case "diagnostic_awaiting_collection_type":
            case "diagnostic_awaiting_lab_choice":
                return diagnosticHandler.handle(user, input);

            case "volunteer_start":
            case "volunteer_choose_mode":
            case "volunteer_ask_datetime":
                return volunteerHandler.handle(user, input);

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

            // 🔥 **FINAL DOCTOR ROUTING**
            case "doctor_find_start":
            case "doctor_awaiting_city":
            case "doctor_awaiting_specialty":
            case "doctor_list":
            case "doctor_details":
                return doctorRouter.handle(user, input);
        }

        log.warn("Unknown intent: {} for user: {}. Defaulting to main menu.", intent, user.getUserId());
        return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
    }

    // -------------- HELPERS --------------
    private InteractiveMessage languageSelect() {
        return InteractiveMessage.builder()
                .body("Please choose your preferred language:")
                .footer("Choose your language / अपनी भाषा चुनें")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("english").title("🇬🇧 English").build(),
                        InteractiveMessage.Button.builder().id("hindi").title("🇮🇳 Hindi").build()))
                .build();
    }

    private Object routeToModule(User user, String input) {

        String curr = user.getCurrentIntent();

        if (curr.startsWith("nutrition"))
            return nutritionCareRouter.handle(user, input);
        if (curr.startsWith("nearby_hospitals"))
            return nearbyHospitalRouter.handle(user, input);
        if (curr.startsWith("palliative"))
            return palliativeCareHandler.handle(user, input);
        if (curr.startsWith("accommodation_food"))
            return accommodationFoodHandler.handle(user, input);
        if (curr.startsWith("diagnostic"))
            return diagnosticHandler.handle(user, input);
        if (curr.startsWith("volunteer"))
            return volunteerHandler.handle(user, input);
        if (curr.startsWith("emotional"))
            return emotionalCareRouter.handle(user, input);

        if (curr.startsWith("doctor"))
            return doctorRouter.handle(user, input);

        return mainMenuService.getMainMenuMessage("en".equals(user.getLanguage()));
    }
}
