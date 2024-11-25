package com.example.Tez_Yetkaz.entity.fr;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Food extends AbsEntity {

    private String name;
    private String description;
    private Integer price;
    private boolean active;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne(optional = false)
    private Category category;
}
