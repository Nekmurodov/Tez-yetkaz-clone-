package com.example.Tez_Yetkaz.entity.fr;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Food extends AbsEntity {

    private String name;
    private String description;
    private Double price;
    private boolean active;

    private UUID attachment;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne(optional = false)
    private Category category;
}
