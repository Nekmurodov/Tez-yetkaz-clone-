package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.order.CreateOrderDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order/")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseData<?> creat(@RequestBody CreateOrderDto createOrderDto) {
        return this.orderService.create(createOrderDto);
    }

    @PutMapping("update/{orderId}")
    public ResponseData<?> update(@PathVariable UUID orderId, CreateOrderDto createOrderDto) {
        return this.orderService.update(orderId, createOrderDto);
    }

    @GetMapping("get/{orderId}")
    public ResponseData<?> get(@PathVariable UUID orderId) {
        return this.orderService.get(orderId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.orderService.getAll(page,size);
    }

    @DeleteMapping("delete/{orderId}")
    public ResponseData<?> delete(@PathVariable UUID orderId) {
        return this.orderService.delete(orderId);
    }

}
