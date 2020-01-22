package app.flora.Global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    private static final String PREF_NAME = "com.camouflage.pref";
    private static final String IS_LOGGED = "isLogged";
    private static final String UserCode = "UserCode";
    private static final String GuestUserId = "GuestUserId";
    private static final String UserAddress = "UserAddress";
    private static final String UserEmail = "UserEmail";
    private static final String UserName = "UserName";
    private static final String UserPassword = "UserPassword";
    private static final String UserMobile = "UserMobile";
    private static final String UserCountryId = "UserCountryId";
    private static  String UserCurrencyCode = "UserCurrencyCode";
    private static String Token = "Token";
    private static String CountryId = "CountryId";
    private static String CountryName = "CountryName";
    private static String RecentlyViewArrayList = "RecentlyViewArrayList";
    private static String IsFirstTime = "IsFirstTime";
    private static String IsNotificationOn = "IsNotificationOn";
    private static String IsVendor = "IsVendor";
    private static String IsRewordPoint = "IsRewordPoint";
    static SharedPreferences pref;
    public static SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;


    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
//---------------------------------------------------------------------------------------------

    public static void setCountryName(String countryName){
        editor.putString(CountryName, countryName);
        editor.commit();
        Log.i(FloraConstant.TAG, "setCountryName inShared : " + countryName);
    }

    public  static String geCountryName(){
        Log.i(FloraConstant.TAG, "geCountryName inShared : " + CountryName);
        return  pref.getString(CountryName,"");
    }
//---------------------------------------------------------------------------------------------

    public static void setCountryId(String countryId){
        editor.putString(CountryId, countryId);
        editor.commit();
        Log.i(FloraConstant.TAG, "setCountryId inShared : " + countryId);
    }

    public  static String getCountryId(){
        Log.i(FloraConstant.TAG, "getCountryId inShared : " + CountryId);
        return  pref.getString(CountryId,"");
    }

//---------------------------------------------------------------------------------------------

    public static void setToken(String token){
        editor.putString(Token, token);
        editor.commit();
        Log.i(FloraConstant.TAG, "setToken inShared : " + token);
    }

//---------------------------------------------------------------------------------------------

    public  static String getToken(){
        Log.i(FloraConstant.TAG, "getToken inShared : " + Token);
        return  pref.getString(Token,"");
    }
//---------------------------------------------------------------------------------------------

    public void setRecentlyViewArrayList(String token){
        editor.putString(RecentlyViewArrayList, token);
        editor.commit();
    }
    public String getRecentlyViewArrayList(){
        return  pref.getString(RecentlyViewArrayList,"");
    }

    public static void LoginSeassion(){
        editor.putBoolean(IS_LOGGED,true);
        editor.commit();
    }
    public static boolean isLoggedin(){
        return  pref.getBoolean(IS_LOGGED,false);
    }

    public static void vendor(){
        editor.putBoolean(IsVendor,true);
        editor.commit();
    }

    public static void customer(){
        editor.putBoolean(IsVendor,false);
        editor.commit();
    }

    public boolean isVendor(){
        return  pref.getBoolean(IsVendor,false);
    }

    public void FirstTime(){
        editor.putBoolean(IsFirstTime,false);
        editor.commit();
    }
    public boolean isFirstTime(){
        return  pref.getBoolean(IsFirstTime,true);
    }

    public void changeNotification(boolean status){
        editor.putBoolean(IsNotificationOn,status);
        editor.commit();
    }
    public boolean isNotificationOn(){
        return  pref.getBoolean(IsNotificationOn,true);
    }

    public static void logout(){
        editor.putBoolean(IS_LOGGED,false);
        editor.commit();
    }

    public static void setUserCode(String code){
        editor.putString(UserCode,code);
        editor.commit();
    }
    public static String getUserCode(){
        return  pref.getString(UserCode,"0");
    }

    public static void setGuestUserId(String code){
        editor.putString(GuestUserId,code);
        editor.commit();
    }
    public static String getGuestUserId(){
        return  pref.getString(GuestUserId,"0");
    }

    public void setUserAddress(String address){
        editor.putString(UserAddress,address);
        editor.commit();
    }
    public String getUserAddress(){
        return  pref.getString(UserAddress,"");
    }


    public void setUserName(String name){
        editor.putString(UserName,name);
        editor.commit();
    }

    public String getUserName(){
        return  pref.getString(UserName,"");
    }

    public String getUserPassword(){
        return  pref.getString(UserPassword,"");
    }


    public void setUserPassword(String password){
        editor.putString(UserPassword,password);
        editor.commit();
    }

    public static String getUserCurrencyCode(){
        Log.i(FloraConstant.TAG, "getUserCurrencyCode inShared : " + UserCurrencyCode);
        return  pref.getString(UserCurrencyCode,"");
    }


    public  static void setUserCurrencyCode(String userCurrencyId){
        editor.putString(UserCurrencyCode, userCurrencyId);
        editor.commit();
        Log.i(FloraConstant.TAG, "setUserCurrencyCode inShared : " + userCurrencyId);
    }



    public String getUserMobile(){
        return  pref.getString(UserMobile,"");
    }


    public void setUserMobile(String mob){
        editor.putString(UserMobile,mob);
        editor.commit();
    }


    public void setUserEmail(String email){
        editor.putString(UserEmail,email);
        editor.commit();
    }
    public String getUserEmail(){
        return  pref.getString(UserEmail,"");
    }

    public void setUserCountryId(String id){
        editor.putString(UserCountryId,id);
        editor.commit();
    }
    public String getUserCountryId(){
        return  pref.getString(UserCountryId,"");
    }


    public void setIsRewordPointd(boolean f){
        editor.putBoolean(IsRewordPoint,f);
        editor.commit();
    }

    public boolean isRewordPoint(){
        return  pref.getBoolean(IsRewordPoint,false);
    }

}
