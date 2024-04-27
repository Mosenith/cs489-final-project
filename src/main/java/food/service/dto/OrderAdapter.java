package food.service.dto;

import food.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter {
    public static OrderDto getOrderDtoFromOrder(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserId(order.getUserId());

        List<String> menuDto = new ArrayList<>();
        menuDto.addAll(order.getMenuNames());
        orderDto.setMenuNames(menuDto);

        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setName(order.getName());
        orderDto.setStreet(order.getStreet());
        orderDto.setCity(order.getCity());
        orderDto.setState(order.getState());
        orderDto.setZip(order.getZip());

        return orderDto;
    }
}
