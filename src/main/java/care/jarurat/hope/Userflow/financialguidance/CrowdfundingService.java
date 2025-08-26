package care.jarurat.hope.Userflow.financialguidance;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Slf4j
@Service
@RequiredArgsConstructor
public class CrowdfundingService {

    public Object handleCrowdfunding(User user, String input) {
        String message = "Crowdfunding can be a powerful way to raise funds for treatment. Here are some popular platforms:\n\n"
                + "*Ketto:* A popular platform for medical fundraising.\n"
                + "*ImpactGuru:* Another well-known platform for medical crowdfunding.\n"
                + "*GiveIndia:* A platform that supports a variety of causes, including medical treatment.\n\n"
                + "**Tips for a successful campaign:**\n"
                + "*Tell a compelling story.*\n"
                + "*Be transparent about your financial needs.*\n"
                + "*Share regular updates with your donors.*";

        return InteractiveMessage.builder()
                .body(message)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title("Back to Menu").build()
                ))
                .build();
    }
}