package dev.lest.MagicFridgeAI.service;

import dev.lest.MagicFridgeAI.dto.FoodDTO;
import dev.lest.MagicFridgeAI.model.FoodModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final WebClient webClient;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    //    curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=YOUR_API_KEY" \
//  -H 'Content-Type: application/json' \
//  -X POST \
//  -d '{"contents": [
//  {"parts": [
//  {"text": "Explain how AI works in a few words"}]}]}'


    //    public Mono<String> genereteRecipe(){
//

    public Mono<String> genereteRecipe(List<FoodDTO> foodDTOList){

        String alimentos = foodDTOList.stream()
                .map(food -> String.format("Nome: %s, Quantidade: %d, Validade: %s, Categoria: %s",
                        food.getName(), food.getQuantity(), food.getValidity(), food.getCategory()))
                .collect(Collectors.joining("\n"));

        String prompt = "Me Sugira uma receita baseada excluivamento nos seguintes itens de  uma lista faça uma receita: " + alimentos;
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of(
                                        "text", prompt
                                )
                        ))
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response ->  {
                    var candidates = (List<Map<String, Map<String,List<Map<String, Object>>>>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        Map<String, Object> text = (Map<String, Object>) candidates.get(0).get("content").get("parts").get(0);
                        return text.get("text").toString();
                    }
                    return "Nenhuma receita foi gerada!";
                });
                //        "candidates": [{"content": {"parts": [{"text": "Aqui está a resposta do modelo."} }]
                //
    }
//    }


}
