package id.co.okhome.okhome.view.fragment.order_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import id.co.okhome.okhome.R;
import id.co.okhome.okhome.data.OrderInfo;
import id.co.okhome.okhome.view.activity.OrderActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeSelectionTwoDaysFragment extends Fragment {

    public static final String TAG = "TimeSelectionTwoDaysFragment";

    private String email;
    private int visitDay1;
    private int visitDay2;
    private String day1;
    private String day2;
    public RadioGroup rg1_day1;
    public RadioGroup rg2_day1;
    public RadioGroup rg1_day2;
    public RadioGroup rg2_day2;
    public String day1Time = " ";
    public String day2Time = " ";
    public String day3Time = " ";
    private String startMonth;
    private int startMonthInt;
    private int startDate;
    private String startDay;
    private int startYear;
    private String startSchedule;
    public TextView view4;


    public TimeSelectionTwoDaysFragment() {
        // Required empty public constructor
    }

    public static TimeSelectionTwoDaysFragment newInstance() {
        TimeSelectionTwoDaysFragment fragment = new TimeSelectionTwoDaysFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_time_selection_two_days, container, false);

        email = OrderInfo.getInstance().GetUserEmailInfo();
        visitDay1 = OrderInfo.getInstance().GetVisitDay1();
        visitDay2 = OrderInfo.getInstance().GetVisitDay2();

        for(int i =1; i < 8; i++) {
            if (visitDay1 == i) {
                day1 = intIntoDay(i);
            } else if (visitDay2 == i) {
                day2 = intIntoDay(i);
            }
        }

        TextView view1 = (TextView) fragmentView.findViewById(R.id.cleaningDay1_two_days);
        view1.setText(day1);
        TextView view2 = (TextView) fragmentView.findViewById(R.id.cleaningDay2_two_days);
        view2.setText(day2);

        rg1_day1 = (RadioGroup) fragmentView.findViewById(R.id.rdb_select_time1_day1_two_days);
        rg2_day1 = (RadioGroup) fragmentView.findViewById(R.id.rdb_select_time2_day1_two_days);
        rg1_day1.clearCheck();
        rg2_day1.clearCheck();
        rg1_day1.setOnCheckedChangeListener(listener1);
        rg2_day1.setOnCheckedChangeListener(listener2);

        rg1_day2 = (RadioGroup) fragmentView.findViewById(R.id.rdb_select_time1_day2_two_days);
        rg2_day2 = (RadioGroup) fragmentView.findViewById(R.id.rdb_select_time2_day2_two_days);
        rg1_day2.clearCheck();
        rg2_day2.clearCheck();
        rg1_day2.setOnCheckedChangeListener(listener3);
        rg2_day2.setOnCheckedChangeListener(listener4);

        OrderActivity activity = (OrderActivity) getActivity();
        view4 = (TextView) fragmentView.findViewById(R.id.startDate_two_days);
        startDate = OrderInfo.getInstance().GetStartDate();
        int month = OrderInfo.getInstance().GetStartMonth();
        startMonth = intToMonth(month);
        int day = OrderInfo.getInstance().GetStartDay();
        startDay = intIntoDay(day);
        view4.setText(String.valueOf(startDate) + " " + startMonth + " " + startDay);

        fragmentView.findViewById(R.id.btn_end_of_time_selection_two_days).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chkId1_day1 = rg1_day1.getCheckedRadioButtonId();
                int chkId2_day1 = rg2_day1.getCheckedRadioButtonId();
                int chk_day1 = chkId1_day1 == -1 ? chkId2_day1 : chkId1_day1;

                switch (chk_day1) {
                    case R.id.btn_eight_thirty_day1_two_days:
                        day1Time = getString(R.string.eight_thirty);
                        break;
                    case R.id.btn_nine_zero_day1_two_days:
                        day1Time = getString(R.string.nine_zero);
                        break;
                    case R.id.btn_fourteen_zero_day1_two_days:
                        day1Time = getString(R.string.fourteen_zero);
                        break;
                    case R.id.btn_fourteen_thirty_day1_two_days:
                        day1Time = getString(R.string.fourteen_thirty);
                        break;
                    case R.id.btn_nineteen_thirty_day1_two_days:
                        day1Time = getString(R.string.nineteen_thirty);
                        break;
                    case R.id.btn_twenty_zero_day1_two_days:
                        day1Time = getString(R.string.twenty_zero);
                        break;
                }

                int chkId1_day2 = rg1_day2.getCheckedRadioButtonId();
                int chkId2_day2 = rg2_day2.getCheckedRadioButtonId();
                int chk_day2 = chkId1_day2 == -1 ? chkId2_day2 : chkId1_day2;

                switch (chk_day2) {
                    case R.id.btn_eight_thirty_day2_two_days:
                        day2Time = getString(R.string.eight_thirty);
                        break;
                    case R.id.btn_nine_zero_day2_two_days:
                        day2Time = getString(R.string.nine_zero);
                        break;
                    case R.id.btn_fourteen_zero_day2_two_days:
                        day2Time = getString(R.string.fourteen_zero);
                        break;
                    case R.id.btn_fourteen_thirty_day2_two_days:
                        day2Time = getString(R.string.fourteen_thirty);
                        break;
                    case R.id.btn_nineteen_thirty_day2_two_days:
                        day2Time = getString(R.string.nineteen_thirty);
                        break;
                    case R.id.btn_twenty_zero_day2_two_days:
                        day2Time = getString(R.string.twenty_zero);
                        break;
                }

                if (day1Time.equals(" ")||day2Time.equals(" ")) {
                    Toast.makeText(getActivity(), "you need to select time", Toast.LENGTH_SHORT).show();
                } else {
                    OrderActivity activity = (OrderActivity) getActivity();
                    OrderInfo.getInstance().AddVisitTimeInfo(day1Time, day2Time, day3Time);
                    activity.nextFragment(BasicInfoFragment1.newInstance(), BasicInfoFragment1.TAG);
                }
            }
        });


        return fragmentView;
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


}
