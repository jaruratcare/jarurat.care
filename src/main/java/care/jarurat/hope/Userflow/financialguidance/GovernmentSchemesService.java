package care.jarurat.hope.Userflow.financialguidance;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.model.financials.GovtScheme;
import care.jarurat.hope.repository.financials.GovtSchemeRepository;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GovernmentSchemesService {

    private final UserService userService;
    private final GovtSchemeRepository govtSchemeRepository;

    public Object handleGovernmentSchemes(User user, String input) {
        String intent = user.getCurrentIntent() != null ? user.getCurrentIntent() : "fg_govt_schemes_start";

        switch (intent) {
            case "fg_govt_schemes_start":
                return askForIncomeRange(user);

            case "fg_govt_schemes_income":
                user.setIncomeRange(input);
                userService.updateUser(user);
                return sendEligibleSchemeList(user);

            case "fg_govt_schemes_details":
                return sendSchemeDetails(user, input);

            default:
                return askForIncomeRange(user);
        }
    }

    private Object askForIncomeRange(User user) {
        user.setCurrentIntent("fg_govt_schemes_income");
        userService.updateUser(user);

        boolean isEnglish = "en".equals(user.getLanguage());

        return InteractiveMessage.builder()
                .body(isEnglish ? "Please select your approximate annual family income." : "कृपया अपनी अनुमानित वार्षिक पारिवारिक आय चुनें।")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("below_1.5_lakh").title(isEnglish ? "Below ₹1.5 Lakh" : "₹1.5 लाख से कम").build(),
                        InteractiveMessage.Button.builder().id("1.5_to_3_lakh").title(isEnglish ? "₹1.5 to ₹3 Lakh" : "₹1.5 - ₹3 लाख").build(),
                        InteractiveMessage.Button.builder().id("above_3_lakh").title(isEnglish ? "Above ₹3 Lakh" : "₹3 लाख से ऊपर").build()
                ))
                .build();
    }

    private Object sendEligibleSchemeList(User user) {
        List<GovtScheme> eligibleSchemes = getEligibleSchemes(user);
        boolean isEnglish = "en".equals(user.getLanguage());

        if (eligibleSchemes.isEmpty()) {
            String body = isEnglish ? "Sorry, based on the information provided, I couldn't find any matching government schemes."
                    : "क्षमा करें, दी गई जानकारी के आधार पर, मुझे कोई मेल खाने वाली सरकारी योजना नहीं मिली।";
            user.setCurrentIntent("financial_guidance_menu");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(body)
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }

        user.setCurrentIntent("fg_govt_schemes_details");
        userService.updateUser(user);

        List<ListMessage.Row> rows = eligibleSchemes.stream()
                .map(scheme -> {
                    String title = scheme.getSchemeName();
                    if (title.length() > 24) {
                        title = title.substring(0, 21) + "...";
                    }
                    return ListMessage.Row.builder().id(scheme.getSchemeName()).title(title).build();
                })
                .collect(Collectors.toList());

        return ListMessage.builder()
                .header(isEnglish ? "Eligible Schemes" : "योग्य योजनाएं")
                .body(isEnglish ? "Please select a scheme from the list to see more details." : "अधिक विवरण देखने के लिए कृपया सूची में से एक योजना चुनें।")
                .buttonText(isEnglish ? "View Schemes" : "योजनाएं देखें")
                .sections(Collections.singletonList(
                        ListMessage.Section.builder().title("Government Schemes").rows(rows).build()
                ))
                .build();
    }

    private Object sendSchemeDetails(User user, String schemeName) {
        GovtScheme selectedScheme = getEligibleSchemes(user).stream()
                .filter(scheme -> scheme.getSchemeName().equals(schemeName))
                .findFirst()
                .orElse(null);

        user.setCurrentIntent("financial_guidance_menu");
        userService.updateUser(user);

        boolean isEnglish = "en".equals(user.getLanguage());

        if (selectedScheme == null) {
            return InteractiveMessage.builder()
                    .body(isEnglish ? "Sorry, I couldn't find the details for that scheme. Please try again." : "क्षमा करें, मुझे उस योजना का विवरण नहीं मिला। कृपया पुनः प्रयास करें।")
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }

        StringBuilder message = new StringBuilder();
        message.append("*Scheme:* ").append(selectedScheme.getSchemeName()).append("\n");
        message.append("*Coverage:* ").append(selectedScheme.getCoverage().replaceAll("<br>", "\n")).append("\n");
        message.append("*Eligibility:* ").append(selectedScheme.getEligibility()).append("\n");
        if (selectedScheme.getApplicationLink() != null && !selectedScheme.getApplicationLink().equalsIgnoreCase("Application is offline")) {
            message.append("*Apply Here:* ").append(selectedScheme.getApplicationLink()).append("\n");
        }

        return InteractiveMessage.builder()
                .body(message.toString())
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                ))
                .build();
    }

    private List<GovtScheme> getEligibleSchemes(User user) {
        List<GovtScheme> allSchemes = govtSchemeRepository.findAll();
        String userIncome = user.getIncomeRange();
        String userCity = user.getCity();

        log.info("Filtering {} schemes for user in city '{}' with income '{}'", allSchemes.size(), userCity, userIncome);

        return allSchemes.stream()
                .filter(scheme -> isIncomeEligible(scheme, userIncome))
                .filter(scheme -> isCityEligible(scheme, userCity))
                .collect(Collectors.toList());
    }

    private boolean isIncomeEligible(GovtScheme scheme, String userIncome) {
        String schemeIncome = scheme.getIncomeRange();
        if (schemeIncome == null || userIncome == null) return true;

        switch (userIncome) {
            case "below_1.5_lakh":
                return schemeIncome.contains("1.25") || schemeIncome.contains("1,25,000") || schemeIncome.contains("BPL") || schemeIncome.contains("1,60,000");
            case "1.5_to_3_lakh":
                return schemeIncome.contains("3,00,000") || schemeIncome.contains("No fixed");
            case "above_3_lakh":
                return schemeIncome.contains("No fixed");
            default:
                return true;
        }
    }

    private boolean isCityEligible(GovtScheme scheme, String userCity) {
        if (userCity == null || scheme.getHospitals() == null || scheme.getHospitals().isEmpty()) {
            return true;
        }

        boolean cityMentioned = false;
        for (Map<String, Object> hospitalEntry : scheme.getHospitals()) {
            if (hospitalEntry.containsKey(userCity)) {
                cityMentioned = true;
                Object cityValue = hospitalEntry.get(userCity);
                if (cityValue instanceof List) {
                    return true;
                }
                if (cityValue instanceof String) {
                    return false;
                }
            }
        }
        return !cityMentioned;
    }
}
