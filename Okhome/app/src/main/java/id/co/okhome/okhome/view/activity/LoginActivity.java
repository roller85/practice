package id.co.okhome.okhome.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

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

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_USER_DATA = "user_data";
    public static final String KEY_USER_DATA_TOKEN = "user_data_token";
    public static final int REQ_TOPUP = 1011;

    private EditText appEmail, appPassword;
    private CheckBox checkBox;
    private boolean checked;
    private String token;
    private int period;
    private int balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        appEmail = (EditText) findViewById(R.id.app_email);
        appPassword = (EditText) findViewById(R.id.app_pw);
        checkBox = (CheckBox) findViewById(R.id.chk_auto_login);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checked = true;
                } else if (!isChecked) {
                    checked = false;
                }
            }
        });

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checked) {
                    attemptLoginWithAutoLogin();
                } else {
                    attemptLoginWithoutAutoLogin();
                }
            }
        });

        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }

    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                attemptLogin();
                break;
            case R.id.btn_signup:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
    */


    private void attemptLoginWithoutAutoLogin() {
        String email = appEmail.getText().toString();
        String password = appPassword.getText().toString();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email)
                .appendQueryParameter("password", password);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.LOGIN)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(findViewById(R.id.content_login), e.getMessage(),Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Snackbar.make(findViewById(R.id.content_login), response.body().toString(), Snackbar.LENGTH_SHORT).show();

                String answer = response.body().string();
                if (answer.equals("failed")) {
                    Toast.makeText(LoginActivity.this, answer, Toast.LENGTH_SHORT).show();
                } else {
                    int dist = answer.indexOf(",");
                    String per = answer.substring(0, dist);
                    period = Integer.parseInt(per);
                    String bal = answer.substring(dist+1);
                    balance = Integer.parseInt(bal);
                    int requestCode = getIntent().getIntExtra("requestCode", -1);

                    String message = appEmail.getText().toString();
                    OrderInfo.getInstance().AddUserEmailInfo(message);

                    if (requestCode == MainActivity.REQ_LOGIN) {
                        ArrayList<Integer> period_balance = new ArrayList<>();
                        period_balance.add(period);
                        period_balance.add(balance);
                        Intent intent = new Intent();
                        intent.putIntegerArrayListExtra("period_balance", period_balance);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if (requestCode == TopUpActivity.REQ_LOGIN) {
                        Intent intent = new Intent(LoginActivity.this, TopUpActivity.class);
                        intent.putExtra("requestCode", REQ_TOPUP);
                        startActivity(intent);
                    }
                }
                /*
                else if (requestCode == TopUpActivity.REQ_SIGNUP) {
                    setResult(RESULT_OK);
                    finish();
                }
                */
            }
        });
    }

    private void attemptLoginWithAutoLogin() {
        final String email = appEmail.getText().toString();
        String password = appPassword.getText().toString();
        UUID uuid = UUID.randomUUID();
        token = uuid.toString();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email)
                .appendQueryParameter("password", password)
                .appendQueryParameter("user_token", token);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        final Request request = new Request.Builder()
                .url(ServerAPI.LOGINAUTO)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(findViewById(R.id.content_login), e.getMessage(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Snackbar.make(findViewById(R.id.content_login), response.body().toString(), Snackbar.LENGTH_LONG).show();
                String answer = response.body().string();

                if (answer.equals("failed")) {
                    Toast.makeText(LoginActivity.this, answer, Toast.LENGTH_SHORT).show();
                } else {
                    if (answer.equals(",")) {
                        SharedPreferences sharedPreferences = getSharedPreferences(KEY_USER_DATA, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_USER_DATA_TOKEN, token);
                        editor.apply();

                        int requestCode = getIntent().getIntExtra("requestCode", -1);
                        String message = appEmail.getText().toString();
                        OrderInfo.getInstance().AddUserEmailInfo(message);

                        if (requestCode == MainActivity.REQ_LOGIN) {
                            setResult(RESULT_OK);
                            finish();
                        } else if (requestCode == TopUpActivity.REQ_LOGIN) {
                            Intent intent = new Intent(LoginActivity.this, TopUpActivity.class);
                            intent.putExtra("requestCode", REQ_TOPUP);
                            startActivity(intent);
                        }
                    } else {
                        SharedPreferences sharedPreferences = getSharedPreferences(KEY_USER_DATA, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_USER_DATA_TOKEN, token);
                        editor.apply();

                        int dist = answer.indexOf(",");
                        String per = answer.substring(0, dist);
                        period = Integer.parseInt(per);
                        String bal = answer.substring(dist+1, answer.length()-1);
                        balance = Integer.parseInt(bal);

                        int requestCode = getIntent().getIntExtra("requestCode", -1);
                        String message = appEmail.getText().toString();
                        OrderInfo.getInstance().AddUserEmailInfo(message);

                        if (requestCode == MainActivity.REQ_LOGIN) {
                            ArrayList<Integer> period_balance = new ArrayList<>();
                            period_balance.add(period);
                            period_balance.add(balance);
                            Intent intent = new Intent();
                            intent.putIntegerArrayListExtra("period_balance", period_balance);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else if (requestCode == TopUpActivity.REQ_LOGIN) {
                            Intent intent = new Intent(LoginActivity.this, TopUpActivity.class);
                            intent.putExtra("requestCode", REQ_TOPUP);
                            startActivity(intent);
                        }
                    }

                }
            }
        });
    }


        /*
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoginApiTask loginApiTask = new LoginApiTask();
            loginApiTask.execute(email, password);
        }
        else {
            Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_SHORT).show();
        }
        */





    /*
    private class LoginApiTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            String email = params[0];
            String password = params[1];

            try {

                URL url = new URL(ServerAPI.LOGIN);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", email)
                        .appendQueryParameter("password", password);
                String query = builder.build().getEncodedQuery();

                Log.d("LoginApiTask", query);

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os,"UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                urlConnection.connect();

                BufferedInputStream in = new BufferedInputStream((urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                byte[] buffer = new byte[8];
                int i;
                while ((i = in.read(buffer)) != -1) {
                    stringBuilder.append(new String(buffer, 0, i));
                }

                urlConnection.disconnect();

                String result = stringBuilder.toString();

                if (result.equals("success")) {
                    return true;
                }
                else {
                    return false;
                }

            } catch (MalformedURLException e) {
                Log.d("LoginApiTask", e.getMessage());
                return false;
            } catch (IOException e) {
                Log.d("LoginApiTask", e.getMessage());
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                String message = appEmail.getText().toString();
                intentMain.putExtra(EXTRA_MESSAGE, message);
                startActivity(intentMain);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aBoolean);
        }
    }
    */
}

