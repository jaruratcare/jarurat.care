package care.jarurat.hope.Userflow.financialguidance;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final UserService userService;

    public Object handleInsurance(User user, String input) {
        String intent = user.getCurrentIntent();

        if (input.equalsIgnoreCase("back_to_financial_menu")) {
            user.setCurrentIntent("financial_guidance_start");
            userService.updateUser(user);
            return null;
        }

        if (input.equalsIgnoreCase("back_to_insurance_menu")) {
            return sendInsuranceYesMenu(user);
        }

        switch (input) {
            case "fg_insurance_start":
                return askAboutInsurance(user);

            case "fg_insurance_yes":
                return sendInsuranceYesMenu(user);

            case "fg_insurance_no":
                return sendInsuranceNoInfo(user);

            case "fg_insurance_check_policy":
                return sendCheckPolicyInfo(user);

            case "fg_insurance_claim_process":
                return sendClaimProcessInfo(user);

            case "fg_insurance_network_hospitals":
                return askForCityForHospitals(user);

            default:
                if ("fg_insurance_network_hospitals_city".equals(intent)) {
                    user.setCity(input);
                    userService.updateUser(user);
                    return sendNetworkHospitals(user);
                }
                return askAboutInsurance(user);
        }
    }

    private Object askAboutInsurance(User user) {
        user.setCurrentIntent("fg_insurance");
        userService.updateUser(user);

        return InteractiveMessage.builder()
                .body("Do you have health insurance?")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("fg_insurance_yes").title("Yes").build(),
                        InteractiveMessage.Button.builder().id("fg_insurance_no").title("No").build()
                ))
                .build();
    }

    private Object sendInsuranceYesMenu(User user) {
        user.setCurrentIntent("fg_insurance_yes");
        userService.updateUser(user);

        return ListMessage.builder()
                .header("Insurance Help")
                .body("How can I help you with your insurance?")
                .buttonText("View Options")
                .sections(Collections.singletonList(
                        ListMessage.Section.builder()
                                .title("Insurance Topics")
                                .rows(Arrays.asList(
                                        ListMessage.Row.builder().id("fg_insurance_check_policy").title("Check Policy Coverage").build(),
                                        ListMessage.Row.builder().id("fg_insurance_claim_process").title("Claim Process").build(),
                                        ListMessage.Row.builder().id("fg_insurance_network_hospitals").title("Network Hospitals").build(),
                                        ListMessage.Row.builder().id("back_to_financial_menu").title("Back to Financial Menu").build()
                                ))
                                .build()
                ))
                .build();
    }

    private Object sendInsuranceNoInfo(User user) {
        user.setCurrentIntent("financial_guidance_start");
        userService.updateUser(user);

        String message = "If you don't have insurance, you can explore these options:\n\n"
                + "*Government Schemes:* Many government schemes, like Ayushman Bharat, provide health coverage.\n"
                + "*Private Insurance:* You can also consider buying a private health insurance plan. Many insurers offer cancer-specific plans.";

        return InteractiveMessage.builder()
                .body(message)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title("Back to Menu").build()
                ))
                .build();
    }

    private Object sendCheckPolicyInfo(User user) {
        user.setCurrentIntent("fg_insurance_yes");
        userService.updateUser(user);

        String message = "To check your policy coverage, you should:\n\n"
                + "*Read your policy document carefully.*\n"
                + "*Look for the section on critical illness coverage.*\n"
                + "*Check for sub-limits on room rent, specific treatments, and pre- and post-hospitalization expenses.*\n"
                + "*Contact your insurance provider if you have any questions.*";

        return InteractiveMessage.builder()
                .body(message)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_insurance_menu").title("Back to Menu").build()
                ))
                .build();
    }

    private Object sendClaimProcessInfo(User user) {
        user.setCurrentIntent("fg_insurance_yes");
        userService.updateUser(user);

        String message = "The claim process can be either cashless or reimbursement:\n\n"
                + "*Cashless:* If you are treated at a network hospital, the hospital will directly settle the bill with the insurance company.\n"
                + "*Reimbursement:* If you are treated at a non-network hospital, you will have to pay the bill first and then get it reimbursed from the insurance company.\n\n"
                + "**Required documents:**\n"
                + "*Claim form*\n"
                + "*Medical certificate*\n"
                + "*Discharge summary*\n"
                + "*All bills and receipts*\n";

        return InteractiveMessage.builder()
                .body(message)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_insurance_menu").title("Back to Menu").build()
                ))
                .build();
    }

    private Object askForCityForHospitals(User user) {
        user.setCurrentIntent("fg_insurance_network_hospitals_city");
        userService.updateUser(user);
        return "Please enter your city to find network hospitals.";
    }

    private Object sendNetworkHospitals(User user) {
        user.setCurrentIntent("fg_insurance_yes");
        userService.updateUser(user);

        String message = "Here are some network hospitals in your city...\n\n(This is a placeholder. In a real application, this would be a list of hospitals.)";

        return InteractiveMessage.builder()
                .body(message)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_insurance_menu").title("Back to Menu").build()
                ))
                .build();
    }
}