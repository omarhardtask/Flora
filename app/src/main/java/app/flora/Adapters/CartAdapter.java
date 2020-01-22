package app.flora.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Global.SessionManager;
import app.flora.Models.Attribute;
import app.flora.Models.AttributeValue;
import app.flora.Models.CartModel;
import app.flora.Models.CategoriesModel;
import app.flora.Models.ProductAttribute;
import app.flora.Models.ShoppingCart;
import app.flora.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    // vars

    private ArrayList<ShoppingCart> list;
    private Context context;
    private final CartAdapter.OnItemClickListener listener;

    public CartAdapter(Context context, ArrayList<ShoppingCart> list
    ,CartAdapter.OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        if (list.get(position).getProduct().getPhoto() != null)
        {
            try {
                Glide.with(context)
                        .load(list.get(position).getProduct().getPhoto())
                        .placeholder(R.drawable.cart_list_noimg)
                        .error(R.drawable.cart_list_noimg)
                        .into(holder.img_item);
            } catch (Exception e) {
                Log.i(FloraConstant.TAG, "CategoryAdapterImage Exception : " + e.getMessage());

            }
        }
        else
        {
            holder.img_item.setImageDrawable(context.getDrawable(R.drawable.cart_list_noimg));
        }

    //    Log.i(FloraConstant.TAG, "getTitle  : " + list.get(position).getTitle());

        String s = "";
        for (ProductAttribute p : list.get(position).getProductAttributes()) {
            for (Attribute a : list.get(position).getProduct().getAttributes()) {
                if (p.getId() == a.getId()) {
                    if (a.getLocalized_names() != null) {
                        s += a.getLocalized_names().get(0).getLocalizedName() + ": ";
                    } else {
                        s += a.getProduct_attribute_name() + ": ";
                    }
                    for (AttributeValue av : a.getAttribute_values()) {
                     //   if (p.getValue() == av.getId()) {
                            s += av.getLocalized_names().get(0).getLocalizedName() +
                                    " [" + av.getPrice_after_adjustment() + "]\n";
                       // }
                    }
                }
            }
        }

        if (s.equals("")) {
            holder.tv_name.setText(list.get(position).getProduct().getLocalizedName()
                    + "\n" + list.get(position).getProduct().getLocalizedShortDescription());
        } else {
            holder.tv_name.setText(list.get(position).getProduct().getLocalizedName() +
                    "\n" + s
                    + "\n" + list.get(position).getProduct().getLocalizedShortDescription());

        }
        holder.tv_quantity.setText(list.get(position).getQuantity() + "");

        holder.tv_price.setText(String.valueOf(list.get(position).getFormattedSubTotal()));


        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteItemClick(position);
            }
        });

        holder.card_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddItemClick(position);
            }
        });

        holder.card_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveItemClick(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("product_id", String.valueOf(list.get(position).getProductId()));
//                Log.i(hesabiConstant.TAG , "send product_id : " +
//                        String.valueOf(list.get(position).getProductId()));
//                productDetailsFragment productDetailsFragment = new productDetailsFragment();
//                productDetailsFragment.setArguments(bundle);
//                Navigator.loadFragment((FragmentActivity) context,
//                        productDetailsFragment, R.id.fragment_container, true, "services");
            }
        }); // click an item

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteItemClick(int position);
        void onAddItemClick(int position);
        void onRemoveItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_item)
        ImageView img_item;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_price)
        TextView tv_price;

        @BindView(R.id.tv_quantity)
        TextView tv_quantity;

        @BindView(R.id.img_remove)
        ImageView img_remove;

        @BindView(R.id.card_increase)
        CardView card_increase;

        @BindView(R.id.card_decrease)
        CardView card_decrease;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
