package care.jarurat.hope.Userflow.financialguidance;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class GovernmentSchemesService {

    private final UserService userService;

    public Object handleGovernmentSchemes(User user, String input) {
        String intent = user.getCurrentIntent() != null ? user.getCurrentIntent() : "fg_govt_schemes_start";

        switch (intent) {
            case "fg_govt_schemes_start":
                return askForIncomeRange(user);

            case "fg_govt_schemes_income":
                user.setIncomeRange(input);
                userService.updateUser(user);
                return sendEligibleGovernmentSchemes(user);

            default:
                return askForIncomeRange(user);
        }
    }

    private Object askForIncomeRange(User user) {
        user.setCurrentIntent("fg_govt_schemes_income");
        userService.updateUser(user);

        return InteractiveMessage.builder()
                .body("Please select your approximate annual family income.")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("below_2.5_lakh").title("Below 2.5 Lakh").build(),
                        InteractiveMessage.Button.builder().id("2.5_to_5_lakh").title("2.5 to 5 Lakh").build(),
                        InteractiveMessage.Button.builder().id("above_5_lakh").title("Above 5 Lakh").build()
                ))
                .build();
    }

    private Object sendEligibleGovernmentSchemes(User user) {
        user.setCurrentIntent("financial_guidance_start");
        userService.updateUser(user);

        // This is where you would have logic to determine eligible schemes based on user.getCity(), user.getCancerType(), and user.getIncomeRange()
        String message = "Based on your information, here are some potentially eligible schemes:\n\n"
                + "*Ayushman Bharat (PM-JAY):* Provides a cover of up to ₹5 lakh per family per year.\n"
                + "*Rashtriya Arogya Nidhi (RAN):* Provides financial assistance to patients living below the poverty line.\n"
                + "*State Illness Assistance Fund:* Many states have their own funds to provide financial aid.";

        return InteractiveMessage.builder()
                .body(message)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title("Back to Menu").build()
                ))
                .build();
    }
}