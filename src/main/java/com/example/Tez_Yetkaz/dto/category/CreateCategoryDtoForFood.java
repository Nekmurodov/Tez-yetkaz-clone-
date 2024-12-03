package com.example.Tez_Yetkaz.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryDtoForFood {

    @NotNull
    @Unique
    private String name;
    private String description;

    private UUID attachmentId;
    private UUID restaurantId;

}
