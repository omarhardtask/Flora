package app.flora.Ui.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.BouquetAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Models.BouquetModel;
import app.flora.Models.CartModel;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FilterFragment extends Fragment {

    // BIND VIEWS

    //===================== 1 ===============
    @BindView(R.id.rv_bouquet_type)
    RecyclerView rv_bouquet_type;

    //===================== 2 ===============

    @BindView(R.id.rv_flower_type)
    RecyclerView rv_flower_type;

    //===================== 3 ===============
    @BindView(R.id.rv_price)
    RecyclerView rv_price;

    //===================== 4 ===============
    @BindView(R.id.rv_occassion)
    RecyclerView rv_occassion;


    // VARS

    //===================== 1 ===============

    private ArrayList<BouquetModel> bouquetArrayList = new ArrayList<>();
    LinearLayoutManager bouquetLayoutManager;
    BouquetAdapter bouquetAdapter;

    //===================== 2 ===============
    private ArrayList<BouquetModel> flowerArrayList = new ArrayList<>();
    LinearLayoutManager flowerLayoutManager;
    BouquetAdapter flowerAdapter;

    //===================== 3  ===============
    private ArrayList<BouquetModel> priceArrayList = new ArrayList<>();
    LinearLayoutManager priceLayoutManager;
    BouquetAdapter priceAdapter;

    //===================== 4  ===============
    private ArrayList<BouquetModel> occasionArrayList = new ArrayList<>();
    LinearLayoutManager occasionLayoutManager;
    BouquetAdapter occasionAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "FilterFragment Called");
        initVisibility();
        initBouquetList();
        initFlowerList();
        initPriceList();
        initOccasionList();
        return view;

    }

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Filter));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    //===================== 1 ================================================================

    private void initBouquetList() {
        if (bouquetArrayList.size() > 0) {
            initBouquetRecyclerView();
        } else {
            fetchBouquet();
        }
    } // initBouquetList

    private void fetchBouquet() {
        bouquetArrayList.clear();
        bouquetArrayList.add(new BouquetModel(
                "Bouquet"));

        bouquetArrayList.add(new BouquetModel(
                "Hand Bouquuets"));

        bouquetArrayList.add(new BouquetModel(
                "Plants"));


        initBouquetRecyclerView();
    } // fetchBouquet

    private void initBouquetRecyclerView() {
        bouquetLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        bouquetAdapter = new BouquetAdapter(getActivity(), bouquetArrayList);
        rv_bouquet_type.setHasFixedSize(true);
        rv_bouquet_type.setLayoutManager(bouquetLayoutManager);
        rv_bouquet_type.setAdapter(bouquetAdapter);
    } // initBouquetRecyclerView

//===================== 2 ===================================================================

    private void initFlowerList() {
        if (flowerArrayList.size() > 0) {
            initFlowerRecyclerView();
        } else {
            fetchFlowers();
        }
    } // initFlowerList

    private void fetchFlowers() {
        flowerArrayList.clear();
        flowerArrayList.add(new BouquetModel(
                "Roses"));

        flowerArrayList.add(new BouquetModel(
                "Lilies"));

        flowerArrayList.add(new BouquetModel(
                "Others"));

        initFlowerRecyclerView();
    } // fetchFlowers

    private void initFlowerRecyclerView() {
        flowerLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        flowerAdapter = new BouquetAdapter(getActivity(), flowerArrayList);
        rv_flower_type.setHasFixedSize(true);
        rv_flower_type.setLayoutManager(flowerLayoutManager);
        rv_flower_type.setAdapter(flowerAdapter);
    } // initFlowerRecyclerView

//===================== 3 ===================================================================

    private void initPriceList() {
        if (priceArrayList.size() > 0) {
            initPriceRecyclerView();
        } else {
            fetchPrice();
        }
    } // initPriceList

    private void fetchPrice() {
        priceArrayList.clear();
        priceArrayList.add(new BouquetModel(
                "10 KWD"));

        priceArrayList.add(new BouquetModel(
                "30 KWD"));

        priceArrayList.add(new BouquetModel(
                "50 KWD"));

        initPriceRecyclerView();
    } // fetchPrice

    private void initPriceRecyclerView() {
        priceLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        priceAdapter = new BouquetAdapter(getActivity(), priceArrayList);
        rv_price.setHasFixedSize(true);
        rv_price.setLayoutManager(priceLayoutManager);
        rv_price.setAdapter(priceAdapter);
    } // initFlowerRecyclerView

//===================== 4 ===================================================================

    private void initOccasionList() {
        if (occasionArrayList.size() > 0) {
            initOccasionRecyclerView();
        } else {
            fetchOccasion();
        }
    } // initOccasionList

    private void fetchOccasion() {
        occasionArrayList.clear();
        occasionArrayList.add(new BouquetModel(
                "Love"));

        occasionArrayList.add(new BouquetModel(
                "Adore"));

        initOccasionRecyclerView();
    } // fetchOccasion

    private void initOccasionRecyclerView() {
        occasionLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        occasionAdapter = new BouquetAdapter(getActivity(), occasionArrayList);
        rv_occassion.setHasFixedSize(true);
        rv_occassion.setLayoutManager(occasionLayoutManager);
        rv_occassion.setAdapter(occasionAdapter);
    } // initOccasionRecyclerView

}
