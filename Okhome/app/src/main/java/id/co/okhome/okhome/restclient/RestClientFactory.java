package id.co.okhome.okhome.restclient;

import java.util.HashMap;
import java.util.Map;

import id.co.okhome.okhome.restmodule.RetrofitFactory;

/**
 * Created by josongmin on 2016-02-18.
 */

//각 retrofit클라이언트들을 싱글톤으로 관리함.
public class RestClientFactory {

    private static Map<String, Object> mapClient = new HashMap<>();

    public final static <T> T getInstance(final Class<T> service){
        final String key = service.toString();
        T client = (T)mapClient.get(key);
        if(client == null){
            client = RetrofitFactory.getRestClient(service);
            mapClient.put(key, client);
        }
        return client;
    }
//
    /**유저용 레스트 클라이언트*/
    public final static ConsultantRestClient getConsultantRestClient(){
        return RestClientFactory.getInstance(ConsultantRestClient.class);
    }

    /**UserCondition용 레스트 클라이언트*/
    public final static UserConditionRestClient getUserConditionRestClient() {
        return RestClientFactory.getInstance(UserConditionRestClient.class);
    }

}
