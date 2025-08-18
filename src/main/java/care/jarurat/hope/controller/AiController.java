package care.jarurat.hope.controller;

import care.jarurat.hope.service.OpenAiServiceWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final OpenAiServiceWrapper aiService;

    public AiController(OpenAiServiceWrapper aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String prompt) {
        return aiService.generateMealPlan(prompt);
    }
}
