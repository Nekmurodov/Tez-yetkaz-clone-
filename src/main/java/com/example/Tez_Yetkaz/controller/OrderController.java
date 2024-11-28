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

    @PutMapping("update-status/{orderId}")
    public ResponseData<?> updateStatus(@PathVariable UUID orderId, boolean status) {
        return this.orderService.updateStatus(orderId, status);
    }

    @PutMapping("update-deliver/{orderId}")
    public ResponseData<?> updateDeliver(@PathVariable UUID orderId, boolean status) {
        return this.orderService.updateDeliver(orderId, status);
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

    @GetMapping("/get-all-by-status")
    public ResponseData<?> getAllByStatus(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return this.orderService.getAllByStatus(page,size);
    }

    @GetMapping("/get-all-by-deliver")
    public ResponseData<?> getAllByDeliver(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return this.orderService.getAllByDeliver(page,size);
    }

    @DeleteMapping("delete/{orderId}")
    public ResponseData<?> delete(@PathVariable UUID orderId) {
        return this.orderService.delete(orderId);
    }

}
