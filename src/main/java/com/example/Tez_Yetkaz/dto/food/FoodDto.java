package com.example.Tez_Yetkaz.dto.food;

import com.example.Tez_Yetkaz.entity.fr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    private UUID id;
    private String name;
    private String description;
    private Double price;
    private boolean active;
    private UUID restaurantId;

    private UUID attachmentId;
    private String uploadPath;

    private Category category;
}
