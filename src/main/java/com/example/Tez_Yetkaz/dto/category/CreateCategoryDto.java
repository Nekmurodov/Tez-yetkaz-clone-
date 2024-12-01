package com.example.Tez_Yetkaz.dto.category;

import com.example.Tez_Yetkaz.enums.CategoryType;
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
public class CreateCategoryDto {

    @NotNull
    @Unique
    private String name;
    private String description;

    private UUID attachmentId;

    @NotNull
    private CategoryType categoryType;

}
