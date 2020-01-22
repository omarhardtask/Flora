package app.flora.Ui.Fragments;

import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
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


public class ForgetPassword extends Fragment {

    @BindView(R.id.et_email)
    MaterialEditText et_email;

    @BindView(R.id.btn_reset_password)
    Button btn_reset_password;

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forget_password_fragment, container, false);
        ButterKnife.bind(this, view);
        initVisibility();
        return view;
    }

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText("");
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_back.setVisibility(View.VISIBLE);
    } // initialize visibiliy


    @OnClick(R.id.btn_reset_password)
    public void resetPasswordClick() {

        if (et_email.getText().toString().length() > 0) {

            loading.setVisibility(View.VISIBLE);
            FixControl.DisableLayout(coordinatorLayout);

            FloraApiCall.getCallingAPIInterface().forgetPassword(
                    FloraConstant.AuthorizationToken,
                    et_email.getText().toString().trim(),
                    new Callback<Response>() {
                        @Override
                        public void success(Response s, Response response)
                        {
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

                                if (outResponse.contains("success")) {
                                    Toast.makeText(getActivity(),   getString(R.string.password_sent), Toast.LENGTH_SHORT).show();
                                    Log.i(FloraConstant.TAG, "forget password success" + outResponse);
                                    getFragmentManager().popBackStack();
                                }
                            }
                            loading.setVisibility(View.INVISIBLE);
                            FixControl.EnableLayout(coordinatorLayout);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            loading.setVisibility(View.INVISIBLE);
                            Log.i(FloraConstant.TAG, "forget password failure" + error.getMessage());
                            FixControl.showErrorMessage(error, coordinatorLayout);
                            FixControl.EnableLayout(coordinatorLayout);
                        }
                    });
        } else {
            Snackbar.make(coordinatorLayout, getString(R.string.FillAllFields), Snackbar.LENGTH_LONG).show();
        }

    }

}
