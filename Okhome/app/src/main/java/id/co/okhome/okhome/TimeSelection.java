package id.co.okhome.okhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TimeSelection extends Fragment implements TimePickerFragment.PickTime {

    private String email;
    private int visitDay1;
    private int visitDay2;
    private int visitDay3;
    private String day1;
    private String day2;
    private String day3;
    private int hourDay1;
    private int minDay1;
    Button btn1;
    Button btn2;
    Button btn3;

    public TimeSelection() {
        // Required empty public constructor
    }

    public static TimeSelection newInstance() {
        TimeSelection fragment = new TimeSelection();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragment5View = inflater.inflate(R.layout.fragment_time_selection, container, false);

        email = getActivity().getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE2);

        SharedPreferences shared1 = getActivity().getPreferences(Context.MODE_PRIVATE);
        visitDay1 = shared1.getInt(OrderActivity.EXTRA_MESSAGE4, 0);
        SharedPreferences shared2 = getActivity().getPreferences(Context.MODE_PRIVATE);
        visitDay2 = shared2.getInt(OrderActivity.EXTRA_MESSAGE5, 0);
        SharedPreferences shared3 = getActivity().getPreferences(Context.MODE_PRIVATE);
        visitDay3 = shared3.getInt(OrderActivity.EXTRA_MESSAGE6, 0);

        for(int i =1; i < 8; i++) {
            if (visitDay1 == i) {
                day1 = intIntoDay(i);
            } else if (visitDay2 == i) {
                day2 = intIntoDay(i);
            }
            else if (visitDay3 ==i) {
                day3 = intIntoDay(i);
            }
        }

        TextView view1 = (TextView) fragment5View.findViewById(R.id.cleaningDay1);
        view1.setText(day1);
        TextView view2 = (TextView) fragment5View.findViewById(R.id.cleaningDay2);
        view2.setText(day2);
        TextView view3 = (TextView) fragment5View.findViewById(R.id.cleaningDay3);
        view3.setText(day3);

        /*
        fragment5View.findViewById(R.id.time_picker1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    android.support.v4.app.DialogFragment timeFragment = new TimePickerFragment();
                    timeFragment.show(getChildFragmentManager(), "timePicker");
                }

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    TimePickerFragment fragmentTime = (TimePickerFragment) getChildFragmentManager().findFragmentByTag("time_picker_fragment");
                    hourDay1 = fragmentTime.getHourPicked();
                    minDay1 = fragmentTime.getMinutePicked();

                    TextView v1 = (TextView) v.findViewById(R.id.time_picker1);
                    v1.setText(String.valueOf(hourDay1) + " : " + String.valueOf(minDay1));
                }
                return true;
            }
        });
        */

        btn1 = (Button) fragment5View.findViewById(R.id.time_picker1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getChildFragmentManager(), "timePicker");
            }

        });

        return fragment5View;
    }

    public void returnTime(String value) {
        btn1.setText(value);
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
