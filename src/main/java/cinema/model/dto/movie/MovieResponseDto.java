package cinema.model.dto.movie;

import lombok.Data;

@Data
public class MovieResponseDto {
    private Long id;
    private String title;
    private String description;
}
