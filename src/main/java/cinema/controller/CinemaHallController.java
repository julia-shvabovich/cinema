package cinema.controller;

import cinema.model.dto.cinemahall.CinemaHallDtoMapper;
import cinema.model.dto.cinemahall.CinemaHallRequestDto;
import cinema.model.dto.cinemahall.CinemaHallResponseDto;
import cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallDtoMapper cinemaHallDtoMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallDtoMapper cinemaHallDtoMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallDtoMapper = cinemaHallDtoMapper;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallDtoMapper.mapToCinemaHall(cinemaHallRequestDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
