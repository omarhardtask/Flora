package app.flora.Ui.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.LocaleHelper;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.GetCustomer;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import app.flora.Ui.Activities.SplashScreen;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MoreFragment extends Fragment {

    TextView user_status;
    View view;
    static FragmentActivity act;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        Log.i(FloraConstant.TAG, "MoreFragment Called");
        initVisibility();
        initAct();
        initViews();
        initStatus();
        initlogout();
        return view;
    }

    private void initlogout() {
        if (user_status.getText().equals(getActivity().getString(R.string.login))) {
            Log.i(FloraConstant.TAG, "btn_status login");


            user_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("comingFrom", "mainActivity_side_menu");
                    Fragment loginFragment = new LoginFragment();
                    loginFragment.setArguments(bundle);
                    Navigator.loadFragment(getActivity(), loginFragment, R.id.fragment_container, true, "home");
                }
            });

        } else if (user_status.getText().equals(getActivity().getString(R.string.logout))) {
            Log.i(FloraConstant.TAG, "btn_status logout");
            user_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle(getActivity().getString(R.string.save))
                            .setMessage(getActivity().getString(R.string.AreYouSureYouWantToLogoutLabel))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    createCustomerLogout(dialog);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.logo_flora)
                            .show();
                }
            });
        }
    }

    private void initAct() {

        if (act == null) {
            act = getActivity();
        } else {
            act = getActivity();
        }
    } // initAct method

    private static void createCustomerLogout(DialogInterface dialog) {

        FloraApiCall.getCallingAPIInterface().createGuestCustomer(
                LanguageSessionManager.getLang(),
                FloraConstant.AuthorizationToken,
                "application/json",
                new Callback<GetCustomer>() {
                    @Override
                    public void success(GetCustomer outResponse, retrofit.client.Response response) {
                        dialog.dismiss();
                        if (outResponse != null) {
                            Log.i(FloraConstant.TAG, "createCustomerLogout success");
                            if (outResponse.getCustomers() != null) {
                                if (outResponse.getCustomers().size() > 0) {
                                    SessionManager.setGuestUserId(outResponse.getCustomers().get(0).getId() + "");
                                    SessionManager.setUserCode("0");
                                    SessionManager.logout();
                                    act.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                    Fragment loginFragment = new LoginFragment();
                                    Navigator.loadFragment(act, loginFragment, R.id.fragment_container, false, "");
                                }
                            }
                        } else {
                            Log.i(FloraConstant.TAG, "null");
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    }
                });

    } // logout method

    private void initStatus() {
        if (!SessionManager.isLoggedin()) {
            Log.i(FloraConstant.TAG, SessionManager.getGuestUserId() + "");
            user_status.setText(getActivity().getString(R.string.login));
            // userStatus.setTitle(act.getString(R.string.login));
            Log.i(FloraConstant.TAG, "btn_status set login ");

//            if (SessionManager.getGuestUserId().equalsIgnoreCase("0")) {
//                createCustomer();
//            }
        } else {
            user_status.setText(getActivity().getString(R.string.logout));
            //userStatus.setTitle(getString(R.string.logout));
            Log.i(FloraConstant.TAG, "btn_status set logout ");
            Log.i(FloraConstant.TAG, "registerGuest else : " + SessionManager.getGuestUserId() + "");
            //initStatus();
        }
    }

    private void initViews() {
        user_status = view.findViewById(R.id.user_status);
    }

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.More));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy

//============================================================================================================

    @OnClick(R.id.rel_whishlist)
    public void initWishList() {
        WishListFragment wishListFragment = new WishListFragment();
        Navigator.loadFragment(getActivity(), wishListFragment, R.id.fragment_container, true, "");
    } // go to wish lists

//============================================================================================================

    @OnClick(R.id.rel_orders)
    public void initOrders() {
        OrdersFragment ordersFragment = new OrdersFragment();
        Navigator.loadFragment(getActivity(), ordersFragment, R.id.fragment_container, true, "");
    } // go to order lists

//============================================================================================================

    @OnClick(R.id.rel_about)
    public void initAbout() {
        WebViewFragment aboutFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", "1");
        aboutFragment.setArguments(bundle);
        Navigator.loadFragment(getActivity(), aboutFragment, R.id.fragment_container, true, "");
    } // go to about us

//============================================================================================================

    @OnClick(R.id.rel_edit_profile)
    public void initEditProfile() {

        if (SessionManager.isLoggedin())
        {
            EditProfileFragment editProfileFragment = new EditProfileFragment();
            Navigator.loadFragment(getActivity(), editProfileFragment, R.id.fragment_container, true, "");
        }
        else
        {
            Toast.makeText(getActivity(), act.getString(R.string.login_first), Toast.LENGTH_SHORT).show();
        }


    } // go to edit profile

//============================================================================================================

    @OnClick(R.id.rel_addresses)
    public void initAddresses() {
        AddressesFragment addressesFragment = new AddressesFragment();
        Navigator.loadFragment(getActivity(), addressesFragment, R.id.fragment_container, true, "");
    } // go to addresses

//============================================================================================================

    @OnClick(R.id.rel_language)
    public void lang() {

        if (MainActivity.isEnglish) {
            LanguageSessionManager.setLang("ar");
            updateViews("ar");
            MainActivity.isEnglish = false;
            startActivity(new Intent(getContext(),
                    SplashScreen.class));
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            getActivity().finishAffinity();
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(FloraConstant.ARABIC_FONT)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        else {
            LanguageSessionManager.setLang("en");
            updateViews("en");
            MainActivity.isEnglish = true;
            startActivity(new Intent(getContext(),
                    SplashScreen.class));
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            getActivity().finishAffinity();
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(FloraConstant.ENGLISH_FONT)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
    } // init change language

//============================================================================================================

    private void updateViews(String languageCode) {
        LocaleHelper.setLocale(getContext(), languageCode);
    } // update view method
}
