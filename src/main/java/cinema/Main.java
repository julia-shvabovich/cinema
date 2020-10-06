package cinema;

import cinema.lib.Injector;
import cinema.model.Movie;
import cinema.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);

        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
