package com.user.order.services.api;

import com.user.order.model.DeliveryCost;
import com.user.order.model.auth.Auth;
import com.user.order.model.auth.ResetPassword;
import com.user.order.model.cancel.CancelReasons;
import com.user.order.model.categories.Categories;
import com.user.order.model.changePassword.ChangePassowrd;
import com.user.order.model.city.Cities;
import com.user.order.model.contact.ContactUs;
import com.user.order.model.country.Countries;
import com.user.order.model.coupon.Coupon;
import com.user.order.model.delivary.Delivary;
import com.user.order.model.directions.Directions;
import com.user.order.model.favorites.AddToFavorite;
import com.user.order.model.favorites.Favorites;
import com.user.order.model.map.publicShops.PublicShops;
import com.user.order.model.notifications.Notifications;
import com.user.order.model.offer.OffersItem;
import com.user.order.model.offers.Offers;
import com.user.order.model.order1.Orders1;
import com.user.order.model.orderdetails.OrderDetails;
import com.user.order.model.orders.publicOrders.PublicOrders;
import com.user.order.model.orders.publicOrders.placeOrder.PublicPlaceOrder;
import com.user.order.model.profile.Profile;
import com.user.order.model.rating.AddRating;
import com.user.order.model.rating.Rating;
import com.user.order.model.rating.Ratings;
import com.user.order.model.reset.ResetPasswordRequest;
import com.user.order.model.settings.Settings;
import com.user.order.model.shop.ShopDetails;
import com.user.order.model.shop.ShopsByCategory;
import com.user.order.model.shop.meals.Meals;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface I1OrderAPI {

    // TODO SETTINGS
    @GET("settings")
    Call<Settings> getSettings(@Header("x-local") String language, @Query("country_id") Integer countryId);

    // TODO COUNTRIES
    @GET("countries-list")
    Call<Countries> getCountries(@Header("x-local") String language);

    // TODO Cities
    @GET("cities/{id}")
    Call<Cities> getCities(@Header("x-local") String language,
                           @Path("id") Integer countryId);

    // TODO LOGIN
    @POST("auth/clientLogin")
    @FormUrlEncoded
    Call<Auth> login(@Header("x-local") String language,
                     @Field("mobile") String phoneNumber,
                     @Field("password") String password,
                     @Field("fcm_token") String fcm);

    // TODO SIGN UP
    @POST("auth/register")
    @FormUrlEncoded
    Call<Auth> signUp(@Header("x-local") String language,
                      @Field("name") String fullName,
                      @Field("mobile") String phoneNumber,
                      @Field("country_id") Integer countryId,
                      @Field("city_id") Integer cityId,
                      @Field("address") String address,
                      @Field("password") String password,
                      @Field("password_confirmation") String confirmPassword,
                      @Field("role") String role,
                      @Field("fcm_token") String fcm);

    // TODO Change Password
    @POST("auth/changePassword")
    @FormUrlEncoded
    Call<ChangePassowrd> changePassword(@Header("x-local") String language,
                                        @Header("Authorization") String token,
                                        @Field("old_password") String oldPassword,
                                        @Field("password") String newPassword,
                                        @Field("password_confirmation") String confirmPassword);

    // TODO RESET PASSWORD
    @POST("auth/resetPassword")
    Call<ResetPassword> resetPassword(@Body ResetPasswordRequest resetPasswordRequest, @Header("X-local") String lang);

    @GET("auth/user")
    Call<Profile> getUserProfile(@Header("x-local") String language,
                                 @Header("Authorization") String token);

    @POST("auth/user/updateProfile")
    @Multipart
    Call<Auth> updateUserProfile(@Header("x-local") String language,
                                 @Header("Authorization") String token,
                                 @Part MultipartBody.Part avatar,
                                 @Part("mobile") RequestBody phoneNumbe,
                                 @Part("name") RequestBody name,
                                 @Part("email") RequestBody email,
                                 @Part("address") RequestBody address,
                                 @Part("country_id") RequestBody countryId,
                                 @Part("city_id") RequestBody cityId);

    @GET("categories")
    Call<Categories> getCategories(@Header("x-local") String language,
                                   @Query("lat") Double latitude,
                                   @Query("lng") Double longitude,
                                   @Query("country_id") Integer countryId);

    @GET("offers/slider")
    Call<Offers> getOffers(@Header("x-local") String language,
                           @Query("lat") Double latitude,
                           @Query("lng") Double longitude,
                           @Query("country_id") Integer countryId);


    // TODO PUBLIC ORDERS

    // TODO Get Delivery Cost
    @POST("getDeliveryCost")
    Call<DeliveryCost> getDeliveryCost(@Header("x-local") String language,
                                       @Header("Authorization") String token,
                                       @Query("country_id") Integer countryId,
                                       @Query("distance") Double distance);

    // TODO Place Order
    @POST("general/order/client/placeOrder")
    @Multipart
    Call<PublicPlaceOrder> createOrder(@Header("x-local") String language,
                                       @Header("Authorization") String token,
                                       @Part List<MultipartBody.Part> images,
                                       @Part("store_name") RequestBody shopName,
                                       @Part("delivery_cost") RequestBody deliveryCost,
                                       @Part("tax") RequestBody tax,
                                       @Part("note") RequestBody note,
                                       @Part("destination_lat") RequestBody destinationLat,
                                       @Part("destination_lng") RequestBody destinationLng,
                                       @Part("destination_address") RequestBody destinationAddress,
                                       @Part("store_lat") RequestBody shopLat,
                                       @Part("store_lng") RequestBody shopLng,
                                       @Part("store_address") RequestBody shopAddress,
                                       @Part("country_id") RequestBody countryId);

    // TODO Get Orders

    // TODO Get public Orders
    @GET("orders/me")
    Call<com.user.order.model.order.Orders> getOrders(@Header("x-local") String language,
                           @Header("Authorization") String token, @Query("limit") int limit, @Query("page") int page);

    // TODO ORDERS
    @GET("general/order/client/ordersList")
    Call<PublicOrders> getPublicOrders(@Header("x-local") String language,
                                       @Header("Authorization") String token);

    // TODO Favorites
    @GET("favoriteList")
    Call<Favorites> getFavorites(@Header("x-local") String language,
                                 @Header("Authorization") String token);

    // TODO delete Favorites
    @FormUrlEncoded
    @POST("storeFavorite")
    Call<ContactUs> deleteFavorites(@Header("x-local") String language,
                                    @Header("Authorization") String token,
                                    @Field("shop_id") String shop_id);

    // TODO Notifications
    @GET("notifications")
    Call<Notifications> getNotifications(@Header("x-local") String language,
                                         @Header("Authorization") String token,
                                         @Header("Accept") String accept);

    // TODO Shops By Category
    @GET("shops/{id}")
    Call<ShopsByCategory> getShopsByCategory(@Header("x-local") String language,
                                             @Header("Authorization") String token,
                                             @Path("id") Integer categoryId,
                                             @Query("lat") Double latitude,
                                             @Query("lng") Double longitude,
                                             @Query("page") Integer page,
                                             @Query("country_id") Integer countryId);

    @POST("contact")
    Call<ContactUs> getContact(@Header("x-local") String language,
                               @Header("Authorization") String token,
                               @Query("country_id") String countryId,
                               @Query("name") String name,
                               @Query("message") String message,
                               @Query("subject") String subject,
                               @Query("mobile") String mobile,
                               @Query("user_id") String user_id);

    // TODO Add To Favorite
    @GET("shop/{id}")
    Call<ShopDetails> getShopDetails(@Header("x-local") String language,
                                     @Path("id") Integer shopId,
                                     @Query("lat") Double latitude,
                                     @Query("lng") Double longitude);

    // TODO Add To Favorite
    @POST("storeFavorite")
    @FormUrlEncoded
    Call<AddToFavorite> removeFavorites(@Header("x-local") String language,
                                        @Header("Authorization") String token,
                                        @Field("shop_id") Integer shopId);

    // TODO Add Rating
    @Headers({"Accept: application/json"})
    @POST("rateMajor")
    @FormUrlEncoded
    Call<AddRating> addRating(@Header("x-local") String language,
                              @Header("Authorization") String token,
                              @Field("content_id") String contentId,
                              @Field("rate") Double rate,
                              @Field("review") String review);

    // TODO Add Rating

    @POST("rateDeliveryDriver")
    @FormUrlEncoded
    Call<ContactUs> rateDeliveryDriver(@Header("x-local") String language,
                                       @Header("Authorization") String token,
                                       @Field("driver_id") String driver_id,
                                       @Field("rate") Double rate,
                                       @Field("review") String review,
                                       @Field("type") String type,
                                       @Field("order_id") String order_id);

    @GET("ratingList/{id}")
    Call<Rating> getRatings(@Header("x-local") String language,
                            @Path("id") String id);

    // TODO Get Meals
    @GET("items/{shopId}/{categoryId}")
    Call<Meals> getMeals(@Header("x-local") String language,
                         @Path("shopId") Integer shopId,
                         @Path("categoryId") Integer categoryId);


    // TODO Post delivered
    @POST("general/order/client/clientConfirmDeliverd")
    Call<ContactUs> delivered(@Header("x-local") String language,
                              @Header("Authorization") String token,
                              @Query("order_id") String order_id);

    // TODO Get Cancel Reasons
    @GET("client/cancelReasons")
    Call<CancelReasons> getCancelReasons(@Header("x-local") String language,
                                         @Header("Authorization") String token);

    // TODO Post cancelOrder
    @POST("general/order/client/cancel-order")
    Call<ContactUs> cancelOrder(@Header("Authorization") String token,
                                @Query("order_id") String order_id,
                                @Query("cancel_reasons_id") String cancel_reasons_id);

    // TODO Post cancelOrder
    @POST("cancelOrder")
    Call<ContactUs> cancelOrder1(@Header("Authorization") String token,
                                @Query("order_id") String order_id,
                                @Query("cancel_reasons_id") String cancel_reasons_id);

    // TODO Post cancelOrder
    @POST("general/order/client/clientConfirmPayment")
    Call<ContactUs> clientConfirmPayment(@Header("Authorization") String token,
                                         @Query("order_id") String order_id,
                                         @Query("transaction_id") String transaction_id,
                                         @Query("payment_type") String payment_type);

    @POST("client/sendChatNotify")
    Call<ContactUs> sendNotification(@Header("Authorization") String token,
                                     @Query("user_id") String user_id,
                                     @Query("order_id") int order_id,
                                     @Query("invoice_no") String invoice_no,
                                     @Query("type") String type);

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
    Call<PublicShops> search(@Query("location") String location,
                             @Query("radius") String radius,
                             @Query("name") String name,
                             @Query("keyword") String keyword,
                             @Query("key") String key);

    @GET("https://maps.googleapis.com/maps/api/directions/json")
    Call<Directions> directions(@Query("origin") String origin,
                                @Query("destination") String destination,
                                @Query("mode") String mode,
                                @Query("key") String key);

    @GET("general/order/client/order/{id}")
    Call<OrderDetails> publicOrderDetails(@Path("id") String order_id, @Header("Authorization") String Authorization);

    @POST("sendOfferOrder")
    Call<ContactUs> sendOfferOrder(
            @Query("shop_id") String shop_id,
            @Query("type_of_receive") String type_of_receive,
            @Query("delivery_type") String delivery_type,
            @Query("country_id") String country_id,
            @Query("items[0][id]") String items,
            @Query("items[0][qty]") String items_qty,
            @Query("payment_type") String payment_type,
            @Query("lat") String lat,
            @Query("lng") String lng,
            @Query("delivery") String delivery,
            @Query("sub_total_1") String sub_total_1,
            @Query("discount") String discount,
            @Query("sub_total_2") String sub_total_2,
            @Query("total") String total,
            @Query("items[0][price]") String items_price,
            @Query("destination_lat") String destination_lat,
            @Query("destination_lng") String destination_lng,
            @Query("destination_address") String destination_address,
            @Query("tax") String tax,
            @Header("Authorization") String Authorization,
            @Header("x-local") String language);

    @POST("getDeliveryCost")
    Call<Delivary> delivary(
            @Query("country_id") String country_id,
            @Query("distance") String distance,
            @Header("Authorization") String Authorization);


    @GET("item/{id}")
    Call<OffersItem> offers(@Path("id") String order_id);


    @POST("checkCoupon")
    Call<Coupon> code(@Query("country_id") String country_id, @Query("code") String code);


    //    String encodedParamName = URLEncoder.encode("items[0][id]", "UTF-8");
    @Headers({("Accept: application/json")})
    @POST("sendOrder")
    @FormUrlEncoded
    Call<ContactUs> sendOrder1(@Header("x-local") String language,
                               @Header("Authorization") String token,
                               @Field("shop_id") Integer shopId,
                               @Field("type_of_receive") String typeOfReceive,
                               @Field("delivery_type") String deliveryType,
                               @Field("coupon_id") Integer couponId,
                               @Field("country_id") Integer countryId,
                               @FieldMap() HashMap<String, Object> partMap,
                               @FieldMap() HashMap<String, Object> partMap1,
                               @FieldMap() HashMap<String, Object> partMap2,
                               @FieldMap() HashMap<String, Object> partMap3,
                               @FieldMap() HashMap<String, Object> partMap4,
                               @FieldMap() HashMap<String, Object> partMap5,
                               @Field("payment_type") String paymentType,
                               @Field("delivery_at") String deliveryAt,
                               @Field("lat") Double shopLat,
                               @Field("lng") Double shopLng,
                               @Field("delivery") Double deliveryCost,
                               @Field("sub_total_1") Double subTotal1,
                               @Field("discount") Integer discount,
                               @Field("sub_total_2") Double subTotal2,
                               @Field("total") Double total,
                               @Field("notes") String notes,
                               @Field("destination_lat") Double destinationLat,
                               @Field("destination_lng") Double destinationLng,
                               @Field("tax") Double tax,
                               @Field("destination_address") String destinationAddress);

    @POST("sendOrder")
    Call<ContactUs> sendOrder(
            @Query("shop_id") String shop_id,
            @Query("type_of_receive") String type_of_receive,
            @Query("delivery_type") String delivery_type,
            @Query("country_id") String country_id,
//            @Query( URLEncoder.encode("items[0][id]", "UTF-8")) String itemId,String id,
//            @Query("items[id][id]") String items,
            @Query("items[0][qty]") String items_qty,
            @Query("payment_type") String payment_type,
            @Query("lat") String lat,
            @Query("lng") String lng,
            @Query("delivery") String delivery,
            @Query("sub_total_1") String sub_total_1,
            @Query("discount") String discount,
            @Query("sub_total_2") String sub_total_2,
            @Query("total") String total,
            @Query("items[0][price]") String items_price,
            @Query("destination_lat") String destination_lat,
            @Query("destination_lng") String destination_lng,
            @Query("destination_address") String destination_address,
            @Query("tax") String tax,
            @Query("coupon_id") String coupon_id,
            @Header("Authorization") String Authorization,
            @Header("x-local") String language) throws UnsupportedEncodingException;

    @GET("order/{id}")
    Call<Orders1> orders(@Path("id") String order_id,@Header("Authorization") String token, @Query("limit") int limit, @Query("page") int page, @Header("X-local") String lang);

    @GET("order/{id}")
    Call<Orders1> order(@Header("Authorization") String token, @Header("X-local") String lang, @Path("id") String order_id);

}
