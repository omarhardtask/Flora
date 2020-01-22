package app.flora.Adapters;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Models.Image;
import app.flora.R;
import app.flora.Ui.Fragments.GalleryFragment;


public class ProductImageSliderAdapter extends PagerAdapter {

    // vars

    private ArrayList<Image> IMAGES;
    private LayoutInflater inflater;
    private FragmentActivity context;
    LanguageSessionManager languageSeassionManager;

    public ProductImageSliderAdapter(FragmentActivity context, ArrayList<Image> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
        languageSeassionManager = new LanguageSessionManager(context);
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {

        View imageLayout = inflater.inflate(R.layout.slider_row, view, false);
        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.image);

        int imgH = ((BitmapDrawable) context.getResources().getDrawable(
                R.drawable.product_details_noimg)).getBitmap().getHeight();

        // imageView.getLayoutParams().height = imgH;

        if (IMAGES.get(position).getSrc().length() > 0) {
            Glide.with(context).load(IMAGES.get(position).getSrc())
                    .placeholder(R.drawable.product_details_noimg)
                    .error(R.drawable.product_details_noimg)
                    .fitCenter()
                    .into(imageView);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (IMAGES.size() > 0) {
                    String[] productImages;
                    productImages = new String[IMAGES.size()];
                    for (int i = 0; i < IMAGES.size(); i++) {
                        productImages[i] = IMAGES.get(i).getSrc();
                    }
                    Bundle b = new Bundle();
                    b.putInt("position", position);
                    b.putStringArray("productImages", productImages);
                    b.putString("type", "offers");
                    b.putString("comingFrom", "offer");
                    Fragment fragment = GalleryFragment.newInstance(context);
                    fragment.setArguments(b);
                    Navigator.loadFragment(context, fragment, R.id.fragment_container, true, "home");
                }
            }
        });
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

}
