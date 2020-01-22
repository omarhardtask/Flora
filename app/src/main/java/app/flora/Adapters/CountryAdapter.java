package app.flora.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.Models.Country;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;

public class CountryAdapter extends BaseAdapter {

    protected static final String TAG = CountryAdapter.class.getSimpleName();
    private LayoutInflater inflater = null;
    FragmentActivity act;
    ArrayList<Country> data;
    LanguageSessionManager languageSeassionManager;
    SessionManager sessionManager;

    public CountryAdapter(FragmentActivity a, ArrayList<Country> list) {
        this.act = a;
        this.data = list;
        languageSeassionManager = new LanguageSessionManager(act);
        sessionManager = new SessionManager(act);
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        try {
            final CountryAdapter.ViewHolder viewHolder;
            if (convertView == null) {

                convertView = inflater.inflate(R.layout.multiple_select_list, null);

                viewHolder = new CountryAdapter.ViewHolder();
                viewHolder.text1 = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_order_id = (TextView) convertView.findViewById(R.id.tv_order_id);
                viewHolder.img_check = (ImageView) convertView.findViewById(R.id.img_check);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (CountryAdapter.ViewHolder) convertView.getTag();
            }

            if (getItem(position) != null) {

                if (data.get(position).getName().equalsIgnoreCase(act.getString(R.string.DoneLabel)) ||
                        data.get(position).getName().equalsIgnoreCase(act.getString(R.string.DoneLabel))) {
                    viewHolder.text1.setTextColor(Color.RED);
                    viewHolder.text1.setTypeface(MainActivity.typeface, Typeface.BOLD);
                    viewHolder.text1.setTextSize(16);
                    viewHolder.text1.setGravity(Gravity.CENTER);
                    viewHolder.text1.setText(data.get(position).getLocalizedNames().get(0).getLocalizedName());

                } else {

                    viewHolder.text1.setGravity(Gravity.CENTER_VERTICAL);
                    viewHolder.text1.setTextSize(12);
                    viewHolder.text1.setTextColor(Color.parseColor("#777777"));
                    viewHolder.text1.setTypeface(MainActivity.typeface);
                    viewHolder.text1.setText(data.get(position).getLocalizedNames().get(0).getLocalizedName());
                   // viewHolder.text1.setText(data.get(position).getName());

                }

                viewHolder.img_check.setVisibility(View.VISIBLE);
                viewHolder.tv_order_id.setTypeface(MainActivity.typeface);
                viewHolder.text1.setTextColor(Color.parseColor("#242b3a"));
                viewHolder.tv_order_id.setVisibility(View.GONE);
                Log.i(FloraConstant.TAG, "countryLocalized 1 : " + data.get(position).getName());
//                Log.i(FloraConstant.TAG, "countryLocalized 2 : " +
//                        data.get(0).getLocalizedNames().get(0).getLocalizedName());
                Log.i(FloraConstant.TAG, "countryLocalized getLang : " + languageSeassionManager.getLang());

                if (languageSeassionManager.getLang().equalsIgnoreCase("en")) {
                    viewHolder.text1.setText(data.get(position).getLocalizedNames().get(0).getLocalizedName());
                   // viewHolder.text1.setText(data.get(position).getName());
                } else {
                    viewHolder.text1.setText(data.get(position).getLocalizedNames().get(0).getLocalizedName());
                   // viewHolder.text1.setText(data.get(position).getName());
                }
            }
            return convertView;
//        } catch (Exception e) {
//            Log.e(TAG + " ", e.getMessage());
//            return null;
//        }
    }

    class ViewHolder {
        TextView text1, tv_order_id;
        ImageView img_check;
    }
}