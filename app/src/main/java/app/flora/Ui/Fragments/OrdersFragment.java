package app.flora.Ui.Fragments;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

import app.flora.Adapters.OrdersAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.Models.Order;
import app.flora.Models.OrderDelete;
import app.flora.Models.OrderModel;
import app.flora.Models.Orders;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdersFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_orders)
    RecyclerView rv_orders;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.loading_progress)
    ProgressBar mloading;

    // vars
    private ArrayList<OrderDelete.OrdersBean> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    OrdersAdapter adapter;
    static FragmentActivity act;
    int page_index = 1;
    boolean endOfREsults = false;
    private boolean loading_flag = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "OrdersFragment Called");
        initVisibility();
        setFont();
        initAct();
        initRecyclerView();
        initCartList();
        return view;
    }

    private void initAct() {
        if (act == null) {
            act = getActivity();
        }
        else
        {
            act = getActivity();
        }
    } // initAct

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.shoppingCartItemsCount();
    } // onResume

    private void setFont() {
        if (LanguageSessionManager.getLang().equalsIgnoreCase("en")) {

            tv_total.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                    ENGLISH_BOLD));
        }
    } // set font

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Orders));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initCartList() {
        if (arrayList.size() > 0) {
            initRecyclerView();
        } else {
            fetchOrders();
        }
    } // init list

    private void fetchOrders() {

        mloading.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().orders(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                SessionManager.getUserCode(),
                page_index + "",
                FloraConstant.Limit,
        new Callback<OrderDelete>() {
                    @Override
                    public void success(OrderDelete outResponse, Response response) {

                        Log.i(FloraConstant.TAG, "getOrders list success : " + outResponse);

                        if (outResponse != null) {

                            if (outResponse.getOrders() != null) {

                                Log.d(FloraConstant.TAG, "not null");


                                arrayList.addAll(outResponse.getOrders());


                                if (arrayList.size() == 0) {
                                    rv_orders.setVisibility(View.GONE);
                                    tv_msg.setText(getString(R.string.order_empty));
                                    tv_msg.setVisibility(View.VISIBLE);
                                    tv_total.setVisibility(View.GONE);
                                }

                                adapter = new OrdersAdapter(act, arrayList);



                                rv_orders.setAdapter(adapter);
                                showTotal();

                                rv_orders.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                        if (!endOfREsults) {
                                            visibleItemCount = layoutManager.getChildCount();
                                            totalItemCount = layoutManager.getItemCount();
                                            pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                                            if (loading_flag) {
                                                if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {

                                                    if (!(arrayList.size() == 0)) {
                                                        mloading.setVisibility(View.VISIBLE);
                                                    }
                                                    loading_flag = false;
                                                    page_index = page_index + 1;
                                                    MoreOrders();
                                                }
                                            }
                                        }
                                    }
                                });
                            }

                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mloading.setVisibility(View.GONE);
                        Snackbar.make(getView(), getString(R.string.try_again), Snackbar.LENGTH_LONG).show();
                    }
                });

    } // fetchOrders

    private void MoreOrders() {

        FloraApiCall.getCallingAPIInterface().orders(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                SessionManager.getUserCode(),
                page_index + "",
                FloraConstant.Limit,
                new Callback<OrderDelete>() {
                    @Override
                    public void success(OrderDelete outResponse, Response response) {
                        if (mloading != null) {

                            if (outResponse != null) {
                                if (outResponse.getOrders() != null) {
                                    if (outResponse.getOrders().isEmpty()) {
                                        endOfREsults = true;
                                        page_index = page_index - 1;
                                    }
                                }
                                loading_flag = true;
                                if (outResponse.getOrders() != null) {
                                    arrayList.addAll(outResponse.getOrders());
                                }
                                adapter.notifyDataSetChanged();
                            }
                            mloading.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mloading.setVisibility(View.GONE);
                    }
                });

    } // load more list when scroll down



    @SuppressLint("SetTextI18n")
    private void showTotal() {
        Double total = 0.0;

        for (int i = 0; i < arrayList.size(); i++) {

            total += arrayList.get(i).getOrder_total();
        }
        // when search where update this quantity !
        Log.i(FloraConstant.TAG,
                "order_total : " + total +   SessionManager.getUserCurrencyCode());
        try {
            tv_total.setText(getActivity().getString(R.string.total)
                    + " :  "
                    + " " + String.format(Locale.US, "%.3f",
                    + total + " " +  SessionManager.getUserCurrencyCode()));
        } catch (Exception e) {
        }
    } // init total



    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapter = new OrdersAdapter(act, arrayList);
        rv_orders.setHasFixedSize(true);
        rv_orders.setLayoutManager(layoutManager);
        rv_orders.setAdapter(adapter);
    } // initRecyclerView


}
