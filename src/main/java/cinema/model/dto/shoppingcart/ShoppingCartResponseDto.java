package cinema.model.dto.shoppingcart;

import cinema.model.dto.ticket.TicketResponseDto;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartResponseDto {
    private Long id;
    private List<TicketResponseDto> tickets;
    private String userEmail;
}
