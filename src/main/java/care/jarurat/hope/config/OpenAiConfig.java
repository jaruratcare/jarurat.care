package care.jarurat.hope.config;

import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Bean
    public OpenAiService openAiService() {

        return new OpenAiService(openaiApiKey,Duration.ofSeconds(200));
    }
}
