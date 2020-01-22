package app.flora.ApiRequests;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface downloadService {

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Header("Authorization") String Authorization,
                                                      @Header("Content-Type") String content_type,
                                                      @Url String fileUrl);

}
