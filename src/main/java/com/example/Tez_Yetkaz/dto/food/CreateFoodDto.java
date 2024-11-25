package com.example.Tez_Yetkaz.dto.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFoodDto {

    private String name;
    private String description;
    private Integer price;
    private boolean active;
    private UUID restaurantId;

    private UUID categoryId;
}
