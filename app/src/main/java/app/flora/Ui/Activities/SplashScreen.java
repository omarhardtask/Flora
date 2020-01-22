package app.flora.Ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.LocaleHelper;
import app.flora.Global.SessionManager;
import app.flora.Models.Stores;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import retrofit.Callback;
import retrofit.RetrofitError;


public class SplashScreen extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        current_store();
        initSplash();
    }

    private void current_store() {

        FloraApiCall.getCallingAPIInterface().current_store(
                FloraConstant.AuthorizationToken,
                new Callback<Stores>() {
                    @Override
                    public void success(Stores outResponse, retrofit.client.Response response) {

                        Log.i(FloraConstant.TAG, "current store response in check out success :" + response.getBody());
                        Log.i(FloraConstant.TAG, "getStore_payment_methods" +
                                outResponse.getStores().get(0).getStoreCurrencies().get(0).getCurrencyCode());

                        if (outResponse.getStores() != null) {

                            if (outResponse.getStores().size() > 0) {
                                if (outResponse.getStores().get(0).getStoreCurrencies().size() > 0) {

                                    Log.i(FloraConstant.TAG, "getStore_payment_methods" +
                                            outResponse.getStores().get(0).getStoreCurrencies()
                                                    .get(0).getCurrencyCode());
                                    SessionManager.setUserCurrencyCode(
                                            outResponse.getStores().get(0).getStoreCurrencies().get(0).getCurrencyCode());

                                    Log.i(FloraConstant.TAG, "getUserCurrencyCode in splash" +
                                            SessionManager.getUserCurrencyCode());
                                }
                            }

                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(FloraConstant.TAG, "current store fail:" + error.getMessage());
                    }
                });
    } // call current store api


    private void initSplash() {


        LanguageSessionManager.getLang();
        SessionManager.getUserCurrencyCode();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.putExtra("comeFrom", "");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 3000);
    } // initialize splash

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        }
    } // disable touch on splash screen

}
