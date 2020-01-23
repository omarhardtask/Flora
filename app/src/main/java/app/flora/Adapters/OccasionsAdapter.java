package app.flora.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.Navigator;
import app.flora.Models.CategoriesModel;
import app.flora.Models.OccasionsModel;
import app.flora.R;
import app.flora.Ui.Fragments.ProductsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OccasionsAdapter extends RecyclerView.Adapter<OccasionsAdapter.MyViewHolder> {

    // vars

    private ArrayList<OccasionsModel.ManufacturersBean> list;
    private Context context;

    public OccasionsAdapter(Context context, ArrayList<OccasionsModel.ManufacturersBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.occasions_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (list.get(position).getImage() != null)
        {
            Glide.with(context)
                    .load(list.get(position).getImage().getSrc())
                    .placeholder(R.drawable.icons_circle)
                    .error(R.drawable.icons_circle)
                    .into(holder.img_occasion);
        }
        else
            {
                holder.img_occasion.setImageDrawable(context.getDrawable(R.drawable.icons_circle));
            }


        Log.i(FloraConstant.TAG, "getTitle  : " +
                list.get(position).getName());

        holder.tv_title.setText(list.get(position).getLocalized_names().get(0).getLocalized_name());


        holder.itemView.setOnClickListener(new View .OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ManufactureId", String.valueOf(list.get(position).getId()));
                bundle.putString("comeFrom", "Occasions");
                Log.i(FloraConstant.TAG , "send ManufactureId : " +
                        String.valueOf(list.get(position).getId()));
                ProductsFragment productDetailsFragment = new ProductsFragment();
                productDetailsFragment.setArguments(bundle);
                Navigator.loadFragment((FragmentActivity) context,
                        productDetailsFragment, R.id.fragment_container, true, "services");
                  }
        }); // click an item

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_occasion)
        ImageView img_occasion;

        @BindView(R.id.tv_title)
        TextView tv_title;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
