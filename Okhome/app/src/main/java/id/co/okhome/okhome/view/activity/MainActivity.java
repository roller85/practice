package id.co.okhome.okhome.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import id.co.okhome.okhome.R;
import id.co.okhome.okhome.data.OrderInfo;
import id.co.okhome.okhome.server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQ_TOPUP = 1001;
    public static final int REQ_TOPUP_RETURN = 1002;
    public static final int REQ_LOGIN = 1005;
    public static final int REQ_LOGIN_RETURN = 1006;
    private String email = "";
    private String user_token = "";
    public TextView textView;
    public TextView txv_balance;
    private int period;
    private int balance;
    private ArrayList<Integer> periodBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//
//        private void loadAllConsultant(){
//            RestClientFactory.getConsultantRestClient().getAllConsultant("10", "0").enqueue(new RetrofitCallback<List<ConsultantModel>>() {
//                @Override
//                public void onSuccess(List<ConsultantModel> result) {
//
//                }
//
//                @Override
//                public void onJodevError(ErrorModel jodevErrorModel) {
//
//                }
//
//                @Override
//                public void onFailure(retrofit2.Call<List<ConsultantModel>> call, Throwable t) {
//
//                }
//            });
//        }
        /*
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.welcome);
        textView.setText("Welcome "+ message);
        email = message;
        */

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.KEY_USER_DATA, MODE_PRIVATE);
        user_token = sharedPreferences.getString(LoginActivity.KEY_USER_DATA_TOKEN, "noToken");

        textView = (TextView) findViewById(R.id.welcome);
        txv_balance = (TextView) findViewById(R.id.current_balance2);

        try {
            OrderInfo.getInstance().GetUserEmailInfo();
        } catch (NullPointerException e) {
            OrderInfo.getInstance().AddUserEmailInfo("guest");
        }

        try {
            period = OrderInfo.getInstance().GetPeriodInfo();
        } catch (NullPointerException e) {
            OrderInfo.getInstance().AddPeriodInfo(-1);
        }


        if(user_token.equals("noToken")) {
            textView.setText("Welcome " + OrderInfo.getInstance().GetUserEmailInfo());
        } else {
            loadTokenFromDataBase();
        }


        findViewById(R.id.btn_toLogin).setOnClickListener(this);
        findViewById(R.id.btn_newOrder).setOnClickListener(this);
        findViewById(R.id.btn_topUp).setOnClickListener(this);
        findViewById(R.id.btn_toLogOut).setOnClickListener(this);
        findViewById(R.id.btn_orderHistory).setOnClickListener(this);
        findViewById(R.id.btn_changeOrder).setOnClickListener(this);
    }

    /*
    class RunThread extends Thread {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
    */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_toLogin:
                Intent intentToLogin = new Intent(this, LoginActivity.class);
                intentToLogin.putExtra("requestCode", REQ_LOGIN);
                startActivityForResult(intentToLogin, REQ_LOGIN_RETURN);
                break;
            case R.id.btn_newOrder:
                if(period==1||period==2||period==3) {
                    Toast.makeText(this, "if you are periodic user, call OKHOME to change", Toast.LENGTH_SHORT).show();
                } else {
                Intent intentToOrder = new Intent(this, OrderActivity.class);
                startActivity(intentToOrder);
                }
                break;
            case R.id.btn_topUp:
                if (OrderInfo.getInstance().GetUserEmailInfo().equals("guest")) {
                    Toast.makeText(this, "if you are guest,"+"\n"+"you need to order first", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentToTopUp = new Intent(this, TopUpActivity.class);
                    intentToTopUp.putExtra("requestCode", REQ_TOPUP);
                    startActivityForResult(intentToTopUp, REQ_TOPUP_RETURN);
                }
                break;
            case R.id.btn_toLogOut:
                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.KEY_USER_DATA, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LoginActivity.KEY_USER_DATA_TOKEN, "noToken");
                editor.apply();
                OrderInfo.getInstance().AddUserEmailInfo("guest");

                Intent intentToLogout = new Intent(this, LogOutActivity.class);
                startActivity(intentToLogout);
                break;

            case R.id.btn_orderHistory:
                if (OrderInfo.getInstance().GetUserEmailInfo().equals("guest")) {
                    Toast.makeText(this, "if you are guest," + "\n" + "you need to login to use this menu", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentOrderHistory = new Intent(this, OrderHistoryActivity.class);
                    startActivity(intentOrderHistory);
                }
                break;

            case R.id.btn_changeOrder:
                if (OrderInfo.getInstance().GetUserEmailInfo().equals("guest")) {
                    Toast.makeText(this, "if you are guest," + "\n" + "you need to login to use this menu", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentChangeOrder = new Intent(this, OrderChangeActivity.class);
                    startActivity(intentChangeOrder);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_TOPUP_RETURN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Top UP Complete", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Top Up Canceled", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQ_LOGIN_RETURN) {
            if (resultCode == RESULT_OK) {
                periodBalance = new ArrayList<>();
                periodBalance = data.getIntegerArrayListExtra("period_balance");
                period = periodBalance.get(0);
                balance = periodBalance.get(1);
                String txt_balance = String.valueOf(balance);
                Toast.makeText(this, "Login Complete", Toast.LENGTH_SHORT).show();
                textView.setText("Welcome " + OrderInfo.getInstance().GetUserEmailInfo());
                txv_balance.setText(txt_balance);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Login Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadTokenFromDataBase() {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("user_token", user_token);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.LOADUSERTOKEN)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(findViewById(R.id.content_main), e.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String answer = response.body().string();
                int dist = answer.indexOf(",");
                int dist2 = answer.indexOf("/");
                email = answer.substring(0,dist);
                String per = answer.substring(dist+1,dist2);
                period = Integer.parseInt(per);
                String bal = answer.substring(dist2+1, answer.length()-1);
                balance = Integer.parseInt(bal);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OrderInfo.getInstance().AddUserEmailInfo(email);
                        textView.setText("Welcome "+email);
                        String txt_bal = String.valueOf(balance);
                        txv_balance.setText(txt_bal);
                    }
                });

                //RunThread thread = new RunThread();
                //thread.setDaemon(true);
                //thread.start();
            }
        });
    }

    /*
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0) {
                OrderInfo.getInstance().AddUserEmailInfo(email);
                textView.setText("Welcome "+email);
                String txt_bal = String.valueOf(balance);
                txv_balance.setText(txt_bal);

            }
        }
    };
    */

}
