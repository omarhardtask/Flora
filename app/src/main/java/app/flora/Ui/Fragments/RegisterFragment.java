package app.flora.Ui.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import app.flora.Adapters.genderAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.LocaleHelper;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.Customer;
import app.flora.Models.GenderModel;
import app.flora.Models.GetCustomer;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import app.flora.Ui.Activities.SplashScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.RetrofitError;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.content.Context.MODE_PRIVATE;
import static app.flora.Ui.Activities.MainActivity.isEnglish;

public class RegisterFragment extends Fragment {


    // bind views
    @BindView(R.id.et_gender)
    MaterialEditText et_gender;
    @BindView(R.id.relative_option_dialog)
    RelativeLayout relative_option_dialog;
    @BindView(R.id.relative_top)
    RelativeLayout relative_top;
    @BindView(R.id.loading_progress)
    ProgressBar loading_progress;
    @BindView(R.id.tv_title)
    TextView tv_title1;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.lv)
    ListView listView1;
    @BindView(R.id.tv_terms)
    TextView tv_terms;
    @BindView(R.id.et_day)
    MaterialEditText et_day;
    @BindView(R.id.et_month)
    MaterialEditText et_month;
    @BindView(R.id.et_year)
    MaterialEditText et_year;
    @BindView(R.id.et_first_name)
    MaterialEditText et_first_name;
    @BindView(R.id.et_last_name)
    MaterialEditText et_last_name;
    @BindView(R.id.et_email)
    MaterialEditText et_email;
    @BindView(R.id.et_mobile)
    MaterialEditText et_mobile;
    @BindView(R.id.et_password)
    MaterialEditText et_password;
    @BindView(R.id.cbTermsConditions)
    CheckBox cbTermsConditions;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinator_layout;
    @BindView(R.id.et_confirm_password)
    MaterialEditText et_confirm_password;

    private ArrayList<GenderModel> genderArrayList = new ArrayList<>();
    String gender_name = "";
    Calendar myCalendar;
    View view;
    DatePickerDialog.OnDateSetListener date;
    boolean isEdit = false, isCart = false;
    GetCustomer getCustomer = new GetCustomer();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register_fragment, container, false);
        ButterKnife.bind(this, view);
        initVisibility();
        fillGender();
        checkIfEdit();
        initDate();
        return view;
    } // onCreateView

    @OnClick(R.id.tv_language)
    public void clickLanguage() {
        FragmentManager fm = getFragmentManager();
        Log.i(FloraConstant.TAG, "Stack before change language : " + fm.getBackStackEntryCount());

        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
        Log.i(FloraConstant.TAG, "Stack after change language : " + fm.getBackStackEntryCount());
        changeLanguage();
    } // click on change language

    private void changeLanguage() {

        if (isEnglish) {
            Log.i(FloraConstant.TAG, "setLang ar");
            LanguageSessionManager.setLang("ar");
            updateViews("ar");
            isEnglish = false;
            Intent intent = new Intent(getActivity(), SplashScreen.class);
            SharedPreferences.Editor editor = getActivity().
                    getSharedPreferences(FloraConstant.MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("guest", "2").apply();
            intent.putExtra("comeFrom", "register");
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            getActivity().finish();
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(FloraConstant.ARABIC_FONT)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        } else {
            Log.i(FloraConstant.TAG, "setLang en");
            LanguageSessionManager.setLang("en");
            updateViews("en");
            isEnglish = true;
            Intent intent = new Intent(getActivity(), SplashScreen.class);
            SharedPreferences.Editor editor = getActivity().
                    getSharedPreferences(FloraConstant.MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("guest", "2").apply();
            //intent.putExtra("comeFrom", "register");
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            getActivity().finish();
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(FloraConstant.ENGLISH_FONT)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
    } // change language

    private void updateViews(String languageCode) {
        LocaleHelper.setLocale(getActivity(), languageCode);
    } // update view method


    @OnClick(R.id.tv_terms)
    public void terms() {
        WebViewFragment aboutFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", "3");
        aboutFragment.setArguments(bundle);
        Navigator.loadFragment(getActivity(), aboutFragment, R.id.fragment_container, true, "");
    } // go to terms

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

    }

    @OnClick(R.id.linear_birth)
    public void initBirth() {
        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    } // initBirthday

    @OnClick(R.id.tv_birthday)
    public void initBirthTextView() {
        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    } // initBirthday

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText("");
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_back.setVisibility(View.VISIBLE);
    } // initialize visibiliy

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

    @OnClick(R.id.btn_register)
    public void registerClick() {
        if (isAllFieldValid()) {
            FixControl.hideKeybord(Objects.requireNonNull(getView()), Objects.requireNonNull(getActivity()));
            registerUser();
        }
    } // click on register

    private void checkIfEdit() {
        if (getArguments() != null) {
            if (getArguments().containsKey("isEdit")) {
                isEdit = getArguments().getBoolean("isEdit");
            }
            if (getArguments().containsKey("isCart")) {
                isCart = getArguments().getBoolean("isCart");
            }
        }
    } // check if the user edit or no

    private void prepareRegisterData() {
        Customer customer = new Customer();
        customer.setFirstName(Objects.requireNonNull(et_first_name.getText()).toString());
        customer.setLastName(Objects.requireNonNull(et_last_name.getText()).toString());
        customer.setName(Objects.requireNonNull(et_first_name.getText()).toString());
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



        if (!isEdit || isCart) {
            customer.setPassword(et_password.getText().toString());
            List<Integer> objectList = new ArrayList<>();
            objectList.add(FloraConstant.NormalUserRoleId);
            customer.setRoleIds(objectList);
        }
        getCustomer.setCustomer(customer);
    } // prepare data to send to api

    private boolean isAllFieldValid() {
        boolean result = true;

        if (et_email.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.email_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (et_first_name.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.first_name) + " " +
                    getString(R.string.cant_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (et_last_name.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.last_name)
                    + " " +
                    getString(R.string.cant_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (et_mobile.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.mobile_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (et_password.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.password_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (et_confirm_password.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.confirm_password_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (!et_email.getText().toString().isEmpty())
            if (!FixControl.isValidEmail(et_email.getText().toString())) {
                Snackbar.make(coordinator_layout, getString(R.string.EmailIsWrongLabel), Snackbar.LENGTH_LONG).show();
                return false;
            }

        if (et_password.getText().toString().length() < 6) {
            Snackbar.make(coordinator_layout, getString(R.string.password_limit), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (!cbTermsConditions.isChecked()) {
            Snackbar.make(coordinator_layout, getString(R.string.MustAgreeFirst), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (!isEdit || isCart) {
            if (!Objects.requireNonNull(et_password.getText()).toString().equals(Objects.requireNonNull(
                    et_confirm_password.getText()).toString())) {
                Snackbar.make(coordinator_layout, getString(R.string.confirm_validation), Snackbar.LENGTH_LONG).show();
                return false;
            }
            if (!et_password.getText().toString().equals(et_confirm_password.getText().toString())) {
                Snackbar.make(coordinator_layout, getString(R.string.save), Snackbar.LENGTH_LONG).show();
                return false;
            }
        }
        return result;
    } // validation for fields


    private void registerUser() {

        FixControl.DisableLayout(coordinator_layout);
        loading_progress.setVisibility(View.VISIBLE);
        prepareRegisterData();
        FloraApiCall.getCallingAPIInterface().
                updateCustomer(LanguageSessionManager.getLang(),
                        FloraConstant.AuthorizationToken,
                        MainActivity.getUserId(),
                        getCustomer, new retrofit.Callback<GetCustomer>() {
                            @Override
                            public void success(GetCustomer outResponse, retrofit.client.Response response) {

                                loading_progress.setVisibility(View.GONE);

                                if (outResponse != null) {

                                    Log.i(FloraConstant.TAG, "not null");

                                    if (outResponse.getCustomers() != null) {
                                        Log.i(FloraConstant.TAG, "register update customer success : " + outResponse);
                                        Log.i(FloraConstant.TAG, "not null 1");
                                        if (outResponse.getCustomers().size() > 0) {

                                            SessionManager.setGuestUserId("0");
                                            SessionManager.setUserCode(outResponse.getCustomers().get(0).getId() + "");
                                            SessionManager.LoginSeassion();
                                            Toast.makeText(getContext(), getString(R.string.register_success), Toast.LENGTH_LONG).show();

                                            Log.i(FloraConstant.TAG,
                                                    "getFragmentManager().getBackStackEntryCount()" +
                                                            getFragmentManager().getBackStackEntryCount());

                                            try {
                                                if (getFragmentManager().getBackStackEntryCount() > 2) {
                                                    Log.i(FloraConstant.TAG, "getFragmentManager()"
                                                            +
                                                            ".getBackStackEntryCount() >= 2");
                                                    getFragmentManager().popBackStack();
                                                    MainActivity.registerGuest();
                                                } else {
                                                    Log.i(FloraConstant.TAG, "registerIntent");
                                                    Intent main = new Intent(getContext(), MainActivity.class);
                                                    main.putExtra("comeFrom", "");
                                                    startActivity(main);
                                                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(
                                                            FloraConstant.MY_PREFS_NAME, MODE_PRIVATE).edit();
                                                    editor.putString("guest", "1").apply();
                                                    getActivity().finish();
                                                }
                                            } catch (Exception e) {
                                            }
                                            Log.i(FloraConstant.TAG, "not null 2");
                                        }
                                    }
                                } else {
                                    Log.d(FloraConstant.TAG, "null");
                                }
                                FixControl.EnableLayout(coordinator_layout);

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                loading_progress.setVisibility(View.GONE);
                                Log.d(FloraConstant.TAG, "update customer failer" + error.getMessage());
                                FixControl.showErrorMessage(error, coordinator_layout);
                                FixControl.EnableLayout(coordinator_layout);

                            }
                        });

    } // update exist user


}
