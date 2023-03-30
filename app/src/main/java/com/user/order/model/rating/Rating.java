
package com.user.order.model.rating;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating implements Serializable
{

    @SerializedName("ratings")
    @Expose
    private Ratings ratings;
    private final static long serialVersionUID = -2096455274538505605L;

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public Rating withRatings(Ratings ratings) {
        this.ratings = ratings;
        return this;
    }

}
