package app.flora.Adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.Models.AttributeValue;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import app.flora.Ui.Fragments.ProductDetailsFragment;


public class AttributeColorValueAdapter extends RecyclerView.Adapter<AttributeColorValueAdapter.ViewHolder> {

    // vars
    private ArrayList<AttributeValue> itemsData;
    FragmentActivity act;
    LanguageSessionManager languageSeassionManager;
    SessionManager sessionManager;
    boolean isr;
    boolean first;
    int index;
    public int mSelectedItem = -1;
    ColorStateList csl;
    String color = "";
    int selectedPosition = -1;

    public AttributeColorValueAdapter(FragmentActivity a, ArrayList<AttributeValue> itemsData, boolean f, int i) {
        this.act = a;
        this.itemsData = itemsData;
        this.isr = f;
        this.first = true;
        this.index = i;
        languageSeassionManager = new LanguageSessionManager(act);
        sessionManager = new SessionManager(act);
    }

    @Override
    public AttributeColorValueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_value_color, null);
        AttributeColorValueAdapter.ViewHolder viewHolder = new AttributeColorValueAdapter.ViewHolder(itemLayoutView);
        languageSeassionManager = new LanguageSessionManager(act);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


        int imgH = ((BitmapDrawable) act.getResources().getDrawable(
                R.drawable.box)).getBitmap().getHeight();

        int imgW = ((BitmapDrawable) act.getResources().getDrawable(
                R.drawable.box)).getBitmap().getHeight();

        viewHolder.checkBox.getLayoutParams().height = imgH;
        viewHolder.checkBox.getLayoutParams().width = imgW;

        if (languageSeassionManager.getLang().equalsIgnoreCase("en")) {
            viewHolder.checkBox.setTypeface(Typeface.createFromAsset(act.getAssets(), FloraConstant.
                    ENGLISH_BOLD));
            viewHolder.radioButton.setTypeface(Typeface.createFromAsset(act.getAssets(), FloraConstant.
                    ENGLISH_BOLD));
        }

        if (itemsData.get(position) != null) {
            if (isr) {
                viewHolder.checkBox.setVisibility(View.GONE);
                viewHolder.radioButton.setText(itemsData.get(position)
                        .getLocalized_names().get(0).getLocalizedName() + " ");

                if (itemsData.get(position).getIs_pre_selected() && first) {
                    mSelectedItem = position;
                }
                viewHolder.radioButton.setChecked(mSelectedItem == position);
            } else {
                Log.i(FloraConstant.TAG, "itemsData" + itemsData.size());
                for (int i = 0; i < itemsData.size(); i++) {
                    Log.i(FloraConstant.TAG, "color" + itemsData.get(position).getColor_squares_rgb());
                    color = itemsData.get(position).getColor_squares_rgb();
                    csl = new ColorStateList(new int[][]{new int[position]}, new int[]{Color.parseColor(color)});
                    viewHolder.checkBox.setButtonTintList(csl);
                    viewHolder.checkBox.setBackgroundColor(Color.parseColor(color));

                    if (color.equals("#ffffff")) {
                        viewHolder.checkBox.setBackground(act.getDrawable(R.drawable.rounded_black));
                        viewHolder.img.setImageDrawable(act.getDrawable(R.drawable.true_icon));
                    } else {
                    }
                }
                viewHolder.checkBox.setTag(position);
                if (position == selectedPosition) {
                    viewHolder.checkBox.setChecked(true);
                    viewHolder.img.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.checkBox.setChecked(false);
                    viewHolder.img.setVisibility(View.GONE);

                }
                viewHolder.checkBox.setOnClickListener(onStateChangedListener(viewHolder.checkBox, position));

                viewHolder.radioButton.setVisibility(View.GONE);
                viewHolder.checkBox.setChecked(itemsData.get(position).getIs_pre_selected());
            }

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (int i = 0; i < itemsData.size(); i++) {
                            if (i == position) {
                                ProductDetailsFragment.attributes.get(index).getAttribute_values()
                                        .get(i).setIs_pre_selected(true);
                            } else {
                                ProductDetailsFragment.attributes.get(index).
                                        getAttribute_values().get(i).setIs_pre_selected(false);
                            }
                        }
                    } else {
                        ProductDetailsFragment.attributes.get(index).getAttribute_values()
                                .get(position).setIs_pre_selected(false);
                    }
                }
            });
        }
    }

    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    selectedPosition = position;
                } else {
                    selectedPosition = -1;
                }

                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout relative_parent;
        CheckBox checkBox;
        RadioButton radioButton;
        ImageView img;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            checkBox = itemLayoutView.findViewById(R.id.check);
            radioButton = itemLayoutView.findViewById(R.id.radio);
            img = itemLayoutView.findViewById(R.id.img);
            relative_parent = itemLayoutView.findViewById(R.id.relative_parent);
            MainActivity.setTextFonts(relative_parent);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    first = false;
                    notifyItemRangeChanged(0, itemsData.size());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

}

