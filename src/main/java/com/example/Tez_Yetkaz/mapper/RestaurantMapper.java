package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.restaurant.CreateRestaurantDto;
import com.example.Tez_Yetkaz.dto.restaurant.RestaurantDto;
import com.example.Tez_Yetkaz.entity.fr.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    public Restaurant toEntity(CreateRestaurantDto createRestaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(createRestaurantDto.getName());
//        restaurant.setAddress(createRestaurantDto.getAddress());
        restaurant.setActive(createRestaurantDto.isActive());
        restaurant.setDescription(createRestaurantDto.getDescription());
//        restaurant.setPhone(createRestaurantDto.getPhone());
        restaurant.setCloseTime(createRestaurantDto.getCloseTime());
        restaurant.setOpenTime(createRestaurantDto.getOpenTime());
        restaurant.setDeliverAmount(createRestaurantDto.getDeliverAmount());
        return restaurant;
    }

    public RestaurantDto toDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setRestaurantId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
//        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setActive(restaurant.isActive());
        restaurantDto.setDescription(restaurant.getDescription());
//        restaurantDto.setPhone(restaurant.getPhone());
        restaurantDto.setCloseTime(restaurant.getCloseTime());
        restaurantDto.setOpenTime(restaurant.getOpenTime());
//        restaurantDto.setCategory(restaurant.getCategory());
//        restaurantDto.setFood(this.foodMapper.toDto(restaurant.getFood()));
        restaurantDto.setDeliverAmount(restaurant.getDeliverAmount());
        restaurantDto.setAttachmentId(restaurant.getAttachment());
        restaurantDto.setUploadPath("http://localhost:8080/api/v1/files/file-show/"+restaurant.getAttachment());
        return restaurantDto;
    }

    public Restaurant toUpdateEntity(Restaurant restaurant, CreateRestaurantDto restaurantDto) {
        if (restaurantDto.getName() != null) {
            restaurant.setName(restaurantDto.getName());
        }
//        if (restaurantDto.getAddress() != null) {
//            restaurant.setAddress(restaurantDto.getAddress());
//        }
        restaurant.setActive(restaurantDto.isActive());
        if (restaurantDto.getDescription() != null) {
            restaurant.setDescription(restaurantDto.getDescription());
        }
//        if (restaurantDto.getPhone() != null) {
//            restaurant.setPhone(restaurantDto.getPhone());
//        }
        if (restaurantDto.getCloseTime() != null) {
            restaurant.setCloseTime(restaurantDto.getCloseTime());
        }
        if (restaurantDto.getOpenTime() != null) {
            restaurant.setOpenTime(restaurantDto.getOpenTime());
        }
        if (restaurantDto.getDeliverAmount() != null) {
            restaurant.setDeliverAmount(restaurantDto.getDeliverAmount());
        }
        return restaurant;
    }

    public List<RestaurantDto> toDto(List<Restaurant> restaurants) {
        List<RestaurantDto> restaurantDto = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDto.add(toDto(restaurant));
        }
        return restaurantDto;
    }

}
