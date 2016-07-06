package id.co.okhome.okhome.restmodule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josongmin on 2016-02-18.
 */
public abstract class RetrofitCallback<T> implements Callback<T>{

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Object obj = response.body();
        if(obj instanceof ErrorModel){
            onJodevError((ErrorModel)obj);
        }else{
            onSuccess((T)obj);
        }
    }

    abstract public void onSuccess(T result);
    abstract public void onJodevError(ErrorModel jodevErrorModel);


}
