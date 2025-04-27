package dev.lest.MagicFridgeAI.controller;

import ch.qos.logback.classic.joran.action.ReceiverAction;
import dev.lest.MagicFridgeAI.dto.FoodDTO;
import dev.lest.MagicFridgeAI.model.FoodModel;
import dev.lest.MagicFridgeAI.service.FoodService;
import dev.lest.MagicFridgeAI.service.GeminiService;
import dev.lest.MagicFridgeAI.service.GptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecipeController {

    private FoodService foodService;
    private GptService gptService;
    private GeminiService geminiService;

    public RecipeController(GptService gptService, GeminiService geminiService, FoodService foodService) {
        this.gptService = gptService;
        this.geminiService = geminiService;
        this.foodService = foodService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> genereteRecipe() {
        List<FoodDTO> foodDTOList = foodService.listFoods();
        return geminiService.genereteRecipe(foodDTOList)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
    
}
