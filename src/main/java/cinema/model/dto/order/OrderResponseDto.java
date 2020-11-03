package cinema.model.dto.order;

import cinema.model.dto.ticket.TicketResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private String userEmail;
    private List<TicketResponseDto> tickets;
    private LocalDateTime orderDate;
}
