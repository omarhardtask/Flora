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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.ShopsAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.FeaturedShopsModel;
import app.flora.Models.GetProducts;
import app.flora.Models.OccasionsModel;
import app.flora.Models.ShoppingCarts;
import app.flora.Network.FloraApiCall;
import app.flora.Network.RetrofitClient;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;

public class FeatureShopsFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_shops)
    RecyclerView rv_shops;

    @BindView(R.id.loading_progress)
    ProgressBar loading_progress;

    @BindView(R.id.tv_msg)
    TextView tv_msg;


    // vars

    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int page_index = 1;
    boolean endOfREsults = false;
    private boolean loading_flag = true;

    private ArrayList<FeaturedShopsModel.VendorsBean> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ShopsAdapter adapter;
    static FragmentActivity act;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.feature_shops_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "FeatureShopsFragment Called");
        initAct();
        initVisibility();
        initOccList();
        return view;
    }

    private void initAct() {

        if (act == null) {

            act = getActivity();

        }
        act = getActivity();
    }

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Featured_Shops));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initOccList() {
        if (arrayList.size() > 0) {
            initRecyclerView();
        } else {
            fetchFeatureShopsList();
        }
    } // init list

    public void fetchFeatureShopsList() {
        loading_progress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().fetchFeatureShops(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                FloraConstant.Limit,
                page_index +"",
                new retrofit.Callback<FeaturedShopsModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void success(FeaturedShopsModel outResponse, retrofit.client.Response response) {

                        Log.i(FloraConstant.TAG, "1===" + arrayList.size() + "=" + page_index);

                        if (outResponse != null) {
                            if (outResponse.getVendors().isEmpty()) {
                                page_index = page_index - 1;
                                endOfREsults = true;
                            }
                            arrayList.clear();
                            arrayList.addAll(outResponse.getVendors());

                            Log.d("fapa", "2===" + arrayList.size() + "=" + page_index);
                            Log.i(FloraConstant.TAG, "search list" + arrayList.size() + "=" + page_index);
                            if (arrayList.size() == 0) {
                                rv_shops.setVisibility(View.GONE);
                                tv_msg.setText(getString(R.string.products_empty));
                                tv_msg.setVisibility(View.VISIBLE);
                            } else {
                                initRecyclerView();
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                setPaging();
                            }
                            loading_flag = true;
                        }
                        loading_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Log.d(FloraConstant.TAG, error.getLocalizedMessage());
                            Toast.makeText(getActivity(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                        }
                        loading_progress.setVisibility(View.GONE);
                    }
                });

    } //  fetchFeatureShopsList method



    private void setPaging() {

        rv_shops.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (!endOfREsults) {

                    visibleItemCount = layoutManager.getChildCount();

                    totalItemCount = layoutManager.getItemCount();

                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading_flag) {

                        if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                            loading_flag = false;

                            if (!(arrayList.size() == 0)) {

                                loading_progress.setVisibility(View.GONE);
                            }
                            page_index = page_index + 1;
                            fetchMoreFeatureShops();

                        }
                    }
                }
            }
        });

    } // set pagination


    private void fetchMoreFeatureShops() {
        Log.i(FloraConstant.TAG, "GetMoreProducts called: ");

        loading_progress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().fetchFeatureShops(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                FloraConstant.Limit,
                page_index + "",
                new Callback<FeaturedShopsModel>() {
                    @Override
                    public void success(FeaturedShopsModel outResponse, Response response) {

                        loading_progress.setVisibility(View.GONE);
                        if (outResponse != null) {
                            if (outResponse.getVendors().isEmpty()) {
                                page_index = page_index - 1;
                                endOfREsults = true;
                            }
                            arrayList.addAll(outResponse.getVendors());
                            Log.i(FloraConstant.TAG, "GetMoreProducts arrayList : " + arrayList.size());

                            adapter.notifyDataSetChanged();
                            loading_flag = true;
                        }
                        loading_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loading_progress.setVisibility(View.GONE);
                    }
                });

    } // fetchMoreFeatureShops


    private void initRecyclerView() {
        adapter = new ShopsAdapter(act, arrayList);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rv_shops.setHasFixedSize(true);
        rv_shops.setLayoutManager(layoutManager);
        rv_shops.setAdapter(adapter);
    } // initRecyclerView


}
