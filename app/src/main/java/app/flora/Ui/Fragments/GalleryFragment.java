package app.flora.Ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Objects;

import app.flora.Adapters.ItemGalleryPagerAdapter;
import app.flora.Global.FixControl;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.SessionManager;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;


public class GalleryFragment extends Fragment {

    // vars
    protected static final String TAG = GalleryFragment.class.getSimpleName();
    static FragmentActivity act;
    static GalleryFragment fragment;
    int[] XY;
    LinearLayout mainLayout;
    SessionManager mSessionManager;
    LanguageSessionManager languageSeassionManager;
    String[] productImages;
    int position = 0;
    ViewPager mpager;
    ProgressBar mloading;
    ItemGalleryPagerAdapter mProductGalleryPagerAdapter;
    ImageView img_rotate;
    ImageView img_back;
    boolean isPortrait = true;
    String comingFrom = "";

    public static GalleryFragment newInstance(FragmentActivity act) {
        fragment = new GalleryFragment();
        GalleryFragment.act = act;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVisibility();
        if (act == null) {
            act = getActivity();
        }
        try {
            XY = FixControl.getScreenWidthAndHeight(act);
            mSessionManager = new SessionManager(act);
            languageSeassionManager = new LanguageSessionManager(act);
            if (getArguments() != null) {
                if (getArguments().containsKey("position")) {
                    comingFrom = getArguments().getString("comingFrom");
                    position = getArguments().getInt("position");
                    productImages = getArguments().getStringArray("productImages");
                }
            }
        } catch (Exception e) {
        }
    }


    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText("");
        ((MainActivity) getActivity()).img_back.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.GONE);
    } // initialize visibiliy


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            mainLayout = (LinearLayout) inflater.inflate(R.layout.gallrery_fragment, null);
            initViews(mainLayout);

            initBack();
            initRotate();
        } catch (Exception e) { }
        return mainLayout;
    }

    private void initRotate() {
        if (LanguageSessionManager.getLang().equals("ar"))
        {
            img_back.setRotation(180);
        }
    }

    private void initBack() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (act.getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    act.finish();
                } else {
                    act.onBackPressed();
                }
            }
        });
    }

    void initViews(final LinearLayout mainLayout) {
        if (mainLayout != null) {
            mpager = mainLayout.findViewById(R.id.pager);
            mloading = mainLayout.findViewById(R.id.loading);
            img_rotate = mainLayout.findViewById(R.id.img_rotate);
            img_back = mainLayout.findViewById(R.id.img_back);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        img_rotate.setVisibility(View.GONE);
        if (comingFrom.equalsIgnoreCase("map")) {
            img_rotate.setVisibility(View.VISIBLE);
        }
        mProductGalleryPagerAdapter = new ItemGalleryPagerAdapter(
                act, productImages, "map", true);
        mpager.setAdapter(mProductGalleryPagerAdapter);
        mpager.setCurrentItem(position);
        Log.e("position--", "" + position);
        img_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPortrait = !isPortrait;
                if (isPortrait) {
                    mProductGalleryPagerAdapter = new ItemGalleryPagerAdapter(
                            act, productImages, comingFrom, true);
                    mpager.setAdapter(mProductGalleryPagerAdapter);
                    mpager.setCurrentItem(position);
                } else {
                    mProductGalleryPagerAdapter = new ItemGalleryPagerAdapter(
                            act, productImages, "map", false);
                    mpager.setAdapter(mProductGalleryPagerAdapter);
                    mpager.setCurrentItem(position);
                }
            }
        });
    }
}