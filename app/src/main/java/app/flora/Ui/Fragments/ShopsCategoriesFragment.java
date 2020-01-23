package app.flora.Ui.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.ShopsCategoriesAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.Category;
import app.flora.Models.GetCategories;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShopsCategoriesFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_shops_categories)
    RecyclerView rv_shops_categories;

    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;

    ArrayList<Category> categoryArrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ShopsCategoriesAdapter adapter;
    Category category = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shops_categories_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG , "ShopsCategoriesFragment Called");
        initCat();
        initVisibility();
        initOccList();
        return view;
    }

    private void initCat() {
        if (getArguments() != null) {
            if (getArguments().containsKey("category")) {

                category = new Gson().fromJson(getArguments().getString("category"),
                        Category.class);
            }
        }
    } // initCat

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Shops_Categories));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initOccList() {
        if (categoryArrayList.size() > 0) {
            initCatRecyclerView();
        } else {
            categories();
        }
    } // init list


    private void categories() {

        loadingProgress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().
                fetchCategories(
                        LanguageSessionManager.getLang(),
                        FloraConstant.AuthorizationToken,
                        category != null ? category.getId() + "" : null,
                        new Callback<GetCategories>() {
                            @Override
                            public void success(GetCategories outResponse, Response response) {

                                if (outResponse != null) {

                                    if (outResponse.getCategories() != null) {

                                        Log.i(FloraConstant.TAG, "categories response : " + outResponse.getCategories());
                                        categoryArrayList.addAll(outResponse.getCategories());
                                        initCatRecyclerView();
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

//    private void setData() {
//
//        adapter = new ShopsCategoriesAdapter(getActivity(), categoryArrayList, "category grid",
//                new ShopsCategoriesAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(int position) {
//                        Log.i(FloraConstant.TAG, "onItemClick section");
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString("comingFrom", "home");
//                        bundle.putString("category", new Gson().toJson(categoryArrayList.get(position)));
//                        Log.i(FloraConstant.TAG, "getHasSubCategories" + categoryArrayList.get(position).getHasSubCategories());
//
//                        if (categoryArrayList.get(position).getHasSubCategories()) {
//                            Fragment shopsCategoriesFragment = new ShopsCategoriesFragment();
//                            shopsCategoriesFragment.setArguments(bundle);
//                            Navigator.loadFragment(getActivity(), shopsCategoriesFragment, R.id.fragment_container, true, "home");
//                            Log.i(FloraConstant.TAG, "has sub category open section fragment");
//
//                        } else {
//                            Fragment fragment = new ProductsFragment();
//                            fragment.setArguments(bundle);
//                            Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
//                            Log.i(FloraConstant.TAG, "not have sub category open product fragment");
//
//                        }
//                    }
//                });
//        initSectionsList();
//        // sections_recycler_view.setAdapter(adapter);
//
//    } // set data

    private void initCatRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false);
        adapter = new ShopsCategoriesAdapter(getActivity(), categoryArrayList);
        rv_shops_categories.setHasFixedSize(true);
        rv_shops_categories.setLayoutManager(layoutManager);
        rv_shops_categories.setAdapter(adapter);
    } // initCatRecyclerView


}
