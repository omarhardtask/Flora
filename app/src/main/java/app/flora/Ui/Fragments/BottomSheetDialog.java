package app.flora.Ui.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import app.flora.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    // bind views
    @BindView(R.id.btn_date)
    Button btn_date;

    @BindView(R.id.btn_price)
    Button btn_price;

    @BindView(R.id.btn_title)
    Button btn_title;

    @BindView(R.id.layout)
    CoordinatorLayout layout;

    // vars
    String title = "";
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        ButterKnife.bind(this, v);
        return v;
    }


    @OnClick(R.id.tv_submit)
    public void clickSubmit() {

        if (title.equals(""))
        {
            Snackbar. make(layout , getString(R.string.choose), Snackbar.LENGTH_LONG)
                     .show();
            return;
        }
        else if (title.equals("Date"))
        {
            Snackbar.make(layout , "Date Selected" , Snackbar.LENGTH_LONG).show();

        }
        else if (title.equals("Price"))
        {
            Snackbar.make(layout , "Price Selected" , Snackbar.LENGTH_LONG).show();

        }
        else if (title.equals("Title"))
        {
            Snackbar.make(layout , "Title Selected" , Snackbar.LENGTH_LONG).show();

        }

    } // clickSubmit

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.btn_date)
    public void clickDate() {
        title = "Date";
        // background color
        btn_date.setBackground(getActivity().getDrawable(R.drawable.rounded_rectangle));
        btn_price.setBackground(getActivity().getDrawable(R.drawable.rounded_grey));
        btn_title.setBackground(getActivity().getDrawable(R.drawable.rounded_grey));
        // text color
        btn_date.setTextColor(getActivity().getColor(R.color.colorPrimary));
        btn_price.setTextColor(getActivity().getColor(R.color.black));
        btn_title.setTextColor(getActivity().getColor(R.color.black));

        //  dismiss();

    } // clickDate


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.btn_price)
    public void clickPrice() {
        title = "Price";
        // background color
        btn_price.setBackground(getActivity().getDrawable(R.drawable.rounded_rectangle));
        btn_date.setBackground(getActivity().getDrawable(R.drawable.rounded_grey));
        btn_title.setBackground(getActivity().getDrawable(R.drawable.rounded_grey));
        // text color
        btn_date.setTextColor(getActivity().getColor(R.color.black));
        btn_price.setTextColor(getActivity().getColor(R.color.colorPrimary));
        btn_title.setTextColor(getActivity().getColor(R.color.black));

          // dismiss();

    } // clickPrice

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.btn_title)
    public void clickTitle() {
        title = "Title";
        // background color
        btn_title.setBackground(getActivity().getDrawable(R.drawable.rounded_rectangle));
        btn_price.setBackground(getActivity().getDrawable(R.drawable.rounded_grey));
        btn_date.setBackground(getActivity().getDrawable(R.drawable.rounded_grey));

        // text color
        btn_date.setTextColor(getActivity().getColor(R.color.black));
        btn_price.setTextColor(getActivity().getColor(R.color.black));
        btn_title.setTextColor(getActivity().getColor(R.color.colorPrimary));

        // dismiss();

    } // clickTitle

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow())
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    } // onActivityCreated

}
