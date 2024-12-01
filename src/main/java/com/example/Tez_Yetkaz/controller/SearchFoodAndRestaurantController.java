package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.SearchFoodAndRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/search-food-and-restaurant/")
public class SearchFoodAndRestaurantController {

    private final SearchFoodAndRestaurantService searchFoodAndRestaurantService;

    @GetMapping("get-food")
    public ResponseData<?> searchFood(@RequestParam String foodName) {
        return this.searchFoodAndRestaurantService.searchFoodByName(foodName);
    }

    @GetMapping("get-restaurant")
    public ResponseData<?> searchRestaurant(@RequestParam String restaurantName) {
        return this.searchFoodAndRestaurantService.searchRestaurantByName(restaurantName);
    }

}
