package cinema.model.dto;

public class TicketResponseDto {
    private Long id;
    private String movieTitle;

    public TicketResponseDto(Long id, String movieTitle) {
        this.id = id;
        this.movieTitle = movieTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
