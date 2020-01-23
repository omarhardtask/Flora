package app.flora.Ui.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.WhishListAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Models.ShoppingCart;
import app.flora.Models.ShoppingCarts;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;


public class WishListFragment extends Fragment {


    // bind views
    @BindView(R.id.rv_whish_list)
    RecyclerView rv_whish_list;
    @BindView(R.id.loading_progress)
    ProgressBar mloading;
    @BindView(R.id.tv_msg)
    TextView tv_msg;


    private ArrayList<ShoppingCart> whishList = new ArrayList<>();

    LinearLayoutManager layoutManager;
    WhishListAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.whish_list_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG , "WishListFragment Called");
        initVisibility();
        initFavList();
        return view;
    }

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.WhishList));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initFavList() {
        if (whishList.size() > 0) {
            initWishRecyclerView();
        } else {
            fetchFavoriteList();
        }
    } // init list

    private void fetchFavoriteList() {

        mloading.setVisibility(View.VISIBLE);
// 1 cart , 2 wish
        FloraApiCall.getCallingAPIInterface().shoppingCartItems(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                "2",
                new Callback<ShoppingCarts>() {
                    @Override
                    public void success(ShoppingCarts outResponse, Response response) {
                        if (outResponse != null) {
                            Log.i(FloraConstant.TAG, "not null");
                            whishList.clear();
                            whishList.addAll(outResponse.getShoppingCarts());
                            Log.i(FloraConstant.TAG, "fav list get success response");
                            Log.i(FloraConstant.TAG, "fav list get success response size" + outResponse.getShoppingCarts().size());

                            if (whishList.size() == 0) {
                                tv_msg.setText(getString(R.string.WishListEmptyNowLabel));
                                tv_msg.setVisibility(View.VISIBLE);
                                rv_whish_list.setVisibility(View.GONE);
                            }

                            if (whishList.size() > 0) {
                                initFavList();
                            }
                        } else {
                            Log.i(FloraConstant.TAG, "null");
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Log.i(FloraConstant.TAG, "fav list getERror response size"
                                    + error.getMessage());

                            FixControl.showErrorMessage(error , view);
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                           // Toast.makeText(getActivity(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                        }
                        mloading.setVisibility(View.GONE);
                    }
                });

    } // fetch favourite list


    private void initWishRecyclerView() {
        adapter = new WhishListAdapter(getActivity(), whishList, new WhishListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("Product", new Gson().toJson(whishList.get(position).getProduct()));
                Fragment fragment = new ProductDetailsFragment();
                fragment.setArguments(bundle);
                Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
            }

            @Override
            public void onDeleteItemClick(int position) {
                if (whishList.size() > position)
                    new AlertDialog.Builder(getActivity())
                            .setTitle(getString(R.string.app_name))
                            .setMessage(getString(R.string.DeleteCartLabel))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    deleteShoppingFavItem(position);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.logo_flora)
                            .show();
            }
        });



        layoutManager = new GridLayoutManager(getContext(),2);

        rv_whish_list.setHasFixedSize(true);
        rv_whish_list.setLayoutManager(layoutManager);
        rv_whish_list.setAdapter(adapter);
    } // initOccRecyclerView

    private void deleteShoppingFavItem(int position) {

        FloraApiCall.getCallingAPIInterface().deleteShoppingCartItem(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                whishList.get(position).getId() + "", new Callback<Response>() {
                    @Override
                    public void success(Response s, Response response) {

                        Log.d(FloraConstant.TAG, "deleteShoppingFavItem success ");
                        TypedInput body = response.getBody();
                        String outResponse = "";

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
                            StringBuilder out = new StringBuilder();
                            String newLine = System.getProperty("line.separator");
                            String line;
                            while ((line = reader.readLine()) != null) {
                                out.append(line);
                                out.append(newLine);
                            }
                            outResponse = out.toString();
                            Log.d("outResponse", "" + outResponse);

                        } catch (Exception ex) { ex.printStackTrace(); }
                        if (outResponse != null) {
                            outResponse = outResponse.replace("\"", "");
                            outResponse = outResponse.replace("\n", "");
                            Log.e("outResponse not null ", outResponse);
                            if (outResponse.equalsIgnoreCase("{}")) {
                                whishList.remove(position);
                                rv_whish_list.getAdapter().notifyItemRemoved(position);
                                rv_whish_list.getAdapter().notifyItemRangeChanged(0,
                                        whishList.size(), whishList);
                                if (whishList.size() == 0) {
                                    Snackbar.make(getView(), getString(R.string.WishListEmptyNowLabel), Snackbar.LENGTH_LONG).show();
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }
                        mloading.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.INVISIBLE);
                    }
                });
    } // delete item reqest to api


}
