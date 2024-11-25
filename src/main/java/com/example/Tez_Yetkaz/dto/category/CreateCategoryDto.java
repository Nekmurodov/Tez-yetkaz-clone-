package com.example.Tez_Yetkaz.dto.category;

import com.example.Tez_Yetkaz.enums.CategoryType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryDto {

    @NotNull
    private String name;
    private String description;

    @NotNull
    private CategoryType categoryType;

}
