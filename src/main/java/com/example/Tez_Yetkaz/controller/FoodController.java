package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.food.CreateFoodDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/food/")
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/create")
    public ResponseData<?> creat(@RequestBody CreateFoodDto createFoodDto) {
        return this.foodService.create(createFoodDto);
    }

    @PutMapping("update/{foodId}")
    public ResponseData<?> update(@PathVariable UUID foodId, CreateFoodDto createFoodDto) {
        return this.foodService.update(foodId, createFoodDto);
    }

    @GetMapping("get/{foodId}")
    public ResponseData<?> get(@PathVariable UUID foodId) {
        return this.foodService.get(foodId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.foodService.getAll(page,size);
    }

    @DeleteMapping("delete/{foodId}")
    public ResponseData<?> delete(@PathVariable UUID foodId) {
        return this.foodService.delete(foodId);
    }

}
