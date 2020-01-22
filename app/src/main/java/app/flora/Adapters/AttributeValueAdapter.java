package app.flora.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

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


public class AttributeValueAdapter extends RecyclerView.Adapter<AttributeValueAdapter.ViewHolder> {

    // vars

    private ArrayList<AttributeValue> itemsData;
    FragmentActivity act;
    LanguageSessionManager languageSeassionManager;
    SessionManager sessionManager;
    boolean isr;
    boolean first;
    int index;
    public int mSelectedItem = -1;


    public AttributeValueAdapter(FragmentActivity a, ArrayList<AttributeValue> itemsData, boolean f, int i) {
        this.act = a;
        this.itemsData = itemsData;
        this.isr = f;
        this.first = true;
        this.index = i;
        languageSeassionManager = new LanguageSessionManager(act);
        sessionManager = new SessionManager(act);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AttributeValueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_value, null);
        AttributeValueAdapter.ViewHolder viewHolder = new AttributeValueAdapter.ViewHolder(itemLayoutView);
        languageSeassionManager = new LanguageSessionManager(act);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AttributeValueAdapter.ViewHolder viewHolder, final int position) {


        if (LanguageSessionManager.getLang().equalsIgnoreCase("en")) {
            viewHolder.checkBox.setTypeface(Typeface.createFromAsset(act.getAssets(), FloraConstant.
                    ENGLISH_FONT));

            viewHolder.radioButton.setTypeface(Typeface.createFromAsset(act.getAssets(), FloraConstant.
                    ENGLISH_FONT));

        }
        if (itemsData.get(position) != null) {
            if (isr) {
                viewHolder.checkBox.setVisibility(View.GONE);
                viewHolder.radioButton.setText(itemsData.get(position).getLocalized_names().get(0).getLocalizedName()
                        + " [" + itemsData.get(position).getPrice_after_adjustment() + "]");
                if (itemsData.get(position).getIs_pre_selected() && first) {
                    mSelectedItem = position;
                }
                viewHolder.radioButton.setChecked(mSelectedItem == position);
            }
            else
                {
                viewHolder.radioButton.setVisibility(View.GONE);
                viewHolder.checkBox.setText(itemsData.get(position).getLocalized_names().get(0).getLocalizedName()
                        + " [" + itemsData.get(position).getPrice_after_adjustment() + "]");
                viewHolder.checkBox.setChecked(itemsData.get(position).getIs_pre_selected());
            }

            viewHolder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (int i = 0; i < itemsData.size(); i++) {
                            if (i == position) {
                                ProductDetailsFragment.attributes.get(index).getAttribute_values().get(i)
                                        .setIs_pre_selected(true);
                            } else {
                                ProductDetailsFragment.attributes.get(index).getAttribute_values().get(i).
                                        setIs_pre_selected(false);
                            }
                        }
                    } else {
                        ProductDetailsFragment.attributes.get(index).getAttribute_values().get(position)
                                .setIs_pre_selected(false);
                    }
                }
            });

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ProductDetailsFragment.attributes.get(index).getAttribute_values()
                            .get(position).setIs_pre_selected(isChecked);
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout relative_parent;
        CheckBox checkBox;
        RadioButton radioButton;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            checkBox = itemLayoutView.findViewById(R.id.check);
            radioButton = itemLayoutView.findViewById(R.id.radio);
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

