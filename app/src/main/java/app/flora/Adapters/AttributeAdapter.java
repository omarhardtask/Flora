package app.flora.Adapters;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.Models.Attribute;
import app.flora.Models.AttributeValue;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import app.flora.Ui.Fragments.ProductDetailsFragment;


public class AttributeAdapter extends RecyclerView.Adapter<AttributeAdapter.ViewHolder> {

    // vars

    private ArrayList<Attribute> itemsData;
    FragmentActivity act;
    LanguageSessionManager languageSeassionManager;
    SessionManager sessionManager;


    public AttributeAdapter(FragmentActivity a, ArrayList<Attribute> itemsData) {
        this.act = a;
        this.itemsData = itemsData;
        languageSeassionManager = new LanguageSessionManager(act);
        sessionManager = new SessionManager(act);
    }

    @Override
    public AttributeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_attribute, null);
        AttributeAdapter.ViewHolder viewHolder = new AttributeAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AttributeAdapter.ViewHolder viewHolder, final int position) {

        if (itemsData.get(position) != null) {
            if (itemsData.get(position).getLocalized_names() != null) {
                viewHolder.tv_title.setText(itemsData.get(position).getLocalized_names().get(0).getLocalizedName() +
                        (itemsData.get(position).getIs_required() ? "*" : ""));
            }
            else
            viewHolder.tv_title.setText(itemsData.get(position).getProduct_attribute_name()
                    + (itemsData.get(position).getIs_required() ? "*" : ""));
            Collections.sort(itemsData.get(position).getAttribute_values());
            int last = -1;

            for (AttributeValue v : itemsData.get(position).getAttribute_values()) {
                if (v.getIs_pre_selected()) {
                    last = itemsData.get(position).getAttribute_values().lastIndexOf(v);
                }
            }

            for (int i = 0; i < itemsData.get(position).getAttribute_values().size(); i++) {
                if (last != i) {
                    itemsData.get(position).getAttribute_values().get(i).setIs_pre_selected(false);
                }
            }
            viewHolder.value_rv.setLayoutManager(new LinearLayoutManager(act));


            if (itemsData.get(position).getAttribute_control_type_name().equals("RadioList")) {
                Log.i(FloraConstant.TAG, "RadioList : " +  itemsData.get(position).getAttribute_values().size());
                viewHolder.value_sp.setVisibility(View.GONE);
                AttributeValueAdapter valueAdapter = new AttributeValueAdapter(act, (ArrayList<AttributeValue>)
                        itemsData.get(position).getAttribute_values(), true, position);
                viewHolder.value_rv.setAdapter(valueAdapter);
            }

         else if (itemsData.get(position).getAttribute_control_type_name().equals("Datepicker")) {
                viewHolder.value_sp.setVisibility(View.GONE);
                ArrayList<AttributeValue> textBoxValues = new ArrayList<>();
                AttributeValue textBox = new AttributeValue();
                textBox.setIs_pre_selected(false);
                textBoxValues.add(textBox);
                ProductDetailsFragment.attributes.get(position).setAttribute_values(textBoxValues);
                viewHolder.tv_title.setVisibility(View.VISIBLE);
                viewHolder.tv_title.setFocusable(false);


                viewHolder.tv_title.setBackground(act.getDrawable(R.drawable.border_text));
                viewHolder.tv_title.setText(R.string.select_date);

                viewHolder.tv_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                int s = monthOfYear + 1;
                                String a = parseDateToddMMyyyy(dayOfMonth + "/" + s + "/" + year);
                                viewHolder.tv_title.setText("" + a);
                                ProductDetailsFragment.attributes.get(position)
                                        .getAttribute_values().get(0).setIs_pre_selected(true);
                                ProductDetailsFragment.attributes.get(position).getAttribute_values().get(0).setName(a);
                            }
                        };
                        final Calendar c = Calendar.getInstance();
                        DatePickerDialog d = new DatePickerDialog(act,
                                dpd, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                        d.getDatePicker().setMinDate(System.currentTimeMillis());
                        d.show();
                    }
                });

            }

            else if (itemsData.get(position).getAttribute_control_type_name().equals("DropdownList")) {

                Log.i(FloraConstant.TAG, "DropdownList : " +  itemsData.get(position).getAttribute_values().size());
                viewHolder.value_rv.setVisibility(View.GONE);
                List<String> spinnerArray = new ArrayList<>();
                if (!itemsData.get(position).getIs_required()) {
                    spinnerArray.add("---");
                }
                for (AttributeValue v : itemsData.get(position).getAttribute_values()) {
                    spinnerArray.add(v.getLocalized_names().get(0).getLocalizedName()
                            + " [" + v.getPrice_after_adjustment() + "]");
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (act, android.R.layout.simple_spinner_item,
                                spinnerArray); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                viewHolder.value_sp.setAdapter(spinnerArrayAdapter);
                viewHolder.value_sp.setSelection(last);
                viewHolder.value_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {

                        if (!itemsData.get(position).getIs_required()) {
                            position1--;
                        }

                        for (int i = 0; i < itemsData.get(position).getAttribute_values().size(); i++) {
                            if (i == position1) {
                                ProductDetailsFragment.attributes.get(position).getAttribute_values().
                                        get(i).setIs_pre_selected(true);
                            } else {
                                ProductDetailsFragment.attributes.get(position).getAttribute_values().get(i).
                                        setIs_pre_selected(false);
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            else if (itemsData.get(position).getAttribute_control_type_name().equals("ColorSquares")) {
                viewHolder.value_sp.setVisibility(View.GONE);
                AttributeColorValueAdapter valueAdapter = new AttributeColorValueAdapter(act,
                        (ArrayList<AttributeValue>) itemsData.get(position).getAttribute_values(),
                        false, position);
                viewHolder.value_rv.setLayoutManager(new LinearLayoutManager(act, RecyclerView.HORIZONTAL, false));
                viewHolder.value_rv.setAdapter(valueAdapter);
            //    viewHolder.tv_title.setText(act.getString(R.string.color));
            } else {
                viewHolder.value_sp.setVisibility(View.GONE);
                AttributeValueAdapter valueAdapter = new AttributeValueAdapter(act,
                        (ArrayList<AttributeValue>) itemsData.get(position).getAttribute_values(), false, position);
                viewHolder.value_rv.setAdapter(valueAdapter);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout relative_parent;
        TextView tv_title;
        RecyclerView value_rv;
        Spinner value_sp;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tv_title = itemLayoutView.findViewById(R.id.title);
            value_rv = itemLayoutView.findViewById(R.id.value_rv1);
            value_sp = itemLayoutView.findViewById(R.id.value_sp);
            relative_parent = itemLayoutView.findViewById(R.id.relative_parent);
            MainActivity.setTextFonts(relative_parent);
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "d/M/yyyy";
        String outputPattern = "EEEE, MMMM dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern,new Locale("en"));

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}

