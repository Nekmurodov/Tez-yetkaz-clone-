package com.example.Tez_Yetkaz.entity;

import com.example.Tez_Yetkaz.entity.fr.Food;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodForOrder extends AbsEntity{
    @OneToOne
    private Food food;
    private int count;
}
