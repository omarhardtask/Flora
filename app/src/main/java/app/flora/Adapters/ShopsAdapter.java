package app.flora.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.willy.ratingbar.RotationRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.Navigator;
import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedShopsModel;
import app.flora.R;
import app.flora.Ui.Fragments.ProductsFragment;
import app.flora.Ui.Fragments.ShopsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.MyViewHolder> {

    // vars

    private ArrayList<FeaturedShopsModel.VendorsBean> list;
    private Context context;

    public ShopsAdapter(Context context, ArrayList<FeaturedShopsModel.VendorsBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shops_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (list.get(position).getImage().getSrc() != null) {
            try {
                Glide.with(context)
                        .load(list.get(position).getImage().getSrc())
                        .placeholder(R.drawable.shops_cate_noimg)
                        .error(R.drawable.shops_cate_noimg)
                        .into(holder.img_shop);
            } catch (Exception e) {
                Log.i(FloraConstant.TAG, "CategoryAdapterImage Exception : " + e.getMessage());

            }
        } else {
            holder.img_shop.setImageDrawable(context.getDrawable(R.drawable.shops_cate_noimg));

        }

        Log.i(FloraConstant.TAG, "getTitle  : " + list.get(position).getLocalizedName());

        holder.tv_title.setText(list.get(position).getLocalizedName());
        holder.tv_desc.setText(list.get(position).getLocalized_descriptions().get(0).getLocalized_description());
//        holder.rating_bar.setRating(Float.parseFloat(list.get(position).getRating()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Product", new Gson().toJson(list.get(position)));
                bundle.putString("shop_name", String.valueOf(list.get(position).getName()));
                bundle.putString("comeFrom", "ShopsAdapter");
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

        @BindView(R.id.img_shop)
        ImageView img_shop;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_desc)
        TextView tv_desc;

        @BindView(R.id.rating_bar)
        RotationRatingBar rating_bar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
