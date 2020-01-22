package app.flora.Adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.Navigator;
import app.flora.Models.FeaturedProductsModel;
import app.flora.Models.Product;
import app.flora.R;
import app.flora.Ui.Fragments.ProductDetailsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    // vars

    private ArrayList<Product> list;
    private Context context;

    public ProductsAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.products_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int imgH = ((BitmapDrawable) context.getResources().getDrawable(
                R.drawable.shop_product_list)).getBitmap().getHeight();

        int imgW = ((BitmapDrawable) context.getResources().getDrawable(
                R.drawable.shop_product_list)).getBitmap().getWidth();

        holder.img_item.getLayoutParams().height = imgH;
        holder.img_item.getLayoutParams().width = imgW;

        if (list.get(position).getPhoto() != null) {
            try {
                Glide.with(context)
                        .load(list.get(position).getPhoto())
                        .placeholder(R.drawable.shop_product_list)
                        .error(R.drawable.shop_product_list)
                        .into(holder.img_item);
            } catch (Exception e) {
                Log.i(FloraConstant.TAG, "CategoryAdapterImage Exception : " + e.getMessage());

            }
        }
        else
        {
            holder.img_item.setImageDrawable(context.getDrawable(R.drawable.shop_product_list));

        }

        Log.i(FloraConstant.TAG, "getTitle in featre  : " + list.get(position).getLocalizedShortDescription()
        );


        if (list.get(position).getDelivery_date_id().equals("0")) {
            holder.tv_desc.setText(context.getString(R.string.same_day_delivery));

        } else if (list.get(position).getDelivery_date_id().equals("1")) {
            holder.tv_desc.setText(context.getString(R.string.same_day_delivery));

        } else if (list.get(position).getDelivery_date_id().equals("2")) {
            holder.tv_desc.setText(context.getString(R.string.after_2_days));

        } else if (list.get(position).getDelivery_date_id().equals("3")) {
            holder.tv_desc.setText(context.getString(R.string.after_3_days));


        } else if (list.get(position).getDelivery_date_id().equals("4")) {
            holder.tv_desc.setText(context.getString(R.string.after_4_days));

        }

        holder.tv_title.setText(list.get(position).getLocalizedName());

        if (list.get(position).getAdded_to_wishlist())
        {
            holder.img_fav.setImageResource(R.drawable.heart_sel);
        }
        else
        {
            holder.img_fav.setImageResource(R.drawable.heart_unsel);

        }




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Product", new Gson().toJson(list.get(position)));
                Fragment fragment = new ProductDetailsFragment();
                fragment.setArguments(bundle);
                Navigator.loadFragment((FragmentActivity) context, fragment, R.id.fragment_container, true, "home");
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

        @BindView(R.id.img_fav)
        ImageView img_fav;

        @BindView(R.id.tv_desc)
        TextView tv_desc;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.item_layout)
        LinearLayout item_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
