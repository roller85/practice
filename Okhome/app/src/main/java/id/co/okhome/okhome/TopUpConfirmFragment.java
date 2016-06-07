package id.co.okhome.okhome;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.io.IOException;

import id.co.okhome.okhome.data.OrderInfo;
import id.co.okhome.okhome.server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpConfirmFragment extends Fragment {

    public static final String TAG = "TopUpConfirmFragment";

    public int requestCode;


    public TopUpConfirmFragment() {
        // Required empty public constructor
    }

    public static TopUpConfirmFragment newInstance() {
        TopUpConfirmFragment fragment = new TopUpConfirmFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater.inflate(R.layout.fragment_top_up_confirm, container, false);

        fragmentView.findViewById(R.id.btn_end_of_top_up_confirmation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                requestCode = topUpActivity.getIntent().getIntExtra("requestCode", -1);
                if (requestCode == MainActivity.REQ_TOPUP) {
                    //Need to do: send Top UP info to the server only
                    try {
                        chargeInfoAdd();
                    } catch (Exception e) {
                        Snackbar.make(fragmentView.findViewById(R.id.fragment_top_up_confirm), e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }

                } else if (requestCode == OrderActivity.REQ_TOPUP) {
                    //Need to do: send Order info + Top up Info to server
                    if (OrderInfo.getInstance().GetUserEmailInfo().equals("guest")) {
                        try {
                            guestInfoAdd();
                        } catch (Exception e) {
                            Snackbar.make(fragmentView.findViewById(R.id.fragment_top_up_confirm), e.getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    } else  {
                        try {
                            userInfoAdd();
                        } catch (Exception e) {
                            Snackbar.make(fragmentView.findViewById(R.id.fragment_top_up_confirm), e.getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                } else if (requestCode == LoginActivity.REQ_TOPUP) {
                    try {
                        userInfoAdd();
                    } catch (Exception e) {
                        Snackbar.make(fragmentView.findViewById(R.id.fragment_top_up_confirm), e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    TopUpActivity activity = (TopUpActivity) getActivity();
                    Intent intent = new Intent(activity, MainActivity.class);
                    Toast.makeText(getActivity(), "Top Up has not been completed", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            }
        });
        return fragmentView;
    }

    private void chargeInfoAdd() {

        String email = OrderInfo.getInstance().GetUserEmailInfo();
        DateTime dateTime = new DateTime();
        int year = dateTime.getYear();
        String month = dateTime.toString("MM");
        String day = dateTime.toString("dd");
        String hour = dateTime.toString("HH");
        String min = dateTime.toString("mm");
        String sec = dateTime.toString("ss");
        String date = dateTime.toString();

        String bank_account_holder  = OrderInfo.getInstance().GetAccountHolderName();
        String bank_name = OrderInfo.getInstance().GetUserBankName();
        String user_phone_number = OrderInfo.getInstance().GetPhoneNumber();

        StringBuilder transfer_id = new StringBuilder();
        transfer_id.append(year);
        transfer_id.append(month);
        transfer_id.append(day);
        transfer_id.append(hour);
        transfer_id.append(min);
        transfer_id.append(sec);
        String transferID = transfer_id.toString();

        StringBuilder time = new StringBuilder();
        time.append(hour);
        time.append(":");
        time.append(min);
        time.append(":");
        time.append(sec);
        String time1 = time.toString();

        String y = String.valueOf(year);
        String m = String.valueOf(month);
        String d = String.valueOf(day);

        int amount = OrderInfo.getInstance().GetExpectedCash();
        String amount1 = String.valueOf(amount);

        int bonus = OrderInfo.getInstance().GetBonus();
        String bonus1 = String.valueOf(bonus);

        boolean transfer_done = false;
        String transferDone = String.valueOf(transfer_done);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("transferID", transferID)
                .appendQueryParameter("userEmail", email)
                .appendQueryParameter("user_phone_number", user_phone_number)
                .appendQueryParameter("bank_account_holder", bank_account_holder)
                .appendQueryParameter("bank_name", bank_name)
                .appendQueryParameter("year", y)
                .appendQueryParameter("date", date)
                .appendQueryParameter("time", time1)
                .appendQueryParameter("amount", amount1)
                .appendQueryParameter("bonus", bonus1)
                .appendQueryParameter("transferDone", transferDone);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.CHARGEINFOADD)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(getView().findViewById(R.id.fragment_top_up_confirm), e.getMessage(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String confirm = response.body().string();
                if (confirm.equals("update complete")) {
                    if (requestCode == LoginActivity.REQ_TOPUP) {
                        TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                        Intent intentToMain = new Intent(topUpActivity, MainActivity.class);
                        startActivity(intentToMain);
                    } else  {
                        Snackbar.make(getView().findViewById(R.id.fragment_top_up_confirm), response.body().toString(), Snackbar.LENGTH_LONG).show();
                        TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                        topUpActivity.setResult(Activity.RESULT_OK);
                        topUpActivity.finish();
                    }

                } else {
                    TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                    topUpActivity.setResult(Activity.RESULT_CANCELED);
                    topUpActivity.finish();
                }

            }
        });

    }

    private void userInfoAdd() {

        String email = OrderInfo.getInstance().GetUserEmailInfo();
        String name = OrderInfo.getInstance().GetHostName();

        int bedroom_number = OrderInfo.getInstance().GetNumberOfBedroom();
        String bedroom = String.valueOf(bedroom_number);

        int bathroom_number = OrderInfo.getInstance().GetNumberOfBathroom();
        String bathroom = String.valueOf(bathroom_number);

        String homeType = OrderInfo.getInstance().GetHomeType();
        String homeSize = OrderInfo.getInstance().GetHomeSize();

        boolean home_existence = OrderInfo.getInstance().GetHomeOwnerExistence();
        String home_owner_existence = String.valueOf(home_existence);

        boolean owner_tool = OrderInfo.getInstance().GetUseOwnerTools();
        String use_owner_tool = String.valueOf(owner_tool);

        String pet_category = OrderInfo.getInstance().GetPetInfo();
        String detail_home = OrderInfo.getInstance().GetHomeDetailInfo();

        int clean_period = OrderInfo.getInstance().GetPeriodInfo();
        String period = String.valueOf(clean_period);

        int day1 = OrderInfo.getInstance().GetVisitDay1();
        String visit_day1 = String.valueOf(day1);
        int day2 = OrderInfo.getInstance().GetVisitDay2();
        String visit_day2 = String.valueOf(day2);
        int day3 = OrderInfo.getInstance().GetVisitDay3();
        String visit_day3 = String.valueOf(day3);

        String time_day1 = OrderInfo.getInstance().GetStartTimeDay1();
        String time_day2 = OrderInfo.getInstance().GetStartTimeDay2();
        String time_day3 = OrderInfo.getInstance().GetStartTimeDay3();

        String region = OrderInfo.getInstance().GetHostRegion();
        String city = OrderInfo.getInstance().GetHostCity();
        String district = OrderInfo.getInstance().GetHostDistrict();
        String detail_address = OrderInfo.getInstance().GetHostDetailedAddress();
        String detail_location = OrderInfo.getInstance().GetHostLocationDetail();

        String phone_number = OrderInfo.getInstance().GetPhoneNumber();
        String bank_name = OrderInfo.getInstance().GetUserBankName();
        String bank_account_holder_name = OrderInfo.getInstance().GetAccountHolderName();


        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email)
                .appendQueryParameter("bedroom", bedroom)
                .appendQueryParameter("bathroom", bathroom)
                .appendQueryParameter("home_type", homeType)
                .appendQueryParameter("home_size", homeSize)
                .appendQueryParameter("home_owner_existence", home_owner_existence )
                .appendQueryParameter("pet_category", pet_category)
                .appendQueryParameter("detail_home", detail_home)
                .appendQueryParameter("period", period)
                .appendQueryParameter("visit_day1", visit_day1)
                .appendQueryParameter("visit_day2", visit_day2)
                .appendQueryParameter("visit_day3", visit_day3)
                .appendQueryParameter("time_day1", time_day1)
                .appendQueryParameter("time_day2", time_day2)
                .appendQueryParameter("time_day3", time_day3)
                .appendQueryParameter("region", region)
                .appendQueryParameter("city", city)
                .appendQueryParameter("district", district)
                .appendQueryParameter("detail_address", detail_address)
                .appendQueryParameter("detail_location", detail_location)
                .appendQueryParameter("host_name", name)
                .appendQueryParameter("phone", phone_number)
                .appendQueryParameter("bank_name", bank_name)
                .appendQueryParameter("account_holder_name", bank_account_holder_name)
                .appendQueryParameter("use_owner_tool", use_owner_tool);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.USERINFOADD)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(getView().findViewById(R.id.fragment_top_up_confirm), e.getMessage(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                if (message.equals("update complete")) {
                    chargeInfoAdd();
                } else {
                    TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                    topUpActivity.setResult(Activity.RESULT_CANCELED);
                    topUpActivity.finish();
                }

            }
        });

    }

    private void guestInfoAdd() {

        String guest_id = OrderInfo.getInstance().GetGuestInfo();
        String name = OrderInfo.getInstance().GetHostName();

        int bedroom_number = OrderInfo.getInstance().GetNumberOfBedroom();
        String bedroom = String.valueOf(bedroom_number);

        int bathroom_number = OrderInfo.getInstance().GetNumberOfBathroom();
        String bathroom = String.valueOf(bathroom_number);

        String homeType = OrderInfo.getInstance().GetHomeType();
        String homeSize = OrderInfo.getInstance().GetHomeSize();

        boolean home_existence = OrderInfo.getInstance().GetHomeOwnerExistence();
        String home_owner_existence = String.valueOf(home_existence);

        boolean owner_tool = OrderInfo.getInstance().GetUseOwnerTools();
        String use_owner_tool = String.valueOf(owner_tool);

        String pet_category = OrderInfo.getInstance().GetPetInfo();
        String detail_home = OrderInfo.getInstance().GetHomeDetailInfo();

        int clean_period = OrderInfo.getInstance().GetPeriodInfo();
        String period = String.valueOf(clean_period);

        int day1 = OrderInfo.getInstance().GetVisitDay1();
        String visit_day1 = String.valueOf(day1);
        int day2 = OrderInfo.getInstance().GetVisitDay2();
        String visit_day2 = String.valueOf(day2);
        int day3 = OrderInfo.getInstance().GetVisitDay3();
        String visit_day3 = String.valueOf(day3);

        String time_day1 = OrderInfo.getInstance().GetStartTimeDay1();
        String time_day2 = OrderInfo.getInstance().GetStartTimeDay2();
        String time_day3 = OrderInfo.getInstance().GetStartTimeDay3();

        String region = OrderInfo.getInstance().GetHostRegion();
        String city = OrderInfo.getInstance().GetHostCity();
        String district = OrderInfo.getInstance().GetHostDistrict();
        String detail_address = OrderInfo.getInstance().GetHostDetailedAddress();
        String detail_location = OrderInfo.getInstance().GetHostLocationDetail();

        final String phone_number = OrderInfo.getInstance().GetPhoneNumber();
        String bank_name = OrderInfo.getInstance().GetUserBankName();
        String bank_account_holder_name = OrderInfo.getInstance().GetAccountHolderName();


        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("guest_id", guest_id)
                .appendQueryParameter("bedroom", bedroom)
                .appendQueryParameter("bathroom", bathroom)
                .appendQueryParameter("home_type", homeType)
                .appendQueryParameter("home_size", homeSize)
                .appendQueryParameter("home_owner_existence", home_owner_existence )
                .appendQueryParameter("pet_category", pet_category)
                .appendQueryParameter("detail_home", detail_home)
                .appendQueryParameter("period", period)
                .appendQueryParameter("visit_day1", visit_day1)
                .appendQueryParameter("visit_day2", visit_day2)
                .appendQueryParameter("visit_day3", visit_day3)
                .appendQueryParameter("time_day1", time_day1)
                .appendQueryParameter("time_day2", time_day2)
                .appendQueryParameter("time_day3", time_day3)
                .appendQueryParameter("region", region)
                .appendQueryParameter("city", city)
                .appendQueryParameter("district", district)
                .appendQueryParameter("detail_address", detail_address)
                .appendQueryParameter("detail_location", detail_location)
                .appendQueryParameter("host_name", name)
                .appendQueryParameter("phone", phone_number)
                .appendQueryParameter("bank_name", bank_name)
                .appendQueryParameter("account_holder_name", bank_account_holder_name)
                .appendQueryParameter("use_owner_tool", use_owner_tool);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.GUESTINFOADD)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(getView().findViewById(R.id.fragment_top_up_confirm), e.getMessage(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String confirm = response.body().string();
                if (confirm.equals("success")) {
                    //need to show register number to guest with pop-up window in MainAct
                    String register = OrderInfo.getInstance().GetGuestInfo();
                    String register_number = register.substring(12);
                    chargeInfoAdd();

                } else if (confirm.equals("failed")) {
                    TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                    topUpActivity.setResult(Activity.RESULT_CANCELED);
                    topUpActivity.finish();
                }
            }
        });

    }

}
