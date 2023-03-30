package com.user.order.model.offers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Offers {

    @SerializedName("offers")
    private List<OfferData> offers;

    public List<OfferData> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferData> offers) {
        this.offers = offers;
    }
}
