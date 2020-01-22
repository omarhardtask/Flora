package app.flora.ApiRequests;

import java.util.List;

import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedProductsModel;
import app.flora.Models.FeaturedShopsModel;
import app.flora.Models.GetCustomer;
import app.flora.Models.OccasionsModel;
import app.flora.Models.SliderModel;
import app.flora.Models.Topics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("sliders")
    Call<SliderModel> fetchSliders(); // home banner slider

//----------------------------------------------------------------------------------

    @GET("categories?show_on_home_page=true")
    Call<CategoriesModel> fetchCategories(); // fetch categories on home

//----------------------------------------------------------------------------------

    @GET("vendors?show_on_home_page=true")
    Call<FeaturedShopsModel> fetchFeatureShops(); // fetch feature shops on home

//----------------------------------------------------------------------------------

    @GET("products?show_on_home_page=true")
    Call<FeaturedProductsModel> fetchFeatureProducts(
            @Query("page") int limit,
            @Query("limit") String page
    ); // fetch feature shops on home

//----------------------------------------------------------------------------------

    @GET("manufacturers")
    Call<OccasionsModel> fetchOccasions(
            @Query("page") int limit,
            @Query("limit") String page
    ); // fetch Occasions

//----------------------------------------------------------------------------------

    @GET("topics/{id}")
    Call<Topics> fetchAboutUs(
            @Path("id") String id); // fetch AboutUs

//----------------------------------------------------------------------------------

    @POST("/customers/{id}")
    Call<GetCustomer> createCustomer(
            @Path("id") String id,
            @Body GetCustomer customer); // createCustomer

//----------------------------------------------------------------------------------


}
