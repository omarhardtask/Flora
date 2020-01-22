package app.flora.Global;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import app.flora.R;


public class Navigator {

    public static void loadFragment(FragmentActivity activity, Fragment baseFrafment, int containerId, boolean isStacked, String s) {
        if (!isStacked) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId, baseFrafment)
                    .commitAllowingStateLoss();
        }
        else {
            activity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(containerId, baseFrafment)
                    .addToBackStack(s)
                    .commit();
        }
    }
}
