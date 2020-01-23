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
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.Navigator;
import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedShopsModel;
import app.flora.R;
import app.flora.Ui.Fragments.ProductsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FeaturedShopsAdapter extends RecyclerView.Adapter<FeaturedShopsAdapter.MyViewHolder> {

    // vars

    private ArrayList<FeaturedShopsModel.VendorsBean> list;
    private Context context;

    public FeaturedShopsAdapter(Context context, ArrayList<FeaturedShopsModel.VendorsBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_shops_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        if (list.get(position).getImage().getSrc() != null) {
            try {
                Glide.with(context)
                        .load(list.get(position).getImage().getSrc())
                        .error(R.drawable.cate_noimg)
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(Target.SIZE_ORIGINAL)
                        .into(holder.img_item);
            } catch (Exception e) {
                Log.i(FloraConstant.TAG, "CategoryAdapterImage Exception : " + e.getMessage());

            }
        } else {
            holder.img_item.setImageDrawable(context.getDrawable(R.drawable.cate_noimg));

        }


        Log.i(FloraConstant.TAG, "getTitle in featre  : " + list.get(position).getLocalizedName());

        holder.tv_item.setText(list.get(position).getLocalizedName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Product", new Gson().toJson(list.get(position)));
                bundle.putString("shop_name", String.valueOf(list.get(position).getName()));
                bundle.putString("comeFrom", "FeaturedShops");
                Log.i(FloraConstant.TAG, "send comeFrom : " +
                        "ShopsAdapter");
                Log.i(FloraConstant.TAG, "send shop_name : " +
                        String.valueOf(list.get(position).getName()));
                ProductsFragment productsFragment = new ProductsFragment();
                productsFragment.setArguments(bundle);
                Navigator.loadFragment((FragmentActivity) context,
                        productsFragment, R.id.fragment_container, true, "");
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

        @BindView(R.id.img_item)
        ImageView img_item;

        @BindView(R.id.tv_item)
        TextView tv_item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
