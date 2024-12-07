package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.category.CreateCategoryDto;
import com.example.Tez_Yetkaz.dto.category.CreateCategoryDtoForFood;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category/")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create-for-restaurant")
    public ResponseData<?> creatForRestaurant(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        return this.categoryService.createForRestaurant(createCategoryDto);
    }

    @PostMapping("/create-for-restaurant-food")
    public ResponseData<?> creatForRestaurantFood(@RequestBody @Valid CreateCategoryDtoForFood createCategoryDto) {
        return this.categoryService.createForRestaurantFood(createCategoryDto);
    }

    @PutMapping("update-for-restaurant/{categoryId}")
    public ResponseData<?> updateForRestaurant(@PathVariable UUID categoryId, @RequestBody @Valid CreateCategoryDto createCategoryDto) {
        return this.categoryService.updateForRestaurant(categoryId, createCategoryDto);
    }

    @PutMapping("update-for-restaurant-food/{categoryId}")
    public ResponseData<?> updateForFood(@PathVariable UUID categoryId, @RequestBody @Valid CreateCategoryDtoForFood createCategoryDto) {
        return this.categoryService.updateForFood(categoryId, createCategoryDto);
    }

    @GetMapping("get/{categoryId}")
    public ResponseData<?> get(@PathVariable UUID categoryId) {
        return this.categoryService.get(categoryId);
    }

    @GetMapping("get-all-for-restaurant/{restaurantId}")
    public ResponseData<?> getAllByRestaurantId(@PathVariable UUID restaurantId) {
        return this.categoryService.getAllByRestaurantId(restaurantId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/get-all-by-food")
    public ResponseData<?> getAllByFood() {
        return this.categoryService.getAllByFood();
    }

    @GetMapping("/get-all-by-restaurant")
    public ResponseData<?> getAllByRestaurant() {
        return this.categoryService.getAllByRestaurant();
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseData<?> delete(@PathVariable UUID categoryId) {
        return this.categoryService.delete(categoryId);
    }

}
