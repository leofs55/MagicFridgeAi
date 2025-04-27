package dev.lest.MagicFridgeAI.controller;


import dev.lest.MagicFridgeAI.dto.FoodDTO;
import dev.lest.MagicFridgeAI.mapper.FoodMapper;
import dev.lest.MagicFridgeAI.model.FoodModel;
import dev.lest.MagicFridgeAI.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/create")
    public ResponseEntity<FoodDTO> create(@RequestBody FoodDTO foodDTO) {
        FoodDTO foodSaved = foodService.saveFood(foodDTO);
        return ResponseEntity.ok(foodSaved);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FoodDTO>> list() {
        List<FoodDTO> foodDTOList = foodService.listFoods();
        return ResponseEntity.ok(foodDTOList);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> showPerId(@PathVariable Long id) {
        return foodService.showFood(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FoodDTO foodDTO) {
        return foodService.changeFood(id, foodDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return foodService.deleteFood(id);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteAll() { return foodService.deleteAll(); }
}
