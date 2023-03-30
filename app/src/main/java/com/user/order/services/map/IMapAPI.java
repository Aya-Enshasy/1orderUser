package com.user.order.services.map;

import com.user.order.model.map.distance.LocationDistance;
import com.user.order.model.map.publicShops.PublicShops;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMapAPI {

    // TODO GOOGLE MAPS PLACES
    @GET("place/nearbysearch/json")
    Call<PublicShops> getPublicShops(@Query("language") String language,
                                    @Query("location") String location,
                                    @Query("rankby") String rankby,
                                    @Query("type") String type,
                                    @Query("key") String key);

    @GET("directions/json")
    Call<LocationDistance> getDistance(@Query("origin") String origin, // TODO User Location
                                       @Query("destination") String destination, // TODO Shop Location
                                       @Query("mode") String mode,
                                       @Query("key") String key);
}
