package dev.lest.MagicFridgeAI.model;


import dev.lest.MagicFridgeAI.Enums.FoodCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_foods")
@NoArgsConstructor
@AllArgsConstructor
public class FoodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
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

    public void setName(String name) {
        this.name = name;
    }

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
