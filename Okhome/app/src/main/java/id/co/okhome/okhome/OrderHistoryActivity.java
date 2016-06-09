package id.co.okhome.okhome;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.okhome.okhome.data.OrderHistoryAdapter;
import id.co.okhome.okhome.data.OrderInfo;
import id.co.okhome.okhome.model.OrderHistoryModel;
import id.co.okhome.okhome.server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    OrderHistoryAdapter mAdapter;
    String myJSON;
    JSONArray orderHistory = null;
    ArrayList<HashMap<String, String>> orderHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        init();
        LoadOrderHistoryDate();
    }

    private void init() {
        initView();
        mAdapter = new OrderHistoryAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initView() {
        orderHistoryList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.okhome_recycler_view);
        mRecyclerView.setHasFixedSize(true);
    }


    private void LoadOrderHistoryDate() {
        String email = OrderInfo.getInstance().GetUserEmailInfo();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.LOADORDERHISTORY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(findViewById(R.id.content_order_history), e.getMessage(),Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myJSON = response.body().string();
                showList();
            }
        });
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            orderHistory = jsonObj.getJSONArray(OrderHistoryModel.TAG_RESULT);
            List<OrderHistoryModel> orderHistoryModels = new ArrayList<>();

            for (int i = 0; i < orderHistory.length(); i++) {
                JSONObject c = orderHistory.getJSONObject(i);
                String date = c.getString(OrderHistoryModel.TAG_DATE);
                String duration = c.getString(OrderHistoryModel.TAG_DURATION);
                String price = c.getString(OrderHistoryModel.TAG_PRICE);
                String supplier = c.getString(OrderHistoryModel.TAG_SUPPLIER);
                String performance = c.getString(OrderHistoryModel.TAG_RATING);

                HashMap<String, String> eachOrderHistory = new HashMap<>();
                eachOrderHistory.put(OrderHistoryModel.TAG_DATE, date);
                eachOrderHistory.put(OrderHistoryModel.TAG_DURATION, duration);
                eachOrderHistory.put(OrderHistoryModel.TAG_PRICE, price);
                eachOrderHistory.put(OrderHistoryModel.TAG_SUPPLIER, supplier);
                eachOrderHistory.put(OrderHistoryModel.TAG_RATING, performance);

                orderHistoryList.add(eachOrderHistory);

                OrderHistoryModel m = new OrderHistoryModel();
                m.date = date;
                m.duration = duration;
                m.price = price;
                m.supplier = supplier;
                m.performance = performance;

                orderHistoryModels.add(m);
            }
            mAdapter.setList(orderHistoryModels);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


