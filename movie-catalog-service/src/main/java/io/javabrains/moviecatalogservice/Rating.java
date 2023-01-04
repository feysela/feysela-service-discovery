package io.javabrains.moviecatalogservice;

public class Rating {

    private String movieId;
    private int numRatings;

    public Rating() {
    }

    public Rating(String movieId, int numRatings) {
        this.movieId = movieId;
        this.numRatings = numRatings;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }
}
