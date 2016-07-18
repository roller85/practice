package id.co.okhome.okhome.restclient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jhkim on 7/13/2016.
 */
public interface UserConditionRestClient {
    @GET("userConditionController/updateUserCondition")
    Call<String> updateUserCondition(@Query("conditionId") String conditionId,
                                  @Query("userId") String userId,
                                  @Query("homeType") String homeType,
                                  @Query("homeSize") String homeSize,
                                  @Query("homeOwnerExistence") String homeOwnerExistence,
                                  @Query("petCategory") String petCategory,
                                  @Query("detailHome") String detailHome,
                                  @Query("useOwnerTool") String useOwnerTool,
                                  @Query("firstStartDate") String firstStartDate,
                                  @Query("cleanPeriod") String cleanPeriod,
                                  @Query("monday") String monday,
                                  @Query("tuesday") String tuesday,
                                  @Query("wednesday") String wednesday,
                                  @Query("thursday") String thursday,
                                  @Query("friday") String friday,
                                  @Query("saturday") String saturday,
                                  @Query("sunday") String sunday,
                                  @Query("region") String region,
                                  @Query("city") String city,
                                  @Query("district") String district,
                                  @Query("detailAddress") String detailAddress,
                                  @Query("detailLocation") String detailLocation,
                                  @Query("hostName") String hostName,
                                  @Query("bedroom") String bedroom,
                                  @Query("bathroom") String bathroom);
}