package app.flora.Ui.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.CategoryProductsAdapter;
import app.flora.Adapters.ProductsAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Models.Category;
import app.flora.Models.GetCategories;
import app.flora.Models.GetProducts;
import app.flora.Models.Product;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductsFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_products)
    RecyclerView rv_products;
    @BindView(R.id.rv_product_cat)
    RecyclerView rv_product_cat;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.loading_progress_products)
    ProgressBar loading_progress_products;

    // vars
    String shop_name = "", comeFrom = "", productCategoryId = "",
            manufacturer_id = "";
    private ArrayList<Product> productsArrayList = new ArrayList<>();
    private ArrayList<Category> categoryProductsArrayList = new ArrayList<>();
    private ArrayList<Category> updateArrayList = new ArrayList<>();
    int page_index = 1, Page_index_ = 1;
    private boolean loading_flag = true;
    private boolean loading_flag_ = true;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    ProductsAdapter productsAdapter;
    CategoryProductsAdapter categoryProductsAdapter;
    boolean endOfREsults = false;
    boolean endOfREsults_ = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int pastVisiblesItems_, visibleItemCount_, totalItemCount_;
    Product product;
    int savedPos = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.products_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "ShopsFragment Called");
        getProductName();
        getManufactureId();
        initComeFrom();
        initVisibility();
        initProductsList();
        initCatProductsList();
        return view;

    } // onCreateView

    private void getManufactureId() {
        if (getArguments().containsKey("ManufactureId")) {
            manufacturer_id = getArguments().getString("ManufactureId");
        }

        Log.i(FloraConstant.TAG, "getManufactureId = " + manufacturer_id);

    } // getManufactureId

    private void initComeFrom() {

        if (comeFrom.equals("ShopsAdapter")) {
            rv_product_cat.setVisibility(View.GONE);

        } else {
            rv_product_cat.setVisibility(View.VISIBLE);
        }

    } // initComeFrom

    @OnClick(R.id.review)
    public void review() {
        ReviewsFragment reviewsFragment = new ReviewsFragment();
        Navigator.loadFragment(getActivity(), reviewsFragment, R.id.fragment_container, true, "");

    } // review

    private void getProductName() {
        assert getArguments() != null;

        if (getArguments().containsKey("shop_name")) {
            shop_name = getArguments().getString("shop_name");
            Log.i(FloraConstant.TAG, "get shop_name in  shops fragment : " + shop_name);
        }

        if (getArguments().containsKey("comeFrom")) {
            comeFrom = getArguments().getString("comeFrom");
            Log.i(FloraConstant.TAG, "get comeFrom in  products fragment : " + comeFrom);
        }

        if (getArguments() != null) {
            Gson gson = new Gson();
            if (getArguments().containsKey("Product")) {
                product = gson.fromJson(getArguments().getString("Product"), Product.class);
            }
        }

    } // getProductNames

    private void initVisibility() {

        if (!shop_name.equals("")) {
            ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(shop_name);
        } else {
            ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getActivity().getString(R.string.products));

        }

        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initProductsList() {

        if (comeFrom.equals("true")) {
            Log.i(FloraConstant.TAG, "comeFrommmmmm: " + comeFrom);
            fetchProductsByCategory(String.valueOf(product.getId()));
        } else if (comeFrom.equals("Occasions")) {
            Log.i(FloraConstant.TAG, "comeFrommmmmm: " + comeFrom);
            fetchOccasionsProducts();
        } else {
            Log.i(FloraConstant.TAG, "comeFrommmmmm elsee : " + comeFrom);

            if (productsArrayList.size() > 0) {
                initProductsRecyclerView();
            } else {
                fetchProducts();
            }
        }

    } //  initProductsList

    private void initCatProductsList() {

        if (!comeFrom.equals("FeaturedShops") && !comeFrom.equals("Occasions")) {
            if (categoryProductsArrayList.size() > 0) {
                initCatProductsRecyclerView();
            } else {
                fetchCatProducts();
            }
        }


    } //  initCatProductsList

    private void fetchCatProducts() {
        Log.i(FloraConstant.TAG, "fetchCatProducts called : ");

        //loadingProgress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().
                fetchCategories(
                        LanguageSessionManager.getLang(),
                        FloraConstant.AuthorizationToken,
                        product.getId() + ""
                        ,
                        new Callback<GetCategories>() {
                            @Override
                            public void success(GetCategories outResponse, Response response) {
                                if (outResponse != null) {
                                    if (outResponse.getCategories() != null) {

                                        Log.i(FloraConstant.TAG, "categories response : " + outResponse.getCategories());
                                        categoryProductsArrayList.clear();
                                        categoryProductsArrayList.addAll(outResponse.getCategories());
                                        initCatProductsRecyclerView();
                                    }

                                } else {
                                    Log.i(FloraConstant.TAG, "categories response null " + response.getBody());

                                }
                                loadingProgress.setVisibility(View.GONE);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.i(FloraConstant.TAG, "categories response failure " + error.getMessage());
                                loadingProgress.setVisibility(View.GONE);
                            }
                        });

    } // fetchCatProducts

    public void fetchOccasionsProducts() {

        loadingProgress.setVisibility(View.VISIBLE);
        Log.i(FloraConstant.TAG, "get manufacturer_id in product list fragment : " + manufacturer_id);
        FloraApiCall.getCallingAPIInterface().fetchOccasionsProducts(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                FloraConstant.Limit,
                page_index + "",
                manufacturer_id,
                new Callback<GetProducts>() {
                    @Override
                    public void success(GetProducts outResponse, Response response) {

                        if (outResponse != null) {
                            productsArrayList.clear();
                            productsArrayList.addAll(outResponse.getProducts());

                            if (productsArrayList.isEmpty()) {
                                page_index = page_index - 1;
                            }

                            if (productsArrayList.size() == 0) {
                                rv_products.setVisibility(View.GONE);
                                tv_msg.setText(getString(R.string.products_empty));
                                tv_msg.setVisibility(View.VISIBLE);
                            }

                            if (productsArrayList.size() > 0) {
                                Log.i(FloraConstant.TAG, "productArrayList success array size = "
                                        + productsArrayList.size());
                                initProductsRecyclerView();
                            } else {
                                Log.i(FloraConstant.TAG, "productArrayList = null");

                            }


                            setPagingOccasionsProduct();

                            loading_flag = true;
                        }
                        loadingProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loadingProgress.setVisibility(View.GONE);
                        FixControl.showErrorMessage(error, getView());
                    }
                });
    } // fetchOccasionsProducts


    public void fetchProducts() {

        loading_progress_products.setVisibility(View.VISIBLE);
        Log.i(FloraConstant.TAG, "get shop_name in product list fragment : " + shop_name);
        FloraApiCall.getCallingAPIInterface().fetchProducts(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                shop_name,
                FloraConstant.Limit,
                page_index + "",
                new Callback<GetProducts>() {
                    @Override
                    public void success(GetProducts outResponse, Response response) {

                        if (outResponse != null) {
                            productsArrayList.clear();
                            productsArrayList.addAll(outResponse.getProducts());

                            if (productsArrayList.isEmpty()) {
                                page_index = page_index - 1;
                            }

                            if (productsArrayList.size() == 0) {
                                rv_products.setVisibility(View.GONE);
                                tv_msg.setText(getString(R.string.products_empty));
                                tv_msg.setVisibility(View.VISIBLE);
                            }

                            if (productsArrayList.size() > 0) {
                                Log.i(FloraConstant.TAG, "productArrayList success array size = "
                                        + productsArrayList.size());

                            } else {
                                Log.i(FloraConstant.TAG, "productArrayList = null");

                            }

                            initProductsRecyclerView();
                            setPaging();

                            loading_flag = true;
                        }
                        loading_progress_products.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loading_progress_products.setVisibility(View.GONE);
                        FixControl.showErrorMessage(error, getView());
                    }
                });
    } // fetchProducts

    private void setPagingOccasionsProduct() {

        rv_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (!endOfREsults) {

                    visibleItemCount = gridLayoutManager.getChildCount();

                    totalItemCount = gridLayoutManager.getItemCount();

                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if (loading_flag) {
                        if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                            loading_flag = false;
                            page_index = page_index + 1;

                            Log.i(FloraConstant.TAG, "GetMoreProducts called in condition");
                            fetchMoreOccasionsProducts();
                        }
                    }
                }
            }
        });

    } // set pagination


    private void fetchMoreProducts() {
        Log.i(FloraConstant.TAG, "GetMoreProducts called: ");

        loadingProgress.setVisibility(View.VISIBLE);

        Log.i(FloraConstant.TAG, "get cat_id in product list fragment : " + shop_name);
        FloraApiCall.getCallingAPIInterface().fetchProducts(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                shop_name,
                FloraConstant.Limit,
                page_index + "",
                new Callback<GetProducts>() {
                    @Override
                    public void success(GetProducts outResponse, Response response) {

                        loadingProgress.setVisibility(View.GONE);
                        if (outResponse != null) {
                            if (outResponse.getProducts().isEmpty()) {
                                page_index = page_index - 1;
                                endOfREsults = true;
                            }
                            productsArrayList.addAll(outResponse.getProducts());
                            Log.i(FloraConstant.TAG, "GetMoreProducts arrayList : " + productsArrayList.size());

                            productsAdapter.notifyDataSetChanged();
                            loading_flag = true;
                        }
                        loadingProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loadingProgress.setVisibility(View.GONE);
                    }
                });

    } // fetchMoreProducts


    private void fetchMoreOccasionsProducts() {
        Log.i(FloraConstant.TAG, "GetMoreProducts called: ");

        loadingProgress.setVisibility(View.VISIBLE);

        Log.i(FloraConstant.TAG, "get cat_id in product list fragment : " + shop_name);
        FloraApiCall.getCallingAPIInterface().fetchOccasionsProducts(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                FloraConstant.Limit,
                page_index + "",
                manufacturer_id,
                new Callback<GetProducts>() {
                    @Override
                    public void success(GetProducts outResponse, Response response) {

                        loadingProgress.setVisibility(View.GONE);
                        if (outResponse != null) {
                            if (outResponse.getProducts().isEmpty()) {
                                page_index = page_index - 1;
                                endOfREsults = true;
                            }
                            productsArrayList.addAll(outResponse.getProducts());
                            Log.i(FloraConstant.TAG, "GetMoreProducts arrayList : " + productsArrayList.size());

                            productsAdapter.notifyDataSetChanged();
                            loading_flag = true;
                        }
                        loadingProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loadingProgress.setVisibility(View.GONE);
                    }
                });

    } // fetchMoreOccasionsProducts


    private void setPaging() {

        rv_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (!endOfREsults) {

                    visibleItemCount = gridLayoutManager.getChildCount();

                    totalItemCount = gridLayoutManager.getItemCount();

                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if (loading_flag) {
                        if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                            loading_flag = false;
                            page_index = page_index + 1;

                            Log.i(FloraConstant.TAG, "GetMoreProducts called in condition");
                            fetchMoreProducts();
                        }
                    }
                }
            }
        });

    } // set pagination


    private void setPagingProductsByCategory() {

        rv_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (!endOfREsults) {

                    visibleItemCount = gridLayoutManager.getChildCount();

                    totalItemCount = gridLayoutManager.getItemCount();

                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if (loading_flag_) {
                        if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                            loading_flag = false;
                            page_index = page_index + 1;
                            Log.i(FloraConstant.TAG, "GetMoreProducts called in condition");
                            fetchMoreProductsByCategory();
                        }
                    }
                }
            }
        });

    } // set pagination

    private void fetchMoreProductsByCategory() {

        Log.i(FloraConstant.TAG, "GetMoreProducts called: ");

        loadingProgress.setVisibility(View.VISIBLE);

        Log.i(FloraConstant.TAG, "get cat_id in product list fragment : " + shop_name);
        FloraApiCall.getCallingAPIInterface().fetchProductsByCategory(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                productCategoryId,
                FloraConstant.Limit,
                page_index + "",
                new Callback<GetProducts>() {
                    @Override
                    public void success(GetProducts outResponse, Response response) {

                        loadingProgress.setVisibility(View.GONE);
                        if (outResponse != null) {
                            if (outResponse.getProducts().isEmpty()) {
                                page_index = page_index - 1;
                                endOfREsults = true;
                            }
                            productsArrayList.addAll(outResponse.getProducts());
                            Log.i(FloraConstant.TAG, "GetMoreProducts arrayList : " + productsArrayList.size());

                            productsAdapter.notifyDataSetChanged();
                            loading_flag = true;
                        }
                        loadingProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loadingProgress.setVisibility(View.GONE);
                    }
                });

    } // fetchMoreProductsByCategory


    private void initCatProductsRecyclerView() {

        categoryProductsAdapter = new CategoryProductsAdapter(getActivity(), categoryProductsArrayList, savedPos,
                new CategoryProductsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        if (categoryProductsArrayList.get(position).getHasSubCategories()) {
                            Log.i(FloraConstant.TAG, "productCategory HasSub ? trueeeeee : "
                                    + categoryProductsArrayList.get(position).getHasSubCategories());
                            Bundle bundle = new Bundle();
                            bundle.putString("Product", new Gson().toJson(categoryProductsArrayList.get(position)));
                            bundle.putString("comeFrom", String.valueOf(categoryProductsArrayList.get(position).getHasSubCategories()));
                            Log.i(FloraConstant.TAG, "send comeFrom : " +
                                    String.valueOf(categoryProductsArrayList.get(position).getHasSubCategories()));
                            ProductsFragment productsFragment = new ProductsFragment();
                            productsFragment.setArguments(bundle);
                            Navigator.loadFragment(getActivity(),
                                    productsFragment, R.id.fragment_container, true, "");
                        } else {
                            Log.i(FloraConstant.TAG, "productCategory HasSub ? falseeee : "
                                    + categoryProductsArrayList.get(position).getHasSubCategories());
                            savedPos = position;
                            categoryProductsAdapter.updateList(updateArrayList, position);
                            productCategoryId = String.valueOf(categoryProductsArrayList.get(position).getId());
                            Log.i(FloraConstant.TAG, "productCategoryId  : " + productCategoryId);
                            fetchProductsByCategory(String.valueOf(categoryProductsArrayList.get(position).getId()));
                            Toast.makeText(getActivity(), categoryProductsArrayList.get(position).getLocalizedName(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rv_product_cat.setHasFixedSize(true);
        rv_product_cat.setLayoutManager(linearLayoutManager);
        rv_product_cat.setAdapter(categoryProductsAdapter);
    } // initCatProductsRecyclerView

    private void initProductsRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        productsAdapter = new ProductsAdapter(getActivity(), productsArrayList);
        rv_products.setHasFixedSize(true);
        rv_products.setLayoutManager(gridLayoutManager);
        rv_products.setAdapter(productsAdapter);
    } // initProductsRecyclerView

    public void fetchProductsByCategory(String id) {

        loadingProgress.setVisibility(View.VISIBLE);
        Log.i(FloraConstant.TAG, "get cat_id in product list fragment : " + shop_name);
        FloraApiCall.getCallingAPIInterface().fetchProductsByCategory(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                id,
                FloraConstant.Limit,
                page_index + "",
                new Callback<GetProducts>() {
                    @Override
                    public void success(GetProducts outResponse, Response response) {

                        if (outResponse != null) {
                            if (outResponse.getProducts().isEmpty()) {
                                page_index = page_index - 1;
                            }
                            productsArrayList.clear();
                            productsArrayList.addAll(outResponse.getProducts());

                            if (productsArrayList.size() == 0) {
                                rv_products.setVisibility(View.GONE);
                                tv_msg.setText(getString(R.string.products_empty));
                                tv_msg.setVisibility(View.VISIBLE);
                            }

//                            if (productsArrayList != null) {
//                                if (productsArrayList.size() > 0) {
//                                    initProductsList();
//                                    Log.i(FloraConstant.TAG, "productArrayList success array size = "
//                                            + productsArrayList.size());
//
//                                } else {
//                                    Log.i(FloraConstant.TAG, "productArrayList = null");
//
//                                }
//                            }
                            initProductsRecyclerView();
                            setPagingProductsByCategory();

                            loading_flag = true;
                        }
                        loadingProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loadingProgress.setVisibility(View.GONE);
                        FixControl.showErrorMessage(error, getView());
                    }
                });
    } // fetchProductsByCategory


}
