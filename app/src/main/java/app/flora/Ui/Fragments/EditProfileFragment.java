package app.flora.Ui.Fragments;


import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import app.flora.Adapters.genderAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.Customer;
import app.flora.Models.GenderModel;
import app.flora.Models.GetCustomer;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EditProfileFragment extends Fragment {

    // bind views
    @BindView(R.id.et_day)
    MaterialEditText et_day;
    @BindView(R.id.et_month)
    MaterialEditText et_month;
    @BindView(R.id.et_year)
    MaterialEditText et_year;
    @BindView(R.id.et_gender)
    MaterialEditText et_gender;
    @BindView(R.id.relative_option_dialog)
    RelativeLayout relative_option_dialog;
    @BindView(R.id.relative_top)
    RelativeLayout relative_top;
    @BindView(R.id.tv_title)
    TextView tv_title1;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.lv)
    ListView listView1;
    @BindView(R.id.loading_progress)
    ProgressBar mloading;
    @BindView(R.id.et_first_name)
    MaterialEditText et_first_name;
    @BindView(R.id.et_last_name)
    MaterialEditText et_last_name;
    @BindView(R.id.et_email)
    MaterialEditText et_email;
    @BindView(R.id.et_mobile)
    MaterialEditText et_mobile;

    @BindView(R.id.btn_save)
    Button btn_save;

    private ArrayList<GenderModel> genderArrayList = new ArrayList<>();
    String gender_name = "";
    Calendar myCalendar;
    View view;
    DatePickerDialog.OnDateSetListener date;
    Customer customer = null;
    Date date1;
    GetCustomer getCustomer = new GetCustomer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "EditProfileFragment Called");
        initVisibility();
        fillGender();
        initEdit();
        initDate();
        return view;
    } // onCreateView

    @OnClick(R.id.btn_save)
    public void clickSave() {
        updateCustomer();
    } // click on save


    private void updateCustomer() {

        btn_save.setClickable(false);
        mloading.setVisibility(View.VISIBLE);
        prepareNewData();
        FloraApiCall.getCallingAPIInterface().updateCustomer(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                MainActivity.getUserId(),
                getCustomer, new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, retrofit.client.Response response) {
                        mloading.setVisibility(View.GONE);
                        if (outResponse != null) {
                            Log.d("body", "not null");
                            if (outResponse.getCustomers() != null) {
                                Log.d("body", "not null 1");
                                Log.i(FloraConstant.TAG, "edit profile success : ");
                                if (outResponse.getCustomers().size() > 0) {
                                    Log.d("body", "not null 2");
                                    getFragmentManager().popBackStack();
                                    try {
                                        Toast.makeText(getActivity(), getActivity().getString(R.string.EditDone), Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        } else {
                            Log.d("body", "null");
                        }
                        btn_save.setClickable(true);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                        Log.d("RetrofitError", "failure");
                    }
                });
    } // edit customer data request to api

    private void prepareNewData() {
        Customer customer = new Customer();
        customer.setFirstName(Objects.requireNonNull(et_first_name.getText()).toString());
        customer.setLastName(Objects.requireNonNull(et_last_name.getText()).toString());
        customer.setEmail(Objects.requireNonNull(et_email.getText()).toString());
        customer.setPhone(Objects.requireNonNull(et_mobile.getText()).toString());

        if (et_gender.getText().toString().equals("Male")) {
            customer.setGender("M");
        } else if (et_gender.getText().toString().equals("Female")) {
            customer.setGender("F");
        }

        customer.setDate_of_birth(et_year.getText().toString() + "-" + et_month.getText().toString()
                + "-" + et_day.getText().toString());
        getCustomer.setCustomer(customer);

    } // prepare new data to send it to the api


    @OnClick(R.id.linear_change_password)
    public void changePassword() {
        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        Navigator.loadFragment(getActivity(), changePasswordFragment, R.id.fragment_container, true, "");
    } // init changePassword


    private void initEdit() {
        if (customer != null) {
            setData();
        } else {
            customerById();
        }
    } // initialize edit profile

    private void setData() {
        try {
            if (customer != null) {
                et_first_name.setText(customer.getFirstName());
                et_last_name.setText(customer.getLastName());
                et_mobile.setText(customer.getLastName());

                try {
                    String dtStart = customer.getDate_of_birth();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    date1 = format.parse(dtStart);
                    System.out.println(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                et_day.setText(sdf.format(date1));
                et_day.setText(sdf.format(date1).split("/")[0]);
                et_month.setText(sdf.format(date1).split("/")[1]);
                et_year.setText(sdf.format(date1).split("/")[2]);

                Log.i(FloraConstant.TAG, "email : ");
                if (customer.getEmail().contains("@example.com")) {
                    et_email.setText("");
                } else {

                    et_email.setText(customer.getEmail());
                }
                et_mobile.setText(customer.getUsername());
            }
        } catch (Exception e) {
        }

    } // set fields with data from api


    private void customerById() {

        mloading.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().customerById(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                SessionManager.getUserCode(),
                new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, Response response) {
                        if (outResponse != null) {
                            Log.i(FloraConstant.TAG, "edit profile response success");
                            if (outResponse.getCustomers() != null) {
                                if (outResponse.getCustomers().size() > 0) {
                                    customer = outResponse.getCustomers().get(0);
                                    setData();
                                }
                            }
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                    }
                });
    } // edit profile api method


    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.EditProfile));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    private void initDate() {
        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    } // initDate

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        et_day.setText(sdf.format(myCalendar.getTime()));
        et_day.setText(sdf.format(myCalendar.getTime()).split("/")[0]);
        et_month.setText(sdf.format(myCalendar.getTime()).split("/")[1]);
        et_year.setText(sdf.format(myCalendar.getTime()).split("/")[2]);
    } // updateLabel

    @OnClick(R.id.linear_birth)
    public void initBirth() {
        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    } // initBirthday

    private void fillGender() {
        genderArrayList.clear();
        genderArrayList.add(new GenderModel(getString(R.string.male)));
        genderArrayList.add(new GenderModel(getString(R.string.female)));
    } // fillGender

    @OnClick(R.id.et_gender)
    public void clickCountry() {
        showGenderDialogue();
    } // init

    private void showGenderDialogue() {

        if (genderArrayList.size() > 0) {
            relative_option_dialog.setVisibility(View.VISIBLE);
            relative_top.setVisibility(View.GONE);

            relative_option_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            tv_cancel.setTypeface(MainActivity.typeface);
            tv_title1.setTypeface(MainActivity.typeface, Typeface.BOLD);
            final genderAdapter alertAdapter1 = new genderAdapter(getActivity(), genderArrayList);
            listView1.setAdapter(alertAdapter1);
            alertAdapter1.notifyDataSetChanged();
            FixControl.setListViewHeightBasedOnChildren(listView1);
            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    GenderModel item = genderArrayList.get(i);
                    relative_option_dialog.setVisibility(View.GONE);
                    relative_top.setVisibility(View.VISIBLE);

                    if (item.getText().equalsIgnoreCase(getString(R.string.DoneLabel)) ||
                            item.getText().equalsIgnoreCase(getString(R.string.DoneLabel))) {
                    } else {
                        alertAdapter1.notifyDataSetChanged();
                        gender_name = genderArrayList.get(i).getText();
                        et_gender.setText(gender_name);
                    }
                }
            });
            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    relative_option_dialog.setVisibility(View.GONE);
                    relative_top.setVisibility(View.VISIBLE);
                }
            });
        }
    } // showGenderDialogue

}
