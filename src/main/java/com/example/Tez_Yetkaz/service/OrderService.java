package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.dto.order.CreateOrderDto;
import com.example.Tez_Yetkaz.entity.Order;
import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.mapper.OrderMapper;
import com.example.Tez_Yetkaz.repository.OrderRepository;
import com.example.Tez_Yetkaz.repository.UserRepository;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    public ResponseData<?> create(CreateOrderDto createOrderDto) {
        Optional<User> user = userRepository.findByIdAndDeletedFalse(createOrderDto.getUserId());
        if (user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        Order order = this.orderMapper.toEntity(createOrderDto);
        this.orderRepository.save(order);
        return ResponseData.successResponse(this.orderMapper.toDto(order));
    }

    public ResponseData<?> update(UUID orderId, CreateOrderDto createOrderDto) {
        Optional<Order> orderOptional = this.orderRepository.findByIdAndDeletedFalse(orderId);
        if (orderOptional.isEmpty()){
            throw new NotFoundException("Order not found");
        }
        Order order = this.orderMapper.toEntity(createOrderDto);
        this.orderRepository.save(order);
        return ResponseData.successResponse(this.orderMapper.toDto(order));
    }

    public ResponseData<?> get(UUID orderId) {
        Optional<Order> orderOptional = this.orderRepository.findByIdAndDeletedFalse(orderId);
        if (orderOptional.isEmpty()){
            throw new NotFoundException("Order not found");
        }
        return ResponseData.successResponse(this.orderMapper.toDto(orderOptional.get()));
    }

    public ResponseData<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> order = this.orderRepository.findAllByDeletedFalse(pageable);
        if (order.isEmpty()){
            throw new NotFoundException("Order not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", orderMapper.toDto(order.toList()));
        response.put("total", order.getTotalElements());
        response.put("totalPages", order.getTotalPages());

        return ResponseData.successResponse(response);
    }

    public ResponseData<?> delete(UUID orderId) {
        Optional<Order> orderOptional = this.orderRepository.findByIdAndDeletedFalse(orderId);
        if (orderOptional.isEmpty()){
            throw new NotFoundException("Order not found");
        }
        Order order = orderOptional.get();
        order.setDeleted(true);
        this.orderRepository.save(order);
        return ResponseData.successResponse("Successfully deleted order");
    }

}
