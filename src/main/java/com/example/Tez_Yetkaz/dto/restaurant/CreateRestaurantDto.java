package com.example.Tez_Yetkaz.dto.restaurant;

import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantDto {

    @NonNull
    private String name;
//    private String address;
//    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String description;
    private boolean active;
    private Double deliverAmount;

    private UUID attachmentId;

    @NonNull
    private UUID categoryId;
}
