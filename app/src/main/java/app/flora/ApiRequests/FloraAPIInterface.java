package app.flora.ApiRequests;

import app.flora.Global.FloraConstant;
import app.flora.Models.Countries;
import app.flora.Models.FeaturedShopsModel;
import app.flora.Models.GetAddress;
import app.flora.Models.GetCategories;
import app.flora.Models.GetCustomer;
import app.flora.Models.GetProducts;
import app.flora.Models.OrderDelete;
import app.flora.Models.Orders;
import app.flora.Models.Reviews;
import app.flora.Models.ShoppingCartItem;
import app.flora.Models.ShoppingCarts;
import app.flora.Models.StateProvinces;
import app.flora.Models.Stores;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;


public interface FloraAPIInterface {

    //====================================================================================
    @PUT("/customers/{id}")
    void updateCustomer(@Header(FloraConstant.LanguageKey) String language_id,
                        @Header("Authorization") String Authorization,
                        @Path("id") String id,
                        @Body GetCustomer customer,
                        Callback<GetCustomer> getGetCustomer); // updateCustomer


    //====================================================================================
    @POST("/guestcustomers")
    void createGuestCustomer(@Header(FloraConstant.LanguageKey) String language_id,
                             @Header("Authorization") String Authorization,
                             @Header("Content-Type") String content_type,
                             Callback<GetCustomer> getGetCustomer); // createGuestCustomer

//====================================================================================

    @GET("/customers/login")
    void login(@Header(FloraConstant.LanguageKey) String language_id,
               @Header("Authorization") String Authorization,
               @Query("username_or_email") String username_or_email,
               @Query("password") String password,
               @Query("current_customer_id") String current_customer_id,
               Callback<GetCustomer> getGetCustomer); // login

//====================================================================================

    @FormUrlEncoded
    @POST("/customers/recoverypassword")
    void forgetPassword(@Header("Authorization") String Authorization,
                        @Field("email") String email,
                        Callback<Response> response); // forget password

//====================================================================================

    @GET("/customers/{id}?fields=addresses,billing_address")
    void customerAddresses(@Header(FloraConstant.LanguageKey) String language_id,
                           @Header("Authorization") String Authorization,
                           @Path("id") String id,
                           Callback<GetCustomer> getGetCustomer); // customerAddresses

    //====================================================================================
    @GET("/current_store")
    void current_store(@Header("Authorization") String Authorization,
                       Callback<Stores> getStores); // current store

//====================================================================================

    @DELETE("/customers/{id}/addresses/{addressId}")
    void deleteAddress(@Header(FloraConstant.LanguageKey) String language_id,
                       @Header("Authorization") String Authorization,
                       @Path("id") String customerId,
                       @Path("addressId") String addressId,
                       Callback<Response> response); // delete address

//====================================================================================

    @GET("/shopping_cart_items/{id}?fields=id,quantity,product_id,product_attributes,&shopping_cart_type_id=1")
    void shoppingCartItemsForOrder(@Header(FloraConstant.LanguageKey) String language_id,
                                   @Header("Authorization") String auth_token,
                                   @Path("id") String id,
                                   Callback<ShoppingCarts> getProductsCallback); // create order

//====================================================================================

    @POST("/orders?fields=id,created_on_utc,customer_currency_code,order_status,order_total")
    void createOrders(@Header(FloraConstant.LanguageKey) String language_id,
                      @Header("Authorization") String Authorization,
                      @Header("Content-Type") String content_type,
                      @Body Orders orders,
                      Callback<Response> response); // create order for payment
//====================================================================================

    @GET("/shopping_cart_items/{id}?fields=quantity&shopping_cart_type_id=1")
    void shoppingCartItemsCount(@Header(FloraConstant.LanguageKey) String language_id,
                                @Header("Authorization") String auth_token,
                                @Path("id") String id,
                                Callback<ShoppingCarts> getProductsCallback); // shoppingCartItemsCount

//====================================================================================

    @GET("/orders/{id}")
    void orderById(@Header(FloraConstant.LanguageKey) String language_id,
                   @Header("Authorization") String Authorization,
                   @Path("id") String id,
                   Callback<OrderDelete> getOrders); // get my order item details

//====================================================================================

    @GET("/orders/{id}/reorder")
    void reorder(@Header(FloraConstant.LanguageKey) String language_id,
                 @Header("Authorization") String auth_token,
                 @Path("id") String id,
                 Callback<ShoppingCarts> getProductsCallback); // reorder

//====================================================================================

    @POST("/customers/{id}/addresses")
    void addAddress(@Header(FloraConstant.LanguageKey) String language_id,
                    @Header("Authorization") String Authorization,
                    @Header("Content-Type") String content_type,
                    @Body GetAddress address,
                    @Path("id") String id,
                    Callback<GetAddress> getAddress); // add new address

//====================================================================================

    @PUT("/customers/{id}/addresses/{addressId}")
    void editAddress(@Header(FloraConstant.LanguageKey) String language_id,
                     @Header("Authorization") String Authorization,
                     @Header("Content-Type") String content_type,
                     @Body GetAddress address,
                     @Path("id") String customerId,
                     @Path("addressId") String addressId,
                     Callback<GetAddress> getAddress); // edit address+

//====================================================================================


    @GET("/countries/{id}/StateProvinces?fields=id,name,country_id,localized_names")
    void stateProvinces(@Header(FloraConstant.LanguageKey) String language_id,
                        @Header("Authorization") String auth_token,
                        @Path("id") String countryId,
                        Callback<StateProvinces> getStateProvince); // get cities

//====================================================================================

