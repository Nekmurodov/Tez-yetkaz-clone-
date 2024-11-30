package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.SearchFoodAndRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/search-food-and-restaurant/")
public class SearchFoodAndRestaurantController {

    private final SearchFoodAndRestaurantService searchFoodAndRestaurantService;

    @GetMapping("get/{foodName}")
    public ResponseData<?> searchFood(@PathVariable String foodName) {
        return this.searchFoodAndRestaurantService.searchFoodByName(foodName);
    }

    @GetMapping("get/{restaurantName}")
    public ResponseData<?> searchRestaurant(@PathVariable String restaurantName) {
        return this.searchFoodAndRestaurantService.searchRestaurantByName(restaurantName);
    }

}
