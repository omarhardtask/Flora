package app.flora.Ui.Fragments;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.Models.Topic;
import app.flora.Models.Topics;
import app.flora.Network.RetrofitClient;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebViewFragment extends Fragment {

    // define ids

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.tv_done)
    TextView tvDone;

    @BindView(R.id.loading)
    ProgressBar loading;

    // variables
    boolean loadingFinished = true, redirect = false;
    String id = "";
    private ArrayList<Topic> getAboutUsArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "WebViewFragment Called");
        initVisibility();
        getSavedId();
        initWebView();
        return view;

    }

    private void getSavedId() {
        id = getArguments().getString("id");
        Log.i(FloraConstant.TAG, "webview id" + id);
    } // get saved id

    private void initVisibility() {
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
      //  ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.About));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);

        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

    @SuppressLint({"SetJavaScriptEnabled", "ResourceType"})
    private void initWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((MainActivity) getActivity()).toolbar.setBackgroundColor(getContext().getColor(R.color.white));
        }
        // ((mainActivity) getActivity()).title.setText(getString(R.string.terms));
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebChromeClient(new WebChromeClient());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 20);
        webView.setLayoutParams(layoutParams);
        getTerms();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                if (!loadingFinished) {
                    redirect = true;
                }
                loadingFinished = false;
                view.loadUrl(urlNewString);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                loadingFinished = false;
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
                loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!redirect) {
                    loadingFinished = true;
                    loading.setVisibility(View.INVISIBLE);
                }

                if (loadingFinished && !redirect) {
                    //HIDE LOADING IT HAS FINISHED
                    loading.setVisibility(View.INVISIBLE);
                } else {
                    redirect = false;
                }
            }
        });
    } // init webview method

    public void getTerms() {

        loading.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().fetchAboutUs(
                id
        ).enqueue(new Callback<Topics>() {
            @Override
            public void onResponse(Call<Topics> call, Response<Topics> response) {
                if (loading != null && call != null) {
                    if (response.body().getTopics() != null) {
                        getAboutUsArrayList = new ArrayList<>();
                        getAboutUsArrayList.addAll(response.body().getTopics());
                        if (getAboutUsArrayList != null) {
                            if (getAboutUsArrayList.size() > 0) {
                                setTerms();
                            }

                        } else {
                            Log.e("settingArrayList", "null");
                        }
                    }
                }
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Topics> call, Throwable t) {
                loading.setVisibility(View.GONE);
            }
        });

    } // get terms method

    private void setTerms() {
        ((MainActivity) getActivity()).title.setText(getAboutUsArrayList.get(0).getTitle());
        String mainHTMLText = "";
        mainHTMLText = getAboutUsArrayList.get(0).getBody();
        String finalHTML = "";
        if (LanguageSessionManager.getLang().equalsIgnoreCase("en")) {
            mainHTMLText = mainHTMLText.replaceAll("font-", "f_nt");
            finalHTML = "<html><head><style type='text/css'>@font-face {font-family: 'Montserrat-Regular';src: url('"
                    + FloraConstant.ENGLISH_FONT + "');}" +
                    " body {background-color: " +
                    "transparent;border: 0px;margin: 10px;padding: 0px;font-family: 'Montserrat-Regular'; font-size: 15px;width: 100%;" +
                    "line-height: 150%; color:" + "#33383f" + ";}</style></head><body dir='LTR'><div style='color: " + "#4d7dd3" + ";font-size:18px;text-align: center;font-weight: bold;'>" + "</div>" +
                    mainHTMLText + "<br /><br /><style type='text/css'> body {width:95%;} iframe { width: 100%;} img {width: 100%;} table {width: 100%;} td {word-wrap: break-word;}</style></body></html>";

        } else {
            finalHTML = "<html><head><style type='text/css'>@font-face {font-family: 'DroidSansArabic';src: url('"
                    + FloraConstant.ARABIC_FONT + "');}" +
                    " body {background-color: " +
                    "transparent;border: 0px;margin: 10px;padding: 0px;font-family: 'DroidSansArabic'; font-size: 15px;width: 100%;" +
                    "line-height: 150%; color:" + "#33383f" + ";}</style></head><body dir='RTL'><div style='color: " + "#4d7dd3" + ";font-size:18px;text-align: center;'>" + "</div>" +
                    mainHTMLText + "<br /><br /><style type='text/css'> body {width:95%;} iframe { width: 100%;} img {width: 100%;} table {width: 100%;} td {word-wrap: break-word;}</style></body></html>";
        }

        Log.d("finalHTML", "" + finalHTML);
        webView.setBackgroundColor(0);
        //webView.setBackgroundColor(Color.parseColor("#ffffff"));
        webView.loadDataWithBaseURL("file:///android_asset/", finalHTML, "text/html", "UTF-8", null);
    } // set terms method
}
