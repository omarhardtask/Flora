package app.flora.Ui.Fragments.Home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import app.flora.Global.FloraConstant;
import app.flora.Models.SliderModel;
import app.flora.Network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<SliderModel.SlidersBean>> list = new MutableLiveData<>();

    public void fetchSlider() {
        RetrofitClient.getInstance().fetchSliders().enqueue(new Callback<SliderModel>() {
            @Override
            public void onResponse(Call<SliderModel> call, Response<SliderModel> response) {
                if (response.body().getSliders() != null)
                list.setValue(response.body().getSliders());
            }

            @Override
            public void onFailure(Call<SliderModel> call, Throwable t) {
                Log.i(FloraConstant.TAG, "error in ViewModel = " + t.getLocalizedMessage());
            }
        });
    } // fetchSlider

}
