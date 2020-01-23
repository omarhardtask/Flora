package app.flora.Ui.Fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.CartAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.CartModel;
import app.flora.Models.CategoriesModel;
import app.flora.Models.ShoppingCart;
import app.flora.Models.ShoppingCartItem;
import app.flora.Models.ShoppingCartItem_;
import app.flora.Models.ShoppingCarts;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

public class CartFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_cart)
    RecyclerView rv_cart;

    @BindView(R.id.loading_progress)
    ProgressBar mloading;

    @BindView(R.id.btn_check)
    Button btn_check;

    @BindView(R.id.rel_bottom)
    RelativeLayout rel_bottom;

    @BindView(R.id.tv_msg)
    TextView tv_msg;

    @BindView(R.id.tv_total)
    TextView tv_total;

    @BindView(R.id.tv_subtotal)
    TextView tv_subtotal;

    @BindView(R.id.tv_delivery_charges)
    TextView tv_delivery_charges;

    @BindView(R.id.view_line)
    View view_line;

    // vars
    private ArrayList<ShoppingCart> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    CartAdapter adapter;
    boolean isCartList;
    static FragmentActivity act;
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "CartFragment Called");
        initAct();
        initVisibility();
        getIsCart();
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
    }

    @OnClick(R.id.btn_check)
    public void clickPay() {
        if (!SessionManager.isLoggedin()) {
            Bundle bundle = new Bundle();
            bundle.putString("comingFrom", "checkOut");
            Fragment loginFragment = new LoginFragment();
            loginFragment.setArguments(bundle);
            Navigator.loadFragment(getActivity(), loginFragment, R.id.fragment_container, true, "home");
        } else {
            if (arrayList.size() != 0) {
                Bundle bundle = new Bundle();
                bundle.putString("comingFrom", "cart");
                Fragment fragment = new AddressesFragment();
                fragment.setArguments(bundle);
                Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "");
            } else {
                Toast.makeText(getActivity(), getString(R.string.ShoppingCartMessageLabel), Toast.LENGTH_SHORT).show();
            }
        }
    } // init payment


    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Cart));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initCartList() {
        if (arrayList.size() > 0) {
            initCartRecyclerView();
        } else {
            fetchCartList();
        }
    } // init list

    private void getIsCart() {
        if (getArguments() != null) {
            isCartList = getArguments().getBoolean("isCartList");
        }
    } // check if added to cart

    private void fetchCartList() {

        mloading.setVisibility(View.VISIBLE);
// 1 cart , 2 wish
        //mainActivity.getUserId()
        FloraApiCall.getCallingAPIInterface().shoppingCartItems(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                "1",
                new Callback<ShoppingCarts>() {
                    @Override
                    public void success(ShoppingCarts outResponse, Response response) {

                        if (outResponse != null) {

                            Log.i(FloraConstant.TAG, "not null");
                            arrayList.clear();
                            arrayList.addAll(outResponse.getShoppingCarts());
                            Log.i(FloraConstant.TAG, "cart list get success response");
                            Log.i(FloraConstant.TAG, "cart list get success response size" + outResponse.getShoppingCarts().size());

                            if (arrayList.size() == 0) {
                                tv_msg.setText(getString(R.string.CartEmptyNowLabel));
                                tv_msg.setVisibility(View.VISIBLE);
                                rv_cart.setVisibility(View.GONE);
                                btn_check.setVisibility(View.GONE);
                                rel_bottom.setVisibility(View.GONE);
                                view_line.setVisibility(View.GONE);
                            }

                            if (arrayList.size() == 0 && isCartList) {
                                tv_msg.setText(getString(R.string.CartEmptyNowLabel));
                                tv_msg.setVisibility(View.VISIBLE);
                                rv_cart.setVisibility(View.GONE);
                                btn_check.setVisibility(View.GONE);
                                rel_bottom.setVisibility(View.GONE);
                                view_line.setVisibility(View.GONE);
                            }

                            if (arrayList.size() > 0) {
                                setData();
                                btn_check.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                    }
                });
    } // fetch Cart


    private void setData() {

        setTotalPrice();
        adapter = new CartAdapter(act, arrayList, new CartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("Product", new Gson().toJson(arrayList.get(position).getProduct()));
                Fragment fragment = new ProductDetailsFragment();
                fragment.setArguments(bundle);
                Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
            }

            @Override
            public void onDeleteItemClick(int position) {

                if (arrayList.size() > position)

                    new AlertDialog.Builder(getActivity())
                            .setTitle(getString(R.string.app_name))
                            .setMessage(getString(R.string.DeleteCartLabel))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    deleteShoppingCartItem(position);
                                     MainActivity.shoppingCartItemsCount();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.logo_flora)
                            .show();
            } // delete item

            @Override
            public void onAddItemClick(int position) {
                boolean isError = false;

                if (arrayList.get(position).getProduct().getOrderMaximumQuantity() != null) {

                    if (arrayList.get(position).getProduct().getOrderMaximumQuantity()
                            < (arrayList.get(position).getQuantity() + 1)) {

                        isError = true;

                        new AlertDialog.Builder(getActivity())
                                .setTitle(getString(R.string.app_name))
                                .setMessage(getString(R.string.MaximumQuantityAllowedLabel).
                                        replace("aaa",
                                                arrayList.get(position).getProduct().
                                                        getOrderMaximumQuantity() + ""))
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.logo_flora)
                                .show();
                    }
                }

                if (!isError) {
                    updateShoppingCart(position, true);
                      MainActivity.shoppingCartItemsCount();
                }
            } // click +

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onRemoveItemClick(int position) {


                if (arrayList.get(position).getQuantity() > 1) {
                    updateShoppingCart(position, false);
                }
                else {

                    new AlertDialog.Builder(getActivity())
                            .setTitle(getString(R.string.app_name))
                            .setMessage(getString(R.string.DeleteCartLabel))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    deleteShoppingCartItem(position);
                                    // mainActivity.shoppingCartItemsCount();
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
                //  mainActivity.shoppingCartItemsCount();
            } // click -
        });
        initCartList();
    } // set adapter

    private void deleteShoppingCartItem(int position) {
        FloraApiCall.getCallingAPIInterface().deleteShoppingCartItem(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                arrayList.get(position).getId()
                        + "",
                new Callback<Response>() {
                    @Override
                    public void success(Response s, Response response) {
                        Log.d(FloraConstant.TAG, "deleteShoppingCartItem success ");
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

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        if (outResponse != null) {
                            outResponse = outResponse.replace("\"", "");
                            outResponse = outResponse.replace("\n", "");
                            Log.e("outResponse not null ", outResponse);

                            if (outResponse.equalsIgnoreCase("{}")) {
                                arrayList.remove(position);
                                rv_cart.getAdapter().notifyItemRemoved(position);
                                rv_cart.getAdapter().notifyItemRangeChanged(
                                        0, arrayList.size(), arrayList);

                                if (arrayList.size() == 0) {
                                    try {
                                        Snackbar.make(getView(), act.getString(R.string.CartEmptyNowLabel), Snackbar.LENGTH_LONG).show();

                                    }
                                    catch (Exception e){}

//                                    FragmentManager fm =  getFragmentManager();
//                                    for (int i = 0; i < fm.getBackStackEntryCount() - 1; i++) {
//                                        fm.popBackStack();
//                                    }
//                                    Fragment homeFragment = new homeFragment();
//                                    Navigator.loadFragment(getActivity(), homeFragment,
//                                            R.id.fragment_container, true, "");
                                    act.getSupportFragmentManager().popBackStack();
                                    // getFragmentManager().popBackStack();
                                    //   mainActivity.tv_count.setVisibility(View.GONE);
                                    //   mainActivity.tv_count.setText("");
                                }
                                setTotalPrice();
                                //  mainActivity.tv_count.setVisibility(View.VISIBLE);
                                //  mainActivity.tv_count.setText(shoppingCartArrayList.size()+"");

                            }
                            updateQuantity();
                        }
                        mloading.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {

                            Toast.makeText(getActivity(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                        }
                        mloading.setVisibility(View.GONE);
                    }

                });

    } // delete item reqest to api

    private void updateShoppingCart(int position, boolean isAdd) {

        mloading.setVisibility(View.VISIBLE);
        rv_cart.setVisibility(View.GONE);
        prepareShoppingCartData(position, isAdd);
        FloraApiCall.getCallingAPIInterface().updateShoppingCart(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                arrayList.get(position).getId() + "",
                "application/json",
                shoppingCartItem, new Callback<ShoppingCarts>() {
                    @Override
                    public void success(ShoppingCarts outResponse, retrofit.client.Response response) {
                        mloading.setVisibility(View.GONE);
                        if (outResponse != null) {

                            Log.i(FloraConstant.TAG, "not null");

                            if (outResponse.getShoppingCarts() != null) {

                                Log.i(FloraConstant.TAG, "not null 1");

                                if (outResponse.getShoppingCarts().size() > 0) {
                                    Log.i(FloraConstant.TAG, "not null 2");
                                    arrayList.set(position, outResponse.getShoppingCarts().get(0));
                                    rv_cart.getAdapter().notifyItemChanged(position,
                                            arrayList.get(position));

                                    rv_cart.setVisibility(View.VISIBLE);
                                    adapter.notifyDataSetChanged();
                                    setTotalPrice();
                                    updateQuantity();
                                }
                            }

                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        getFragmentManager().popBackStack();
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                        Log.d("RetrofitError", "failure");
                    }
                });

    } // update shopping cart items

    private void updateQuantity() {
        int count = 0;
        for (ShoppingCart shoppingCart : arrayList) {
            count = count + shoppingCart.getQuantity();
        }
    } // update quantity value

    private void prepareShoppingCartData(int position, boolean isAdd) {
        ShoppingCartItem_ cartItem_ = new ShoppingCartItem_();
        cartItem_.setCustomerId(arrayList.get(position).getCustomerId());
        cartItem_.setProductId(arrayList.get(position).getProductId());
        cartItem_.setQuantity(isAdd ? (arrayList.get(position).getQuantity() + 1)
                : (arrayList.get(position).getQuantity() - 1));
        cartItem_.setShoppingCartType(FloraConstant.ShoppingCartType[isCartList ? 0 : 1]);
        shoppingCartItem.setShoppingCartItem(cartItem_);
    } // prepareShoppingCartData



    @SuppressLint("SetTextI18n")
    private void setTotalPrice() {
        double total = 0;


        for (ShoppingCart shoppingCart : arrayList) {
            total = total + shoppingCart.getSub_total();
        }

        Log.i(FloraConstant.TAG, "total : " + total);

        if (LanguageSessionManager.getLang().equalsIgnoreCase("en")) {
            try {
                tv_subtotal.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                        ENGLISH_BOLD));

                btn_check.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                        ENGLISH_BOLD));

                Log.i(FloraConstant.TAG, "CurrencyCode in cart fragment : " +
                        SessionManager.getUserCurrencyCode());

            } catch (Exception e) {}

        }
        try {
            tv_subtotal.setText(
                    FixControl.round2(total + "") + " " + SessionManager.getUserCurrencyCode());
        } catch (Exception e) {
        }

        tv_delivery_charges.setText("0");
        tv_total.setText("0");

    } // setTotalPrice value


    private void initCartRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
       // adapter = new CartAdapter(getActivity(), arrayList);
        rv_cart.setHasFixedSize(true);
        rv_cart.setLayoutManager(layoutManager);
        rv_cart.setAdapter(adapter);
    } // initCartRecyclerView


}
