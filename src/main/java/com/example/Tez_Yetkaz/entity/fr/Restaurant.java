package com.example.Tez_Yetkaz.entity.fr;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Restaurant extends AbsEntity {

    private String name;
//    private String address;
//    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String description;
    private boolean active;
    private Double deliverAmount;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> food;

    @ManyToOne(optional = false)
    private Category category;

}
