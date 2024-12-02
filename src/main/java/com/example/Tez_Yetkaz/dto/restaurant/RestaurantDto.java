package com.example.Tez_Yetkaz.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private UUID restaurantId;
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String description;
    private boolean active;
    private Double deliverAmount;

    private UUID attachmentId;
    private String uploadPath;


}
