package id.co.okhome.okhome.view.fragment.order_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.co.okhome.okhome.R;
import id.co.okhome.okhome.data.OrderInfo;
import id.co.okhome.okhome.view.activity.MainActivity;
import id.co.okhome.okhome.view.activity.OrderActivity;
import id.co.okhome.okhome.view.activity.TopUpActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderConfirmFragment extends Fragment {

    public static final String TAG = "OrderConfirmFragment";


    private TextView service_start_schedule;
    private TextView service_duration;
    private TextView service_period;
    private TextView service_charge;
    private TextView service_every_or_not;
    private int visitDay1;
    private int visitDay2;
    private int visitDay3;
    private int balance;


    public OrderConfirmFragment() {
        // Required empty public constructor
    }

    public static OrderConfirmFragment newInstance() {
        OrderConfirmFragment fragment = new OrderConfirmFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment8View = inflater.inflate(R.layout.fragment_order_confirm, container, false);

        try {
            balance = OrderInfo.getInstance().GetBalance();
        } catch (NullPointerException e) {
            OrderInfo.getInstance().AddBalanceInfo(-1);
        }

        OrderActivity activity = (OrderActivity) getActivity();

        int month = OrderInfo.getInstance().GetStartMonth();

        String service_start_month = intToMonth(month);

        int date = OrderInfo.getInstance().GetStartDate();

        int day = OrderInfo.getInstance().GetStartDay();

        String service_start_day = intIntoDay(day);

        String start_time = OrderInfo.getInstance().GetStartTimeDay1();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(service_start_month);
        stringBuilder.append(" ");
        stringBuilder.append(date);
        stringBuilder.append(" ");
        stringBuilder.append(service_start_day);
        stringBuilder.append(" ");
        stringBuilder.append(start_time);

        service_start_schedule = (TextView) fragment8View.findViewById(R.id.txv_service_start_schedule);
        service_start_schedule.setText(stringBuilder);

        int duration = OrderInfo.getInstance().GetCleaningHours();

        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(duration);
        stringBuilder1.append(" ");
        stringBuilder1.append("HOURS");
        stringBuilder1.append(" / ");
        stringBuilder1.append("DAY");

        service_duration = (TextView) fragment8View.findViewById(R.id.txv_service_duration);
        service_duration.setText(stringBuilder1);

        service_every_or_not = (TextView) fragment8View.findViewById(R.id.txv_every_or_not);
        if (OrderInfo.getInstance().GetPeriodInfo()==0) {
            service_every_or_not.setText("in");
        } else {
            service_every_or_not.setText("in every");
        }

        visitDay1 = OrderInfo.getInstance().GetVisitDay1();
        visitDay2 = OrderInfo.getInstance().GetVisitDay2();
        visitDay3 = OrderInfo.getInstance().GetVisitDay3();

        String day1 = intIntoDay(visitDay1);
        String day2 = intIntoDay(visitDay2);
        String day3 = intIntoDay(visitDay3);

        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(day1);
        if (day2 != null) {
            stringBuilder2.append(" / ");
            stringBuilder2.append(day2);
            if (day3 != null) {
                stringBuilder2.append(" / ");
                stringBuilder2.append(day3);
            }
        }



        service_period = (TextView) fragment8View.findViewById(R.id.txv_service_days);
        service_period.setText(stringBuilder2);

        int chargePerWeek = OrderInfo.getInstance().GetChargePerWeek();

        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(chargePerWeek);
        stringBuilder3.append(" Rp/week");

        service_charge = (TextView) fragment8View.findViewById(R.id.txv_service_charge);
        service_charge.setText(stringBuilder3);

        fragment8View.findViewById(R.id.btn_end_of_order_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OrderInfo.getInstance().GetUserEmailInfo().equals("guest")) {
                    OrderActivity activity1 = (OrderActivity) getActivity();
                    Intent intent = new Intent(activity1, TopUpActivity.class);
                    intent.putExtra("requestCode", OrderActivity.REQ_TOPUP);
                    activity1.startActivityForResult(intent, OrderActivity.REQ_TOPUP_RETURN);
                } else if (balance > 0) {

                    //if balance is greater than 0, need to upload this order
                    //발란스있으면, 여기까지 오더를 올려야 함
                    //0이 아니라 최소 청소 한번 할 수 있는 금액으로 바꿔야함
                    //AddUserConditioninfo();

                    OrderActivity activity1 = (OrderActivity) getActivity();
                    Intent intent = new Intent(activity1, MainActivity.class);
                    startActivity(intent);
                } else {
                    OrderActivity activity1 = (OrderActivity) getActivity();
                    Intent intent = new Intent(activity1, TopUpActivity.class);
                    intent.putExtra("requestCode", OrderActivity.REQ_TOPUP);
                    activity1.startActivityForResult(intent, OrderActivity.REQ_TOPUP_RETURN);
                }
            }

        });

        return fragment8View;
    }

    /*
    private void AddUserConditioninfo() {
        OrderActivity orderActivity = new OrderActivity();
        final ProgressDialog p = ProgressDialog.show(orderActivity,"Loading","");

        RestClientFactory.updateUserConditionRestClient().updateUserCondition("2", "3",
                OrderInfo.getInstance().GetHomeType(),
                OrderInfo.getInstance().GetHomeSize(),
                OrderInfo.getInstance().GetHomeOwnerExistence(),
                OrderInfo.getInstance().GetPetInfo(),
                OrderInfo.getInstance().GetHomeDetailInfo(),
                OrderInfo.getInstance().GetUseOwnerTools(),
                OrderInfo.getInstance().GetStartDate(),
                OrderInfo.getInstance().GetPeriodInfo(),
                OrderInfo.getInstance().)
    }
    */

    /*
    private void attemptAddOrderInfo() {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email)
                .appendQueryParameter("bedroom", String.valueOf(number_of_bedroom))
                .appendQueryParameter("bathroom", String.valueOf(number_of_bathroom))
                .appendQueryParameter("homeType", home_type)
                .appendQueryParameter("homeSize", home_size)
                .appendQueryParameter("homeownerExistence", String.valueOf(homeowner_existence))
                .appendQueryParameter("catExistence", String.valueOf(pet_cat_existence))
                .appendQueryParameter("dogExistence", String.valueOf(pet_dog_existence))
                .appendQueryParameter("otherExistence", String.valueOf(pet_other_existence))
                .appendQueryParameter("nonexist", String.valueOf(pet_nonexist))
                .appendQueryParameter("detailsAboutHome", detail)
                .appendQueryParameter("period", period)
                .appendQueryParameter("visitDay1", visitDay1)
                .appendQueryParameter("visitDay2", visitDay2)
                .appendQueryParameter("visitDay3", visitDay3)
                .appendQueryParameter("timeDay1", timeDay1)
                .appendQueryParameter("timeDay2", timeDay2)
                .appendQueryParameter("timeDay3", timeDay3)
                .appendQueryParameter("hostCity", hostCity)
                .appendQueryParameter("hostRegion", hostRegion)
                .appendQueryParameter("hostDistrict", hostDistrict)
                .appendQueryParameter("detailedAddress", detailedAddress)
                .appendQueryParameter("locationDetail", locationDetail)
                .appendQueryParameter("hostName", hostName)
                .appendQueryParameter("hostPhoneNumber", hostPhoneNumber)
                ;
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.BASICINFO1ADD)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(getActivity().findViewById(R.id.fragment_basic_info1), "Okhttp : " + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Snackbar.make(getActivity().findViewById(R.id.fragment_basic_info1), "Okhttp : " + response.body().string(), Snackbar.LENGTH_LONG).show();
                OrderActivity activity = (OrderActivity) getActivity();
                activity.GetUserOrder().AddHomeInfo(number_of_bedroom, number_of_bathroom, home_type, home_size, homeowner_existence);
                activity.GetUserOrder().AddPetInfo(pet_cat_existence, pet_dog_existence, pet_other_existence, pet_nonexist);
                activity.nextFragment(BasicInfoFragment2.newInstance(), "basic_info_fragment2");
            }
        });
    }
    */

    public String intToMonth(int month) {
        if (month==1) { return "JAN";
        } else if (month==2) { return  "FEB";
        } else if (month==3) { return "MAR";
        } else if (month==4) { return  "APR";
        } else if (month==5) { return "MAY";
        } else if (month==6) { return "JUN";
        } else if (month==7) { return "JUL";
        } else if (month==8) { return "AUG";
        } else if (month==9) { return "SEP";
        } else if (month==10) { return "OCT";
        } else if (month==11) { return "SEP";
        } else { return "DEC";
        }
    }

    public String intIntoDay(int day) {
        if (day == 1) {
            return (String) getText(R.string.monday);
        }
        if (day == 2) {
            return (String) getText(R.string.tuesday);
        }
        if (day == 3) {
            return (String) getText(R.string.wednesday);
        }
        if (day == 4) {
            return (String) getText(R.string.thursday);
        }
        if (day == 5) {
            return (String) getText(R.string.friday);
        }
        if (day == 6) {
            return (String) getText(R.string.saturday);
        }
        if (day == 7) {
            return (String) getText(R.string.sunday);
        }
        else {
            return null;
        }
    }
}
