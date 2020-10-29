package cinema.controller;

import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.model.dto.shoppingcart.ShoppingCartDtoMapper;
import cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartDtoMapper shoppingCartDtoMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication,
                                @RequestParam Long movieSessionId) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(details.getUsername()).get();
        shoppingCartService.addSession(movieSessionService.findById(movieSessionId).get(), user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(details.getUsername()).get();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartDtoMapper.mapToDto(shoppingCart);
    }
}
