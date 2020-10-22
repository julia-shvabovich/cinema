package cinema;

import cinema.config.AppConfig;
import cinema.exception.AuthenticationException;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.security.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        MovieService movieService = context.getBean(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie.setDescription("I never heard of the film");
        movieService.add(movie);
        movieService.getAll().forEach(logger::info);

        CinemaHall testHall = new CinemaHall();
        testHall.setCapacity(20);
        testHall.setDescription("little hall");
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHallService.add(testHall);
        cinemaHallService.getAll().forEach(logger::info);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(testHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(logger::info);

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        User testUser = new User();
        testUser.setEmail("shvabovichjulia@gmail.com");
        testUser.setPassword("12345");
        testUser = authenticationService.register(testUser.getEmail(), testUser.getPassword());
        try {
            testUser = authenticationService.login(testUser.getEmail(), testUser.getPassword());
            logger.info("Logged user: " + testUser);
        } catch (AuthenticationException e) {
            logger.error("AuthenticationException occured " + e);
        }

        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, testUser);
        ShoppingCart cart = shoppingCartService.getByUser(testUser);
        logger.info("Shopping cart of the user is " + cart);
        shoppingCartService.clear(cart);

        OrderService orderService = context.getBean(OrderService.class);
        cart = shoppingCartService.getByUser(testUser);
        orderService.completeOrder(cart.getTickets(), testUser);
        orderService.getOrderHistory(testUser).forEach(logger::info);
    }
}
