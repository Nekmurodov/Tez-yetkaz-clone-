package com.example.Tez_Yetkaz.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private UUID orderId;
    private boolean status;
    private boolean deliver;
    private String location;
    private String description;

    private UUID userId;

    private List<FoodForOrderDto> foods;

    private Double foodsAmount;
    private Double deliverAmount;
    private Double allAmount;

    private String restaurantName;
    private String uploadPath;
    private UUID attachmentId;

}
