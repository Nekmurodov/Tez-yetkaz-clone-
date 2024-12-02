package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.category.CategoryDto;
import com.example.Tez_Yetkaz.dto.category.CreateCategoryDto;
import com.example.Tez_Yetkaz.entity.fr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    public Category toEntity(CreateCategoryDto createCategoryDto) {
        Category category = new Category();
        category.setName(createCategoryDto.getName());
        category.setDescription(createCategoryDto.getDescription());
        category.setCategoryType(createCategoryDto.getCategoryType());
        return category;
    }

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCategoryType(category.getCategoryType());
        categoryDto.setAttachmentId(category.getAttachmentId());
        categoryDto.setUploadPath("http://localhost:8080/api/v1/files/file-show/"+category.getAttachmentId());
        return categoryDto;
    }

    public List<CategoryDto> toDto(List<Category> categoryList) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDtoList.add(toDto(category));
        }
        return categoryDtoList;
    }

}
