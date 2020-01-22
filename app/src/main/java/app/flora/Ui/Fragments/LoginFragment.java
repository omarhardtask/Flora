package app.flora.Ui.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.LocaleHelper;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.GetCustomer;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import app.flora.Ui.Activities.SplashScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.content.Context.MODE_PRIVATE;
import static app.flora.Ui.Activities.MainActivity.isEnglish;

public class LoginFragment extends Fragment {


    @BindView(R.id.et_email)
    MaterialEditText et_email;

    @BindView(R.id.et_password)
    MaterialEditText et_password;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinator_layout;

    @BindView(R.id.loading_progress)
    ProgressBar loading_progress;

    @BindView(R.id.tv_language)
    TextView tv_language;

    // vars

    String coming = "";
    FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);
        initVisibility();
        checkEmail();
        checkComeFrom();
        return view;
    }

    @OnClick(R.id.tv_language)
    public void clickLanguage() {
        fm = getFragmentManager();
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
            SessionManager.setKEY_LangId("2");
            updateViews("ar");
            isEnglish = false;
            Intent intent = new Intent(getActivity(), SplashScreen.class);
            //intent.putExtra("comeFrom", "login");
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
           // intent.putExtra("comeFrom", "login");
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

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText("");
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_back.setVisibility(View.VISIBLE);
    } // initialize visibiliy


    private void checkComeFrom() {
        if (getArguments() != null) {
            if (getArguments().containsKey("comingFrom")) {
                coming = getArguments().getString("comingFrom");
            }
        }
        if (coming != null)
            Log.i(FloraConstant.TAG, "coming from : " + coming);
    } // check come from


    @OnClick(R.id.tv_login_guest)
    public void loginGuest() {
        if (coming == "checkOut") {
            Bundle bundle = new Bundle();
            bundle.putString("comingFrom", "cart");
            Fragment fragment = new AddressesFragment();
            fragment.setArguments(bundle);
            Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
        } else {
            Intent main = new Intent(getActivity(), MainActivity.class);
            SharedPreferences.Editor editor = Objects.requireNonNull(getActivity())
                    .getSharedPreferences(FloraConstant.MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("guest", "1").apply();
            startActivity(main);
            getActivity().finish();
        }
    } // click on tv_login_guest


    @OnClick(R.id.tv_forget_password)
    void forgetPassword() {
        Fragment forgetPasswordFragment = new ForgetPassword();
        Navigator.loadFragment(getActivity(), forgetPasswordFragment, R.id.fragment_container, true, "");
    } // click on forget password


    @OnClick(R.id.btn_register)
    public void registerAccount() {
        Fragment registerFragment = new RegisterFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isEdit", true);
        bundle.putBoolean("isCart", true);
        registerFragment.setArguments(bundle);
        Navigator.loadFragment(getActivity(), registerFragment, R.id.fragment_container, true, "");

    } // click on register

    private void checkEmail() {

        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et_email.setCompoundDrawables(null, null, null, null);
                if (charSequence.toString().length() > 0) {
                    if (FixControl.isValidEmail(charSequence.toString())) {
                        Drawable img = getContext().getResources().getDrawable(R.drawable.true_icon);
                        img.setBounds(0, 0, 51, 39);
                        et_email.setCompoundDrawables(null, null, img, null);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    } // email validation method

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isAllFieldValid() {
        boolean result = true;

        if (et_email.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.email_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (et_password.getText().toString().length() == 0) {
            Snackbar.make(coordinator_layout, getString(R.string.password_empty), Snackbar.LENGTH_LONG).show();
            return false;
        }


        if (!et_email.getText().toString().isEmpty()) {
            if (!FixControl.isValidEmail(et_email.getText().toString())) {
                Snackbar.make(coordinator_layout, getString(R.string.EmailIsWrongLabel), Snackbar.LENGTH_LONG).show();
                Log.i(FloraConstant.TAG, "et_email wrong email");
                return false;
            }
        }
        return result;
    } // check validation

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.btn_login)
    public void loginClick() {
        FixControl.hideKeybord(Objects.requireNonNull(getView()), Objects.requireNonNull(getActivity()));
        if (isAllFieldValid()) {
            login();
        }
    } // click on login

    private void login() {

        loading_progress.setVisibility(View.VISIBLE);
        FixControl.DisableLayout(coordinator_layout);

        FloraApiCall.getCallingAPIInterface().login(LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken, et_email.getText().toString(), et_password.getText().toString(),
                SessionManager.getGuestUserId(), new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, retrofit.client.Response response) {
                        Log.i(FloraConstant.TAG, "login success : " + response.getBody().toString());
                        loading_progress.setVisibility(View.GONE);
                        FixControl.EnableLayout(coordinator_layout);

                        if (outResponse != null) {
                            Log.i(FloraConstant.TAG, " body not null");

                            if (outResponse.getCustomers() != null) {
                                Log.i(FloraConstant.TAG, "not null 1");

                                if (outResponse.getCustomers().size() > 0) {
                                    Log.i(FloraConstant.TAG, "not null 2");

                                    if (Integer.parseInt(outResponse.getCustomers().get(0).getVendor_id()) > 0) {
                                        SessionManager.vendor();
                                    } else {
                                        SessionManager.customer();
                                    }

                                    SessionManager.setGuestUserId("0");
                                    SessionManager.setUserCode(outResponse.getCustomers().get(0).getId() + "");
                                    SessionManager.LoginSeassion();
                                    Toast.makeText(getContext(), getString(R.string.login_sccess), Toast.LENGTH_LONG).show();
                                    try {
                                        if (getFragmentManager().getBackStackEntryCount() > 1) {
                                            getFragmentManager().popBackStack();
                                            MainActivity.registerGuest();
                                        } else {
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
                                }
                            }
                        } else {
                            Log.i(FloraConstant.TAG, "null");
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        loading_progress.setVisibility(View.GONE);
                        Log.i("RetrofitError", "failure");
                        FixControl.showErrorMessage(error, getView());
                        FixControl.EnableLayout(coordinator_layout);
                    }
                });

    } // login request api

    @Override
    public void onStart() {
        super.onStart();
        if (SessionManager.isLoggedin()) {
            getFragmentManager().popBackStack();
        }
    } // onstart

}
