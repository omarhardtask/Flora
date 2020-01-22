package app.flora.Ui.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Adapters.CountryAdapter;
import app.flora.Adapters.StateAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.Models.Address;
import app.flora.Models.Countries;
import app.flora.Models.Country;
import app.flora.Models.GetAddress;
import app.flora.Models.GetCustomer;
import app.flora.Models.StateProvince;
import app.flora.Models.StateProvinces;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;

public class AddAddressFragment extends Fragment {

    // define ids

    @BindView(R.id.et_address_title)
    MaterialEditText etAddressTitle;
    @BindView(R.id.tv_country)
    TextView tv_country;
    @BindView(R.id.et_city)
    TextView etCity;
    @BindView(R.id.et_phone)
    MaterialEditText etPhone;
    @BindView(R.id.et_first_name)
    MaterialEditText et_first_name;
    @BindView(R.id.et_last_name)
    MaterialEditText et_last_name;
    @BindView(R.id.et_street_number)
    MaterialEditText et_street_number;
    @BindView(R.id.et_block)
    MaterialEditText etBlock;
    @BindView(R.id.et_address_details)
    MaterialEditText etAddressDetails;
    @BindView(R.id.loading)
    ProgressBar mloading;
    @BindView(R.id.relative_option_dialog)
    RelativeLayout relative_option_dialog;
    @BindView(R.id.tv_title)
    TextView tv_title1;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.lv)
    ListView listView1;
    @BindView(R.id.constrain_top)
    ConstraintLayout constrain_top;
    @BindView(R.id.et_email)
    MaterialEditText et_email;
    @BindView(R.id.btn_add_address)
    Button btn_add_address;
    @BindView(R.id.loading1)
    ProgressBar loading1;
    @BindView(R.id.layout)
    ScrollView layout;


    // variables
    boolean isEdit = false;
    SessionManager mSessionManager;
    LanguageSessionManager languageSeassionManager;
    GetAddress getAddress = new GetAddress();
    Address address = null;
    ArrayList<Country> countryArrayList = new ArrayList<>();
    ArrayList<StateProvince> stateArrayList = new ArrayList<>();
    String countryId = "", countryName = "", stateId = "", stateName = "";
    static FragmentActivity act;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_address_fragment, container, false);
        ButterKnife.bind(this, view);
        initSession();
        initVisibility();
        getArgument();
        initDefaultData();
        setData();
        return view;
    } // onCreateView


    private void getArgument() {
        Gson gson = new Gson();
        if (getArguments() != null) {

            if (getArguments().containsKey("isEdit")) {
                isEdit = getArguments().getBoolean("isEdit");
            }
            if (getArguments().containsKey("Address")) {
                address = gson.fromJson(getArguments().getString("Address"),
                        Address.class);
            }
        }
    } // get argument data

    private void setData() {

        if (isEdit && address != null) {
            ((MainActivity) getActivity()).title.setText(getString(R.string.Edit));
            //  etAddressTitle.setText(address.getAddress1());
            tv_country.setText(address.getCountry());
            et_first_name.setText(address.getFirstName());
            et_last_name.setText(address.getLastName());
            et_email.setText(address.getEmail());
            etPhone.setText(address.getPhoneNumber());
            etAddressDetails.setText(address.getFirstName());
            et_street_number.setText(address.getAddress1());
            etBlock.setText(address.getBlock());
            etCity.setText(address.getCity());
            countryId = address.getCountryId() + "";
            countryName = address.getCountry();
            Log.i(FloraConstant.TAG, "firstName" + address.getFirstName());
            Log.i(FloraConstant.TAG, "lastName" + address.getLastName());
            Log.i(FloraConstant.TAG, "stateId" + address.getStateProvinceId());
            Log.i(FloraConstant.TAG, "countryId" + address.getCountryId());
            Log.i(FloraConstant.TAG, "etBlock" + address.getBlock());
            Log.i(FloraConstant.TAG, "et_street_number" + address.getArea());
            Log.i(FloraConstant.TAG, "etCity" + address.getCity());
            stateId = address.getStateProvinceId() + "";
            stateName = address.getProvince();

            if (countryArrayList.size() == 0) {
                countries(false);
            }
            if (stateArrayList.size() == 0) {
                if (countryId.length() > 0) {
                    states(false);
                }
            }
        }
    } // set data

    private void initDefaultData() {
        if (!isEdit && mSessionManager.isLoggedin()) {
            customerById();
        }
    } // init default data

    private void customerById() {

        mloading.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().customerInfo(
                languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                mSessionManager.getUserCode(), new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, retrofit.client.Response response) {

                        if (outResponse != null) {
                            Log.d(FloraConstant.TAG, "not null");
                            if (outResponse.getCustomers().size() > 0) {
                                et_email.setText(outResponse.getCustomers().get(0).getEmail());
                                etPhone.setText(outResponse.getCustomers()
                                        .get(0).getPhone());

                            }
                        } else {
                            Log.i(FloraConstant.TAG, "null");
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                    }
                });

    } // customerById

    @OnClick(R.id.et_city)
    void selectCity() {
        if (countryId.length() > 0) {
            Log.i(FloraConstant.TAG, "ifff outside");
            if (stateArrayList.size() > 0) {
                showStatesDialogue();
                Log.i(FloraConstant.TAG, "iffff");
            } else {
                Log.i(FloraConstant.TAG, "elseeee");
                states(true);
            }
        } else {
            Log.i(FloraConstant.TAG, "elseeee outside");
            try {
                Toast.makeText(getActivity(), getString(R.string.choose_country), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
            }
        }

    } // select area

    private void states(boolean showDialog) {

        mloading.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().stateProvinces(
                languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                countryId
                , new Callback<StateProvinces>() {
                    @Override
                    public void success(StateProvinces outResponse, retrofit.client.Response response) {

                        if (outResponse != null) {

                            if (outResponse.getStateProvinces() != null) {
                                Log.d(FloraConstant.TAG, "not null");
                                stateArrayList.clear();
                                stateArrayList.addAll(outResponse.getStateProvinces());
                                Log.i(FloraConstant.TAG, "state array list " + stateArrayList.size());

                                if (showDialog) {
                                    showStatesDialogue();
                                }
                            }
                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mloading.setVisibility(View.GONE);
                        FixControl.showErrorMessage(error, getView());
                    }
                });

    } // get cities from webservices

    private void showStatesDialogue() {

        if (countryArrayList.size() > 0) {
            relative_option_dialog.setVisibility(View.VISIBLE);
            constrain_top.setVisibility(View.GONE);
            btn_add_address.setVisibility(View.GONE);

            relative_option_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            tv_title1.setText(getString(R.string.SelectLabel));
            tv_cancel.setTypeface(MainActivity.typeface);
            tv_title1.setTypeface(MainActivity.typeface, Typeface.BOLD);
            final StateAdapter alertAdapter1 = new StateAdapter(act, stateArrayList);
            listView1.setAdapter(alertAdapter1);
            alertAdapter1.notifyDataSetChanged();
            FixControl.setListViewHeightBasedOnChildren(listView1);

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    StateProvince item = stateArrayList.get(i);

                    relative_option_dialog.setVisibility(View.GONE);
                    constrain_top.setVisibility(View.VISIBLE);
                    btn_add_address.setVisibility(View.VISIBLE);

                    if (item.getName().equalsIgnoreCase(getString(R.string.DoneLabel)) ||
                            item.getName().equalsIgnoreCase(getString(R.string.DoneLabel))) {
                    } else {
                        alertAdapter1.notifyDataSetChanged();
                        stateId = stateArrayList.get(i).getId() + "";
                         stateName = stateArrayList.get(i).getLocalized_names().get(0).getLocalizedName();
                        //stateName = stateArrayList.get(i).getName();
                        etCity.setText(stateName);
                    }
                }
            });

            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    relative_option_dialog.setVisibility(View.GONE);
                    constrain_top.setVisibility(View.VISIBLE);
                    btn_add_address.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @OnClick(R.id.tv_country)
    void selectCountry() {
        if (countryArrayList.size() > 0) {
            showCountriesDialogue();
        } else {
            countries(true);
        }
    } // click on country

    private void countries(boolean showDialog) {

        loading1.setVisibility(View.VISIBLE);

        FloraApiCall.getCallingAPIInterface().countries(
                languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                new Callback<Countries>() {
                    @Override
                    public void success(Countries outResponse, retrofit.client.Response response) {

                        if (outResponse != null) {
                            if (outResponse.getCountries() != null) {
                                Log.d(FloraConstant.TAG, "not null");
                                Log.d(FloraConstant.TAG, "response countries success");
                                countryArrayList.clear();
                                countryArrayList.addAll(outResponse.getCountries());
                                if (showDialog) {
                                    showCountriesDialogue();
                                }
                            }
                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }

                        loading1.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loading1.setVisibility(View.GONE);
                        FixControl.showErrorMessage(error, getView());
                    }
                });
    } // get countries from api

    private void showCountriesDialogue() {

        if (countryArrayList.size() > 0) {

            relative_option_dialog.setVisibility(View.VISIBLE);
            constrain_top.setVisibility(View.GONE);
            btn_add_address.setVisibility(View.GONE);

            relative_option_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            tv_title1.setText(getString(R.string.SelectLabel));

            tv_cancel.setTypeface(MainActivity.typeface);
            tv_title1.setTypeface(MainActivity.typeface, Typeface.BOLD);
            final CountryAdapter alertAdapter1 = new CountryAdapter(getActivity(), countryArrayList);
            listView1.setAdapter(alertAdapter1);
            FixControl.setListViewHeightBasedOnChildren(listView1);


            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Country item = countryArrayList.get(i);

                    relative_option_dialog.setVisibility(View.GONE);
                    constrain_top.setVisibility(View.VISIBLE);
                    btn_add_address.setVisibility(View.VISIBLE);

                    if (item.getName().equalsIgnoreCase(getString(R.string.DoneLabel)) ||
                            item.getName().equalsIgnoreCase(getString(R.string.DoneLabel))) {
                    } else {
                        alertAdapter1.notifyDataSetChanged();
                        countryId = countryArrayList.get(i).getId() + "";
                         countryName = countryArrayList.get(i).getLocalizedNames().get(0).getLocalizedName();
                       // countryName = countryArrayList.get(i).getName();
                        tv_country.setText(countryName);
                    }
                }
            });

            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    relative_option_dialog.setVisibility(View.GONE);
                    constrain_top.setVisibility(View.VISIBLE);
                    btn_add_address.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void initSession() {
        mSessionManager = new SessionManager(getContext());
        languageSeassionManager = new LanguageSessionManager(getContext());

        if (act == null) {
            act = getActivity();
        }
    } // initSession method

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.add_address));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    @OnClick(R.id.btn_add_address)
    public void clickAddAddress() {

        if (languageSeassionManager.getLang().equalsIgnoreCase("en")) {
            btn_add_address.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                    FloraConstant.
                            ENGLISH_BOLD));
        }
        Log.i(FloraConstant.TAG, "countryId.length()  " + countryId.length());
        Log.i(FloraConstant.TAG, "stateId.length()  " + stateId.length());

        if (
            //etAddressTitle.getText().toString().length() > 0
                countryId.length() > 0
                        && stateId.length() > 0
                        && etCity.length() > 0
                        && et_first_name.length() > 0
                        && et_last_name.length() > 0
                        && tv_country.length() > 0
                        && etPhone.getText().toString().length() > 0
                        && et_street_number.length() > 0
                        && etBlock.length() > 0
                        && etAddressDetails.length() > 0
        ) {
            if (isEdit) {
                editAddress();
                Log.i(FloraConstant.TAG, "editAddress ");
            } else {
                Log.i(FloraConstant.TAG, "addAddress ");
                addAddress();
            }
        } else {
            Snackbar.make(getView(), getString(R.string.FieldMissing), Snackbar.LENGTH_LONG).show();
        }

    } // click on add address

    private void editAddress() {

        Log.i(FloraConstant.TAG, "editAddress called ");

        FixControl.DisableLayout(layout);
        mloading.setVisibility(View.VISIBLE);
        prepareAddressData();
        FloraApiCall.getCallingAPIInterface().editAddress(
                languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                "application/json",
                getAddress,
                mSessionManager.getUserCode(),
                address.getId(),
                new Callback<GetAddress>() {
                    @Override
                    public void success(GetAddress outResponse, retrofit.client.Response response) {
                        Log.i(FloraConstant.TAG, "editAddress success ");
                        mloading.setVisibility(View.GONE);
                        if (outResponse != null) {
                            Log.i(FloraConstant.TAG, "editAddress not null ");
                            if (outResponse.getAddresses() != null) {
                                if (outResponse.getAddresses().size() > 0) {
                                    address = outResponse.getAddresses().get(0);
                                    try {
                                        getFragmentManager().popBackStack();
                                        Toast.makeText(getActivity(), getString(R.string.address_edited), Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                    }
                                    Log.i(FloraConstant.TAG, "editAddress .size() > 0");


                                }
                            }
                        } else {
                            Log.i(FloraConstant.TAG, "null");
                        }
                        FixControl.EnableLayout(layout);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.EnableLayout(layout);
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                        Log.i(FloraConstant.TAG, "RetrofitError" + error.getMessage());
                    }
                });
    } // edit address

    private void addAddress() {

        mloading.setVisibility(View.VISIBLE);
        FixControl.DisableLayout(layout);

        prepareAddressData();
        FloraApiCall.getCallingAPIInterface().addAddress(
                languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                "application/json",
                getAddress,
                MainActivity.getUserId(), new Callback<GetAddress>() {
                    @Override
                    public void success(GetAddress outResponse, retrofit.client.Response response) {
                        mloading.setVisibility(View.GONE);
                        if (outResponse != null) {
                            Log.i(FloraConstant.TAG, "not null");
                            if (outResponse.getAddresses() != null) {
                                if (outResponse.getAddresses().size() > 0) {
                                    address = outResponse.getAddresses().get(0);
                                    Log.i(FloraConstant.TAG, "not null 1");
                                    getFragmentManager().popBackStack();
                                    try {
                                        Toast.makeText(getActivity(), getString(R.string.address_success), Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        } else {
                            Log.i(FloraConstant.TAG, "null");
                        }
                        FixControl.EnableLayout(layout);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.EnableLayout(layout);
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                        Log.i(FloraConstant.TAG, error.getLocalizedMessage());
                    }
                });
    } // add address reqest to api

    private void prepareAddressData() {
        try {
            Address address = new Address();
            address.setFirstName(et_first_name.getText().toString());
            address.setLastName(et_last_name.getText().toString());
            address.setAddress1(et_street_number.getText().toString());
            address.setAddress2(etAddressDetails.getText().toString());
            address.setCountryId(Integer.parseInt(countryId));
            address.setStateProvinceId(Integer.parseInt(stateId));
            address.setEmail(et_email.getText().toString());
            address.setArea(etCity.getText().toString());
            address.setBlock(etBlock.getText().toString());
            address.setCountry(tv_country.getText().toString());
            address.setCity(etCity.getText().toString());
            address.setPhoneNumber(etPhone.getText().toString());
            getAddress.setAddress(address);
        } catch (Exception e) {
        }
    } // prepare fields to send to api
}
