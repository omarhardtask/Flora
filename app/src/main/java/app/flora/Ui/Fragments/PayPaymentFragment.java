package app.flora.Ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.Order;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PayPaymentFragment extends Fragment {

    // define ids
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.loading)
    ProgressBar mloading;

    // variables
    static FragmentActivity act;
    static PayPaymentFragment fragment;
    int[] XY;
    LinearLayout mainLayout;
    SessionManager mSessionManager;
    LanguageSessionManager languageSeassionManager;
    String Id = "" , order_id = "";
    int dataSetBefore = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_payment_fragment, container, false);
        ButterKnife.bind(this, view);
        initSession();
        getOrder();
        initWebView();
        return view;
    }

    private void initWebView() {
        if (dataSetBefore == 0) {
            dataSetBefore = 1;
      //      mainActivity.setupDefaultSettings();
            ((MainActivity) getActivity()).title.setText(getString(R.string.order_details));
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebChromeClient(new WebChromeClient());
            webView.loadUrl(Id);
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Log.d("url_resp", url.toLowerCase());
                    if (url.toLowerCase().contains(act.getString(R.string.SuccessPage))) {
                        Log.d("url_resp", "contains thanks");
                        Order cOrder = new Order();
                        cOrder.setId(Integer.parseInt(order_id.replaceAll("\n", "")));
                        Fragment fragment = new OrdersDetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("Order", new Gson().toJson(cOrder));
                        bundle.putBoolean(FloraConstant.Code,true);
                        fragment.setArguments(bundle);
                        Navigator.loadFragment(act, fragment, R.id.fragment_container, false, "home");
                        return false;
                    }

                    if (url.toLowerCase().contains(act.getString(R.string.ErrorPage))) {
                        Log.d("url_resp", "contains error");
                        Snackbar.make(mainLayout, act.getString(R.string.OperationFailed), Snackbar.LENGTH_LONG).show();
                        getFragmentManager().popBackStackImmediate();
                        return false;
                    }
                    Log.d("url_resp", "contains nothing");
                    webView.loadUrl(url);
                    return true;

                }

            });

        }
    } // init webview

    private void getOrder() {
        if (getArguments() != null) {
            Id = getArguments().getString("id");
            order_id = getArguments().getString("order_id");
        }
    } // get order

    private void initSession() {
        if (act == null) {
            act = getActivity();
        }
        XY = FixControl.getScreenWidthAndHeight(act);
        mSessionManager = new SessionManager(act);
        languageSeassionManager = new LanguageSessionManager(act);
    } // init session

}
