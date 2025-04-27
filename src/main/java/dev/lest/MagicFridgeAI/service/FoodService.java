package dev.lest.MagicFridgeAI.service;

import dev.lest.MagicFridgeAI.dto.FoodDTO;
import dev.lest.MagicFridgeAI.mapper.FoodMapper;
import dev.lest.MagicFridgeAI.model.FoodModel;
import dev.lest.MagicFridgeAI.repository.FoodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodService {

    private final FoodMapper foodMapper;
    private final FoodRepository foodRepository;


    public FoodService(FoodMapper foodMapper, FoodRepository foodRepository) {
        this.foodMapper = foodMapper;
        this.foodRepository = foodRepository;
    }

    public FoodDTO saveFood(FoodDTO foodDTO) {
        FoodModel foodModel = foodMapper.map(foodDTO);
        FoodModel foodSaved = foodRepository.save(foodModel);
        return foodMapper.map(foodSaved);
    }

    public List<FoodDTO> listFoods() {
        List<FoodModel> foodModels = foodRepository.findAll();
        return foodModels.stream()
                .map(foodMapper::map)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> showFood(Long id) {
        Optional<FoodModel> foodModel = foodRepository.findById(id);
        if (foodModel.isPresent()) {
            return ResponseEntity.ok(foodModel.map(foodMapper::map).orElse(null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado o alimento!");
    }

    public ResponseEntity<?> changeFood(Long id, FoodDTO foodDto) {
        return foodRepository.findById(id)
                .map(existingItem -> {
                    foodDto.setId(existingItem.getId());
                    FoodModel atualizado = foodRepository.save(foodMapper.map(foodDto));
                    return ResponseEntity.ok(atualizado);
                    }).
                orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteFood(Long id) {
        Optional<FoodModel> foodModel = foodRepository.findById(id);
        if (foodModel.isPresent()) {
            foodRepository.delete(foodModel.get());
            return ResponseEntity.ok("Alimento deletado!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado o alimento!");
    }

    public ResponseEntity<?> deleteAll() {
        foodRepository.deleteAll();
        return ResponseEntity.ok("Alimentos deletados!");
    }

}
