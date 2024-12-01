package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.dto.category.CreateCategoryDto;
import com.example.Tez_Yetkaz.entity.fr.Attachment;
import com.example.Tez_Yetkaz.entity.fr.Category;
import com.example.Tez_Yetkaz.enums.CategoryType;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.mapper.CategoryMapper;
import com.example.Tez_Yetkaz.repository.AttachmentRepository;
import com.example.Tez_Yetkaz.repository.CategoryRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final AttachmentRepository attachmentRepository;
    private final FileService fileService;

    public ResponseData<?> create(CreateCategoryDto createCategoryDto) {
        Optional<Attachment> attachment = this.attachmentRepository.findById(createCategoryDto.getAttachmentId());
        if (attachment.isEmpty()){
            throw new NotFoundException("Attachment not found");
        }
        Category category = this.categoryMapper.toEntity(createCategoryDto);
        category.setAttachmentId(attachment.get().getId());
        this.categoryRepository.save(category);
        return ResponseData.successResponse(this.categoryMapper.toDto(category));
    }

    public ResponseData<?> update(UUID categoryId, CreateCategoryDto createCategoryDto) {
        Optional<Category> categoryOptional = this.categoryRepository.findByIdAndDeletedFalse(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        Optional<Attachment> attachment = this.attachmentRepository.findById(createCategoryDto.getAttachmentId());
        if (attachment.isEmpty()){
            throw new NotFoundException("Attachment not found");
        }
        Category category = this.categoryMapper.toEntity(createCategoryDto);
        if (categoryOptional.get().getAttachmentId() == null) {
            category.setAttachmentId(createCategoryDto.getAttachmentId());
        }
        else if (createCategoryDto.getAttachmentId() !=null && fileService.deleteFile(categoryOptional.get().getAttachmentId())){
            category.setAttachmentId(createCategoryDto.getAttachmentId());
        }
        categoryRepository.save(category);
        return null;
    }

    public ResponseData<?> get(UUID categoryId) {
        Optional<Category> categoryOptional = this.categoryRepository.findByIdAndDeletedFalse(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return ResponseData.successResponse(this.categoryMapper.toDto(categoryOptional.get()));
    }

    public ResponseData<?> getAll() {
        List<Category> categoryList = this.categoryRepository.findAllByDeletedFalse();
        return ResponseData.successResponse(this.categoryMapper.toDto(categoryList));
    }

    public ResponseData<?> getAllByFood() {
        List<Category> categoryList = this.categoryRepository.findAllByDeletedFalseAndCategoryType(CategoryType.FOOD);
        return ResponseData.successResponse(this.categoryMapper.toDto(categoryList));
    }

    public ResponseData<?> getAllByRestaurant() {
        List<Category> categoryList = this.categoryRepository.findAllByDeletedFalseAndCategoryType(CategoryType.RESTAURANT);
        return ResponseData.successResponse(this.categoryMapper.toDto(categoryList));
    }

    public ResponseData<?> delete(UUID categoryId) {
        Optional<Category> categoryOptional = this.categoryRepository.findByIdAndDeletedFalse(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        Category category = categoryOptional.get();
        category.setDeleted(true);
        this.categoryRepository.save(category);
        return ResponseData.successResponse("success");
    }
}
