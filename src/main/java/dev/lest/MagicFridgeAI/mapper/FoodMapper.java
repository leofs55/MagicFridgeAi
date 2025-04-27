package dev.lest.MagicFridgeAI.mapper;

import dev.lest.MagicFridgeAI.dto.FoodDTO;
import dev.lest.MagicFridgeAI.model.FoodModel;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {

    public FoodDTO map(FoodModel foodModel) {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(foodModel.getId());
        foodDTO.setName(foodModel.getName());
        foodDTO.setCategory(foodModel.getCategory());
        foodDTO.setQuantity(foodModel.getQuantity());
        foodDTO.setValidity(foodModel.getValidity());

        return foodDTO;
    }

    public FoodModel map(FoodDTO foodDTO) {

        FoodModel foodModel = new FoodModel();
        foodModel.setId(foodDTO.getId());
        foodModel.setName(foodDTO.getName());
        foodModel.setCategory(foodDTO.getCategory());
        foodModel.setQuantity(foodDTO.getQuantity());
        foodModel.setValidity(foodDTO.getValidity());

        return foodModel;

    }
}
