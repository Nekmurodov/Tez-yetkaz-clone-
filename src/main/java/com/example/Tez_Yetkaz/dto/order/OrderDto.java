package com.example.Tez_Yetkaz.dto.order;

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
public class OrderDto {

    private UUID orderId;
    private boolean status;
    private boolean deliver;
    private String location;
    private LocalTime orderTime;
    private String description;

    private UUID userId;

    private List<Food> foods;

    private Double foodsAmount;
    private Double deliverAmount;
    private Double allAmount;

}
