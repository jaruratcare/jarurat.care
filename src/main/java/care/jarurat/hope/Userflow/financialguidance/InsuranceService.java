package care.jarurat.hope.Userflow.financialguidance;

import care.jarurat.hope.model.InteractiveMessage;
import care.jarurat.hope.model.ListMessage;
import care.jarurat.hope.model.User;
import care.jarurat.hope.model.financials.Hospital;
import care.jarurat.hope.model.financials.Policy;
import care.jarurat.hope.repository.financials.HospitalRepository;
import care.jarurat.hope.repository.financials.PolicyRepository;
import care.jarurat.hope.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final UserService userService;
    private final PolicyRepository policyRepository;
    private final HospitalRepository hospitalRepository;

    public Object handleInsurance(User user, String input) {
        String intent = user.getCurrentIntent() != null ? user.getCurrentIntent() : "fg_insurance_start";

        switch (intent) {
            case "fg_insurance_start":
                return askHasPolicy(user);

            case "fg_insurance_has_policy":
                return handleHasPolicyResponse(user, input);

            case "fg_insurance_provider_name":
                return handleProviderName(user, input);

            case "fg_insurance_apply_type":
                return handleApplyPolicyTypeResponse(user, input);

            case "fg_insurance_find_hospitals":
                // This intent is reached after a policy is matched (Yes path)
                // or after a policy is selected from a list (No path, if we add that option)
                if ("find_hospitals".equals(input)) {
                    return askForCity_Hospital(user);
                } else {
                    // User chose "Back to Menu"
                    user.setCurrentIntent("financial_guidance_menu");
                    userService.updateUser(user);
                    return "Returning to the Financial Guidance menu.";
                }

            case "fg_insurance_hospitals_city":
                return sendHospitalList(user, input);

            default:
                return askHasPolicy(user);
        }
    }

    private Object askHasPolicy(User user) {
        user.setCurrentIntent("fg_insurance_has_policy");
        userService.updateUser(user);
        boolean isEnglish = "en".equals(user.getLanguage());

        return InteractiveMessage.builder()
                .body(isEnglish ? "Do you already have a health insurance policy?" : "क्या आपके पास पहले से स्वास्थ्य बीमा पॉलिसी है?")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("yes_policy").title(isEnglish ? "Yes" : "हाँ").build(),
                        InteractiveMessage.Button.builder().id("no_policy").title(isEnglish ? "No" : "नहीं").build()
                ))
                .build();
    }

    private Object handleHasPolicyResponse(User user, String input) {
        boolean isEnglish = "en".equals(user.getLanguage());

        if ("yes_policy".equals(input)) {
            user.setCurrentIntent("fg_insurance_provider_name");
            userService.updateUser(user);
            return isEnglish ? "Please share your insurance provider's name (e.g., Star Health, LIC)." : "कृपया अपने बीमा प्रदाता का नाम बताएं (उदाहरण: स्टार हेल्थ, एलआईसी)।";
        } else if ("no_policy".equals(input)) {
            return askApplyPolicyType(user);
        } else {
            return askHasPolicy(user); // Re-prompt if invalid input
        }
    }

    private Object handleProviderName(User user, String providerName) {
        boolean isEnglish = "en".equals(user.getLanguage());
        List<Hospital> allHospitals = hospitalRepository.findAll();

        Optional<String> matchedPolicyName = allHospitals.stream()
                .map(Hospital::getPolicyName)
                .filter(name -> name.toLowerCase().contains(providerName.toLowerCase()))
                .findFirst();

        if (matchedPolicyName.isPresent()) {
            // Store the selected policy name for the next step
            user.setTempPolicySelection(matchedPolicyName.get());
            // Directly ask for city to find hospitals
            return askForCity_Hospital(user);
        } else {
            user.setCurrentIntent("financial_guidance_menu");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(isEnglish ? "Sorry, I couldn't find a policy matching that provider name. Please try again or select from the main menu." : "क्षमा करें, मुझे उस प्रदाता नाम से मेल खाने वाली कोई पॉलिसी नहीं मिली। कृपया पुनः प्रयास करें या मुख्य मेनू से चुनें।")
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }
    }

    private Object askApplyPolicyType(User user) {
        user.setCurrentIntent("fg_insurance_apply_type");
        userService.updateUser(user);
        boolean isEnglish = "en".equals(user.getLanguage());

        return InteractiveMessage.builder()
                .body(isEnglish ? "What type of policy would you like to know about for applying?" : "आप आवेदन करने के लिए किस प्रकार की पॉलिसी के बारे में जानना चाहेंगे?")
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("Govt PSU").title(isEnglish ? "Government (PSU)" : "सरकारी (पीएसयू)").build(),
                        InteractiveMessage.Button.builder().id("Private").title(isEnglish ? "Private" : "निजी").build()
                ))
                .build();
    }

    private Object handleApplyPolicyTypeResponse(User user, String policyType) {
        boolean isEnglish = "en".equals(user.getLanguage());
        List<Policy> policies = policyRepository.findByGovtOrPrivate(policyType);
        log.info("Found {} policies of type {}", policies.size(), policyType);

        if (policies.isEmpty()) {
            user.setCurrentIntent("financial_guidance_menu");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(isEnglish ? "Sorry, I couldn't find any policies of that type." : "क्षमा करें, मुझे उस प्रकार की कोई पॉलिसी नहीं मिली।")
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }

        StringBuilder message = new StringBuilder(isEnglish ? "Here's how to apply for some " + policyType + " policies:\n\n" : "यहां कुछ " + policyType + " पॉलिसियों के लिए आवेदन कैसे करें:\n\n");

        // Summarize application details for a few policies of the chosen type
        policies.stream().limit(2).forEach(policy -> {
            message.append("*Policy:* ").append(policy.getPolicyName()).append("\n");
            message.append("*Coverage:* ").append(policy.getCoverage()).append("\n");
            message.append("*Claim Steps:* ").append(policy.getClaimSteps()).append("\n");
            if (policy.getApplicationLink() != null && !policy.getApplicationLink().isBlank()) {
                message.append("*Apply Here:* ").append(policy.getApplicationLink()).append("\n");
            }
            if (policy.getApplicationGuide() != null && !policy.getApplicationGuide().isBlank()) {
                message.append("*Application Guide:* ").append(policy.getApplicationGuide()).append("\n");
            }
            if (policy.getRequiredDocs() != null && !policy.getRequiredDocs().isBlank()) {
                message.append("*Required Docs:* ").append(policy.getRequiredDocs()).append("\n");
            }
            message.append("\n---\n\n");
        });

        String finalMessage = message.toString();
        if (finalMessage.length() > 1024) {
            finalMessage = finalMessage.substring(0, 1020) + "...";
        }

        user.setCurrentIntent("financial_guidance_menu");
        userService.updateUser(user);

        return InteractiveMessage.builder()
                .body(finalMessage)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                ))
                .build();
    }

    // This method is now only used for the 'Yes' path to show details before finding hospitals
    private Object sendPolicyDetails(User user, String policyName) {
        Policy selectedPolicy = policyRepository.findAll().stream()
                .filter(p -> p.getPolicyName().equals(policyName))
                .findFirst().orElse(null);

        boolean isEnglish = "en".equals(user.getLanguage());

        if (selectedPolicy == null) {
            user.setCurrentIntent("financial_guidance_menu");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(isEnglish ? "Sorry, I couldn't find the details for that policy. Please try again." : "क्षमा करें, मुझे उस पॉलिसी का विवरण नहीं मिला। कृपया पुनः प्रयास करें।")
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }

        // Store the selected policy name for the next step (Find Hospitals)
        user.setTempPolicySelection(selectedPolicy.getPolicyName());
        user.setCurrentIntent("fg_insurance_find_hospitals");
        userService.updateUser(user);

        StringBuilder message = new StringBuilder();
        message.append("*Policy:* ").append(selectedPolicy.getPolicyName()).append("\n");
        message.append("*Coverage:* ").append(selectedPolicy.getCoverage()).append("\n");
        message.append("*Eligibility:* ").append(selectedPolicy.getEligibility()).append("\n");
        message.append("*Claim Steps:* ").append(selectedPolicy.getClaimSteps()).append("\n");
        if (selectedPolicy.getApplicationLink() != null && !selectedPolicy.getApplicationLink().isBlank()) {
            message.append("*Apply Here:* ").append(selectedPolicy.getApplicationLink()).append("\n");
        }
        if (selectedPolicy.getApplicationGuide() != null && !selectedPolicy.getApplicationGuide().isBlank()) {
            message.append("*Application Guide:* ").append(selectedPolicy.getApplicationGuide()).append("\n");
        }
        if (selectedPolicy.getRequiredDocs() != null && !selectedPolicy.getRequiredDocs().isBlank()) {
            message.append("*Required Docs:* ").append(selectedPolicy.getRequiredDocs()).append("\n");
        }

        String finalMessage = message.toString();
        if (finalMessage.length() > 1024) {
            finalMessage = finalMessage.substring(0, 1020) + "...";
        }

        return InteractiveMessage.builder()
                .body(finalMessage)
                .buttons(Arrays.asList(
                        InteractiveMessage.Button.builder().id("find_hospitals").title(isEnglish ? "Find Hospitals" : "अस्पताल खोजें").build(),
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                ))
                .build();
    }

    private Object askForCity_Hospital(User user) {
        boolean isEnglish = "en".equals(user.getLanguage());
        if (user.getCity() != null && !user.getCity().isEmpty()) {
            return sendHospitalList(user, user.getCity());
        }
        user.setCurrentIntent("fg_insurance_hospitals_city");
        userService.updateUser(user);
        return isEnglish ? "To find hospitals, please tell me your city." : "अस्पताल खोजने के लिए, कृपया मुझे अपना शहर बताएं।";
    }

    private Object sendHospitalList(User user, String city) {
        String policyName = user.getTempPolicySelection();
        boolean isEnglish = "en".equals(user.getLanguage());

        if (policyName == null) {
            user.setCurrentIntent("financial_guidance_menu");
            userService.updateUser(user);
            return InteractiveMessage.builder()
                    .body(isEnglish ? "Sorry, something went wrong. Please start over from the financial guidance menu." : "क्षमा करें, कुछ गलत हो गया। कृपया वित्तीय मार्गदर्शन मेनू से फिर से शुरू करें।")
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }

        List<Hospital> hospitals = hospitalRepository.findByPolicyNameAndCity(policyName, city);

        // Clear the temporary selection and reset intent
        user.setTempPolicySelection(null);
        user.setCurrentIntent("financial_guidance_menu");
        userService.updateUser(user);

        if (hospitals == null || hospitals.isEmpty()) {
            return InteractiveMessage.builder()
                    .body(String.format(isEnglish ? "Sorry, I couldn't find any hospitals in %s that accept the %s policy." : "क्षमा करें, मुझे %s में कोई भी अस्पताल नहीं मिला जो %s पॉलिसी स्वीकार करता हो।", city, policyName))
                    .buttons(Collections.singletonList(
                            InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                    ))
                    .build();
        }

        StringBuilder message = new StringBuilder(String.format(isEnglish ? "Here are hospitals in %s that accept %s:\n\n" : "%s में कुछ अस्पताल हैं जो %s स्वीकार करते हैं:\n\n", city, policyName));
        for (Hospital hospital : hospitals) {
            message.append("*Name:* ").append(hospital.getHospitalName()).append("\n");
            if (hospital.getAddress() != null) message.append("*Address:* ").append(hospital.getAddress()).append("\n");
            if (hospital.getContact() != null) message.append("*Contact:* ").append(hospital.getContact()).append("\n");
            message.append("\n---\n\n");
        }

        String finalMessage = message.toString();
        if (finalMessage.length() > 1024) {
            finalMessage = finalMessage.substring(0, 1020) + "...";
        }

        return InteractiveMessage.builder()
                .body(finalMessage)
                .buttons(Collections.singletonList(
                        InteractiveMessage.Button.builder().id("back_to_financial_menu").title(isEnglish ? "Back to Menu" : "वापस मेनू में").build()
                ))
                .build();
    }
}