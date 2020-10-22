package cinema.controller;

import cinema.model.MovieSession;
import cinema.model.dto.MovieSessionDtoMapper;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionDtoMapper movieSessionDtoMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionDtoMapper movieSessionDtoMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionDtoMapper = movieSessionDtoMapper;
    }

    @PostMapping
    public MovieSession addMovieSession(
            @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        return movieSessionService.add(movieSessionDtoMapper
                .mapToMovieSession(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllAvailableSessions(Long movieId, String date) {
        return movieSessionService.findAvailableSessions(movieId, LocalDate.parse(date)).stream()
                .map(movieSessionDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
