package app.flora.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Models.Address;
import app.flora.Models.AddressModel;
import app.flora.Models.CategoriesModel;
import app.flora.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.MyViewHolder> {

    // vars

    private ArrayList<Address> list;
    private Context context;
    private final OnItemClickListener listener;
    String comingFrom = "";

    public AddressesAdapter(Context context, ArrayList<Address> list ,  String comingFrom,   OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.comingFrom = comingFrom;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.imd_delete.setVisibility(View.GONE);
        holder.img_edit.setVisibility(View.GONE);

        if (comingFrom.equalsIgnoreCase("cart")) {

            if (list.get(position).isSelected()) {
                holder.tv_title.setTextColor(Color.parseColor("#4d7dd3"));

            }
        }
        else {
            holder.imd_delete.setVisibility(View.VISIBLE);
            holder.img_edit.setVisibility(View.VISIBLE);
        }


        holder.tv_title.setText(list.get(position).getFirstName() +
                " " + list.get(position).getLastName());

        holder.tv_city.setText(getFullAddress(list.get(position)));


        holder.imd_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemDeleteClick(position);
            }
        });

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemEditClick(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });


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
        void onItemDeleteClick(int position);
        void onItemEditClick(int position);
    }


    private String getFullAddress(Address addresses) {

        String content = "";
     //   content = addresses.getFirstName() ;
        //+ " " + addresses.getLastName();
        if (addresses.getEmail().length() > 0) {
            content = content + "" + context.getString(R.string.email) + ": " + addresses.getEmail();
        }

        if (addresses.getPhoneNumber().length() > 0) {
            content = content + "\n\n" + context.getString(R.string.mobile) + ": " + addresses.getPhoneNumber();
        }

        if (addresses.getProvince() != null)
            if (addresses.getProvince().length() > 0) {
                content = content + "\n\n" + addresses.getProvince();

                if (addresses.getCountry() != null)
                    if (addresses.getCountry().length() > 0) {
                        content = content + " , " + addresses.getCountry();
                    }
            }


        return content;

    } // get address



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_city)
        TextView tv_city;

        @BindView(R.id.img_edit)
        ImageView img_edit;

        @BindView(R.id.imd_delete)
        ImageView imd_delete;

        @BindView(R.id.item_layout)
        LinearLayout item_layout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
