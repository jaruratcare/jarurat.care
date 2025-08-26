package care.jarurat.hope.Userflow.financialguidance;

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
public class FinancialGuidanceService {

    private final UserService userService;

    public Object sendMainFinancialGuidanceMenu(User user) {
        user.setCurrentIntent("financial_guidance_menu");
        userService.updateUser(user);

        return ListMessage.builder()
                .header("Financial Guidance")
                .body("Please choose a topic to get started.")
                .buttonText("View Options")
                .sections(Collections.singletonList(
                        ListMessage.Section.builder()
                                .title("Types of Financial Help")
                                .rows(Arrays.asList(
                                        ListMessage.Row.builder().id("fg_govt_schemes_start").title("Government Schemes").build(),
                                        ListMessage.Row.builder().id("fg_ngos_and_trusts_start").title("NGOs & Trusts").build(),
                                        ListMessage.Row.builder().id("fg_crowdfunding_start").title("Crowdfunding Platforms").build(),
                                        ListMessage.Row.builder().id("fg_insurance_start").title("Insurance/Policy").build(),
                                        ListMessage.Row.builder().id("main_menu").title("Back to Main Menu").build()
                                ))
                                .build()
                ))
                .build();
    }
}