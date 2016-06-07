package id.co.okhome.okhome;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import id.co.okhome.okhome.data.OrderChangeAdapter;
import id.co.okhome.okhome.data.OrderInfo;
import id.co.okhome.okhome.server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderChangeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static final String TAG_RESULT = "result";
    public static final String TAG_DATE = "cleaning_date";
    public static final String TAG_TIME = "cleaning_time";
    public static final String TAG_DURATION = "duration";
    public static final String TAG_PAYAMOUNT = "payAmount";

    String myJSON;
    JSONArray orderChange = null;
    ArrayList<HashMap<String,String>> orderChangeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_change);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.order_change_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        orderChangeList = new ArrayList<>();

        loadOrderChange();

        mAdapter = new OrderChangeAdapter(orderChangeList);
        mRecyclerView.setAdapter(mAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadOrderChange() {
        String email = OrderInfo.getInstance().GetUserEmailInfo();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.LOADORDERFUTURE)
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

    private void showList() {

        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            orderChange = jsonObj.getJSONArray(TAG_RESULT);

            for (int i = 0; i < orderChange.length(); i++) {
                JSONObject c = orderChange.getJSONObject(i);
                String date = c.getString(TAG_DATE);
                String time = c.getString(TAG_TIME);
                String duration = c.getString(TAG_DURATION);
                String price = c.getString(TAG_PAYAMOUNT);

                HashMap<String, String> eachChangeList = new HashMap<>();
                eachChangeList.put(TAG_DATE, date);
                eachChangeList.put(TAG_TIME, time);
                eachChangeList.put(TAG_DURATION, duration);
                eachChangeList.put(TAG_PAYAMOUNT, price);

                orderChangeList.add(eachChangeList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
