package com.example.Tez_Yetkaz.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

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
    private String description;

    private UUID userId;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<FoodForOrder> foods;

    private Double foodsAmount;
    private Double deliverAmount;

    private Double allAmount;

}
