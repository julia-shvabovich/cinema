package cinema.model.dto.ticket;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketResponseDto {
    private Long id;
    private String movieTitle;

    public TicketResponseDto(Long id, String movieTitle) {
        this.id = id;
        this.movieTitle = movieTitle;
    }
}
