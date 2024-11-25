package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.order.CreateOrderDto;
import com.example.Tez_Yetkaz.dto.order.OrderDto;
import com.example.Tez_Yetkaz.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    public Order toEntity(CreateOrderDto createOrderDto){
        Order order = new Order();
        order.setDescription(createOrderDto.getDescription());
        order.setOrderTime(createOrderDto.getOrderTime());
        order.setFoods(createOrderDto.getFoods());
        order.setDeliver(createOrderDto.isDeliver());
        order.setLocation(createOrderDto.getLocation());
        order.setUserId(createOrderDto.getUserId());
        order.setStatus(createOrderDto.isStatus());
        order.setFoodsAmount(createOrderDto.getFoodsAmount());
        order.setDeliverAmount(createOrderDto.getDeliverAmount());
        order.setAllAmount(createOrderDto.getAllAmount());
        return order;
    }

    public OrderDto toDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setDescription(order.getDescription());
        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setFoods(order.getFoods());
        orderDto.setDeliver(order.isDeliver());
        orderDto.setStatus(order.isStatus());
        orderDto.setLocation(order.getLocation());
        orderDto.setUserId(order.getUserId());
        orderDto.setOrderId(order.getId());
        orderDto.setFoodsAmount(order.getFoodsAmount());
        orderDto.setDeliverAmount(order.getDeliverAmount());
        orderDto.setAllAmount(order.getAllAmount());
        return orderDto;
    }

    public List<OrderDto> toDto(List<Order> orders){
        List<OrderDto> orderDto = new ArrayList<>();
        for (Order order : orders) {
            orderDto.add(toDto(order));
        }
        return orderDto;
    }

}
