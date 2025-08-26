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
public class NgosAndTrustsService {

    public Object handleNgosAndTrusts(User user, String input) {
        String message = "Here are some NGOs and trusts that can help with cancer treatment costs:\n\n"
                + "*Indian Cancer Society:* One of the oldest and most prominent NGOs, the ICS provides financial aid for treatment to underprivileged patients through its \"Cancer Cure Fund.\"\n"
                + "*YouWeCan Foundation:* Established by cricketer Yuvraj Singh, this foundation provides financial assistance for the treatment of pediatric cancer patients from underprivileged backgrounds.\n"
                + "*Cancer Patients Aid Association (CPAA):* The CPAA offers a wide range of services, including financial assistance for treatment, counseling, and rehabilitation.\n"
                + "*Tata Trusts:* The Tata Trusts have a long history of supporting cancer care in India and provide financial assistance to patients.";

        return InteractiveMessage.builder()
                .body(message)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title("Back to Menu").build()
                ))
                .build();
    }
}