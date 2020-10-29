package cinema.model.dto.shoppingcart;

import cinema.model.dto.ticket.TicketResponseDto;
import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private List<TicketResponseDto> tickets;
    private String userEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
