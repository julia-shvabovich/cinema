package cinema;

import cinema.exception.AuthenticationException;
import cinema.lib.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.User;
import cinema.security.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie.setDescription("I never heard of the film");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall testHall = new CinemaHall();
        testHall.setCapacity(20);
        testHall.setDescription("little hall");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(testHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(testHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(System.out::println);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User testUser = new User();
        testUser.setEmail("shvabovichjulia@gmail.com");
        testUser.setPassword("12345");
        System.out.println("Registered user: "
                + authenticationService.register(testUser.getEmail(), testUser.getPassword()));
        try {
            System.out.println("Logged user: "
                    + authenticationService.login(testUser.getEmail(), testUser.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println("AuthenticationException occured " + e);
        }
    }
}
