package cinema.controller;

import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.model.dto.order.OrderDtoMapper;
import cinema.model.dto.order.OrderResponseDto;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public OrderController(OrderService orderService, OrderDtoMapper orderDtoMapper,
                           ShoppingCartService shoppingCartService, UserService userService) {
        this.orderService = orderService;
        this.orderDtoMapper = orderDtoMapper;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartService
                .getByUser(userService.findByEmail(details.getUsername()).get());
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(details.getUsername()).get();
        return orderService.getOrderHistory(user).stream()
                .map(orderDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
