package cinema.model.dto.order;

import cinema.model.Order;
import cinema.model.dto.ticket.TicketResponseDto;
import cinema.service.OrderService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
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
        List<TicketResponseDto> tickets = order.getTickets().stream()
                .map(ticket -> new TicketResponseDto(ticket.getId(),
                        ticket.getMovieSession().getMovie().getTitle()))
                .collect(Collectors.toList());
        orderResponseDto.setTickets(tickets);
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}
