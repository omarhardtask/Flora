package app.flora.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import app.flora.ApiRequests.ApiRequest;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedProductsModel;
import app.flora.Models.FeaturedShopsModel;
import app.flora.Models.GetCustomer;
import app.flora.Models.OccasionsModel;
import app.flora.Models.SliderModel;
import app.flora.Models.Topics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://flora.hardtask.info/api/";
    private ApiRequest apiRequest;
    private static RetrofitClient instance;
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

//----------------------------------------------------------------------------------

    public RetrofitClient() {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", FloraConstant.AuthorizationToken)
                        .addHeader("Accept-Language", LanguageSessionManager.getLang())
                        .build();
                return chain.proceed(request);
            }
        });
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiRequest = retrofit.create(ApiRequest.class);
    } // Retrofit Client Method

//----------------------------------------------------------------------------------

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    } // get Instance

//----------------------------------------------------------------------------------

    public Call<SliderModel> fetchSliders() {
        return apiRequest.fetchSliders();
    } // fetchSliders

//----------------------------------------------------------------------------------

    public Call<CategoriesModel> fetchCategoriesOnHome() {
        return apiRequest.fetchCategories();
    } // fetch Categories On Home Screen

//----------------------------------------------------------------------------------

    public Call<FeaturedShopsModel> fetchFeatureShopsOnHome() {
        return apiRequest.fetchFeatureShops();
    } // fetch Feature Shops On Home Screen

//----------------------------------------------------------------------------------

    public Call<FeaturedProductsModel> fetchFeatureProductsOnHome(int page , String limit) {
        return apiRequest.fetchFeatureProducts(page , limit);
    } // fetch Feature Prodcts On Home Screen

//----------------------------------------------------------------------------------

    public Call<OccasionsModel> fetchOccasions(int page , String limit) {
        return apiRequest.fetchOccasions(page , limit);
    } // fetch Occasions

//----------------------------------------------------------------------------------

    public Call<Topics> fetchAboutUs(String id) {
        return apiRequest.fetchAboutUs(id);
    } // fetch Occasions

//----------------------------------------------------------------------------------

    public Call<GetCustomer> createCustomer(String id , GetCustomer customer) {
        return apiRequest.createCustomer(id ,customer);
    } // fetch Occasions


}
