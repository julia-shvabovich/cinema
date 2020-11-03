package cinema.model.dto.cinemahall;

import lombok.Data;

@Data
public class CinemaHallResponseDto {
    private Long id;
    private int capacity;
    private String description;
}
