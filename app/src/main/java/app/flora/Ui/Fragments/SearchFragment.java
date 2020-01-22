package app.flora.Ui.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

import app.flora.Adapters.OccasionsAdapter;
import app.flora.Adapters.ProductsAdapter;
import app.flora.ApiRequests.FloraAPIInterface;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.GetProducts;
import app.flora.Models.OccasionsModel;
import app.flora.Models.Product;
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

public class SearchFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_products)
    RecyclerView rv_products;

    @BindView(R.id.loading_progress)
    ProgressBar loading_progress;

    @BindView(R.id.tv_msg)
    TextView tv_msg;

    @BindView(R.id.tv_search_for)
    TextView tv_search_for;

    // vars
    String comingFrom = "", query = "";
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int page_index = 1;
    boolean endOfREsults = false;
    private boolean loading_flag = true;

    private ArrayList<OccasionsModel.ManufacturersBean> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ProductsAdapter adapter;
    static FragmentActivity act;
    ArrayList<Product> productArrayList = new ArrayList<>();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "SearchFragment Called");
        initAct();
        getComingFrom();
        setText();
        initVisibility();
        initOccList();
        return view;
    }

    private void setText() {
        tv_search_for.setText(act.getString(R.string.search_for) + " " + query);
    } // setText

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void getComingFrom() {

        assert getArguments() != null;
        if (getArguments().containsKey("comingFrom")) {
            comingFrom = getArguments().getString("comingFrom");
        }


        Log.i(FloraConstant.TAG, "comingFrom = " + comingFrom);

        if (getArguments().containsKey("query")) {
            query = getArguments().getString("query");
            Log.i(FloraConstant.TAG, "query = " + query);
        }
    } // get coming method

    private void initAct() {
        if (act == null) {
            act = getActivity();
        }
        act = getActivity();
    } // initAct

    @SuppressLint("SetTextI18n")
    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.search));
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
            initProdRecyclerView();
        } else {
            fetchSearchProducts();
        }
    } // init list

    public void fetchSearchProducts() {
        loading_progress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().products_search(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                null,
                FloraConstant.Limit,
                page_index + "",
                query,
                new retrofit.Callback<GetProducts>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void success(GetProducts outResponse, retrofit.client.Response response) {

                        Log.i(FloraConstant.TAG, "1===" + arrayList.size() + "=" + page_index);

                        if (outResponse != null) {
                            if (outResponse.getProducts().isEmpty()) {
                                page_index = page_index - 1;
                                endOfREsults = true;
                            }
                            productArrayList.clear();
                            productArrayList.addAll(outResponse.getProducts());

                            Log.d("fapa", "2===" + productArrayList.size() + "=" + page_index);
                            Log.i(FloraConstant.TAG, "search list" + productArrayList.size() + "=" + page_index);
                            if (productArrayList.size() == 0) {
                                rv_products.setVisibility(View.GONE);
                                tv_search_for.setVisibility(View.GONE);
                                tv_msg.setText(getString(R.string.products_empty));
                                tv_msg.setVisibility(View.VISIBLE);
                            } else {
                                initProdRecyclerView();
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

    } //  fetchProducts method

    private void setPaging() {

        rv_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (!endOfREsults) {

                    visibleItemCount = layoutManager.getChildCount();

                    totalItemCount = layoutManager.getItemCount();

                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading_flag) {

                        if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                            loading_flag = false;

                            if (!(productArrayList.size() == 0)) {

                                loading_progress.setVisibility(View.GONE);
                            }
                            page_index = page_index + 1;
                            Log.i(FloraConstant.TAG, "fetchMoreSearchProducts called in condition");
                            fetchMoreSearchProducts();

                        }
                    }
                }
            }
        });

    } // set pagination

    private void fetchMoreSearchProducts() {
        Log.i(FloraConstant.TAG, "GetMoreProducts called: ");

        loading_progress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().products_search(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                null,
                FloraConstant.Limit,
                page_index + "",
                query,
                new Callback<GetProducts>() {
                    @Override
                    public void success(GetProducts outResponse, Response response) {

                        loading_progress.setVisibility(View.GONE);
                        if (outResponse != null) {
                            if (outResponse.getProducts().isEmpty()) {
                                page_index = page_index - 1;
                                endOfREsults = true;
                            }
                            productArrayList.addAll(outResponse.getProducts());
                            Log.i(FloraConstant.TAG, "GetMoreProducts arrayList : " + productArrayList.size());

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

    } // fetchMoreSearchProducts


    void initProdRecyclerView() {
        adapter = new ProductsAdapter(getContext(), productArrayList);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        rv_products.setHasFixedSize(true);
        rv_products.setLayoutManager(layoutManager);
        rv_products.setAdapter(adapter);
    } // initProductsList

}
