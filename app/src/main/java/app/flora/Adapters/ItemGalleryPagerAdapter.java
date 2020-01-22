package app.flora.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.polites.android.GestureImageView;

import app.flora.R;
import app.flora.Ui.Activities.MainActivity;


public class ItemGalleryPagerAdapter extends PagerAdapter {

    // vars

    private Activity activity;
    private String[] mGetProductGalleryClass;
    private static LayoutInflater inflater = null;
    DisplayImageOptions options;
    String type = "";
    boolean isPortrait = true;

    public ItemGalleryPagerAdapter(Activity a, String[] d, String type, boolean isPortrait) {
        activity = a;
        this.isPortrait = isPortrait;
        this.type = type;
        mGetProductGalleryClass = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        options = new DisplayImageOptions.Builder().cacheOnDisc(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true)
                .cacheOnDisc(true).displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    @Override
    public int getCount() {
        return mGetProductGalleryClass.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public void finishUpdate(View container) {

    }

    @Override
    public Object instantiateItem(final ViewGroup viewGroup, final int position) {
        View imageLayout = null;
        if (imageLayout == null) {
            imageLayout = inflater.inflate(R.layout.gallery_item, viewGroup, false);
        }
        FrameLayout frameLayout = imageLayout
                .findViewById(R.id.main_parent);


        frameLayout.setBackgroundColor(Color.BLACK);
        if (type.equalsIgnoreCase("map")) {
            frameLayout.setBackgroundColor(Color.BLACK);
        }

        final GestureImageView imageView = imageLayout
                .findViewById(R.id.image);

        final ProgressBar spinner = imageLayout
                .findViewById(R.id.loading);

        MainActivity.mImageLoader.displayImage(mGetProductGalleryClass[position]
                , imageView, options, new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        spinner.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view,
                                                FailReason failReason) {
                        spinner.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String arg0, View view, Bitmap arg2) {
                        spinner.setVisibility(View.GONE);
                        if (isPortrait) {
                            imageView.setRotation(360);
                        } else {
                            imageView.setRotation(90);
                        }
                    }

                    @Override
                    public void onLoadingCancelled(String arg0, View arg1) {
                        spinner.setVisibility(View.GONE);
                    }
                });
        ((ViewPager) viewGroup).addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View container) {
    }
}
