package com.example.Tez_Yetkaz.entity.fr;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import com.example.Tez_Yetkaz.enums.CategoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category extends AbsEntity {
    @NonNull
    private String name;
    private String description;

    private UUID attachmentId;
    private UUID restaurantId;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

}
