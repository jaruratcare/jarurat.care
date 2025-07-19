package care.jarurat.hope.service;

import care.jarurat.hope.model.SupportModule;
import care.jarurat.hope.repository.ResourceRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SupportModuleService {

    private final ResourceRepository resourceRepository;
    private final WhatsAppService whatsappService;
    private final UserService userService;

    public SupportModuleService(ResourceRepository resourceRepository,
                                @Lazy WhatsAppService whatsappService,
                                UserService userService) {
        this.resourceRepository = resourceRepository;
        this.whatsappService = whatsappService;
        this.userService = userService;

    }
    public void handleModule(String userId, String option) {
        String key = switch (option.trim()) {
            case "1" -> "financial";
            case "2" -> "nutrition";
            case "3" -> "emotional";
            case "4" -> "hospitals";
            case "5" -> "stay_food";
            case "6" -> "labs";
            case "7" -> "palliative";
            case "8" -> "volunteer";
            default -> null;
        };

        if (key == null) {
            whatsappService.sendTextMessage(userId, "❌ Invalid option. Please choose from 1 to 8.");
            return;
        }

        String lang = userService.getUser(userId).getLanguage(); // "English" or "Hindi"
        lang = lang.equalsIgnoreCase("Hindi") ? "hi" : "en";      // Convert to lang code

        SupportModule module = resourceRepository.getSupportModule(key, lang);

        if (module == null) {
            whatsappService.sendTextMessage(userId, "⚠️ This module is currently unavailable.");
            return;
        }

        String response = "📌 *" + module.getTitle() + "*\n\n" + module.getContent();
        whatsappService.sendTextMessage(userId, response);
    }
}

