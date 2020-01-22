package app.flora.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.flora.Global.FloraConstant;
import app.flora.Models.BouquetModel;
import app.flora.Models.CartModel;
import app.flora.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BouquetAdapter extends RecyclerView.Adapter<BouquetAdapter.MyViewHolder> {

    // vars

    private ArrayList<BouquetModel> list;
    private Context context;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    int clickedPos;

    public BouquetAdapter(Context context, ArrayList<BouquetModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bouquet_type_item, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.checkBox.setText(list.get(position).getTitle());
        holder.checkBox.setChecked(list.get(position).isSelected());
        holder.checkBox.setTag(position);

        holder.checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckBox cb = (CheckBox)v;
                clickedPos = (Integer) cb.getTag();

                if(cb.isChecked())
                {
                    if(lastChecked != null)
                    {
                        lastChecked.setChecked(false);
                        list.get(lastCheckedPos).setSelected(false);
                    }

                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                }
                else
                    lastChecked = null;

                list.get(clickedPos).setSelected(cb.isSelected());
                Toast.makeText(context, list.get(position).getTitle() + " " + clickedPos, Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.radio_btn)
        CheckBox checkBox;

         public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
