package app.flora.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import app.flora.Global.FloraConstant;
import app.flora.Models.SliderModel;
import app.flora.R;


public class BigSliderAdapter extends SliderViewAdapter<BigSliderAdapter.SliderAdapterVH> {

    Context context;
    List<SliderModel.SlidersBean> sliders = new ArrayList<>();

    public BigSliderAdapter(Context context, List<SliderModel.SlidersBean> sliders) {
        this.context = context;
        this.sliders = sliders;
    }

    @Override
    public BigSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_big_layout_item, null);
        return new BigSliderAdapter.SliderAdapterVH(inflate);
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