    @GET("/countries")
    void countries(@Header(FloraConstant.LanguageKey) String language_id,
                   @Header("Authorization") String auth_token,
                   Callback<Countries> getStateProvince); // show countries

//====================================================================================

    @GET("/customers/{id}?fields=phone,email,first_name,last_name,username")
    void customerInfo(@Header(FloraConstant.LanguageKey) String language_id,
                      @Header("Authorization") String Authorization,
                      @Path("id") String id,
                      Callback<GetCustomer> getGetCustomer); // get customer information from api in contact us screen

//====================================================================================

    @POST("/customers/changepassword")
    void changePassword(@Header(FloraConstant.LanguageKey) String language_id,
                        @Header("Authorization") String Authorization,
                        @Query("customer_id") String customer_id,
                        @Query("old_password") String old_password,
                        @Query("new_password") String new_password,
                        Callback<GetCustomer> getGetCustomer); // changePassword

//====================================================================================

    @GET("/customers/{id}")
    void customerById(@Header(FloraConstant.LanguageKey) String language_id,
                      @Header("Authorization") String Authorization,
                      @Path("id") String id,
                      Callback<GetCustomer> getGetCustomer); // get data in profile screen

//====================================================================================

    @GET("/orders")
    void orders(@Header(FloraConstant.LanguageKey) String language_id,
                @Header("Authorization") String Authorization,
                @Query("customer_id") String customer_id,
                @Query("page") String page,
                @Query("limit") String limit,
                Callback<OrderDelete> getOrders); // load my orders list

//====================================================================================

    @GET("/shopping_cart_items/{id}")
    void shoppingCartItems(@Header(FloraConstant.LanguageKey) String language_id,
                           @Header("Authorization") String auth_token,
                           @Path("id") String id,
                           @Query("shopping_cart_type_id") String shopping_cart_type_id,
                           Callback<ShoppingCarts> getProductsCallback); // get cart list and wish list

//====================================================================================

    @PUT("/shopping_cart_items/{id}")
    void updateShoppingCart(@Header(FloraConstant.LanguageKey) String language_id,
                            @Header("Authorization") String Authorization,
                            @Path("id") String id,
                            @Header("Content-Type") String content_type,
                            @Body ShoppingCartItem cartItem,
                            Callback<ShoppingCarts> getShoppingCarts); // update quantity when increase or decrease

//====================================================================================

    @DELETE("/shopping_cart_items/{id}")
    void deleteShoppingCartItem(@Header(FloraConstant.LanguageKey) String language_id,
                                @Header("Authorization") String Authorization,
                                @Path("id") String id,
                                Callback<Response> response); // delete item from cart or wish list

//====================================================================================

    @GET("/categories")
    void fetchCategories(@Header(FloraConstant.LanguageKey) String language_id,
                         @Header("Authorization") String auth_token,
                         @Query("parent_category_id") String parent_category_id,
                         Callback<GetCategories> getGetCategories);  // fetchCategories

//====================================================================================

    @GET("/vendors")
    void fetchShops(@Header(FloraConstant.LanguageKey) String language_id,
                    @Header("Authorization") String auth_token,
                    @Query("category_id") String category_id,
                    Callback<FeaturedShopsModel> getGetCategories);  // fetchCategories

//====================================================================================

    @GET("/product_reviews")
    void fetchReviews(@Header(FloraConstant.LanguageKey) String language_id,
                      @Header("Authorization") String auth_token,
                      Callback<Reviews> getGetCategories);  // fetchCategories

//====================================================================================

    @GET("/products")
    void fetchProducts(@Header(FloraConstant.LanguageKey) String language_id,
                       @Header("Authorization") String auth_token,
                       @Query("customer_id") String user_id,
                       @Query("vendor_name") String vendor_name,
                       @Query("limit") String limit,
                       @Query("page") String page,
                       Callback<GetProducts> getProductsCallback); // fetch products

//====================================================================================

    @GET("/products")
    void fetchProductsByCategory(@Header(FloraConstant.LanguageKey) String language_id,
                       @Header("Authorization") String auth_token,
                       @Query("customer_id") String user_id,
                       @Query("category_id") String category_id,
                       @Query("limit") String limit,
                       @Query("page") String page,
                       Callback<GetProducts> getProductsCallback); // fetch products

//====================================================================================

    @GET("/products/search")
    void products_search(@Header(FloraConstant.LanguageKey) String language_id,
                         @Header("Authorization") String auth_token,
                         @Query("category_id") String category_id, //0
                         @Query("limit") String limit, //5
                         @Query("page") String page, // 0
                         @Query("query") String query,
                         Callback<GetProducts> getProductsCallback); // products_search

//====================================================================================

    @POST("/shopping_cart_items")
    void createShoppingCart(@Header(FloraConstant.LanguageKey) String language_id,
                            @Header("Authorization") String Authorization,
                            @Header("Content-Type") String content_type,
                            @Body ShoppingCartItem cartItem,
                            Callback<ShoppingCarts> getShoppingCarts); // createShoppingCart


//====================================================================================

    @GET("/vendors?show_on_home_page=true")
    void fetchFeatureShops(@Header(FloraConstant.LanguageKey) String language_id,
                           @Header("Authorization") String auth_token,
                           @Query("limit") String limit,
                           @Query("page") String page,
                           Callback<FeaturedShopsModel> getProductsCallback); // get cart list and wish list

//====================================================================================

    @GET("/products")
    void fetchOccasionsProducts(@Header(FloraConstant.LanguageKey) String language_id,
                       @Header("Authorization") String auth_token,
                       @Query("limit") String limit,
                       @Query("page") String page,
                       @Query("manufacturer_id") String manufacturer_id,
                       Callback<GetProducts> getProductsCallback); // fetch products




}
