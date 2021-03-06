package id.co.okhome.okhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.joda.time.DateTime;


public class TimeSelectionFragment extends Fragment  {

    public static final String TAG = "TimeSelectionFragment";

    private String email;
    private int visitDay1;
    private int visitDay2;
    private int visitDay3;
    private String day1;
    private String day2;
    private String day3;
    public RadioGroup rg1_day1;
    public RadioGroup rg2_day1;
    public RadioGroup rg1_day2;
    public RadioGroup rg2_day2;
    public RadioGroup rg1_day3;
    public RadioGroup rg2_day3;
    public String day1Time;
    public String day2Time;
    public String day3Time;
    private String startMonth;
    private int startDate;
    private String startDay;

    public TimeSelectionFragment() {
        // Required empty public constructor
    }

    public static TimeSelectionFragment newInstance() {
        TimeSelectionFragment fragment = new TimeSelectionFragment();
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


        btn1 = (Button) fragment5View.findViewById(R.id.time_picker1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getChildFragmentManager(), "timePicker");
            }

        });
        */

        rg1_day1 = (RadioGroup) fragment5View.findViewById(R.id.rdb_select_time1_day1);
        rg2_day1 = (RadioGroup) fragment5View.findViewById(R.id.rdb_select_time2_day1);
        rg1_day1.clearCheck();
        rg2_day1.clearCheck();
        rg1_day1.setOnCheckedChangeListener(listener1);
        rg2_day1.setOnCheckedChangeListener(listener2);

        int chkId1_day1 = rg1_day1.getCheckedRadioButtonId();
        int chkId2_day1 = rg2_day1.getCheckedRadioButtonId();
        int chk_day1 = chkId1_day1 == -1 ? chkId2_day1 : chkId1_day1;

        switch (chk_day1) {
            case R.id.btn_eight_thirty_day1:
                day1Time = getString(R.string.eight_thirty);
                break;
            case R.id.btn_nine_zero_day1:
                day1Time = getString(R.string.nine_zero);
                break;
            case R.id.btn_fourteen_zero_day1:
                day1Time = getString(R.string.fourteen_zero);
                break;
            case R.id.btn_fourteen_thirty_day1:
                day1Time = getString(R.string.fourteen_thirty);
                break;
            case R.id.btn_nineteen_thirty_day1:
                day1Time = getString(R.string.nineteen_thirty);
                break;
            case R.id.btn_twenty_zero_day1:
                day1Time = getString(R.string.twenty_zero);
                break;
        }

        rg1_day2 = (RadioGroup) fragment5View.findViewById(R.id.rdb_select_time1_day2);
        rg2_day2 = (RadioGroup) fragment5View.findViewById(R.id.rdb_select_time2_day2);
        rg1_day2.clearCheck();
        rg2_day2.clearCheck();
        rg1_day2.setOnCheckedChangeListener(listener3);
        rg2_day2.setOnCheckedChangeListener(listener4);

        int chkId1_day2 = rg1_day2.getCheckedRadioButtonId();
        int chkId2_day2 = rg2_day2.getCheckedRadioButtonId();
        int chk_day2 = chkId1_day2 == -1 ? chkId2_day2 : chkId1_day2;

        switch (chk_day2) {
            case R.id.btn_eight_thirty_day2:
                day2Time = getString(R.string.eight_thirty);
                break;
            case R.id.btn_nine_zero_day2:
                day2Time = getString(R.string.nine_zero);
                break;
            case R.id.btn_fourteen_zero_day2:
                day2Time = getString(R.string.fourteen_zero);
                break;
            case R.id.btn_fourteen_thirty_day2:
                day2Time = getString(R.string.fourteen_thirty);
                break;
            case R.id.btn_nineteen_thirty_day2:
                day2Time = getString(R.string.nineteen_thirty);
                break;
            case R.id.btn_twenty_zero_day2:
                day2Time = getString(R.string.twenty_zero);
                break;
        }


        rg1_day3 = (RadioGroup) fragment5View.findViewById(R.id.rdb_select_time1_day3);
        rg2_day3 = (RadioGroup) fragment5View.findViewById(R.id.rdb_select_time2_day3);
        rg1_day3.clearCheck();
        rg2_day3.clearCheck();
        rg1_day3.setOnCheckedChangeListener(listener5);
        rg2_day3.setOnCheckedChangeListener(listener6);

        int chkId1_day3 = rg1_day3.getCheckedRadioButtonId();
        int chkId2_day3 = rg2_day3.getCheckedRadioButtonId();
        int chk_day3 = chkId1_day3 == -1 ? chkId2_day3 : chkId1_day3;

        switch (chk_day3) {
            case R.id.btn_eight_thirty_day3:
                day3Time = getString(R.string.eight_thirty);
                break;
            case R.id.btn_nine_zero_day3:
                day3Time = getString(R.string.nine_zero);
                break;
            case R.id.btn_fourteen_zero_day3:
                day3Time = getString(R.string.fourteen_zero);
                break;
            case R.id.btn_fourteen_thirty_day3:
                day3Time = getString(R.string.fourteen_thirty);
                break;
            case R.id.btn_nineteen_thirty_day3:
                day3Time = getString(R.string.nineteen_thirty);
                break;
            case R.id.btn_twenty_zero_day3:
                day3Time = getString(R.string.twenty_zero);
                break;
        }

        calculateNearestCleaningDay(visitDay1, visitDay2, visitDay3);
        TextView view4 = (TextView) fragment5View.findViewById(R.id.startDate);
        view4.setText(String.valueOf(startDate) + " " + startMonth + " " + startDay);

        return fragment5View;
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2_day1.setOnCheckedChangeListener(null);
                rg2_day1.clearCheck();
                rg2_day1.setOnCheckedChangeListener(listener2);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1_day1.setOnCheckedChangeListener(null);
                rg1_day1.clearCheck();
                rg1_day1.setOnCheckedChangeListener(listener1);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2_day2.setOnCheckedChangeListener(null);
                rg2_day2.clearCheck();
                rg2_day2.setOnCheckedChangeListener(listener4);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener listener4 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1_day2.setOnCheckedChangeListener(null);
                rg1_day2.clearCheck();
                rg1_day2.setOnCheckedChangeListener(listener3);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener5 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2_day3.setOnCheckedChangeListener(null);
                rg2_day3.clearCheck();
                rg2_day3.setOnCheckedChangeListener(listener6);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener listener6 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1_day3.setOnCheckedChangeListener(null);
                rg1_day3.clearCheck();
                rg1_day3.setOnCheckedChangeListener(listener5);
            }
        }
    };

    /*
    public void returnTime(String value) {
        btn1.setText(value);
    }
    */

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

    public void calculateNearestCleaningDay(int day1, int day2, int day3) {
        int daysToWait = 7;
        int i = 0;
        boolean found = false;
        DateTime dt = new DateTime();
        DateTime result = dt.plusDays(daysToWait);
        int dow = result.getDayOfWeek();

        while(!found) {
            if(dow+i == day1 || dow+i == day2 || dow+i == day3) {
                found = true;
                startDate = result.plusDays(i).getDayOfMonth();
                DateTime.Property pdow = result.plusDays(i).monthOfYear();
                startMonth = pdow.getAsShortText();
                int date = result.plusDays(i).getDayOfWeek();
                startDay = intIntoDay(date);
                break;
            } else {
                i++;
            }
        }

    }


}
