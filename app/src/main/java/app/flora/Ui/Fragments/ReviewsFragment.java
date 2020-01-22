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

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.ReviewsAdapter;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedShopsModel;
import app.flora.Models.Reviews;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ReviewsFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_reviews)
    RecyclerView rv_reviews;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;

    private ArrayList<Reviews.ProductReviewsBean> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ReviewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reviews_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG , "ReviewsFragment Called");
        initVisibility();
        initReviewsList();
        return view;

    } // onCreateView

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Reviews));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initReviewsList() {
        if (arrayList.size() > 0) {
            initReviewsRecyclerView();
        } else {
            fetchReviews();
        }
    } // initReviewsList

    private void fetchReviews() {

        loadingProgress.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().
                fetchReviews(
                        LanguageSessionManager.getLang(),
                        FloraConstant.AuthorizationToken,
                        new Callback<Reviews>() {
                            @Override
                            public void success(Reviews outResponse, Response response) {

                                if (outResponse != null) {

                                    if (outResponse.getProduct_reviews() != null) {

                                        Log.i(FloraConstant.TAG, "categories response : " + outResponse.getProduct_reviews());
                                        arrayList.addAll(outResponse.getProduct_reviews());
                                        initReviewsList();
                                    }
                                }
                                else {
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

    } // fetchReviews

    private void initReviewsRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false);
        adapter = new ReviewsAdapter(getActivity(), arrayList);
        rv_reviews.setHasFixedSize(true);
        rv_reviews.setLayoutManager(layoutManager);
        rv_reviews.setAdapter(adapter);
    } // initReviewsRecyclerView


}
