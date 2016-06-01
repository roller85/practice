package id.co.okhome.okhome;



import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import id.co.okhome.okhome.Data.MyAdapter;
import id.co.okhome.okhome.Data.OrderInfo;
import id.co.okhome.okhome.Server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public String date;
    public String price;
    public String nameOfSupplier;
    public RatingBar rating;
    public String priceNameOfSupplier;

    public static final String TAG_RESULT = "order_history_result";
    public static final String TAG_DATE = "order_history_date";
    public static final String TAG_DURATION = "order_history_duration";
    public static final String TAG_PRICE = "order_history_price";
    public static final String TAG_SUPPLIER = "order_history_supplier";
    public static final String TAG_RATING = "order_history_rating";

    String myJSON;

    JSONArray orderHistory = null;
    ArrayList<HashMap<String, String>> orderHistoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.okhome_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        orderHistoryList = new ArrayList<>();

        // specify an adapter


        LoadOrderHistoryDate();

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
            orderHistory = jsonObj.getJSONArray(TAG_RESULT);
            //orderHistory가 null로 인식되어있는듯함

            for (int i = 0; i < orderHistory.length(); i++) {
                JSONObject c = orderHistory.getJSONObject(i);
                String date = c.getString(TAG_DATE);
                String duration = c.getString(TAG_DURATION);
                String price = c.getString(TAG_PRICE);
                String supplier = c.getString(TAG_SUPPLIER);
                String rating = c.getString(TAG_RATING);

                HashMap<String, String> eachOrderHistory = new HashMap<>();
                eachOrderHistory.put(TAG_DATE, date);
                eachOrderHistory.put(TAG_DURATION, duration);
                eachOrderHistory.put(TAG_PRICE, price);
                eachOrderHistory.put(TAG_SUPPLIER, supplier);
                eachOrderHistory.put(TAG_RATING, rating);

                orderHistoryList.add(eachOrderHistory);
            }
            mAdapter = new MyAdapter(orderHistoryList);
            mRecyclerView.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


