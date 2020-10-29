package cinema.controller;

import cinema.model.ShoppingCart;
import cinema.model.dto.moviesession.MovieSessionDtoMapper;
import cinema.model.dto.moviesession.MovieSessionRequestDto;
import cinema.model.dto.shoppingcart.ShoppingCartDtoMapper;
import cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void addMovieSession(Authentication user,
                                @RequestBody MovieSessionRequestDto movieSession) {
        shoppingCartService.addSession(movieSessionDtoMapper.mapToMovieSession(movieSession),
                userService.findUser(user).get());
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication user) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(
                userService.findUser(user).get());
        return shoppingCartDtoMapper.mapToDto(shoppingCart);
    }
}
