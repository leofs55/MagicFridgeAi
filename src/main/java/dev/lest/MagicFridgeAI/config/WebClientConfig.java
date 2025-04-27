package dev.lest.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${gemini.api.url}${gemini.key}")
    private String chatGptUrl;

    @Bean
    public WebClient webClient( WebClient.Builder builder) {
        return builder.baseUrl(chatGptUrl).build();
    }

    public String getChatGptUrl() {
        return chatGptUrl;
    }
}
