package dev.lest.MagicFridgeAI.service;

import dev.lest.MagicFridgeAI.config.WebClientConfig;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
public class GptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("GPT_KEY");

    public GptService(WebClient webClient) {
        this.webClient = webClient;
    }

//    curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=YOUR_API_KEY" \
//  -H 'Content-Type: application/json' \
//  -X POST \
//  -d '{"contents": [
//  {"parts": [
//  {"text": "Explain how AI works in a few words"}]}]}'

    public Mono<String> genereteRecipe() {
        String prompt = "Sugira uma receita aleatoria";
        Map<String, Object> resquestBody = Map.of(
                "model", "gpt-4o-mini",
                "store", true,
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(resquestBody)
                .retrieve()
                .bodyToMono(String.class);
//                .map(response -> {
//                    var choices = (List<Map<String, Object>>) response.get("choices");
//                    if (choices != null && !choices.isEmpty()) {
//                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
//                        return message.get("content").toString();
//                    }
//                    return "Nenhuma receita foi gerada!";
//                });
    }

}

