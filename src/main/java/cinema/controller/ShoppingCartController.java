package cinema.controller;

import cinema.model.ShoppingCart;
import cinema.model.dto.moviesession.MovieSessionDtoMapper;
import cinema.model.dto.moviesession.MovieSessionRequestDto;
import cinema.model.dto.shoppingcart.ShoppingCartDtoMapper;
import cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionDtoMapper movieSessionDtoMapper;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionDtoMapper movieSessionDtoMapper,
                                  ShoppingCartDtoMapper shoppingCartDtoMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionDtoMapper = movieSessionDtoMapper;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam String userEmail,
                                @RequestBody MovieSessionRequestDto movieSession) {
        shoppingCartService.addSession(movieSessionDtoMapper.mapToMovieSession(movieSession),
                userService.findByEmail(userEmail).get());
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam String email) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(
                userService.findByEmail(email).get());
        return shoppingCartDtoMapper.mapToDto(shoppingCart);
    }
}
