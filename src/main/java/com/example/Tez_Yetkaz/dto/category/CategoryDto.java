package com.example.Tez_Yetkaz.dto.category;

import com.example.Tez_Yetkaz.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private UUID id;
    private String name;
    private String description;

    private UUID attachmentId;
    private String uploadPath;

    private CategoryType categoryType;
}
