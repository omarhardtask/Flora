package app.flora.Ui.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.BaselineLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.LocaleHelper;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.GetCustomer;
import app.flora.Models.ShoppingCart;
import app.flora.Models.ShoppingCarts;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Fragments.AddAddressFragment;
import app.flora.Ui.Fragments.BottomSheetDialog;
import app.flora.Ui.Fragments.CartFragment;
import app.flora.Ui.Fragments.FeaturedProductsFragment;
import app.flora.Ui.Fragments.FilterFragment;
import app.flora.Ui.Fragments.Home.HomeFragment;
import app.flora.Ui.Fragments.LoginFragment;
import app.flora.Ui.Fragments.MoreFragment;
import app.flora.Ui.Fragments.OccasionsFragment;
import app.flora.Ui.Fragments.ProductsFragment;
import app.flora.Ui.Fragments.SearchFragment;
import app.flora.Ui.Fragments.ShopsCategoriesFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.SearchView)
    SearchView SearchView;

    @BindView(R.id.linear_search)
    public LinearLayout linear_search;

    @BindView(R.id.title)
    public TextView title;

    @BindView(R.id.img_back)
    public ImageView img_back;

    @BindView(R.id.img_sort)
    public ImageView img_sort;

    @BindView(R.id.img_filter)
    public ImageView img_filter;

    @BindView(R.id.img_add)
    public ImageView img_add;

    @BindView(R.id.img_logo)
    public ImageView img_logo;

    public static Typeface typeface;
    public static CardView toolbar;
    public static BottomNavigationView bottomNavigationView;
    Typeface avenirHeavy;
    static TextView badge;
    private static View notificationBadge;
    static BottomNavigationMenuView menuView;
    static BottomNavigationItemView itemView;
    String guestValue = "";
    static AppCompatActivity act;
    public static ImageLoader mImageLoader;
    public static boolean isEnglish = false;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(LocaleHelper.onAttach(newBase)));
    } // attach language method


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initLanguage();
        shoppingCartItemsCount();
        registerGuest();
        getGuestValue();
        initAddAddress();
        initSearchBackground();
        setupBottomNavigationView();
        setNavigationTypeface();
        if (savedInstanceState == null) {
            if (guestValue.equals("1")) {
                if (SessionManager.isLoggedin()) {
                    setupBottomNavigationView();
                    Fragment homeFragment = new HomeFragment();
                    Navigator.loadFragment(this, homeFragment, R.id.fragment_container, false, "");
                    Log.i(FloraConstant.TAG, "login as a guest clicked open home screen");
                }
            } else
            {
                Log.i(FloraConstant.TAG, "guestValue = 0 open login screen");
                Fragment loginFragment = new LoginFragment();
                Navigator.loadFragment(this, loginFragment, R.id.fragment_container, false, "");
              //  initLoginVisibility();
            }
        } else {
            setupBottomNavigationView();
        }
    } // onCreate

    private void initLanguage() {

        if (LanguageSessionManager.getLang() != null && !LanguageSessionManager.getLang().equals("")) {

            if (LanguageSessionManager.getLang().equals("en")) {
                Log.i(FloraConstant.TAG, "Apply english font in main ");
                updateViews("en");
                isEnglish = true;
                typeface = Typeface.createFromAsset(getAssets(), FloraConstant.ENGLISH_FONT);
                CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(FloraConstant.ENGLISH_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build());
            }
            else if (LanguageSessionManager.getLang().equals("ar")) {
                Log.i(FloraConstant.TAG, "Apply arabic font in main ");
                updateViews("ar");
                isEnglish = false;
                typeface = Typeface.createFromAsset(getAssets(), FloraConstant.ARABIC_FONT);
                CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(FloraConstant.ARABIC_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build());
            }
        } else {
            updateViews("en");
            isEnglish = true;
        }

    } // initialize language method

    private void updateViews(String languageCode) {
        LocaleHelper.setLocale(this, languageCode);
    } // updateViews

    private void initAddAddress() {
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddAddressFragment();
                if (!SessionManager.isLoggedin()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("comingFrom", "cart");
                    fragment.setArguments(bundle);
                }
                Navigator.loadFragment(act, fragment, R.id.fragment_container, true, "home");
            }
        });
    } // initAddAddress

    public static void setTextFonts(ViewGroup root) {

        if (LanguageSessionManager.getLang().equals("ar")) {
            typeface = Typeface.createFromAsset(act.getAssets(), FloraConstant.ARABIC_FONT);
        }
        else {
            typeface  = Typeface.createFromAsset(act.getAssets(), FloraConstant.ENGLISH_FONT);

        }

        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(typeface);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(typeface);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(typeface);
            } else if (v instanceof TextInputLayout) {
                ((TextInputLayout) v).setTypeface(typeface);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(typeface);
            } else if (v instanceof ViewGroup) {
                setTextFonts((ViewGroup) v);
            }
        }
    } // set font

    public static void registerGuest() {

        if (!SessionManager.isLoggedin()) {
            Log.i(FloraConstant.TAG, SessionManager.getGuestUserId() + "");
           // MoreFragment.user_status.setText(act.getString( R.string.login));
           // userStatus.setTitle(act.getString(R.string.login));
            Log.i(FloraConstant.TAG, "btn_status set login ");

            if (SessionManager.getGuestUserId().equalsIgnoreCase("0")) {
                createCustomer();
            }
        } else {
          //  MoreFragment.user_status.setText(act.getString( R.string.logout));
            //userStatus.setTitle(getString(R.string.logout));
            Log.i(FloraConstant.TAG, "btn_status set logout ");
            Log.i(FloraConstant.TAG, "registerGuest else : " + SessionManager.getGuestUserId() + "");
            //initStatus();
        }
    } // register guest method

    public static void shoppingCartItemsCount() {
        FloraApiCall.getCallingAPIInterface().shoppingCartItemsCount
                (LanguageSessionManager.getLang(),
                        FloraConstant.AuthorizationToken,
                        MainActivity.getUserId(),
                        new Callback<ShoppingCarts>() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void success(ShoppingCarts outResponse, Response response) {
                              //  cart_count.setVisibility(View.GONE);
                                if (outResponse != null) {

                                    Log.i(FloraConstant.TAG, "not null");
                                    Log.i(FloraConstant.TAG, "shoppingCartItemsCount success : " +
                                            outResponse.getShoppingCarts().size());
                                    int count = 0;
                                    if (outResponse.getShoppingCarts().size() > 0) {

                                        addBadgeView();
                                       // cart_count.setVisibility(View.VISIBLE);
                                        Log.i(FloraConstant.TAG, "shoppingCartItemsCount success : " +
                                                outResponse.getShoppingCarts().size());

                                        for (ShoppingCart shoppingCart : outResponse.getShoppingCarts()) {
                                            count = count + shoppingCart.getQuantity();
                                        }

                                        Log.i(FloraConstant.TAG, "shoppingCartItemsCount" +
                                                " count in main : " + count);
                                        //cart_count.setText(count + "");
                                    }
                                    else
                                    {
                                        removeBadge();
                                     //   cart_count.setVisibility(View.GONE);
                                    }


                                } else {
                                    Log.d(FloraConstant.TAG, "null");
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                //GlobalFunctions.showErrorMessage(error, fragmentContainer);
                                //checking here token is expired or not

                            }
                        });
    } // get shopping cart list item count

    private static void createCustomer() {

        FloraApiCall.getCallingAPIInterface().createGuestCustomer(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                "application/json", new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, retrofit.client.Response response) {

                        if (outResponse != null) {

                            Log.d(FloraConstant.TAG, "not null");

                            if (outResponse.getCustomers() != null) {

                                Log.d(FloraConstant.TAG, "not null 1");

                                if (outResponse.getCustomers().size() > 0) {
                                    Log.d(FloraConstant.TAG, "not null 2");
                                    SessionManager.setGuestUserId(outResponse.getCustomers().get(0).getId() + "");
                                }
                            }
                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d(FloraConstant.TAG, error.getMessage());
                    }
                });
    } // create customer as guest

    private void getGuestValue() {
        SharedPreferences prefs = getSharedPreferences(FloraConstant.MY_PREFS_NAME, MODE_PRIVATE);
        guestValue = prefs.getString("guest", "");
        Log.i(FloraConstant.TAG, "guestValue : " + guestValue);
    } // get guest saved value

    @OnClick(R.id.img_sort)
    public void initSort() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(getSupportFragmentManager(), "BottomSheet");
    } // init sort

    @OnClick(R.id.img_filter)
    public void initFilter() {
        FilterFragment filterFragment = new FilterFragment();
        Navigator.loadFragment(this, filterFragment, R.id.fragment_container, true, "");
    } // init sort

    public static void removeBadge() {
        badge.setBackgroundColor(Color.parseColor("#00000000"));
        Log.i(FloraConstant.TAG, "remove badge function ");
    } // add cart badge

    public static void addBadgeView() {
        badge.setBackgroundResource(R.drawable.noti_circle);
        Log.i(FloraConstant.TAG, "add badge function ");
    } // add cart badge

    public static String getUserId() {

        String id = "";

        if (SessionManager.isLoggedin()) {
            id = SessionManager.getUserCode();
        } else {
            id = SessionManager.getGuestUserId();
        }
        return id;
    } // get the guest user id

    public void setNavigationTypeface() {
        if (LanguageSessionManager.getLang() != null && !LanguageSessionManager.getLang().equals("")) {
            if (LanguageSessionManager.getLang().equals("en")) {
//                avenirHeavy = Typeface.createFromAsset(this.getAssets(),
//                        FloraConstant.ENGLISH_FONT);
            } else {
                avenirHeavy = Typeface.createFromAsset(this.getAssets(),
                        FloraConstant.ARABIC_FONT);

            }
        }

        ViewGroup navigationGroup = (ViewGroup) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < navigationGroup.getChildCount(); i++) {
            ViewGroup navUnit = (ViewGroup) navigationGroup.getChildAt(i);
            for (int j = 0; j < navUnit.getChildCount(); j++) {
                View navUnitChild = navUnit.getChildAt(j);
                if (navUnitChild instanceof BaselineLayout) {
                    BaselineLayout baselineLayout = (BaselineLayout) navUnitChild;
                    for (int k = 0; k < baselineLayout.getChildCount(); k++) {
                        View baselineChild = baselineLayout.getChildAt(k);
                        if (baselineChild instanceof TextView) {
                            TextView textView = (TextView) baselineChild;
                            textView.setTypeface(avenirHeavy);
                        }
                    }
                }
            }
        }
    } // set font style for bottom navigation

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                onBackPressed();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    } // init Back from device

    private void initViews() {
        act = MainActivity.this;
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        itemView = (BottomNavigationItemView) menuView.getChildAt(3);
        notificationBadge = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_badge, menuView, false);
        badge = notificationBadge.findViewById(R.id.badge);
        itemView.addView(notificationBadge);
        mImageLoader = ImageLoader.getInstance();
        this.mImageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
    } // initViews

    private void initSearchBackground() {

        SearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_search.setBackground(getDrawable(R.drawable.search_bar));
                img_logo.setVisibility(View.GONE);
                img_filter.setVisibility(View.GONE);
                img_sort.setVisibility(View.GONE);
            }
        });
        SearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                linear_search.setBackground(null);
                img_logo.setVisibility(View.VISIBLE);
                return false;
            }
        });

        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putString("comingFrom", "search");
                bundle.putString("query", "" + query);
                Log.i(FloraConstant.TAG, "sendSearch : " + query);
                SearchFragment fragment = new SearchFragment();
                fragment.setArguments(bundle);
                Navigator.loadFragment(act, fragment, R.id.fragment_container, false, "");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


    } // initSearchBackground

    private void setupBottomNavigationView() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (bottomNavigationView != null) {
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
            Log.i(FloraConstant.TAG, "setupBottomNavigationView if");

        } else {
            Log.i(FloraConstant.TAG, "setupBottomNavigationView else");


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    } // setup bottom navigation click listener

    public void selectFragment(MenuItem item) {

        item.setChecked(true);
//=======================================================================================================================
        switch (item.getItemId()) {
            // Home screen
            case R.id.bottom_home:
                HomeFragment homeFragment = new HomeFragment();
                Navigator.loadFragment(this, homeFragment, R.id.fragment_container, true, "");
                break;
//=======================================================================================================================

            // Occasions screen
            case R.id.bottom_my_occasion:
                OccasionsFragment occasionsFragment = new OccasionsFragment();
                Navigator.loadFragment(this, occasionsFragment, R.id.fragment_container, true, "");
                break;
//=======================================================================================================================

            // Occasions screen
            case R.id.bottom_shop:
                ShopsCategoriesFragment shopsCategoriesFragment = new ShopsCategoriesFragment();
                Navigator.loadFragment(this, shopsCategoriesFragment, R.id.fragment_container, true, "");
                break;
//=======================================================================================================================
            // Occasions screen
            case R.id.bottom_cart:
                CartFragment cartFragment = new CartFragment();
                Navigator.loadFragment(this, cartFragment, R.id.fragment_container, true, "");
                break;
//=======================================================================================================================
            // More screen
            case R.id.bottom_more:
                MoreFragment moreFragment = new MoreFragment();
                Navigator.loadFragment(this, moreFragment, R.id.fragment_container, true, "");
                break;
//=======================================================================================================================


        }
    } // select bottom navigation fragments when clicked
}
