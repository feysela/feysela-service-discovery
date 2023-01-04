package io.javabrains.ratingsdataservice;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingsDataResource {

    @GetMapping("/{movieId}")
    public Rating getRatingByMovieId(@PathVariable String movieId) {
        return new Rating(movieId, 5);
    }

    @GetMapping("/user/{userId}")
    public UserRating getAllRatingsByUserId(@PathVariable String userId) {
        List<Rating> ratingList = Arrays.asList(
                new Rating("movie1", 3),
                new Rating("movie2", 4),
                new Rating("movie3", 5)
        );

        UserRating userRating = new UserRating();
        userRating.setUserRatingsList(ratingList);
        return userRating;
    }

}
