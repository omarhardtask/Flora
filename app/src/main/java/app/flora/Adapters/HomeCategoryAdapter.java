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
import app.flora.R;
import app.flora.Ui.Fragments.ProductsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder> {

    // vars

    private ArrayList<CategoriesModel.CategoriesBean> list;
    private Context context;


    public HomeCategoryAdapter(Context context, ArrayList<CategoriesModel.CategoriesBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_home_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        try {
            Glide.with(context)
                    .load(list.get(position).getImage().getSrc())
                    .placeholder(R.drawable.cate_noimg)
                    .error(R.drawable.cate_noimg)
                    .into(holder.img_cat);
        } catch (Exception e) {
            Log.i(FloraConstant.TAG, "CategoryAdapterImage Exception : " + e.getMessage());

        }

        Log.i(FloraConstant.TAG, "getTitle  : " +
                list.get(position).getLocalizedName());

        holder.tv_cat.setText(
                list.get(position).getLocalizedName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Product", new Gson().toJson(list.get(position)));
                bundle.putString("comeFrom", String.valueOf(list.get(position).isHas_sub_categories()));
                Log.i(FloraConstant.TAG , "send comeFrom : " +
                        String.valueOf(list.get(position).isHas_sub_categories()));
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

        @BindView(R.id.img_cat)
        ImageView img_cat;

        @BindView(R.id.tv_cat)
        TextView tv_cat;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
