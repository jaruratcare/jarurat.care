package care.jarurat.hope.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class OpenAiServiceWrapper {

    private final OpenAiService openAiService;

    public OpenAiServiceWrapper(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(200));
    }

    public String generateResponse(String systemPrompt, String userPrompt, double temperature, int maxTokens) {
        try {
            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model("gpt-4o-mini-2024-07-18")
                    .messages(List.of(
                            new ChatMessage("system", systemPrompt),
                            new ChatMessage("user", userPrompt)
                    ))
                    .temperature(temperature)
                    .maxTokens(maxTokens)
                    .build();

            return openAiService.createChatCompletion(request)
                    .getChoices()
                    .get(0)
                    .getMessage()
                    .getContent()
                    .trim();

        } catch (com.theokanning.openai.OpenAiHttpException e) {
            log.error("OpenAI error", e);
            if (e.getMessage().contains("quota")) {
                return "⚠️ AI service quota exceeded. Please try again later.";
            }
            throw e;
        }
    }
}
