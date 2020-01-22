package app.flora.Ui.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.Models.GetCustomer;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;

public class ChangePasswordFragment extends Fragment {

    // define ids

    @BindView(R.id.et_old_password)
    MaterialEditText etOldPassword;

    @BindView(R.id.et_new_password)
    MaterialEditText etNewPassword;

    @BindView(R.id.et_confirm_new_password)
    MaterialEditText etConfirmNewPassword;

    @BindView(R.id.btn_save)
    Button btnSave;

    @BindView(R.id.loading_progress)
    ProgressBar mloading;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    // variables
    SessionManager mSessionManager;
    LanguageSessionManager languageSeassionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password_fragment, container, false);
        ButterKnife.bind(this, view);
        initVisibility();
        initSession();
        return view;
    } // onCreateView

    private void initSession() {
        mSessionManager = new SessionManager(getContext());
        languageSeassionManager = new LanguageSessionManager(getContext());
    } // initSession method

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.change_password));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy



    @OnClick(R.id.btn_save)
    void tv_change_password() {
        if (etNewPassword.getText().toString().length() > 0 &&
                etConfirmNewPassword.getText().toString().length() > 0 &&
                etOldPassword.getText().toString().length() > 0) {
            if (etNewPassword.getText().toString().equals(etConfirmNewPassword.getText().toString())) {
                changePassword();
            } else {
                Snackbar.make(coordinatorLayout, getString(R.string.ConfirmPasswordShouldBeSame), Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(coordinatorLayout, getString(R.string.FillAllFields), Snackbar.LENGTH_LONG).show();
        }
    } // click on change password

    private void changePassword() {

        mloading.setVisibility(View.VISIBLE);
        FloraApiCall.getCallingAPIInterface().changePassword(
                languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                mSessionManager.getUserCode(),
                etOldPassword.getText().toString(),
                etNewPassword.getText().toString(), new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, retrofit.client.Response response) {
                        Log.i(FloraConstant.TAG, "change password success");

                        mloading.setVisibility(View.GONE);
                        if (outResponse != null) {
                            Log.d("body", "not null");
                            if (outResponse.getCustomers() != null) {
                                Log.d("body", "not null 1");
                                if (outResponse.getCustomers().size() > 0) {
                                    Log.d("body", "not null 2");
                                    Snackbar.make(getView(), getString(R.string.PassworduUpdated), Snackbar.LENGTH_LONG).show();
                                    try {

                                        Toast.makeText(getContext(), getString(R.string.PassworduUpdated), Toast.LENGTH_LONG).show();
                                    } catch (Exception e) {
                                    }
                                    getFragmentManager().popBackStack();
                                }
                            }
                        } else {
                            Log.d("body", "null");
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, coordinatorLayout);
                        mloading.setVisibility(View.GONE);
                        Log.d("RetrofitError", "failure");
                    }
                });
    } // change password from api

}
