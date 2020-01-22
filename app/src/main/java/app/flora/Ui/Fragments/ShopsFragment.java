package app.flora.Ui.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.ShopsAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedShopsModel;
import app.flora.Models.GetCategories;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShopsFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_shops)
    RecyclerView rv_shops;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;

    // vars
    String cat_name = "" , cat_id = "";
    private ArrayList<FeaturedShopsModel.VendorsBean> shopArrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ShopsAdapter shopsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shops_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG , "ShopsFragment Called");
        getCatData();
        initVisibility();
        initShopsList();
        return view;
    }

    private void getCatData() {
        if (getArguments().containsKey("cat_name")) {
            cat_name = getArguments().getString("cat_name");
            cat_id = getArguments().getString("cat_id");
            Log.i(FloraConstant.TAG, "get cat_name in  shops fragment : " + cat_name);
            Log.i(FloraConstant.TAG, "get cat_id  shops fragment : " + cat_id);
        }
    } // getCatData

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(cat_name);
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initShopsList() {
        if (shopArrayList.size() > 0) {
            initShopsRecyclerView();
        } else {
            fetchShops();
        }
    } // init initShopsList

    private void fetchShops() {

        loadingProgress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().
                fetchShops(
                        LanguageSessionManager.getLang(),
                        FloraConstant.AuthorizationToken,
                       cat_id,
                        new Callback<FeaturedShopsModel>() {
                            @Override
                            public void success(FeaturedShopsModel outResponse, Response response) {

                                if (outResponse != null) {

                                    if (outResponse.getVendors() != null) {

                                        Log.i(FloraConstant.TAG, "categories response : " + outResponse.getVendors());
                                        shopArrayList.addAll(outResponse.getVendors());
                                        initShopsList();
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

    } // get castegories from api

    private void initShopsRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false);
        shopsAdapter = new ShopsAdapter(getActivity(), shopArrayList);
        rv_shops.setHasFixedSize(true);
        rv_shops.setLayoutManager(layoutManager);
        rv_shops.setAdapter(shopsAdapter);
    } // initShopsRecyclerView
}
