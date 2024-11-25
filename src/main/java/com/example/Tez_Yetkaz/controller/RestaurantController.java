package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.restaurant.CreateRestaurantDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurant/")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseData<?> creat(@RequestBody CreateRestaurantDto createRestaurantDto) {
        return this.restaurantService.create(createRestaurantDto);
    }

    @PutMapping("update/{restaurantId}")
    public ResponseData<?> update(@PathVariable UUID restaurantId, CreateRestaurantDto createRestaurantDto) {
        return this.restaurantService.update(restaurantId, createRestaurantDto);
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

}
