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
public class CreateRestaurantDto {

    private String name;
    private String address;
    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String description;
    private boolean active;

    private UUID categoryId;
}
