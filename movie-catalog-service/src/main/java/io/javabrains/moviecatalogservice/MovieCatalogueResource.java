package io.javabrains.moviecatalogservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<MovieCatalogue> getMovieCatalog(@PathVariable("userId") String userId) {

        final String userRatingURL = "http://ratings-data-service/rating/user/" + userId;
        UserRating userRating = getUserRatingByURL(restTemplate, "http://ratings-data-service/rating/user/1");
        List<Rating> ratings = userRating.getUserRatingsList();

        return ratings.stream().map(rating -> {

            final String movieInfoURL = "http://movie-info-service/movie/";
            if (movieInfoURL.isEmpty()) throw new RuntimeException();
            final String movieURL = movieInfoURL + rating.getMovieId();
            if (restTemplate == null || movieURL.isEmpty()) throw new RuntimeException();
            Movie movie = getMovieByURL(restTemplate, movieURL);

            final String movieName = movie.getName();
            final String movieDescription = "The billionaire if without a suit";
            final int movieRatings = rating.getNumRatings();
            return new MovieCatalogue(movieName, movieDescription, movieRatings);
        }).collect(Collectors.toList());

    }

    private Movie getMovieByURL(WebClient.Builder webClientBuilder, String movieURL) {
        return webClientBuilder.build()
                .get()
                .uri(movieURL)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }

    private Movie getMovieByURL(RestTemplate restTemplate, String movieURL) {
        return restTemplate.getForObject(movieURL, Movie.class);
    }

    private UserRating getUserRatingByURL(RestTemplate restTemplate, String userRatingURL) {
        return restTemplate.getForObject(userRatingURL, UserRating.class);
    }
}
