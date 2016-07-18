package id.co.okhome.okhome.restclient;

//http://211.172.247.236:8080/pp_news/commonController/loadCategoryParent?projectKey=GUKMINSORI

import java.util.List;

import id.co.okhome.okhome.model.ConsultantModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConsultantRestClient {

    @GET("consultantController/getAllConsultant")
    Call<List<ConsultantModel>> getAllConsultant(@Query("pageSize") String pageSize, @Query("lastId") String lastId);
}


