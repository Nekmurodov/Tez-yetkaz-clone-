package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.dto.food.CreateFoodDto;
import com.example.Tez_Yetkaz.entity.fr.Attachment;
import com.example.Tez_Yetkaz.entity.fr.Category;
import com.example.Tez_Yetkaz.entity.fr.Food;
import com.example.Tez_Yetkaz.entity.fr.Restaurant;
import com.example.Tez_Yetkaz.enums.CategoryType;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.mapper.FoodMapper;
import com.example.Tez_Yetkaz.repository.AttachmentRepository;
import com.example.Tez_Yetkaz.repository.CategoryRepository;
import com.example.Tez_Yetkaz.repository.FoodRepository;
import com.example.Tez_Yetkaz.repository.RestaurantRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileService fileService;

    public ResponseData<?> create(CreateFoodDto createFoodDto) {
        Optional<Category> category = categoryRepository.findByIdAndDeletedFalseAndCategoryType(createFoodDto.getCategoryId(), CategoryType.FOOD);
        if (category.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        Optional<Restaurant> restaurant = this.restaurantRepository.findByIdAndDeletedFalse(createFoodDto.getRestaurantId());
        if (restaurant.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        Optional<Attachment> attachment = this.attachmentRepository.findById(createFoodDto.getAttachmentId());
        if (attachment.isEmpty()){
            throw new NotFoundException("Attachment not found");
        }

        Food food = this.foodMapper.toEntity(createFoodDto);
        food.setCategory(category.get());
        food.setRestaurant(restaurant.get());
        food.setAttachment(attachment.get());

        this.foodRepository.save(food);
        return ResponseData.successResponse(this.foodMapper.toDto(food));
    }

    public ResponseData<?> update(UUID foodId, CreateFoodDto createFoodDto) {
        Optional<Food> foodOptional = this.foodRepository.findByIdAndDeletedFalse(foodId);
        if (foodOptional.isEmpty()){
            throw new NotFoundException("Food not found");
        }
        Optional<Restaurant> restaurant = this.restaurantRepository.findByIdAndDeletedFalse(createFoodDto.getRestaurantId());
        if (restaurant.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        Optional<Category> category = categoryRepository.findByIdAndDeletedFalseAndCategoryType(createFoodDto.getCategoryId(), CategoryType.FOOD);
        if (category.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        Optional<Attachment> attachment = this.attachmentRepository.findById(createFoodDto.getAttachmentId());
        if (attachment.isEmpty()){
            throw new NotFoundException("Attachment not found");
        }
        Food food = this.foodMapper.toUpdateEntity(foodOptional.get(), createFoodDto);
        food.setCategory(category.get());
        food.setRestaurant(restaurant.get());
        if (foodOptional.get().getAttachment() == null){
            food.setAttachment(attachment.get());
        }
        else if (createFoodDto.getAttachmentId() != null && fileService.deleteFile(foodOptional.get().getAttachment().getId())){
            food.setAttachment(attachment.get());
        }
        this.foodRepository.save(food);
        return ResponseData.successResponse(this.foodMapper.toDto(food));
    }

    public ResponseData<?> get(UUID foodId) {
        Optional<Food> foodOptional = this.foodRepository.findByIdAndDeletedFalse(foodId);
        if (foodOptional.isEmpty()){
            throw new NotFoundException("Food not found");
        }
        return ResponseData.successResponse(this.foodMapper.toDto(foodOptional.get()));
    }

    public ResponseData<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Food> foodPage = this.foodRepository.findAllByDeletedFalse(pageable);
        if (foodPage.isEmpty()){
            throw new NotFoundException("Food not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", foodMapper.toDto(foodPage.toList()));
        response.put("total", foodPage.getTotalElements());
        response.put("totalPages", foodPage.getTotalPages());

        return new ResponseData<>(response, true);
    }

    public ResponseData<?> delete(UUID foodId) {
        Optional<Food> foodOptional = this.foodRepository.findByIdAndDeletedFalse(foodId);
        if (foodOptional.isEmpty()){
            throw new NotFoundException("Food not found");
        }
        Food food = foodOptional.get();
        food.setDeleted(true);
        this.foodRepository.save(food);
        return ResponseData.successResponse("success");
    }

    public ResponseData<?> getAllByRestaurantId(UUID restaurantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Food> foodPage = this.foodRepository.findAllByDeletedFalseAndRestaurantId(pageable, restaurantId);
        if (foodPage.isEmpty()){
            throw new NotFoundException("Foods not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", foodMapper.toDto(foodPage.toList()));
        response.put("total", foodPage.getTotalElements());
        response.put("totalPages", foodPage.getTotalPages());

        return new ResponseData<>(response, true);
    }

    public ResponseData<?> getAllByCategoryId(UUID categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Food> foodPage = this.foodRepository.findAllByDeletedFalseAndCategoryId(pageable, categoryId);
        if (foodPage.isEmpty()){
            throw new NotFoundException("Food not found!");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", foodMapper.toDto(foodPage.toList()));
        response.put("total", foodPage.getTotalElements());
        response.put("totalPages", foodPage.getTotalPages());

        return new ResponseData<>(response, true);
    }
}
