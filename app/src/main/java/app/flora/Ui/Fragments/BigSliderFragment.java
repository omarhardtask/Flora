package app.flora.Ui.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.flora.Adapters.BigSliderAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.SliderModel;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BigSliderFragment extends Fragment {

    @BindView(R.id.imageSlider)
    SliderView slider;
    @BindView(R.id.img_back)
    ImageView img_back;
    SliderModel sliders = null;
    List<SliderModel.SlidersBean> slidersArray = new ArrayList<>();

    // vars
    String image_position = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_big_slider, container, false);
        ButterKnife.bind(this, view);
        initVisibility();
        getImages();
        initSlider();
        initRotation();
        return view;

    }

    private void initRotation() {

        if (LanguageSessionManager.getLang().equals("en")) {
            img_back.setRotation(180);
        } else {
            img_back.setRotation(0);
        }

    } // initRotation


    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText("");
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
    } // initialize visibiliy


    @OnClick(R.id.img_back)
    public void initBack() {
        if (Objects.requireNonNull(getActivity()).getSupportFragmentManager().getBackStackEntryCount() == 1) {
            getActivity().finish();
        } else {
            getActivity().onBackPressed();
        }
    } // initBack

    private void initSlider() {
        BigSliderAdapter adapter = new BigSliderAdapter(getContext(), slidersArray);
        slider.setSliderAdapter(adapter);
        slider.setCurrentPagePosition(Integer.parseInt(image_position));
        slider.setIndicatorAnimation(IndicatorAnimations.WORM);
        slider.setSliderTransformAnimation(SliderAnimations.FIDGETSPINTRANSFORMATION);
        slider.setAutoCycleDirection(SliderView.AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS);
        slider.setScrollTimeInSec(4); //set scroll delay in seconds :
        slider.startAutoCycle();
    } // initialize sliderList method


    private void  getImages() {
        if (getArguments() != null) {
            Gson gson = new Gson();
            if (getArguments().containsKey("Slider")) {

                sliders = gson.fromJson(getArguments().getString("Slider"), SliderModel.class);
                image_position = getArguments().getString("POS");
                slidersArray = (ArrayList<SliderModel.SlidersBean>) getArguments().getSerializable("SliderArray");

            }
        }
    } // get saved product object

}
