package cinema.model.dto.moviesession;

import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long id;
    private String showTime;
    private String movieTitle;
    private Long cinemaHallId;
}
