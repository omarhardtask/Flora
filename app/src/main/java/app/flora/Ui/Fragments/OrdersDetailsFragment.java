package app.flora.Ui.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Objects;

import app.flora.Adapters.OrderDetailsAdapter;
import app.flora.ApiRequests.downloadService;
import app.flora.Global.FixControl;
import app.flora.Global.FloraConstant;
import app.flora.Global.LanguageSessionManager;
import app.flora.Global.Navigator;
import app.flora.Global.SessionManager;
import app.flora.Models.FloraCityDownloadAPIInterface;
import app.flora.Models.Order;
import app.flora.Models.OrderDelete;
import app.flora.Models.Orders;
import app.flora.Models.ShoppingCarts;
import app.flora.Network.FloraApiCall;
import app.flora.R;
import app.flora.Ui.Activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;

public class OrdersDetailsFragment extends Fragment {

    // define ids
    @BindView(R.id.tv_order_details)
    TextView tv_order_details;
    @BindView(R.id.tv_temp)
    TextView tv_temp;
    @BindView(R.id.my_recycler_view)
    RecyclerView my_recycler_view;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.linear_invoice)
    LinearLayout linear_invoice;
    @BindView(R.id.linear_cancel)
    LinearLayout linear_cancel;
    @BindView(R.id.linear_re_order)
    LinearLayout linear_re_order;
    @BindView(R.id.linear_complain)
    LinearLayout linear_complain;
    @BindView(R.id.loading)
    ProgressBar mloading;
    @BindView(R.id.linear_bottom)
    LinearLayout linear_bottom;
    @BindView(R.id.tv_invoice)
    TextView tv_invoice;
    @BindView(R.id.tv_reorder)
    TextView tv_reorder;
    @BindView(R.id.tv_complain)
    TextView tv_complain;
    @BindView(R.id.linear_refund)
    LinearLayout linear_refund;

    // variables
    SessionManager mSessionManager;
    LanguageSessionManager languageSeassionManager;
    String orderId = "";
    OrderDelete.OrdersBean order = null;
    RecyclerView.Adapter adapter;
    LinearLayoutManager mLayoutManager;
    static FragmentActivity act;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_details_fragment, container, false);
        ButterKnife.bind(this, view);
        initVisibility();
        initSession();
        getOrderObject();
        initDetails();
        initActivity();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.shoppingCartItemsCount();
    } // onResume

    private void initActivity() {
        if (act == null) {
            act = getActivity();
        }
    } // init activity

    private void initVisibility() {
        ((MainActivity) Objects.requireNonNull(getActivity())).title.setText(getString(R.string.order_details));
        ((MainActivity) getActivity()).img_back.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_sort.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_filter.setVisibility(View.GONE);
        ((MainActivity) getActivity()).img_logo.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).linear_search.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).img_add.setVisibility(View.GONE);
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    } // initialize visibiliy


//    @OnClick(R.id.linear_refund)
//    public void refund() {
//        Fragment refundFragment = new refundFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("Order", new Gson().toJson(order));
//        refundFragment.setArguments(bundle);
//        Navigator.loadFragment(act, refundFragment, R.id.fragment_container, true, "");
//    } // init refund

//    @OnClick(R.id.linear_cancel)
//    public void clickCancel() {
//        new AlertDialog.Builder(getActivity())
//                .setMessage(act.getString(R.string.cancel))
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        // init cancel request
//                        cancelOrder();
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .setIcon(R.drawable.logo)
//                .show();
//    } // init cancel

//    private void complain(String edittext) {
//
//        Log.i(mySallaConstant.TAG, "complain orderId : " + orderId);
//        mloading.setVisibility(View.VISIBLE);
//        mySallaApiCall.getCallingAPIInterface().complain(
//                languageSeassionManager.getLang(),
//                mySallaConstant.AuthorizationToken,
//                orderId,
//                edittext,
//                new Callback<Orders>() {
//                    @Override
//                    public void success(Orders outResponse, Response response) {
//
//                        if (outResponse != null) {
//
//                            if (outResponse.getOrders() != null) {
//
//                                Log.i(mySallaConstant.TAG, "complain success ");
//                                try {
//                                    Toast.makeText(getActivity(), act.getString(R.string.complain_send), Toast.LENGTH_SHORT).show();
//                                } catch (Exception e) {
//                                }
//                                getFragmentManager().popBackStack();
//                            }
//                        } else {
//                            Log.i(mySallaConstant.TAG, "complain null ");
//
//                        }
//                        mloading.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.i(mySallaConstant.TAG, "complain fail ");
//                        mloading.setVisibility(View.GONE);
//                        try {
//                            Toast.makeText(getActivity(), act.getString(R.string.try_again), Toast.LENGTH_SHORT).show();
//
//                        } catch (Exception e) {
//                        }
//                        //  Snackbar.make(getView(), getString(R.string.try_again), Snackbar.LENGTH_LONG).show();
//                    }
//                });
//
//    } // complain

