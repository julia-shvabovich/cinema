package cinema.model.dto;

import cinema.model.Order;
import cinema.service.OrderService;
import cinema.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {
    private final UserService userService;

    public OrderDtoMapper(UserService userService, OrderService cinemaHallService) {
        this.userService = userService;
    }

    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTickets(order.getTickets());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}
