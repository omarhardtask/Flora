package app.flora.Ui.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.AddressesAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.Address;
import app.flora.Models.AddressModel;
import app.flora.Models.GetCustomer;
import app.flora.Models.Order;
import app.flora.Models.OrderItem;
import app.flora.Models.Orders;
import app.flora.Models.Payment;
import app.flora.Models.ShoppingCart;
import app.flora.Models.ShoppingCarts;
import app.flora.Models.Stores;
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

import static app.flora.Ui.Activities.MainActivity.shoppingCartItemsCount;

public class AddressesFragment extends Fragment {

    // bind views
    @BindView(R.id.rv_addresses)
    RecyclerView rv_addresses;

    @BindView(R.id.loading_progress)
    ProgressBar mloading;

    @BindView(R.id.tv_msg)
    TextView tv_msg;


    LinearLayoutManager layoutManager;
    AddressesAdapter adapter;
    ArrayList<Address> addressesArrayList = new ArrayList<>();
    String comingFrom = "", addressId = "", strName = "",
            strName2 = "";
    ArrayList<Payment> payments = new ArrayList<>();
    ArrayList<String> Payments_Test = new ArrayList<>();
    int addressPosition = 0;
    Orders orders = new Orders();
    static FragmentActivity act;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addresses_fragment, container, false);
        ButterKnife.bind(this, view);
        initAct();
        getComingFrom();
        Log.i(FloraConstant.TAG, "AddressesFragment Called");
        initVisibility();
        initAddRecyclerView();

        current_store();
        getAddress();
        return view;
    }

    private void initAct() {
        if (act == null) {
            act = getActivity();
        } else {
            act = getActivity();
        }
    }


    @OnClick(R.id.btn_add_address)
    void tv_add_address() {
        addressId = "";
        Fragment fragment = new AddAddressFragment();
        if (!SessionManager.isLoggedin()) {
            Bundle bundle = new Bundle();
            bundle.putString("comingFrom", "cart");
            fragment.setArguments(bundle);
        }
        Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
    } // click add new address

    private void current_store() {

        payments.clear();

        FloraApiCall.getCallingAPIInterface().current_store(
                FloraConstant.AuthorizationToken,
                new Callback<Stores>() {
                    @Override
                    public void success(Stores outResponse, retrofit.client.Response response) {

                        Log.i(FloraConstant.TAG, "current store response in check out success :" + response.getBody());

                        if (outResponse.getStores() != null) {
                            try {
                                if (outResponse.getStores().size() > 0) {
                                    if (outResponse.getStores().get(0).getStore_payment_methods().size() > 0) {

                                        payments.addAll(outResponse.getStores().get(0).getStore_payment_methods());

                                        Log.i(FloraConstant.TAG, "getStore_payment_methods" +
                                                ": " + outResponse.getStores().get(0).getStore_payment_methods().size());

                                        Log.i(FloraConstant.TAG, "getStore_payment_methods" +
                                                ": " + outResponse.getStores().get(0).getStore_payment_methods().get(0).getSystem_name());

                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(FloraConstant.TAG, "current store fail:" + error.getMessage());
                    }
                });
    } // call current store api

    private void getComingFrom() {
        if (getArguments() != null) {
            if (getArguments().containsKey("comingFrom")) {
                comingFrom = getArguments().getString("comingFrom");
            }
            Log.i(FloraConstant.TAG, "comingFrom : " + comingFrom);

        }
    } // get coming from method


    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.Addresses));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy


    private void getAddress() {
        mloading.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().customerAddresses(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, Response response) {

                        addressesArrayList.clear();

                        if (outResponse.getCustomers() != null) {

                            if (outResponse.getCustomers().size() > 0) {
                                addressesArrayList.addAll(outResponse.getCustomers().get(0).getAddresses());
                                Log.i(FloraConstant.TAG, "addressesArrayList size : " +
                                        outResponse.getCustomers().get(0).getAddresses().size());
                                if (addressesArrayList.size() == 0) {
                                    rv_addresses.setVisibility(View.GONE);
                                    tv_msg.setText(getString(R.string.address_empty));
                                    tv_msg.setVisibility(View.VISIBLE);
                                }
                            }
                        }


                        adapter = new AddressesAdapter(act, addressesArrayList,
                                comingFrom, new AddressesAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                if (comingFrom.equals("cart")) {
                                    selectAddress(position);
                                    Address addresses = addressesArrayList.get(position);
                                    if (addresses.isSelected()) {
                                        Log.i(FloraConstant.TAG, "addressId length : " + addressId.length());
                                        Log.i(FloraConstant.TAG, "paymentsize size : " + payments.size());

                                        if (addressId.length() > 0) {
                                            Log.i(FloraConstant.TAG, "addressId iffff : " + addressId);
                                            showDialog();
                                        }
                                    }

                                } else {
                                    Log.i(FloraConstant.TAG, "addressId elseee : " + addressId);

                                }
                            }

                            @Override
                            public void onItemDeleteClick(int position) {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle(getActivity().getString(R.string.Confirm))
                                        .setMessage(getActivity().getString(R.string.DeleteAddressLabel))
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                if (addressesArrayList.size() > position)
                                                    Log.i(FloraConstant.TAG, "ADDRESSPOSITION = " +
                                                            position);
                                                deleteAddress(position);
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

                            @Override
                            public void onItemEditClick(int position) {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("isEdit", true);
                                bundle.putString("Address", new Gson().toJson(addressesArrayList.get(position)));
                                Log.i(FloraConstant.TAG, "sendddd : " + addressesArrayList.get(position).getStateProvinceId());
                                Log.i(FloraConstant.TAG, "sendArea : " + addressesArrayList.get(position).getArea());
                                Fragment fragment = new AddAddressFragment();
                                fragment.setArguments(bundle);
                                Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
                            }
                        });
                        rv_addresses.setAdapter(adapter);
                        if (comingFrom.equalsIgnoreCase("cart")) {
                            selectBusinessAddress(outResponse.getCustomers().get(0).getBillingAddress());
                        }

                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                    }
                });

    } // get address list from api

    private void selectBusinessAddress(Address billingAddress) {
        for (int i = 0; i < addressesArrayList.size(); i++) {
            Address address = addressesArrayList.get(i);
            if (billingAddress != null) {
                if (address.getId().equalsIgnoreCase(billingAddress.getId())) {
                    selectAddress(i);
                }
            }
        }
    } // selectBusinessAddress method

    private void deleteAddress(int position) {

        Log.i(FloraConstant.TAG, "deleteAddress called");

        FloraApiCall.getCallingAPIInterface().deleteAddress(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                addressesArrayList.get(position).getId() + "",
                new Callback<Response>() {
                    @Override
                    public void success(Response s, Response response) {

                        TypedInput body = response.getBody();
                        String outResponse = "";
                        Log.i(FloraConstant.TAG, "deleteAddress success");

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
                            Log.i(FloraConstant.TAG, "deleteAddress outResponse != null");

                            outResponse = outResponse.replace("\"", "");
                            outResponse = outResponse.replace("\n", "");
                            Log.i(FloraConstant.TAG, outResponse);
                            if (outResponse.equalsIgnoreCase("{}")) {
                                addressesArrayList.remove(position);
                                rv_addresses.getAdapter().notifyItemRemoved(position);
                                rv_addresses.getAdapter().notifyItemRangeChanged(0, addressesArrayList.size());
                                //  adapter.notifyDataSetChanged();
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
    } // delete address

    private void showDialog() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
        builderSingle.setIcon(R.drawable.logo_flora);
        builderSingle.setTitle(getString(R.string.payment));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.select_dialog_item);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.select_dialog_item);

        arrayAdapter.clear();
        Payments_Test.clear();
        Payments_Test.add(FloraConstant.PAYMENT_CASH);

        for (int i = 0; i < Payments_Test.size(); i++) {

            arrayAdapter.addAll(Payments_Test.get(i));

            //  arrayAdapter.addAll(payments.get(i).getFriendly_name());
            // arrayAdapter2.addAll(payments.get(i).getSystem_name());
        }

        builderSingle.setNegativeButton(getActivity().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  strName = arrayAdapter.getItem(which);
                //   strName2 = arrayAdapter2.getItem(which);
                shoppingCartItemsForOrder();
                dialog.dismiss();
            }
        });
        builderSingle.show();
    } // init dialog

    private void shoppingCartItemsForOrder() {

        mloading.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().shoppingCartItemsForOrder(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                new Callback<ShoppingCarts>() {
                    @Override
                    public void success(ShoppingCarts outResponse, retrofit.client.Response response) {

                        if (outResponse != null) {
                            Log.d(FloraConstant.TAG, "not null");
                            if (outResponse.getShoppingCarts().size() > 0) {
                                createOrders(outResponse.getShoppingCarts());
                            } else {
                                Snackbar.make(getView(), getString(R.string.CartEmptyNowLabel), Snackbar.LENGTH_LONG).show();
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
    } // init payment

    private void prepareOrdersData(ArrayList<ShoppingCart> cartArrayList) {

        Log.i(FloraConstant.TAG, "setArea : " + addressesArrayList.get(addressPosition).getArea());
        Log.i(FloraConstant.TAG, "setBillingAddressId : " + Integer.valueOf(addressId));

        Order order = new Order();
        order.setPaymentMethodSystemName(FloraConstant.PAYMENT_CASH);
        // order.setPaymentMethodSystemName(strName2);
        order.setPayment_by(2);

        order.setBillingAddressId(Integer.valueOf(addressId));
        order.setShipping_address_id(Integer.valueOf(addressId));
        order.setCustomerId(Integer.parseInt(MainActivity.getUserId()));
        Address billingAddress = new Address();
        billingAddress.setFirstName(addressesArrayList.get(addressPosition).getFirstName());
        billingAddress.setLastName(addressesArrayList.get(addressPosition).getLastName());
        billingAddress.setEmail(addressesArrayList.get(addressPosition).getEmail());
        billingAddress.setCountryId(addressesArrayList.get(addressPosition).getCountryId());
        billingAddress.setStateProvinceId(addressesArrayList.get(addressPosition).getStateProvinceId());
        billingAddress.setPhoneNumber(addressesArrayList.get(addressPosition).getPhoneNumber());
        billingAddress.setId(addressesArrayList.get(addressPosition).getId());
        billingAddress.setCity(addressesArrayList.get(addressPosition).getCity());
        billingAddress.setArea(addressesArrayList.get(addressPosition).getArea());
        billingAddress.setBlock(addressesArrayList.get(addressPosition).getBlock());
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(billingAddress);
        order.setShipping_method(FloraConstant.method);
        order.setShipping_rate_computation_method_system_name(
                FloraConstant.shipping_rate_computation_method_system_name);

        ArrayList<OrderItem> itemArrayList = new ArrayList<>();

        for (ShoppingCart cart : cartArrayList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cart.getProductId());
            orderItem.setProductAttributes(cart.getProductAttributes());
            orderItem.setQuantity(cart.getQuantity());
            itemArrayList.add(orderItem);
        }
        orders.setOrder(order);
    } // prepare order to send request

    private void createOrders(ArrayList<ShoppingCart> cartArrayList) {

        mloading.setVisibility(View.VISIBLE);
        prepareOrdersData(cartArrayList);
        FloraApiCall.getCallingAPIInterface().createOrders(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                "application/json",
                orders,
                new Callback<Response>() {
                    @Override
                    public void success(Response s, Response response) {
                        if (isVisible()) {
                            shoppingCartItemsCount();
                        }
                        TypedInput body = response.getBody();
                        String outResponse = "";
                        Log.i(FloraConstant.TAG, "ordersss response" + outResponse);

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
                            outResponse = outResponse.replaceAll("\"", "");
                            String[] mArrayStringValues = outResponse.split(",");
                            String payURL = mArrayStringValues[0];
                            Log.e("aaaaa", payURL);

                            if (payURL.contains("http")) {
                                Bundle b = new Bundle();
                                Log.e("aaaaa1", payURL);
                                b.putString("id", payURL);
                                b.putString("order_id", mArrayStringValues[1]);
                                Fragment fragment = new PayPaymentFragment();
                                fragment.setArguments(b);
                                Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
                            }
                            else {
                                Log.i(FloraConstant.TAG, "ordersss response elsee" + outResponse);

                                Order cOrder = new Order();
                                cOrder.setId(Integer.parseInt
                                        (mArrayStringValues[1].replaceAll("\n", "")));
                                FragmentManager fm = getFragmentManager();
                                for (int i = 0; i < fm.getBackStackEntryCount() - 1; i++) {
                                    fm.popBackStack();
                                }

                                Fragment fragment = new OrdersDetailsFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("Order", new Gson().toJson(cOrder));
                                fragment.setArguments(bundle);
                                Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (outResponse != null) {
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                        Log.d("RetrofitError", "failure");
                    }
                });
    } // create order request

    @Override
    public void onResume() {
        super.onResume();
        shoppingCartItemsCount();
    } // onResume


    private void makeAllUnSelected() {
        for (int i = 0; i < addressesArrayList.size(); i++) {
            Address addresses = addressesArrayList.get(i);
            if (addresses != null) {
                addresses.setSelected(false);
            }
            addressesArrayList.set(i, addresses);
        }
    } // un select addresses

    private void selectAddress(int position) {

        if (addressesArrayList.size() > position) {
            makeAllUnSelected();
            Address addresses = addressesArrayList.get(position);
            addressId = "";
            addressPosition = 0;
            if (addresses.isSelected()) {
                addresses.setSelected(false);
            } else {
                addressPosition = position;
                addressId = addresses.getId();
                addresses.setSelected(true);
            }

            addressesArrayList.set(position, addresses);
            rv_addresses.getAdapter().notifyItemRangeChanged(0, addressesArrayList.size(), addressesArrayList);
        }
    } // select address


    private void initAddRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rv_addresses.setHasFixedSize(true);
        rv_addresses.setLayoutManager(layoutManager);
        rv_addresses.setAdapter(adapter);
    } // initAddRecyclerView


}
