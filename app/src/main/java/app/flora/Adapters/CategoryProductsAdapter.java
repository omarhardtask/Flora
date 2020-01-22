package app.flora.Adapters;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.ArrayList;
import app.flora.Global.FloraConstant;
import app.flora.Models.Category;
import app.flora.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryProductsAdapter extends RecyclerView.Adapter<CategoryProductsAdapter.MyViewHolder> {

    // vars

    private ArrayList<Category> list;
    private Context context;
    int pos;
    final OnItemClickListener listener;


    public void updateList(ArrayList<Category> updateList, int index) {
        pos = index;
        list.addAll(updateList);
        notifyDataSetChanged();
    }

    public CategoryProductsAdapter(Context context, ArrayList<Category> list, int pos, OnItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.pos = pos;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position == pos) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.img_cat_product.setBorderColor(context.getColor(R.color.colorPrimary));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.img_cat_product.setBorderColor(0);
            }
        }

        if (list.get(position).getImage().getSrc() != null) {
            try {
                Glide.with(context)
                        .load(list.get(position).getImage().getSrc())
                        .placeholder(R.drawable.top_cate_noimg)
                        .error(R.drawable.top_cate_noimg)
                        .into(holder.img_cat_product);
            } catch (Exception e) {
                Log.i(FloraConstant.TAG, "CategoryAdapterImage Exception : " + e.getMessage());
            }
        } else {
            holder.img_cat_product.setImageDrawable(context.getDrawable(R.drawable.top_cate_noimg));
        }

        Log.i(FloraConstant.TAG, "getTitle in CategoryProductsAdapter  : " + list.get(position).getLocalizedName());

        holder.tv_cat_product.setText(list.get(position).getLocalizedName());

        holder.img_cat_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("Product", new Gson().toJson(list.get(position)));
//                bundle.putString("product_id", String.valueOf(list.get(position).getId()));
//                Log.i(FloraConstant.TAG , "send product_id from cat prod adapter: " +
//                        String.valueOf(list.get(position).getId()));
//                ProductsFragment productsFragment = new ProductsFragment();
//                productsFragment.setArguments(bundle);
//                Navigator.loadFragment((FragmentActivity) context,
//                        productsFragment, R.id.fragment_container, true, "services");
//            }
//        }); // click an item

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_cat_product)
        CircularImageView img_cat_product;

        @BindView(R.id.tv_cat_product)
        TextView tv_cat_product;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
