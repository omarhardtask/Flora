package app.flora.Controller;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings;
import android.util.Log;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.LocaleHelper;
import app.flora.Global.SessionManager;
import app.flora.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppController extends Application {


    // vars

    private static AppController mInstance;
    protected static final String TAG = AppController.class.getSimpleName();
    public static LanguageSessionManager mLangSessionManager;
    public static SessionManager mSessionManager;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "en"));
    }

    public static Context getContext(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        mSessionManager = new SessionManager(this);
        mLangSessionManager = new LanguageSessionManager(this);
        super.onCreate();
        mInstance = this;
        if (mLangSessionManager.getLang().equals("en")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(FloraConstant.ENGLISH_FONT)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        else if (mLangSessionManager.getLang().equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(FloraConstant.ARABIC_FONT)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @SuppressLint("LongLogTag")
    public static synchronized AppController getInstance() {
        if (mInstance == null) {
            try {
                mInstance = AppController.class.newInstance();
            } catch (InstantiationException e) {
                Log.e(TAG
                        + " "
                        + " getInstance: InstantiationException>>LineNumber: "
                        + Thread.currentThread().getStackTrace()[2]
                        .getLineNumber(), e.getMessage());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.e(TAG
                        + " "
                        + " getInstance: IllegalAccessException>>LineNumber: "
                        + Thread.currentThread().getStackTrace()[2]
                        .getLineNumber(), e.getMessage());
                e.printStackTrace();
            }
        }
        return mInstance;
    }

    //function to get the device id
    @SuppressLint("HardwareIds")
    public String getIMEI() {
        return Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
