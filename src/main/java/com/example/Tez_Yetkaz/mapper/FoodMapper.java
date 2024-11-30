package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.food.CreateFoodDto;
import com.example.Tez_Yetkaz.dto.food.FoodDto;
import com.example.Tez_Yetkaz.entity.fr.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodMapper {

    public Food toEntity(CreateFoodDto createFoodDto) {
        Food food = new Food();
        food.setName(createFoodDto.getName());
        food.setDescription(createFoodDto.getDescription());
        food.setPrice(createFoodDto.getPrice());
        food.setActive(createFoodDto.isActive());
        return food;
    }

    public FoodDto toDto(Food food) {
        FoodDto foodDto = new FoodDto();
        foodDto.setId(food.getId());
        foodDto.setName(food.getName());
        foodDto.setDescription(food.getDescription());
        foodDto.setPrice(food.getPrice());
        foodDto.setActive(food.isActive());
        foodDto.setCategory(food.getCategory());
        foodDto.setRestaurantId(food.getRestaurant().getId());
        foodDto.setAttachmentId(food.getAttachment().getId());
        return foodDto;
    }

    public Food toUpdateEntity(Food food, CreateFoodDto foodDto) {
        if (foodDto.getName() != null)
            food.setName(foodDto.getName());
        if (foodDto.getDescription() != null)
            food.setDescription(foodDto.getDescription());
        if (foodDto.getPrice() != null)
            food.setPrice(foodDto.getPrice());
        food.setActive(food.isActive());
        return food;
    }

    public List<FoodDto> toDto(List<Food> foods) {
        List<FoodDto> foodDto = new ArrayList<>();
        for (Food food : foods) {
            foodDto.add(toDto(food));
        }
        return foodDto;
    }

}
