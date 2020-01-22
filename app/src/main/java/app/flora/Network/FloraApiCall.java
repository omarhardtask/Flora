package app.flora.Network;

import app.flora.ApiRequests.FloraAPIInterface;
import app.flora.Global.FloraConstant;
import retrofit.RestAdapter;

public class FloraApiCall {

    static FloraAPIInterface apiCall;


    public static FloraAPIInterface getCallingAPIInterface() {

        if (apiCall == null) {

            RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).
                    setClient(new myUrlConnectionClient()).
                    setEndpoint(FloraConstant.BASE_URL).build();

            apiCall = restAdapter.create(FloraAPIInterface.class);

        }

        return apiCall;
    }

}
