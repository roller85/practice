package id.co.okhome.okhome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderConfirmFragment extends Fragment {

    public static final String TAG = "OrderConfirmFragment";

    private TextView service_start_schedule;
    private TextView service_duration;
    private TextView service_period;
    private TextView service_charge;


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

        OrderActivity activity = (OrderActivity) getActivity();

        int month = activity.GetUserOrder().GetStartMonth();
        String service_start_month = intToMonth(month);

        int date = activity.GetUserOrder().GetStartDate();

        int day = activity.GetUserOrder().GetStartDay();
        String service_start_day = intIntoDay(day);

        String start_time = activity.GetUserOrder().GetStartTimeDay1();

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




        return fragment8View;
    }

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
