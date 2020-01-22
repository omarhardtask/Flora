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
import com.google.gson.Gson;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.Navigator;
import app.flora.Models.CategoriesModel;
import app.flora.Models.Category;
import app.flora.R;
import app.flora.Ui.Fragments.ShopsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopsCategoriesAdapter extends RecyclerView.Adapter<ShopsCategoriesAdapter.MyViewHolder> {

    // vars

    private ArrayList<Category> list;
    private Context context;

    public ShopsCategoriesAdapter(Context context, ArrayList<Category> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shops_categories_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (list.get(position).getPhoto() != null) {
            try {
                Glide.with(context)
                        .load(list.get(position).getPhoto())
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

        holder.tv_shop.setText(list.get(position).getLocalizedName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Category", new Gson().toJson(list.get(position)));
                bundle.putString("cat_id", String.valueOf(list.get(position).getId()));
                bundle.putString("cat_name", String.valueOf(list.get(position).getName()));
                Log.i(FloraConstant.TAG, "send category_id : " +
                        String.valueOf(list.get(position).getId()));
                ShopsFragment shopsFragment = new ShopsFragment();
                shopsFragment.setArguments(bundle);
                Navigator.loadFragment((FragmentActivity) context,
                        shopsFragment, R.id.fragment_container, true, "");
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

        @BindView(R.id.tv_shop)
        TextView tv_shop;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
