package com.example.Tez_Yetkaz.entity;

import com.example.Tez_Yetkaz.entity.fr.Food;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order extends AbsEntity {

    private boolean status;
    private boolean deliver;
    private String location;
    private LocalTime orderTime;
    private String description;

    private UUID userId;

    @OneToMany
    private List<Food> foods;

    private Double foodsAmount;
    private Double deliverAmount;

    private Double allAmount;

}
