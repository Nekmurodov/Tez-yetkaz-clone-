package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.category.CreateCategoryDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category/")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseData<?> creat(@RequestBody CreateCategoryDto createCategoryDto) {
        return this.categoryService.create(createCategoryDto);
    }

    @PutMapping("update/{categoryId}")
    public ResponseData<?> update(@PathVariable UUID categoryId, CreateCategoryDto createCategoryDto) {
        return this.categoryService.update(categoryId, createCategoryDto);
    }

    @GetMapping("get/{categoryId}")
    public ResponseData<?> get(@PathVariable UUID categoryId) {
        return this.categoryService.get(categoryId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll() {
        return this.categoryService.getAll();
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseData<?> delete(@PathVariable UUID categoryId) {
        return this.categoryService.delete(categoryId);
    }

}
