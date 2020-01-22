package app.flora.Adapters;

import android.content.Context;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.willy.ratingbar.RotationRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;

import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Models.CategoriesModel;
import app.flora.Models.Reviews;
import app.flora.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    // vars

    private ArrayList<Reviews.ProductReviewsBean> list;
    private Context context;

    public ReviewsAdapter(Context context, ArrayList<Reviews.ProductReviewsBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reviews_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_title.setText(list.get(position).getCustomer().getFirst_name());
        holder.tv_desc.setText(list.get(position).getReview_text());
        holder.tv_date.setText(FixControl.convertDateToString(list.get(position).getCreated_on_utc()));
        holder.rating_bar.setRating(list.get(position).getRating());


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

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_desc)
        TextView tv_desc;

        @BindView(R.id.tv_date)
        TextView tv_date;

        @BindView(R.id.rating_bar)
        RotationRatingBar rating_bar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
