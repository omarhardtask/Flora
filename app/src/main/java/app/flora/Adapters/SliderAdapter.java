package app.flora.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.flora.Global.FloraConstant;
import app.flora.Global.Navigator;
import app.flora.Models.SliderModel;
import app.flora.R;
import app.flora.Ui.Fragments.BigSliderFragment;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

     Context context;
     List<SliderModel.SlidersBean> sliders = new ArrayList<>();

    public SliderAdapter(Context context, List<SliderModel.SlidersBean> sliders) {
        this.context = context;
        this.sliders = sliders;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        try {
            Glide.with(viewHolder.itemView)
                    .load(sliders.get(position).getImage().getSrc())
                    .fitCenter()
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .placeholder(R.drawable.slider_noimg)
                    .error(R.drawable.slider_noimg)
                    .into(viewHolder.imageViewBackground);
        } catch (Exception e) {
            Log.i(FloraConstant.TAG, "Image Exc in Adapter : " + e.getLocalizedMessage());
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Slider", new Gson().toJson(sliders.get(position)));
                bundle.putString("POS", String.valueOf(position));
                bundle.putSerializable("SliderArray", (Serializable) sliders);
                Fragment bigSlider = new BigSliderFragment();
                bigSlider.setArguments(bundle);
                Navigator.loadFragment((FragmentActivity) context, bigSlider, R.id.fragment_container, true, "services");
               }
        });

    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
