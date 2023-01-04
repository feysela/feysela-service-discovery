package io.javabrains.moviecatalogservice;

import java.util.List;

public class UserRating {

    private List<Rating> userRatingsList;

    public void setUserRatingsList(List<Rating> userRatingsList) {
        this.userRatingsList = userRatingsList;
    }

    public List<Rating> getUserRatingsList() {
        return userRatingsList;
    }
}
