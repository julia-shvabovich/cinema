package cinema.model.dto;

import cinema.model.ShoppingCart;
import cinema.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoMapper {
    private final UserService userService;

    public ShoppingCartDtoMapper(UserService userService) {
        this.userService = userService;
    }

    public ShoppingCart mapToShoppingCart(ShoppingCartRequestDto shoppingCartRequestDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTickets(shoppingCartRequestDto.getTickets());
        shoppingCart.setUser(userService.findByEmail(shoppingCartRequestDto.getUserEmail()).get());
        return shoppingCart;
    }

    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTickets(shoppingCart.getTickets());
        shoppingCartResponseDto.setUserEmail(shoppingCart.getUser().getEmail());
        return shoppingCartResponseDto;
    }

}
