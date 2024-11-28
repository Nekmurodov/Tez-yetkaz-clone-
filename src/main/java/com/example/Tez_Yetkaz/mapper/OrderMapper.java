package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.order.CreateOrderDto;
import com.example.Tez_Yetkaz.dto.order.FoodForOrderDto;
import com.example.Tez_Yetkaz.dto.order.OrderDto;
import com.example.Tez_Yetkaz.entity.FoodForOrder;
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
        order.setFoods(toEntityFood(createOrderDto.getFoods()));
        order.setDeliver(false);
        order.setLocation(createOrderDto.getLocation());
        order.setUserId(createOrderDto.getUserId());
        order.setStatus(true);
        order.setFoodsAmount(createOrderDto.getFoodsAmount());
        order.setDeliverAmount(createOrderDto.getDeliverAmount());
        order.setAllAmount(createOrderDto.getAllAmount());
        return order;
    }

    public OrderDto toDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setDescription(order.getDescription());
        orderDto.setFoods(toDtoFood(order.getFoods()));
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

    public FoodForOrderDto toDtoFood(FoodForOrder food){
        FoodForOrderDto foodForOrderDto = new FoodForOrderDto();
        foodForOrderDto.setFoodId(food.getId());
        foodForOrderDto.setCount(food.getCount());
        return foodForOrderDto;
    }

    public List<FoodForOrderDto> toDtoFood(List<FoodForOrder> foods){
        List<FoodForOrderDto> foodForOrderDto = new ArrayList<>();
        for (FoodForOrder food : foods) {
            foodForOrderDto.add(toDtoFood(food));
        }
        return foodForOrderDto;
    }

    public FoodForOrder toEntityFood(FoodForOrderDto foodForOrderDto){
        FoodForOrder foodForOrder = new FoodForOrder();
        foodForOrder.setId(foodForOrderDto.getFoodId());
        foodForOrder.setCount(foodForOrderDto.getCount());
        return foodForOrder;
    }

    public List<FoodForOrder> toEntityFood(List<FoodForOrderDto> foods){
        List<FoodForOrder> foodForOrder = new ArrayList<>();
        for (FoodForOrderDto foodForOrderDto : foods) {
            foodForOrder.add(toEntityFood(foodForOrderDto));
        }
        return foodForOrder;
    }

}
