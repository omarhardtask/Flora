package app.flora.Network;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

public class myUrlConnectionClient extends UrlConnectionClient {

    @Override protected HttpURLConnection openConnection(Request request) {
        HttpURLConnection connection = null;
        try {
            connection = super.openConnection(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setConnectTimeout(60 * 2000);
        connection.setReadTimeout(60 * 2000);
        return connection;
    }
}
