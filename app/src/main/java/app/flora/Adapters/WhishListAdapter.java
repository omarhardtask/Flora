package app.flora.Adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Models.CategoriesModel;
import app.flora.Models.FeaturedProductsModel;
import app.flora.Models.ShoppingCart;
import app.flora.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WhishListAdapter extends RecyclerView.Adapter<WhishListAdapter.MyViewHolder> {

    // vars

    private ArrayList<ShoppingCart> list = new ArrayList<>();
    private final WhishListAdapter.OnItemClickListener listener;

    private Context context;

    public WhishListAdapter(Context context, ArrayList<ShoppingCart> list
            ,  WhishListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.whishlist_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int imgH = ((BitmapDrawable) context.getResources().getDrawable(
                R.drawable.shop_product_list)).getBitmap().getHeight();

        int imgW = ((BitmapDrawable) context.getResources().getDrawable(
                R.drawable.shop_product_list)).getBitmap().getWidth();

        holder.img_item.getLayoutParams().height = imgH;
       // holder.img_item.getLayoutParams().width = imgW;

        if (list.get(position).getProduct().getPhoto() != null)
        {
            Glide.with(context)
                    .load(list.get(position).getProduct().getPhoto())
                    .placeholder(R.drawable.shop_product_list)
                    .error(R.drawable.shop_product_list)
                    .into(holder.img_item);
        }
        else
        {
            holder.img_fav.setImageDrawable(context.getDrawable(R.drawable.shop_product_list));
        }

        if (list.get(position).getProduct().getDelivery_date_id().equals("0")) {
            holder.tv_desc.setText(context.getString(R.string.same_day_delivery));

        } else if (list.get(position).getProduct().getDelivery_date_id().equals("1")) {
            holder.tv_desc.setText(context.getString(R.string.same_day_delivery));

        } else if (list.get(position).getProduct().getDelivery_date_id().equals("2")) {
            holder.tv_desc.setText(context.getString(R.string.after_2_days));

        } else if (list.get(position).getProduct().getDelivery_date_id().equals("3")) {
            holder.tv_desc.setText(context.getString(R.string.after_3_days));


        } else if (list.get(position).getProduct().getDelivery_date_id().equals("4")) {
            holder.tv_desc.setText(context.getString(R.string.after_4_days));

        }

        holder.tv_title.setText(list.get(position).getProduct().getLocalizedName());

        holder.img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteItemClick(position);
            }
        }); // delete

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        }); // click an item

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Bundle bundle = new Bundle();
////                bundle.putString("product_id", String.valueOf(list.get(position).getProductId()));
////                Log.i(hesabiConstant.TAG , "send product_id : " +
////                        String.valueOf(list.get(position).getProductId()));
////                productDetailsFragment productDetailsFragment = new productDetailsFragment();
////                productDetailsFragment.setArguments(bundle);
////                Navigator.loadFragment((FragmentActivity) context,
////                        productDetailsFragment, R.id.fragment_container, true, "services");
//                  }
//        }); // click an item

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteItemClick(int position);
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
