package app.flora.Ui.Fragments.Home;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.flora.Adapters.HomeCategoryAdapter;
import app.flora.Adapters.FeaturedProductsAdapter;
import app.flora.Adapters.FeaturedShopsAdapter;
import app.flora.Adapters.SliderAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedProductsModel;
import app.flora.Models.FeaturedShopsModel;
import app.flora.Models.SliderModel;
import app.flora.Network.RetrofitClient;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import app.flora.Ui.Fragments.FeatureShopsFragment;
import app.flora.Ui.Fragments.FeaturedProductsFragment;
import app.flora.Ui.Fragments.FilterFragment;
import app.flora.Ui.Fragments.ShopsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    // bind views
    @BindView(R.id.imageSlider)
    SliderView slider;

    @BindView(R.id.categories_recycler)
    RecyclerView categories_recycler;

    @BindView(R.id.featured_shops_recycler)
    RecyclerView featured_shops_recycler;

    @BindView(R.id.featured_products_recycler)
    RecyclerView featured_products_recycler;

    @BindView(R.id.img_arrow_1)
    ImageView img_arrow_1;

    @BindView(R.id.img_arrow_2)
    ImageView img_arrow_2;

    @BindView(R.id.tv_feature_shops)
    TextView tv_feature_shops;

    @BindView(R.id.tv_feature_products)
    TextView tv_feature_products;

    @BindView(R.id.loading_progress)
    ProgressBar loading_progress;

    // vars
    private List<SliderModel.SlidersBean> sliders = new ArrayList<>();
    private ArrayList<CategoriesModel.CategoriesBean> categoriesArrayList = new ArrayList<>();
    private ArrayList<FeaturedShopsModel.VendorsBean> featuredShopsArrayList = new ArrayList<>();
    private ArrayList<FeaturedProductsModel.ProductsBean> featuredProductsArrayList = new ArrayList<>();
    HomeCategoryAdapter categoryAdapter;
    FeaturedShopsAdapter featuredShopsAdapter;
    FeaturedProductsAdapter featuredProductsAdapter;
    LinearLayoutManager CatLayoutManager;
    LinearLayoutManager FeaturedShopsLayoutManager;
    LinearLayoutManager FeaturedProductsLayoutManager;
    HomeViewModel homeViewModel;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int page_index = 1;
    boolean endOfREsults = false;
    private boolean loading_flag = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "HomeFragment Called");
        initVisibility();
        initBold();
        initViewModel();
        initSlider();
        initRotation();
        initCatList();
        initFeaturedShopsList();
        initFeaturedProductsList();
        return view;
    } // onCreateView

    private void initBold() {
        if (LanguageSessionManager.getLang().equals("en"))
        {
            tv_feature_shops.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                    ENGLISH_BOLD));

            tv_feature_products.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                    ENGLISH_BOLD));
        }
        else if (LanguageSessionManager.getLang().equals("ar"))
        {
            tv_feature_shops.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                    ARABIC_BOLD));

            tv_feature_products.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                    ARABIC_BOLD));
        }
    } // initBold

    @OnClick(R.id.img_arrow_2)
    public void initFeatureProducts() {
        Bundle bundle = new Bundle();
        bundle.putString("comingFrom", "");
        bundle.putString("query", "" + "");
        Log.i(FloraConstant.TAG, "sendSearch : " + "");
        FeaturedProductsFragment fragment = new FeaturedProductsFragment();
        fragment.setArguments(bundle);
        Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "");

    } // initFeatureProducts

    @OnClick(R.id.img_arrow_1)
    public void initFeatureShops() {
        FeatureShopsFragment fragment = new FeatureShopsFragment();
        Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "");

    } // initFeatureProducts

    private void initViewModel() {
        //  loading_progress.setVisibility(View.VISIBLE);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.fetchSlider();
        homeViewModel.list.observe(this, new Observer<List<SliderModel.SlidersBean>>() {
            @Override
            public void onChanged(List<SliderModel.SlidersBean> sliderModels) {
                if (sliderModels != null) {
                    SliderAdapter sliderAdapter = new SliderAdapter(getActivity(), sliderModels);
                    slider.setSliderAdapter(sliderAdapter);
                    loading_progress.setVisibility(View.GONE);
                }
            }
        });
    } // init view model

    private void initRotation() {
        if (LanguageSessionManager.getLang().equals("ar")) {
            img_arrow_1.setRotation(180);
            img_arrow_2.setRotation(180);
        } else {
            img_arrow_1.setRotation(0);
            img_arrow_2.setRotation(0);
        }
    } // init Rotation

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Home));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.shoppingCartItemsCount();
    }

    private void initCatList() {
        if (categoriesArrayList.size() > 0) {
            initCatRecyclerView();
        } else {
            fetchCategories();
        }
    } // init list

    private void initFeaturedShopsList() {
        if (featuredShopsArrayList.size() > 0) {
            initFeaturedShopsRecyclerView();
        } else {
            fetchFeaturedShops();
        }
    } // init list

    private void initFeaturedProductsList() {
        if (featuredProductsArrayList.size() > 0) {
            initFeaturedProductsRecyclerView();
        } else {
            fetchFeaturedProducts();
        }
    } // init list

    private void fetchFeaturedShops() {

        // loading_progress.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchFeatureShopsOnHome().enqueue(
                new Callback<FeaturedShopsModel>() {
                    @Override
                    public void onResponse(Call<FeaturedShopsModel> call, Response<FeaturedShopsModel> response) {

                        if (response.body() != null) {
                            Log.i(FloraConstant.TAG, "fetchFeaturedShops Array size = " + response
                                    .body().getVendors().size());


                            featuredShopsArrayList.addAll(response.body().getVendors());
                            initFeaturedShopsRecyclerView();
                        }
                        loading_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<FeaturedShopsModel> call, Throwable t) {
                        Log.i(FloraConstant.TAG, "error in Retrofit = " + t.getLocalizedMessage());
                        loading_progress.setVisibility(View.GONE);

                    }
                });

        initFeaturedShopsRecyclerView();
    } // fetchFeaturedShops

    private void fetchFeaturedProducts() {

        Log.i(FloraConstant.TAG, "fetchFeaturedProducts Called ");

        //  loading_progress.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchFeatureProductsOnHome(
                page_index,
                FloraConstant.SMALL_Limit
        ).enqueue(
                new Callback<FeaturedProductsModel>() {
                    @Override
                    public void onResponse(Call<FeaturedProductsModel> call, Response<FeaturedProductsModel> response) {
                        Log.i(FloraConstant.TAG, "fetchFeaturedProducts Array size = " + response
                                .body().getProducts().size());

                        if (response.body() != null) {
                            featuredProductsArrayList.addAll(response.body().getProducts());
                            initFeaturedProductsRecyclerView();
                        }
                        if (featuredProductsArrayList.isEmpty()) {
                            page_index = page_index - 1;
                            endOfREsults = true;
                        }

                        setPaging();
                        loading_flag = true;
                        loading_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<FeaturedProductsModel> call, Throwable t) {
                        Log.i(FloraConstant.TAG, "error in Retrofit = " + t.getLocalizedMessage());
                        loading_progress.setVisibility(View.GONE);

                    }
                });

    } // fetchFeaturedProducts

    private void setPaging() {
        Log.i(FloraConstant.TAG, "setPaging called : ");

        featured_products_recycler.post(new Runnable() {
            @Override
            public void run() {
                featured_products_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (!endOfREsults) {
                            visibleItemCount = FeaturedProductsLayoutManager.getChildCount();
                            totalItemCount = FeaturedProductsLayoutManager.getItemCount();
                            pastVisiblesItems = FeaturedProductsLayoutManager.findFirstVisibleItemPosition();
                            if (loading_flag) {
                                if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                                    loading_flag = false;
                                    if (!(featuredProductsArrayList.size() == 0)) {
                                        loading_progress.setVisibility(View.GONE);
                                    }
                                    page_index = page_index + 1;
                                    fetchMoreFeatureProducts();
                                }
                            }
                        }
                    }
                });
            }
        });

    } // set pagination

    private void fetchMoreFeatureProducts() {
        loading_progress.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchFeatureProductsOnHome(
                page_index,
                FloraConstant.SMALL_Limit
        ).enqueue(new Callback<FeaturedProductsModel>() {
            @Override
            public void onResponse(Call<FeaturedProductsModel> call, Response<FeaturedProductsModel> response) {

                loading_progress.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.body().getProducts().isEmpty()) {
                        page_index = page_index - 1;
                        endOfREsults = true;
                    }
                    featuredProductsArrayList.addAll(response.body().getProducts());
                    featuredProductsAdapter.notifyDataSetChanged();
                    loading_flag = true;
                }
                loading_progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<FeaturedProductsModel> call, Throwable t) {
                Log.i(FloraConstant.TAG, "error in Retrofit = " + t.getLocalizedMessage());
                loading_progress.setVisibility(View.GONE);

            }
        });
    } // fetchMoreFeatureProducts

    private void fetchCategories() {

        loading_progress.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchCategoriesOnHome().enqueue(
                new Callback<CategoriesModel>() {
                    @Override
                    public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {

                        if (response.body() != null) {

                            categoriesArrayList.addAll(response.body().getCategories());
                            initCatRecyclerView();
                        }
                        loading_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<CategoriesModel> call, Throwable t) {
                        Log.i(FloraConstant.TAG, "error in Retrofit = " + t.getLocalizedMessage());
                        loading_progress.setVisibility(View.GONE);
                    }
                });

    } // fetchCategories


    private void initCatRecyclerView() {
        CatLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        categoryAdapter = new HomeCategoryAdapter(getActivity(), categoriesArrayList);
        categories_recycler.setHasFixedSize(true);
        categories_recycler.setLayoutManager(CatLayoutManager);
        categories_recycler.setAdapter(categoryAdapter);
    } // initCatRecyclerView


    private void initFeaturedShopsRecyclerView() {
        FeaturedShopsLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        featuredShopsAdapter = new FeaturedShopsAdapter(getActivity(), featuredShopsArrayList);
        featured_shops_recycler.setHasFixedSize(true);
        featured_shops_recycler.setLayoutManager(FeaturedShopsLayoutManager);
        featured_shops_recycler.setAdapter(featuredShopsAdapter);
    } // initFeaturedRecyclerView

    private void initFeaturedProductsRecyclerView() {
        FeaturedProductsLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        featuredProductsAdapter = new FeaturedProductsAdapter(getActivity(), featuredProductsArrayList);
        featured_products_recycler.setHasFixedSize(true);
        featured_products_recycler.setLayoutManager(FeaturedProductsLayoutManager);
        featured_products_recycler.setAdapter(featuredProductsAdapter);
    } // initFeaturedRecyclerView

    private void initSlider() {
        SliderAdapter adapter = new SliderAdapter(getContext(), sliders);
        slider.setSliderAdapter(adapter);
        slider.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        slider.setSliderTransformAnimation(SliderAnimations.FIDGETSPINTRANSFORMATION);
        slider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        slider.setScrollTimeInSec(4); //set scroll delay in seconds :
        slider.startAutoCycle();
    } // initialize sliderList method
}
