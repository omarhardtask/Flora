package app.flora.Adapters;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.Order;
import app.flora.Models.OrderDelete;
import app.flora.Models.OrderItem;
import app.flora.R;
import app.flora.Ui.Fragments.ProductDetailsFragment;

import static android.content.Context.MODE_PRIVATE;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {

    private List<OrderDelete.OrdersBean.OrderItemsBean> itemsData;
    FragmentActivity act;
    LanguageSessionManager languageSeassionManager;
    SessionManager sessionManager;
    OrderDelete.OrdersBean order;
    String weight_name = "";




    public OrderDetailsAdapter(FragmentActivity a, List<OrderDelete.OrdersBean.OrderItemsBean> itemsData,
                               OrderDelete.OrdersBean order) {
        this.act = a;
        this.itemsData = itemsData;
        this.order = order;

    }

    private void getPrefrence() {
        SharedPreferences prefs = act.getSharedPreferences(FloraConstant.MY_PREFS_NAME, MODE_PRIVATE);
        if (prefs != null)
            weight_name = prefs.getString("weight_name", "");
        Log.i(FloraConstant.TAG, "get weight_name in adapter : " + weight_name);
    } // get weight saved name


    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public OrderDetailsAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_row, null);
        OrderDetailsAdapter.ViewHolder viewHolder = new OrderDetailsAdapter.ViewHolder(itemLayoutView);
        languageSeassionManager = new LanguageSessionManager(act);
        sessionManager = new SessionManager(act);
        getPrefrence();
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NotNull OrderDetailsAdapter.ViewHolder viewHolder, final int position) {

        if (itemsData.get(position) != null) {

            viewHolder.tv_title.setText(
                    itemsData.get(position).getProduct().getLocalized_names().get(0).getLocalized_name()
                    + "\n"
                    +
                    itemsData.get(position).getAttribute_description()
                            .replace("<br />",
                            "\n"));

            viewHolder.tv_attribute.setText(itemsData.get(position).getAttribute_description().replace
                    ("<br>", "\n"));

            viewHolder.tv_vendor_name.setVisibility(View.GONE);

            if (itemsData.get(position).getProduct().getVendor() != null) {

                viewHolder.tv_vendor_name.setVisibility(View.VISIBLE);

                viewHolder.tv_vendor_name.setText(itemsData.get(position).getProduct().getVendor().getName());

            }

            Log.i(FloraConstant.TAG, "getBy_weight in order adapter : " +
                    itemsData.get(position).getProduct().getWeight());

            if (itemsData.get(position).getProduct().getWeight() != null)
                if (itemsData.get(position).getProduct().getWeight().equals(true)) {
                    viewHolder.tv_quantity.setText(act.getString(R.string.Quantity) + ": " +
                            itemsData.get(position).getProduct().getWeight() + " " + weight_name);
                } else {

                    viewHolder.tv_quantity.setText(act.getString(R.string.Quantity) + ": " + itemsData.get(position).getQuantity());
                }

            Log.i(FloraConstant.TAG, "Quantity in adapter : " + itemsData.get(position).getQuantity());
            Log.i(FloraConstant.TAG, "getCustomerCurrencyCode in adapter : " + order.getCustomer_currency_code());

            viewHolder.tv_price.setText(
                    + itemsData.get(position).getUnit_price_incl_tax()  + " "
                            + order.getCustomer_currency_code());
            viewHolder.tv_sub_total.setText(act.getString(R.string.SubTotalLabel) + ": "
                    + itemsData.get(position).getPrice_excl_tax() + " " + order.getCustomer_currency_code() );

            // viewHolder.tv_barcode.setVisibility(View.GONE);

//            if (order.getOrderStatus().equalsIgnoreCase(chefConstant.OrderCompleteStatus) &&
//                    order.getPaymentStatus().equalsIgnoreCase(chefConstant.PaymentStatusPaid)) {

//                if (itemsData.get(position).getCardsList() != null) {
//
//                    String barcode = "";

//                    for (Cards cards : itemsData.get(position).getCardsList()) {
//
//                        if (barcode.length() > 0) {
//
//                            barcode = barcode + "\n\n*" + cards.getCardCouponCode() + "*";
//
//                        } else {
//                            barcode = "*" + cards.getCardCouponCode() + "*";
//                        }
//                    }

//                    if (itemsData.get(position).getCardsList().size() > 0) {
//                        viewHolder.tv_barcode.setTypeface(Typeface.createFromAsset(act.getAssets(),
//                                mySallaConstant.Barcode_Font));
//                        viewHolder.tv_barcode.setVisibility(View.VISIBLE);
//                        viewHolder.tv_barcode.setText(barcode);
//
//                    }

            // }

            //   }

            viewHolder.tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Product", new Gson().toJson(itemsData.get(position).getProduct()));
                    Fragment fragment = new ProductDetailsFragment();
                    fragment.setArguments(bundle);
                    Navigator.loadFragment(act, fragment, R.id.fragment_container, true, "home");
                }
            });

        }

    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relative_parent, relative_content;
        TextView tv_title, tv_vendor_name, tv_quantity, tv_price, tv_sub_total, tv_attribute;
        TextView tv_barcode;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            // tv_barcode = itemLayoutView.findViewById(R.id.tv_barcode);
            tv_vendor_name = itemLayoutView.findViewById(R.id.tv_vendor_name);
            tv_quantity = itemLayoutView.findViewById(R.id.tv_quantity);
            tv_price = itemLayoutView.findViewById(R.id.tv_price);
            tv_sub_total = itemLayoutView.findViewById(R.id.tv_sub_total);
            tv_title = itemLayoutView.findViewById(R.id.tv_title);
            tv_attribute = itemLayoutView.findViewById(R.id.tv_attribute);
            relative_content = itemLayoutView
                    .findViewById(R.id.relative_content);
            relative_parent = itemLayoutView
                    .findViewById(R.id.relative_parent);
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}
