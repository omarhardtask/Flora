package app.flora.Global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class LanguageSessionManager {

    private static final String PREF_NAME = "com.ais.pref.lang";
    public static final String KEY_Lang = "KEY_Lang";
    public static final String KEY_LangId = "KEY_LangId";
    public static final String KEY_RegID = "regId";
    public static final String KEY_NotificationStatus = "NotificationStatus";
    private static final String IS_Registered = "IsRegistered";

    static SharedPreferences pref;
    static  SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;


    public static String getRegId() {
        return pref.getString(KEY_RegID, "");
    }

    public static void setRegId(String id) {
        editor.putString(KEY_RegID, id);
        editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public LanguageSessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static String getLang() {
        return pref.getString(KEY_Lang, "en");
    }

    public static void setLang(String lang) {
        editor.putString(KEY_Lang, lang);
        editor.commit();
    }
}
