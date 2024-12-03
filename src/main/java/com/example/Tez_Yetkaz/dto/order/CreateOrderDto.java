package com.example.Tez_Yetkaz.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {

    private String location;
    private List<FoodForOrderDto> foods;
    private String description;

    private Double foodsAmount;
    private Double deliverAmount;
    private Double allAmount;

}
