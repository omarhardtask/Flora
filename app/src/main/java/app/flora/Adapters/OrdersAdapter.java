package app.flora.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.Navigator;
import app.flora.Models.Order;
import app.flora.Models.OrderDelete;
import app.flora.Models.OrderModel;
import app.flora.R;
import app.flora.Ui.Fragments.OrdersDetailsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    // vars

    private ArrayList<OrderDelete.OrdersBean> list;
    private Context context;

    public OrdersAdapter(Context context, ArrayList<OrderDelete.OrdersBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_order_number.setText("#"  + String.valueOf(list.get(position).getId()));
        holder.tv_order_date.setText(  FixControl.convertDateToString( list.get(position).getCreated_on_utc()));
      // holder.tv_title.setText(list.get(position).getBilling_address().getAddress1());
        holder.tv_city.setText(list.get(position).getBilling_address().getCountry() +
                " " +list.get(position).getBilling_address().getProvince() );
        holder.tv_status.setText(list.get(position).getOrder_status());
        holder.tv_price.setText(list.get(position).getOrder_total() + list.get(position).getCustomer_currency_code());


        Log.d(FloraConstant.TAG, "getID in adapter : " +String.valueOf(list.get(position).getId()) );


        if (list.get(position).getOrder_status().equals("Complete"))
        {
            holder.tv_status.setTextColor(context.getColor(R.color.Completed));
        }

        else  if (list.get(position).getOrder_status().equals("Processing"))
        {
            holder.tv_status.setTextColor(context.getColor(R.color.Processed));
        }

        else  if (list.get(position).getOrder_status().equals("Pending"))
        {
            holder.tv_status.setTextColor(context.getColor(R.color.Pending));
        }
        else  if (list.get(position).getOrder_status().equals("Cancelled"))
        {
            holder.tv_status.setTextColor(context.getColor(R.color.Cancelled));
        }

        else
        {
            holder.tv_status.setTextColor(context.getColor(R.color.colorPrimary));

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new OrdersDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Order", new Gson().toJson(list.get(position)));
                fragment.setArguments(bundle);
                Log.i(FloraConstant.TAG, "send orderId from adapter : " + list.get(position).getId());

                Navigator.loadFragment((FragmentActivity) context, fragment, R.id.fragment_container, true, "");
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

        @BindView(R.id.tv_order_number)
        TextView tv_order_number;

        @BindView(R.id.tv_order_date)
        TextView tv_order_date;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_city)
        TextView tv_city;

        @BindView(R.id.tv_status)
        TextView tv_status;

        @BindView(R.id.tv_price)
        TextView tv_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
