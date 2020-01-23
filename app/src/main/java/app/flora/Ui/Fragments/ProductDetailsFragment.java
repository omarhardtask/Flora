package app.flora.Ui.Fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import app.flora.Adapters.AttributeAdapter;
import app.flora.Adapters.ProductImageSliderAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.Attribute;
import app.flora.Models.AttributeValue;
import app.flora.Models.FeaturedProductsModel;
import app.flora.Models.Product;
import app.flora.Models.ProductAttribute;
import app.flora.Models.ShoppingCartItem;
import app.flora.Models.ShoppingCartItem_;
import app.flora.Models.ShoppingCarts;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductDetailsFragment extends Fragment {

    // Bind Views
//    @BindView(R.id.img_item)
//    ImageView img_item;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.tv_delivery)
    TextView tv_delivery;


    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.attribute_rv)
    RecyclerView attribute_rv;

    @BindView(R.id.tv_quantity)
    TextView tvQuantity;

    @BindView(R.id.loading_progress)
    ProgressBar mloading;

    @BindView(R.id.layout)
    CoordinatorLayout layout;

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    @BindView(R.id.page_indicator)
    TabLayout page_indicator;

    // vars
    Product product = null;
    public static ArrayList<Attribute> attributes = new ArrayList<>();
    public static FragmentActivity act;
    boolean added;
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
    int quantity = 1, NUM_PAGES = 0, currentPage = 0;
    String s = "", delivery_text = "";
    Timer swipeTimer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_details_fragment, container, false);
        ButterKnife.bind(this, view);
        initVisibility();
        initAct();
        initBold();
        getProductObject();
        initDefaultQuantity();
        return view;
    }

    private void initBold() {
        if (LanguageSessionManager.getLang().equals("en"))
        {
            tv_delivery.setTypeface(Typeface.createFromAsset(act.getAssets(), FloraConstant.
                    ENGLISH_BOLD));


        }
        else if (LanguageSessionManager.getLang().equals("ar"))
        {
            tv_delivery.setTypeface(Typeface.createFromAsset(act.getAssets(), FloraConstant.
                    ARABIC_BOLD));

        }
    }

    private void setupSlider() {
        Log.i(FloraConstant.TAG, "setupSlider called ");
        Log.i(FloraConstant.TAG, "product.getImages() " + product.getImages());
        ProductImageSliderAdapter slidingImageAdapter = new ProductImageSliderAdapter(act, product.getImages());
        slidingImageAdapter.notifyDataSetChanged();
        view_pager.setAdapter(slidingImageAdapter);
        page_indicator.setupWithViewPager(view_pager, true);
        NUM_PAGES = product.getImages().size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                view_pager.setCurrentItem(currentPage++, true);
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage = position;
                if (position == NUM_PAGES) {
                    currentPage = 0;
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    } // init slider


    @OnClick(R.id.btn_check)
    void addToCartClick() {
        addToCart();
    } // addToCartClick method


    public void addToCart() {
        s = "";
        boolean required = true;

        try {
            for (Attribute a : attributes) {
                if (a.getIs_required()) {

                    Log.i(FloraConstant.TAG, "a.getIs_required()" + a.getIs_required());
                    Log.i(FloraConstant.TAG, a.getLocalized_names().get(0).getLocalizedName());

                    boolean f = false;
                    for (AttributeValue av : a.getAttribute_values()) {
                        Log.i(FloraConstant.TAG, "1" + av.getLocalized_names().get(0).getLocalizedName());

                        if (av.getIs_pre_selected()) {
                            Log.i(FloraConstant.TAG, "av.getIs_pre_selected()" + av.getIs_pre_selected());
                            Log.i(FloraConstant.TAG, "2" + av.getLocalized_names().get(0).getLocalizedName());
                            f = true;
                        }
                    }

                    Log.i(FloraConstant.TAG, "fffffff" + f);

                    if (!f) {
                        Log.i(FloraConstant.TAG, "!f" + f);

                        if (a.getLocalized_names() != null) {
                            Log.i(FloraConstant.TAG, "a.getLocalized_names()" + a.getLocalized_names());
                            s += a.getLocalized_names().get(0).getLocalizedName() + ", ";
                        } else {
                            s += "" + ", ";
                        }
                    }
                    if (!f) {
                        Log.i(FloraConstant.TAG, "required in ifff" + required);
                        required = f;
                    }
                }
            }
        } catch (Exception e) {
        }

        Log.i(FloraConstant.TAG, "required : " + required);
        if (required) {
            createShoppingCart(true);
            Log.i(FloraConstant.TAG, "trueeeee");
        } else {
            Snackbar.make(getView(), getString(R.string.DataMissing1) + "\n" +
                    s.substring(0, s.length() - 2), Snackbar.LENGTH_LONG).show();
            Log.i(FloraConstant.TAG, "falseee");
        }
    } // init add to cart method


    private void initDefaultQuantity() {

        try {
            if (product.getOrderMinimumQuantity() == null) {
                quantity = 1;
                Log.i(FloraConstant.TAG, "product.getOrderMinimumQuantity() == null" + quantity);

            }
            if (product.getOrderMinimumQuantity() != null)
                quantity = product.getOrderMinimumQuantity();
            if (quantity <= 0) {
                quantity = 1;
            }
            Log.i(FloraConstant.TAG, "default minimum quantity == " + quantity);
            tvQuantity.setText(quantity + "");
        } catch (Exception e) {
        }
    } // init default quantity

    @OnClick(R.id.card_decrease)
    void img_minus() {

        if (quantity > 1) {
            quantity = quantity - 1;
        }
        tvQuantity.setText(quantity + "");
    } // click on minus

    @OnClick(R.id.card_increase)
    void img_add() {
        quantity = quantity + 1;
        tvQuantity.setText(quantity + "");
    } // click on plus


    private void initAct() {

        if (act == null) {
            act = getActivity();
        } else {
            act = getActivity();
        }
    } // initAct

    @OnClick(R.id.fab)
    void addFavourite() {
        createShoppingCart(false);
    } // click on fav image

    private void createShoppingCart(boolean isAddToCart) {

        mloading.setVisibility(View.VISIBLE);
        FixControl.DisableLayout(layout);
        prepareShoppingCartData(isAddToCart);
        FloraApiCall.getCallingAPIInterface().createShoppingCart(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                "application/json",
                shoppingCartItem, new Callback<ShoppingCarts>() {
                    @Override
                    public void success(ShoppingCarts outResponse, retrofit.client.Response response) {
                        mloading.setVisibility(View.GONE);
                        FixControl.EnableLayout(layout);
                        if (outResponse != null) {
                            Log.d("body", "not null");
                            if (outResponse.getShoppingCarts() != null) {
                                Log.d("body", "not null 1");
                                if (outResponse.getShoppingCarts().size() > 0) {

                                    Log.d("body", "not null 2");

                                    if (isAddToCart) {
                                        new AlertDialog.Builder(act)
                                                .setTitle(act.getString(R.string.app_name))
                                                .setMessage(act.getString(R.string.ProductAddedLabel))
                                                .setPositiveButton(R.string.ContinueShopping, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .setNegativeButton(act.getString(R.string.checkout), new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        Fragment fragment = new CartFragment();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putBoolean("isCartList", true);
                                                        fragment.setArguments(bundle);
                                                        Navigator.loadFragment(act, fragment,
                                                                R.id.fragment_container, true, "home");
                                                    }
                                                })
                                                .setIcon(R.drawable.logo_flora)
                                                .show();
                                    } else {

                                        fab.setImageResource(R.drawable.fave_icon_sel);
                                        Snackbar.make(layout,
                                                act.getString(R.string.ProductAddedWishlistLabel), Snackbar.LENGTH_LONG).show();

                                    }

                                    if (!MainActivity.getUserId().equalsIgnoreCase("0")) {

                                        MainActivity.shoppingCartItemsCount();

                                    }

                                }

                            }

                        } else {

                            Log.d("body", "null");

                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        FixControl.showErrorMessage(error, layout);

                        mloading.setVisibility(View.GONE);

                        FixControl.EnableLayout(layout);

                        Log.d("RetrofitError", "failure");

                    }

                });

    }


    private void prepareShoppingCartData(boolean isAddToCart) {

        ShoppingCartItem_ cartItem_ = new ShoppingCartItem_();
        cartItem_.setCustomerId(Integer.parseInt(MainActivity.getUserId()));
        cartItem_.setProductId(product.getId());
        cartItem_.setQuantity(quantity);
        cartItem_.setShoppingCartType(FloraConstant.ShoppingCartType[isAddToCart ? 0 : 1]);

        List<ProductAttribute> attributeList = new ArrayList<>();
        int i = 0;
        for (Attribute a : attributes) {
            for (AttributeValue av : a.getAttribute_values()) {
                if (av.getIs_pre_selected()) {
                    i++;
                    ProductAttribute p = new ProductAttribute();
                    p.setId(i);
                    p.setCode(a.getId());
                    p.setValue(av.getId() + "");
                    if (a.getAttribute_control_type_name().equals("MultilineTextbox") ||
                            a.getAttribute_control_type_name().equals("TextBox") ||
                            a.getAttribute_control_type_name().equals("Datepicker")) {
                        p.setValue(av.getName());
                    } else {
                        p.setValue(av.getId() + "");
                    }
                    attributeList.add(p);
                }
            }
        }
        cartItem_.setProduct_attributes(attributeList);

        shoppingCartItem.setShoppingCartItem(cartItem_);
    } // prepareShoppingCartData method

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getActivity().getString(R.string.ProductDetails));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy


    private void getProductObject() {
        if (getArguments() != null) {
            Gson gson = new Gson();
            if (getArguments().containsKey("Product")) {
                product = gson.fromJson(getArguments().getString("Product"), Product.class);
            }
        }
        // product_name = product.getTitle();
        setData();
        Log.i(FloraConstant.TAG, "product price in details : " + product.getPrice());
    } // get saved product

    @SuppressLint("SetTextI18n")
    private void setData() {

        if (product.getAdded_to_wishlist()) {
            fab.setImageResource(R.drawable.fave_icon_sel);
            Log.i(FloraConstant.TAG, "(product.getAdded_to_wishlist().equals(true)" + product.getAdded_to_wishlist());
        } else {
            fab.setImageResource(R.drawable.fave_icon_unsel);
            Log.i(FloraConstant.TAG, "(product.getAdded_to_wishlist().equals(false)" + product.getAdded_to_wishlist());
        }


        setupSlider();

//        Glide.with(getActivity())
//                .load(product.getPhoto())
//                .placeholder(R.drawable.product_details_noimg)
//                .centerCrop()
//                .error(R.drawable.product_details_noimg)
//                .into(img_item);


        if (product.getDelivery_date_id().equals("0")) {
            delivery_text = getString(R.string.same_day_delivery);

        }
        if (product.getDelivery_date_id().equals(null)) {
            delivery_text = getString(R.string.same_day_delivery);

        } else if (product.getDelivery_date_id().equals("1")) {
            delivery_text = getString(R.string.same_day_delivery);

        } else if (product.getDelivery_date_id().equals("2")) {
            delivery_text = getString(R.string.after_2_days);

        } else if (product.getDelivery_date_id().equals("3")) {
            delivery_text = getString(R.string.after_3_days);

        } else if (product.getDelivery_date_id().equals("4")) {
            delivery_text = getString(R.string.after_4_days);

        }


        tv_delivery.setText( "( " + delivery_text + " )");

        tv_title.setText(product.getLocalizedName());
        tv_desc.setText(product.getLocalizedShortDescription());
        tv_price.setText(product.getPrice() + " " + SessionManager.getUserCurrencyCode());


        // attributes

        attributes.clear();
        try {
            if (product.getAttributes().size() > 0) {
                attributes.addAll(product.getAttributes());
                Collections.sort(attributes);
                AttributeAdapter attributeAdapter = new AttributeAdapter(getActivity(), attributes);
                LinearLayoutManager layoutManager = new
                        LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                attribute_rv.setLayoutManager(layoutManager);
                attribute_rv.setAdapter(attributeAdapter);

                Log.i(FloraConstant.TAG, "attributes : " + attributes.size());
                Log.i(FloraConstant.TAG, "attributes : " + product.getAttributes().size());
                //    Log.i(chefConstant.TAG, "attributes : " + attributes.get(0).getAttribute_values().size());
            }
        } catch (Exception e) {
            Log.i(FloraConstant.TAG, "Exception :  " + e.getMessage());
        }

    } // setData
}
