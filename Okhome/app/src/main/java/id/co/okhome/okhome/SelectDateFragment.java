package id.co.okhome.okhome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.Calendar;

import id.co.okhome.okhome.Data.OrderInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectDateFragment extends Fragment {

    public static final String TAG = "SelectDateFragment";

    DatePicker datePicker;
    private int firstStartDate=0;
    private int firstStartDay=0;
    private int firstStartMonth=0;
    private int firstStartYear=0;

    public SelectDateFragment() {
        // Required empty public constructor
    }

    public static SelectDateFragment newInstance() {
        SelectDateFragment fragment = new SelectDateFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment6View = inflater.inflate(R.layout.fragment_select_date, container, false);

        datePicker = (DatePicker) fragment6View.findViewById(R.id.first_date_picker);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar1 = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, 2);
        calendar1.add(Calendar.MONTH, 2);
        datePicker.setMinDate(calendar.getTimeInMillis());
        datePicker.setMaxDate(calendar1.getTimeInMillis());

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DateTime dt = new DateTime(year, monthOfYear+1, dayOfMonth, 0, 0, 0);
                firstStartDay = dt.getDayOfWeek();
                firstStartDate = dt.getDayOfMonth();
                firstStartMonth = dt.getMonthOfYear();
                firstStartYear = dt.getYear();
                Toast.makeText(getActivity(), firstStartYear+"/"+firstStartMonth+"/"+firstStartDate+"/"+firstStartDay,Toast.LENGTH_SHORT).show();

            }
        });

        fragment6View.findViewById(R.id.btn_end_of_select_date_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstStartDate==0||firstStartDay==0||firstStartMonth==0||firstStartYear==0) {
                    Toast.makeText(getActivity(), "Please Select Starting Date", Toast.LENGTH_SHORT).show();
                } else {
                    OrderActivity activity = (OrderActivity) getActivity();
                    OrderInfo.getInstance().AddStartDayInfo(firstStartDay, firstStartDate, firstStartMonth, firstStartYear);
                    activity.nextFragment(SelectDayFragment.newInstance(), SelectDayFragment.TAG);
                }

            }
        });

        return fragment6View;
    }

}
