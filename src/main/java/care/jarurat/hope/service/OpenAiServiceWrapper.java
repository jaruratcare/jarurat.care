package care.jarurat.hope.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;


import okhttp3.OkHttpClient;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceWrapper {

    private final OpenAiService openAiService;

    public OpenAiServiceWrapper(@Value("${openai.api.key}") String apiKey) {

    this.openAiService = new OpenAiService(apiKey,Duration.ofSeconds(200));
}

    public String generateMealPlan(String preferences) {
        try {
            ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4o-mini-2024-07-18")
                .messages(List.of(
                    new ChatMessage("system", "You are a nutritionist who creates healthy Indian meal plans."),
                    new ChatMessage("user", preferences)
                ))
                .temperature(0.7)
                .maxTokens(400)
                .build();

            return openAiService.createChatCompletion(request)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent()
                .trim();

        } catch (com.theokanning.openai.OpenAiHttpException e) {
            if (e.getMessage().contains("quota")) {
                return "⚠️ Sorry, we are currently unable to generate your meal plan because our AI service quota has been exceeded. Please try again later or type 'Hi' to return to main menu.";
            }
            throw e;
        }
    }
}
