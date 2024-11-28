package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.restaurant.CreateRestaurantDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurant/")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<?> creat(@RequestBody CreateRestaurantDto createRestaurantDto,
                                 @Validated @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return this.restaurantService.create(createRestaurantDto, multipartFile);
    }

    @PutMapping(value = "update/{restaurantId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<?> update(@PathVariable UUID restaurantId, CreateRestaurantDto createRestaurantDto,
                                  @Validated @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return this.restaurantService.update(restaurantId, createRestaurantDto, multipartFile);
    }

    @GetMapping("get/{restaurantId}")
    public ResponseData<?> get(@PathVariable UUID restaurantId) {
        return this.restaurantService.get(restaurantId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.restaurantService.getAll(page,size);
    }

    @DeleteMapping("delete/{restaurantId}")
    public ResponseData<?> delete(@PathVariable UUID restaurantId) {
        return this.restaurantService.delete(restaurantId);
    }

    @GetMapping("/get-all-by-category/{categoryId}")
    public ResponseData<?> getAllByCategoryId(@PathVariable UUID categoryId, @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.restaurantService.getAllByCategory(categoryId, page, size);
    }
}
