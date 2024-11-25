package com.example.Tez_Yetkaz.entity.fr;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import com.example.Tez_Yetkaz.enums.CategoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category extends AbsEntity {
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

}
