package dev.lest.MagicFridgeAI.dto;

import dev.lest.MagicFridgeAI.Enums.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {

    private long id;

    private String name;

    private FoodCategory category;

    private Integer quantity;

    private LocalDateTime validity;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getValidity() {
        return validity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }

}
