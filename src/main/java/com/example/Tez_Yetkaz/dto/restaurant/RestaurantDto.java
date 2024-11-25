package com.example.Tez_Yetkaz.dto.restaurant;

import com.example.Tez_Yetkaz.entity.fr.Category;
import com.example.Tez_Yetkaz.entity.fr.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private UUID restaurantId;
    private String name;
    private String address;
    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String description;
    private boolean active;

    private List<Food> food;

    private Category category;
}
