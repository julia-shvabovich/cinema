
package cinema.model.dto.shoppingcart;

import cinema.model.ShoppingCart;
import cinema.model.dto.ticket.TicketResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoMapper {
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        List<TicketResponseDto> tickets = shoppingCart.getTickets().stream()
                .map(ticket -> new TicketResponseDto(ticket.getId(),
                        ticket.getMovieSession().getMovie().getTitle()))
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTickets(tickets);
        shoppingCartResponseDto.setUserEmail(shoppingCart.getUser().getEmail());
        return shoppingCartResponseDto;
    }
}
