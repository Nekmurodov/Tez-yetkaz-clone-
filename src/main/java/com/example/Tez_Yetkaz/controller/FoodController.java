package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.food.CreateFoodDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/food/")
public class FoodController {

    private final FoodService foodService;

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<?> creat(@RequestBody CreateFoodDto createFoodDto,
                                 @Validated @RequestParam("file") MultipartFile multipartFile) {
        return this.foodService.create(createFoodDto, multipartFile);
    }

    @PutMapping(value = "update/{foodId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<?> update(@PathVariable UUID foodId, CreateFoodDto createFoodDto,
                                  @Validated @RequestParam("file") MultipartFile multipartFile) {
        return this.foodService.update(foodId, createFoodDto, multipartFile);
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

    @GetMapping("/get-all-by-restaurant/{restaurantId}")
    public ResponseData<?> getAllByRestaurantId(@PathVariable UUID restaurantId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return this.foodService.getAllByRestaurantId(restaurantId,page,size);
    }

    @GetMapping("/get-all-by-category/{categoryId}")
    public ResponseData<?> getAllByCategory(@PathVariable UUID categoryId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return this.foodService.getAllByCategoryId(categoryId,page,size);
    }

}
