package id.co.okhome.okhome.restclient;

import id.co.okhome.okhome.model.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jhkim on 7/18/2016.
 */
public interface UserRestClient {
    @GET("userController/createToken")
    Call<String> createToken();

    @GET("userController/loadToken")
    Call<UserModel> loadToken(@Query("deviceToken") String device_token);
}
