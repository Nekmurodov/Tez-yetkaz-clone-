package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.dto.restaurant.CreateRestaurantDto;
import com.example.Tez_Yetkaz.entity.fr.Attachment;
import com.example.Tez_Yetkaz.entity.fr.Category;
import com.example.Tez_Yetkaz.entity.fr.Restaurant;
import com.example.Tez_Yetkaz.enums.CategoryType;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.mapper.RestaurantMapper;
import com.example.Tez_Yetkaz.repository.AttachmentRepository;
import com.example.Tez_Yetkaz.repository.CategoryRepository;
import com.example.Tez_Yetkaz.repository.RestaurantRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileService fileService;

    public ResponseData<?> create(CreateRestaurantDto createRestaurantDto){
        Optional<Category> category = categoryRepository.
                findByIdAndDeletedFalseAndCategoryType(createRestaurantDto.getCategoryId(), CategoryType.RESTAURANT);
        if (category.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        Optional<Attachment> attachment = this.attachmentRepository.findById(createRestaurantDto.getAttachmentId());
        if (attachment.isEmpty()){
            throw new NotFoundException("Attachment not found");
        }
        Restaurant restaurant = this.restaurantMapper.toEntity(createRestaurantDto);
        restaurant.setCategory(category.get());
        restaurant.setAttachment(createRestaurantDto.getAttachmentId());
        this.restaurantRepository.save(restaurant);
        return ResponseData.successResponse(this.restaurantMapper.toDto(restaurant));
    }

    public ResponseData<?> update(UUID restaurantId, CreateRestaurantDto createRestaurantDto) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByIdAndDeletedFalse(restaurantId);
        if (restaurantOptional.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        Optional<Category> category = categoryRepository.
                findByIdAndDeletedFalseAndCategoryType(createRestaurantDto.getCategoryId(), CategoryType.RESTAURANT);
        if (category.isEmpty()){
            throw new NotFoundException("Category not found!");
        }
        Optional<Attachment> attachment = this.attachmentRepository.findById(createRestaurantDto.getAttachmentId());
        if (attachment.isEmpty()){
            throw new NotFoundException("Attachment not found");
        }
        Restaurant restaurant = this.restaurantMapper.toUpdateEntity(restaurantOptional.get(), createRestaurantDto);
        restaurant.setCategory(category.get());
        if (restaurantOptional.get().getAttachment() == null) {
            restaurant.setAttachment(createRestaurantDto.getAttachmentId());
        }
        else if (createRestaurantDto.getAttachmentId() !=null && fileService.deleteFile(restaurantOptional.get().getAttachment())){
            restaurant.setAttachment(createRestaurantDto.getAttachmentId());
        }
        this.restaurantRepository.save(restaurant);

        return ResponseData.successResponse(this.restaurantMapper.toDto(restaurant));
    }

    public ResponseData<?> get(UUID restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByIdAndDeletedFalse(restaurantId);
        if (restaurantOptional.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        return ResponseData.successResponse(this.restaurantMapper.toDto(restaurantOptional.get()));
    }

    public ResponseData<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurantPage = this.restaurantRepository.findAllByDeletedFalse(pageable);
        if (restaurantPage.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", restaurantMapper.toDto(restaurantPage.toList()));
        response.put("total", restaurantPage.getTotalElements());
        response.put("totalPages", restaurantPage.getTotalPages());

        return ResponseData.successResponse(response);
    }

    public ResponseData<?> delete(UUID restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByIdAndDeletedFalse(restaurantId);
        if (restaurantOptional.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        Restaurant restaurant = restaurantOptional.get();
        restaurant.setDeleted(true);
        this.restaurantRepository.save(restaurant);

        return ResponseData.successResponse("success");
    }

    public ResponseData<?> getAllByCategory(UUID categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurantPage = this.restaurantRepository.findAllByDeletedFalseAndCategoryId(pageable, categoryId);
        if (restaurantPage.isEmpty()){
            throw new NotFoundException("Restaurants not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", restaurantMapper.toDto(restaurantPage.toList()));
        response.put("total", restaurantPage.getTotalElements());
        response.put("totalPages", restaurantPage.getTotalPages());

        return ResponseData.successResponse(response);
    }
}
