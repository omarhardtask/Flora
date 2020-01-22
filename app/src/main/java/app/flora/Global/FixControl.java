package app.flora.Global;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

public class FixControl {

    private static int PERMISSION_CODE = 23;


//===============================================================================

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            listItem.setLayoutParams(new android.widget.AbsListView.LayoutParams(0,0));
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    } // setListViewHeightBasedOnChildren

//===============================================================================

    public static void hideKeybord(View view, Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    } // hide keyboard

//===============================================================================
    public static void DisableLayout(ViewGroup layout) {
        layout.setEnabled(false);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                DisableLayout((ViewGroup) child);
            } else {
                child.setEnabled(false);
                child.setClickable(false);
            }
        }
    } // DisableLayout

//===============================================================================

    public static void EnableLayout(ViewGroup layout) {
        layout.setEnabled(true);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                EnableLayout((ViewGroup) child);
            } else {
                child.setEnabled(true);
                child.setClickable(true);
            }
        }
    } //EnableLayout

//===============================================================================

    public static void showErrorMessage(RetrofitError error, View mainLayout) {

        Log.d("fahim", "showErrorMessage");

        Log.d("fahim", "showErrorMessage===" + error);

        if (error.getResponse() != null) {

            Response response = error.getResponse();

            if (response.getBody() != null) {

                Log.d("fahim", "error001==" + response.getBody());

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

                Log.d("fahim", "error002==" + outResponse);

                if (outResponse != null) {

                    Log.d("fahim", "error003");

                    JSONObject jsonObject = null;

                    try {

                        jsonObject = new JSONObject(outResponse);

                        if (jsonObject.has("errors")) {

                            Log.d("fahim", "error004");

                            outResponse = jsonObject.getString("errors").replaceAll("\"", "");

                            if (outResponse.split(",").length > 0) {

                                if (outResponse.split(",")[0].split(":").length > 1) {

                                    outResponse = outResponse.split(",")[0].split(":")[1].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\}", "");

                                    Log.d("outResponse", "3 " + outResponse);

                                    Snackbar.make(mainLayout, outResponse, Snackbar.LENGTH_LONG).show();

                                }

                            }

                        }

                        Log.d("fahim", "error005==" + outResponse);

                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                }

                Log.d("fahim", "error006==" + outResponse);
            }

        }

    } // show error message

//===============================================================================

    public final static  boolean isValidEmail(String email){
        if(email==null){
            return false;
        }
        else {
            return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
            //  return Pattern.compile("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,64}$").matcher(email).matches();
        }
    } // validation on email

//===============================================================================

    @SuppressLint("NewApi")
    public static int[] getScreenWidthAndHeight(Context context) {
        int columnWidth;
        int columnHeigh;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        try {
            if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
                display.getSize(point);
            } else {
                point.x = display.getWidth();
                point.y = display.getHeight();
            }

        } catch (NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        columnHeigh = point.y;
        return new int[]{columnWidth, columnHeigh};
    } // get screen size

//===============================================================================

    public static String round2 (String price){
        price = price.replace(".",",");
        Log.e("price",""+price);
        String[] pArray = price.split(",");
        Log.e("pArray",""+pArray.length);
        Log.e("pArray 0",""+pArray[0]);
        Log.e("pArray [1]",""+pArray[1]);
        if(pArray[1].length()==1){
            pArray[1] = pArray[1]+"00";
            Log.e("pArray 1",""+pArray[1]);
        }
        else if(pArray[1].length()==2){
            pArray[1] = pArray[1]+"0";
            Log.e("pArray 2",""+pArray[1]);
        }
        else if(pArray[1].length()==3){
            pArray[1] = pArray[1];
            Log.e("pArray 3",""+pArray[1]);
        }
        else{
            pArray[1] = (pArray[1].charAt(0)+"" + pArray[1].charAt(1)+"" + pArray[1].charAt(2))+"";
            Log.e("pArray 3>",""+pArray[1]);
        }
        Log.e("pArray 1 final",""+pArray[1]);
        double d = Double.parseDouble(pArray[0]+"."+pArray[1]);
        Log.e("pArray d", "" + pArray[0]+"."+pArray[1]);
        return pArray[0]+"."+pArray[1]+"";
    } // round method

//===============================================================================

    public static String convertDateToString1(String date) {

        String date1 = "";

        SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        dateFormatter1.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH);

        int index = date.lastIndexOf('/');

        try {

            date1 = dateFormatter2.format(dateFormatter1.parse(date.substring(index + 1)));

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return date1;

    } // convert date to string

//===============================================================================

    public static void requestWriteExternalStoragePermission(FragmentActivity act) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(act, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        }
        ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
    } // Requesting permission

//===============================================================================

    public static boolean isWriteExternalStorageAllowed(FragmentActivity act) {
        int result = ContextCompat.checkSelfPermission(act, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    } // check the permission status

//===============================================================================

    public static String convertDateToString(String date) {

        String date1 = "";

        SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        dateFormatter1.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa", Locale.ENGLISH);

        int index = date.lastIndexOf('/');

        try {

            date1 = dateFormatter2.format(dateFormatter1.parse(date.substring(index + 1)));

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return date1;

    } // convert the date to string

//===============================================================================

}
