package com.user.order.services.api;

import com.user.order.model.settings.CountryCode;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPAPI {

    // TODO COUNTRY CODE
    @GET("json")
    Call<CountryCode> getCountryCode();
}
