package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.entity.fr.Food;
import com.example.Tez_Yetkaz.entity.fr.Restaurant;
import com.example.Tez_Yetkaz.mapper.FoodMapper;
import com.example.Tez_Yetkaz.mapper.RestaurantMapper;
import com.example.Tez_Yetkaz.repository.FoodRepository;
import com.example.Tez_Yetkaz.repository.RestaurantRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchFoodAndRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final RestaurantMapper restaurantMapper;

    public ResponseData<?> searchFoodByName(String name) {
        List<Food> foods = this.foodRepository.findAllByNameLikeIgnoreCase(name);
        if (foods.isEmpty()) {
            return ResponseData.successResponse("No food found");
        }
        return ResponseData.successResponse(this.foodMapper.toDto(foods));
    }

    public ResponseData<?> searchRestaurantByName(String name) {
        List<Restaurant> restaurants = this.restaurantRepository.findAllByNameLikeIgnoreCase(name);
        if (restaurants.isEmpty()) {
            return ResponseData.successResponse("No restaurant found");
        }
        return ResponseData.successResponse(this.restaurantMapper.toDto(restaurants));
    }

}
