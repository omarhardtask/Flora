package app.flora.Ui.Fragments;


import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.OccasionsAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Models.OccasionsModel;
import app.flora.Network.RetrofitClient;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OccasionsFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_occasions)
    RecyclerView rv_occasions;

    @BindView(R.id.loading_progress)
    ProgressBar loading_progress;

    @BindView(R.id.tv_msg)
    TextView tv_msg;

    // vars

    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int page_index = 1;
    boolean endOfREsults = false;
    private boolean loading_flag = true;

    private ArrayList<OccasionsModel.ManufacturersBean> arrayList = new ArrayList<>();
    LinearLayoutManager OccLayoutManager;
    OccasionsAdapter adapter;
    static FragmentActivity act;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.occasions_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "OccasionsFragment Called");
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
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Occasions));
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
            initOccRecyclerView();
        } else {
            fetchOccasions();
        }
    } // init list

    private void fetchOccasions() {

        loading_progress.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchOccasions(
                page_index,
                FloraConstant.Limit
        ).enqueue(
                new Callback<OccasionsModel>() {
                    @Override
                    public void onResponse(Call<OccasionsModel> call, Response<OccasionsModel> response) {

                        assert response.body() != null;
                        arrayList.clear();
                        arrayList.addAll(response.body().getManufacturers());

                        Log.i(FloraConstant.TAG, "fetchOccasions Success  size " + response.body()
                                .getManufacturers().size());

                        if (arrayList.size() == 0) {
                             tv_msg.setText(getString(R.string.occasion_empty));
                             tv_msg.setVisibility(View.VISIBLE);
                            rv_occasions.setVisibility(View.GONE);
                        }

                        if (arrayList.isEmpty()) {
                            page_index = page_index - 1;
                            endOfREsults = true;
                        }

                        initOccRecyclerView();
                        setPaging();
                        loading_flag = true;
                        loading_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<OccasionsModel> call, Throwable t) {
                        Log.i(FloraConstant.TAG, "error in Retrofit = " + t.getLocalizedMessage());
                        loading_progress.setVisibility(View.GONE);

                    }
                });
    } // fetchOccasions


    private void setPaging() {
        Log.i(FloraConstant.TAG, "setPaging called : ");

        rv_occasions.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!endOfREsults) {
                    visibleItemCount = OccLayoutManager.getChildCount();
                    totalItemCount = OccLayoutManager.getItemCount();
                    pastVisiblesItems = OccLayoutManager.findFirstVisibleItemPosition();
                    if (loading_flag) {
                        if ((visibleItemCount + pastVisiblesItems) + 2 >= totalItemCount) {
                            if (!(arrayList.size() == 0)) {
                                loading_progress.setVisibility(View.GONE);

                            }
                            loading_flag = false;
                            page_index = page_index + 1;
                            fetchMoreOccasions();
                        }
                    }
                }
            }
        });
    } // set pagination

    private void fetchMoreOccasions() {

        Log.i(FloraConstant.TAG, "fetchMoreOccasions called : ");

        loading_progress.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchOccasions(
                page_index,
                FloraConstant.Limit)
                .enqueue(new Callback<OccasionsModel>() {
            @Override
            public void onResponse(Call<OccasionsModel> call, Response<OccasionsModel> response) {

                loading_progress.setVisibility(View.GONE);

                if (response.body() != null) {
                    if (response.body().getManufacturers().isEmpty()) {
                        page_index = page_index - 1;
                        endOfREsults = true;
                    }
                    loading_flag = true;
                    arrayList.addAll(response.body().getManufacturers());
                    adapter.notifyDataSetChanged();
                    Log.i(FloraConstant.TAG, "fetchMoreOccasions array : " + arrayList.size());
                }
                loading_progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<OccasionsModel> call, Throwable t) {
                Log.i(FloraConstant.TAG, "error in Retrofit = " + t.getLocalizedMessage());
                loading_progress.setVisibility(View.GONE);
            }
        });
    } // fetchMoreOccasions

    private void initOccRecyclerView() {
        adapter = new OccasionsAdapter(act, arrayList);
        OccLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rv_occasions.setHasFixedSize(true);
        rv_occasions.setLayoutManager(OccLayoutManager);
        rv_occasions.setAdapter(adapter);
    } // initOccRecyclerView


}
