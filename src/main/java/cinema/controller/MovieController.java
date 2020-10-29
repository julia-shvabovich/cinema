package cinema.controller;

import cinema.model.Movie;
import cinema.model.dto.movie.MovieDtoMapper;
import cinema.model.dto.movie.MovieRequestDto;
import cinema.model.dto.movie.MovieResponseDto;
import cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieDtoMapper movieDtoMapper;

    public MovieController(MovieService movieService, MovieDtoMapper movieDtoMapper) {
        this.movieService = movieService;
        this.movieDtoMapper = movieDtoMapper;
    }

    @PostMapping
    public Movie addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return movieService.add(movieDtoMapper.mapToMovie(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
