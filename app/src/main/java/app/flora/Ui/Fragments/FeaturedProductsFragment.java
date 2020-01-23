package app.flora.Ui.Fragments;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.FeaturedProductsAdapter;
import app.flora.Adapters.FeaturedProductsFragmentAdapter;
import app.flora.Adapters.OccasionsAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.FeaturedProductsModel;
import app.flora.Models.GetProducts;
import app.flora.Models.OccasionsModel;
import app.flora.Models.Product;
import app.flora.Network.RetrofitClient;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedProductsFragment extends Fragment {

    // bind views
    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.loading_progress)
    ProgressBar loading_progress;

    @BindView(R.id.tv_msg)
    TextView tv_msg;

    // vars

    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int page_index = 1;
    boolean endOfREsults = false;
    private boolean loading_flag = true;
    private ArrayList<FeaturedProductsModel.ProductsBean> arrayList = new ArrayList<>();
    ArrayList<Product> productArrayList = new ArrayList<>();

    FeaturedProductsFragmentAdapter adapter;
    static FragmentActivity act;
    GridLayoutManager LayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.featured_product_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "FeaturedProductsFragment Called");
        initAct();
        initVisibility();
        initFeaturedProductsList();
        return view;
    }


    private void initAct() {

        if (act == null) {

            act = getActivity();

        }
        act = getActivity();
    } // initAct

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Featured_Products));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initFeaturedProductsList() {
        if (arrayList.size() > 0) {
            initFeaturedProductsRecyclerView();
        } else {
            fetchFeaturedProducts();
        }
    } // init list


    private void fetchFeaturedProducts() {

        Log.i(FloraConstant.TAG, "fetchFeaturedProducts Called ");

        loading_progress.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchFeatureProductsOnHome(
                page_index,
                FloraConstant.Limit
        ).enqueue(
                new Callback<FeaturedProductsModel>() {
                    @Override
                    public void onResponse(Call<FeaturedProductsModel> call, Response<FeaturedProductsModel> response) {
                        Log.i(FloraConstant.TAG, "fetchFeaturedProducts Array size = " + response
                                .body().getProducts().size());

                        if (response.body() != null) {
                            arrayList.addAll(response.body().getProducts());
                            initFeaturedProductsRecyclerView();
                        }
                        if (arrayList.isEmpty()) {
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

        rv.post(new Runnable() {
            @Override
            public void run() {
                rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (!endOfREsults) {
                            visibleItemCount = LayoutManager.getChildCount();
                            totalItemCount = LayoutManager.getItemCount();
                            pastVisiblesItems = LayoutManager.findFirstVisibleItemPosition();
                            if (loading_flag) {
                                if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                                    loading_flag = false;
                                    if (!(arrayList.size() == 0)) {
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
                    arrayList.addAll(response.body().getProducts());
                    adapter.notifyDataSetChanged();
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


    private void initFeaturedProductsRecyclerView() {
        LayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new FeaturedProductsFragmentAdapter(getActivity(), arrayList);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(LayoutManager);
        rv.setAdapter(adapter);
    } // initFeaturedRecyclerView


}