//    @TargetApi(Build.VERSION_CODES.M)
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @OnClick(R.id.linear_complain)
//    public void clickComplain() {
//        // popup where he will enter details and submit
//
//        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
//
//        final EditText edittext = new EditText(getActivity());
//        ColorStateList colorStateList = ColorStateList.valueOf(getContext().getColor(R.color.colorPrimary));
//        edittext.setBackgroundTintList(colorStateList);
//        alert.setMessage(act.getString(R.string.enter_complain));
//        alert.setTitle(act.getString(R.string.send_complain));
//        alert.setView(edittext);
//        alert.setIcon(getContext().getDrawable(R.drawable.logo));
//        alert.setPositiveButton(getString(R.string.send), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                String complain_text = edittext.getText().toString();
//                Log.i(mySallaConstant.TAG, "complain_text complain : " + complain_text);
//                complain(complain_text);
//
//            }
//        });
//
//
//        alert.setNegativeButton(act.getString(R.string.cancel), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                dialog.dismiss();
//            }
//        });
//
//        alert.show();
//
//    } // click on reorder


    @OnClick(R.id.linear_invoice)
    public void clickInvoice() {
        if (order == null) {
            Log.d(FloraConstant.TAG, "order is null");
        } else {
            Log.d(FloraConstant.TAG, "order is not null==" + order.getOrder_status());
        }
        if (order.getOrder_status().equalsIgnoreCase(FloraConstant.OrderCompleteStatus) &&
                order.getPayment_status().equalsIgnoreCase(FloraConstant.PaymentStatusPaid)) {

            Log.d(FloraConstant.TAG, "order is staus complete " + order.getOrder_status());

            if (FixControl.isWriteExternalStorageAllowed(getActivity())) {

                String filename = Calendar.getInstance().getTimeInMillis() + ".pdf";

                String outPath = Environment.getExternalStorageDirectory()
                        + File.separator
                        + "Download"
                        + File.separator
                        + filename;
                Log.d(FloraConstant.TAG, "orderId in download" + orderId);
                startDownload(FloraConstant.Download_Invoice.replace("aaa", orderId), outPath);
                return;
            }

            if (!FixControl.isWriteExternalStorageAllowed(getActivity())) {
                FixControl.requestWriteExternalStoragePermission(getActivity());
                Log.d(FloraConstant.TAG, "order  staus need access " + order.getOrder_status());
            }
        }

    } // click on Invoice

    private void startDownload(String fileUrl, final String filePath) {

        downloadService downloadService =
                FloraCityDownloadAPIInterface.getClient().create(app.flora.ApiRequests.downloadService.class);

        Call<ResponseBody> call = downloadService.downloadFileWithDynamicUrlSync(
                FloraConstant.AuthorizationToken, "application/x-www-form-urlencoded", fileUrl);

        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(Call<ResponseBody> call, final retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("startDownload", "server contacted and has file");
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            mloading.setVisibility(View.VISIBLE);
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Log.d("download", "completed");
                            mloading.setVisibility(View.GONE);
                            File file = new File(filePath);
                            Intent install = new Intent(Intent.ACTION_VIEW);
                            install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Uri apkURI = FileProvider.getUriForFile(
                                    getActivity(),
                                    getActivity().getApplicationContext()
                                            .getPackageName() + ".provider", file);
                            install.setDataAndType(apkURI, "application/pdf");
                            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            try {
                                getActivity().startActivity(install);
                            } catch (ActivityNotFoundException e) {
                            }
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body(), filePath);
                            Log.d("startDownload", "file download was a success? " + writtenToDisk);
                            return null;
                        }
                    }.execute();
                } else {
                    Log.d("startDownload", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("startDownload", "error");
            }
        });
    } // start download pdf

    private boolean writeResponseBodyToDisk(ResponseBody body, String filePath) {
        try {
            // todo change the file location/name according to your needs
            File f = new File(Environment.getExternalStorageDirectory(), File.separator + "Download");

            f.mkdirs();

            File futureStudioIconFile = new File(filePath);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("startDownload 1 ->", "file download: " + fileSizeDownloaded + " of " + fileSize);

                    if (futureStudioIconFile.exists())
                        Log.d("startDownload 1 ->", "file path: " + futureStudioIconFile.getAbsolutePath());
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    } // writeResponseBodyToDisk

    @OnClick(R.id.linear_re_order)
    public void clickReOrder() {
        reorder();
    } // click on reorder

//    private void cancelOrder() {
//
//        mloading.setVisibility(View.VISIBLE);
//
//        mySallaApiCall.getCallingAPIInterface().candelOrder(
//                languageSeassionManager.getLang(),
//                mySallaConstant.AuthorizationToken,
//                orderId,
//                new Callback<ShoppingCarts>() {
//                    @Override
//                    public void success(ShoppingCarts outResponse, retrofit.client.Response response) {
//
//                        if (outResponse != null) {
//                            Log.i(chefConstant.TAG, "not null");
//                            Log.i(mySallaConstant.TAG, "cancelOrder api success");
//
//                            try {
//                                getFragmentManager().popBackStack();
//                                Toast.makeText(getActivity(), act.getString(R.string.order_cancelled), Toast.LENGTH_SHORT).show();
//                            } catch (Exception e) {
//                            }
//                        } else {
//                            Log.i(mySallaConstant.TAG, "cancelOrder null");
//                        }
//                        mloading.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        GlobalFunctions.showErrorMessage(error, getView());
//                        mloading.setVisibility(View.GONE);
//                    }
//                });
//    } // cancelOrder

    private void reorder() {

        mloading.setVisibility(View.VISIBLE);

        FloraApiCall.getCallingAPIInterface().reorder(
                languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                orderId,
                new Callback<ShoppingCarts>() {
                    @Override
                    public void success(ShoppingCarts outResponse, Response response) {

                        if (outResponse != null) {
                            Log.d(FloraConstant.TAG, "not null");
                            Log.i(FloraConstant.TAG, "reorder api success");
                            Fragment fragment = new CartFragment();
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("isCartList", true);
                            fragment.setArguments(bundle);
                            Navigator.loadFragment(getActivity(), fragment, R.id.fragment_container, true, "home");
                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                    }
                });
    } // reorder

    private void initDetails() {
        if (order != null) {
            setData();
        } else {
            orders();
        }
    } // init details

    @SuppressLint("SetTextI18n")
    private void setData() {

        if (languageSeassionManager.getLang().equalsIgnoreCase("en")) {

            try {
                tv_invoice.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                        ENGLISH_BOLD));
                tv_reorder.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                        ENGLISH_BOLD));
                tv_complain.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), FloraConstant.
                        ENGLISH_BOLD));

            } catch (Exception e) {
            }

        }

        if (order.getOrder_status().equalsIgnoreCase(FloraConstant.Cancelled)) {
            linear_cancel.setVisibility(View.GONE);

        }

        // make it visible in this case
        if (order.getOrder_status().equalsIgnoreCase(FloraConstant.OrderCompleteStatus)) {
            //  linear_refund.setVisibility(View.VISIBLE);
        }

        if (order.getOrder_status().equalsIgnoreCase(FloraConstant.OrderCompleteStatus) &&
                order.getPayment_status().equalsIgnoreCase(FloraConstant.PaymentStatusPaid)) {
            linear_invoice.setVisibility(View.VISIBLE);
            tv_reorder.setVisibility(View.VISIBLE);
        }

        if (order != null) {
            if (getArguments().getBoolean(FloraConstant.Code)) {
                Log.d("body15", "not null");
                if (order.getOrder_status().equalsIgnoreCase(FloraConstant.OrderCompleteStatus) &&
                        order.getPayment_status().equalsIgnoreCase(FloraConstant.PaymentStatusPaid)) {
                    Log.d("body16", "not null");
                    if (isAdded()) {
                        new AlertDialog.Builder(getContext())
                                .setTitle(act.getString(R.string.Confirm))
                                .setMessage(act.getString(R.string.order_done))
                                .setPositiveButton(act.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setCancelable(false)
                                .setIcon(R.drawable.logo_flora)
                                .show();
                    }
                }
            }
            String htmlContent = "";
            try {
                htmlContent = "<h3><b>" + act.getString(R.string.Order) + "&nbsp;#" + order.getId() + "</b></h3>";

            } catch (Exception e) {
            }

            if (order.getCreated_on_utc() != null) {
                if (order.getCreated_on_utc().length() > 0) {
                    try {
                        htmlContent = htmlContent + "" + act.getString(R.string.OrdersDateLabel) + ":&nbsp;" +
                                FixControl.convertDateToString1(order.getCreated_on_utc());

                    } catch (Exception e) {
                    }
                }
            }
            if (order.getOrder_status() != null) {

                if (order.getOrder_status().length() > 0) {
                    try {
                        htmlContent = htmlContent + "<br/>" + act.getString(R.string.OrdersStatusLabel) + ":&nbsp;" + order.getOrder_status();

                    } catch (Exception e) {
                    }

                }
            }
            if (order.getOrder_total() != null) {
                if (order.getOrder_status().length() > 0) {
                    try {
                        htmlContent = htmlContent + "<br/>" + act.getString(R.string.OrdersTotalLabel) + ":&nbsp;<b>" +
                                order.getCustomer_currency_code() + FixControl.round2(order.getOrder_total() + "") + "</b>";
                    } catch (Exception e) {
                    }
                }
            }
            if (order.getBilling_address() != null) {
                try {
                    htmlContent = htmlContent + "<br/><br /><b>" + act.getString(R.string.BillingAddress) + "</b><br/>" +
                            getString(R.string.name) + ":&nbsp;" + order.getBilling_address().getFirst_name()
                            + " " + order.getBilling_address().getLast_name();

                    htmlContent = htmlContent + "<br />" + act.getString(R.string.email) + ":&nbsp;" +
                            order.getBilling_address().getEmail();

                    htmlContent = htmlContent + "<br />" + act.getString(R.string.mobile) + ":&nbsp;" +
                            order.getBilling_address().getPhone_number();

                    htmlContent = htmlContent + "<br />" + act.getString(R.string.StateLabel) + ":&nbsp;" +
                            order.getBilling_address().getProvince();

                    htmlContent = htmlContent + "<br />" + act.getString(R.string.CountryLabel) + ":&nbsp;" +
                            order.getBilling_address().getCountry();
                } catch (Exception e) {
                }
            }
            try {
                htmlContent = htmlContent + "<br /><br /><b>" + act.getString(R.string.PaymentLabel) + "</b><br />" +
                        act.getString(R.string.PaymentMethodLabel) + "&nbsp;" + order.getPayment_method_system_name()
                        .split("\\.")[order.getPayment_method_system_name().split("\\.").length - 1];

                htmlContent = htmlContent + "<br />" + act.getString(R.string.PaymentStatus) + "&nbsp;" + order.getPayment_status();

                htmlContent = htmlContent + "<br /><br /><b>" + act.getString(R.string.ProductsLabel1) + "</b>";

                Log.d("htmlContent", "" + htmlContent);

                if (Build.VERSION.SDK_INT >= 24) {

                    tv_order_details.setText(Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY));
                } else {

                    tv_order_details.setText(Html.fromHtml(htmlContent));
                }
            } catch (Exception e) {
            }
        }
        Log.i(FloraConstant.TAG, "getOrderItems size : " + order.getOrder_items().size());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        my_recycler_view.setLayoutManager(mLayoutManager);
        my_recycler_view.setItemAnimator(new DefaultItemAnimator());
        adapter = new OrderDetailsAdapter(act, order.getOrder_items(), order);
        my_recycler_view.setAdapter(adapter);

        try {
            tv_total.setText(act.getString(R.string.total) + ": " +
                    FixControl.round2(order.getOrder_total() + " " + order.getCustomer_currency_code()));

        } catch (Exception e) {
        }

        linear_bottom.setVisibility(View.VISIBLE);

        if (order.getOrder_status().equalsIgnoreCase(FloraConstant.OrderCompleteStatus) &&
                order.getPayment_status().equalsIgnoreCase(FloraConstant.PaymentStatusPaid)) {
            tv_msg.setVisibility(View.GONE);
            linear_invoice.setVisibility(View.VISIBLE);
            linear_re_order.setVisibility(View.VISIBLE);
            //   linear_complain.setVisibility(View.VISIBLE);

        }

    } // set the texts data

    private void orders() {

        mloading.setVisibility(View.VISIBLE);
//orderId 4
        FloraApiCall.getCallingAPIInterface().orderById(languageSeassionManager.getLang(),
                FloraConstant.AuthorizationToken,
                orderId
                , new Callback<OrderDelete>() {
                    @Override
                    public void success(OrderDelete outResponse, Response response) {

                        if (outResponse != null) {

                            if (outResponse.getOrders() != null) {

                                Log.d(FloraConstant.TAG, "not null1");
                                if (outResponse.getOrders().size() > 0) {
                                    order = outResponse.getOrders().get(0);
                                    setData();
                                }
                            }
                        } else {
                            Log.d(FloraConstant.TAG, "null");
                        }
                        mloading.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FixControl.showErrorMessage(error, getView());
                        mloading.setVisibility(View.GONE);
                    }
                });
    } // get order details for the item

    private void initSession() {
        mSessionManager = new SessionManager(getContext());
        languageSeassionManager = new LanguageSessionManager(getContext());
    } // init session

    private void getOrderObject() {

        linear_invoice.setVisibility(View.GONE);
        linear_re_order.setVisibility(View.GONE);

        if (getArguments() != null) {
            Gson gson = new Gson();
            if (getArguments().containsKey("Order")) {
                OrderDelete.OrdersBean order = gson.fromJson(getArguments().getString("Order"),
                        OrderDelete.OrdersBean.class);
                orderId = order.getId() + "";
                Log.i(FloraConstant.TAG, "orderId in myOrder details : " + orderId);
            }
        }
    } // get saved details for item

}
