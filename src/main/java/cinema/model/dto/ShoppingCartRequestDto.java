package cinema.model.dto;

import cinema.model.Ticket;
import java.util.List;

public class ShoppingCartRequestDto {
    private List<Ticket> tickets;
    private String userEmail;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
