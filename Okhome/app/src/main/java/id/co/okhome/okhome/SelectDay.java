package id.co.okhome.okhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;


public class SelectDay extends Fragment {

    private int visitDay1;
    private int visitDay2;
    private int visitDay3;
    private String email;
    private int cleaning_period;
    private int btnLimit;
    private int btnPressed = 0;
    private ArrayList<Integer>  mSchedule;



    public SelectDay() {
        // Required empty public constructor
    }

    public static SelectDay newInstance() {
        SelectDay fragment = new SelectDay();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragment4View = inflater.inflate(R.layout.fragment_select_day, container, false);

        email = getActivity().getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE2);
        SharedPreferences shared = getActivity().getPreferences(Context.MODE_PRIVATE);
        cleaning_period = shared.getInt(OrderActivity.EXTRA_MESSAGE3,0);

        switch (cleaning_period) {
            case 0:
                btnLimit = 1;
                break;
            case 1:
                btnLimit = 1;
                break;
            case 2:
                btnLimit = 2;
                break;
            case 3:
                btnLimit = 3;
                break;
        }

        while (btnPressed < btnLimit) {

            ToggleButton tog_monday = (ToggleButton) fragment4View.findViewById(R.id.btn_monday);
            tog_monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(1);
                        btnPressed = btnPressed + 1;
                    } else {
                    }
                }
            });

            ToggleButton tog_tuesday = (ToggleButton) fragment4View.findViewById(R.id.btn_tuesday);
            tog_tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(2);
                        btnPressed = btnPressed + 1;
                    } else {
                    }
                }
            });

            ToggleButton tog_wednesday = (ToggleButton) fragment4View.findViewById(R.id.btn_wednesday);
            tog_wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(3);
                        btnPressed = btnPressed + 1;
                    } else {
                    }
                }
            });

            ToggleButton tog_thursday = (ToggleButton) fragment4View.findViewById(R.id.btn_thursday);
            tog_thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(4);
                        btnPressed = btnPressed + 1;
                    } else {
                    }
                }
            });

            ToggleButton tog_friday = (ToggleButton) fragment4View.findViewById(R.id.btn_friday);
            tog_friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(5);
                        btnPressed = btnPressed + 1;
                    } else {
                    }
                }
            });

            ToggleButton tog_saturday = (ToggleButton) fragment4View.findViewById(R.id.btn_saturday);
            tog_saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(6);
                        btnPressed = btnPressed + 1;
                    } else {
                    }
                }
            });

            ToggleButton tog_sunday = (ToggleButton) fragment4View.findViewById(R.id.btn_sunday);
            tog_sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(7);
                        btnPressed = btnPressed + 1;
                    } else {
                    }
                }
            });

        }

        Collections.sort(mSchedule);
        for(int i = 0; i < mSchedule.size(); i++) {
            if(i==0) {
                visitDay1 = mSchedule.get(i);
            }
            else if (i==1) {
                visitDay2 = mSchedule.get(i);
            }
            else {
                visitDay3 = mSchedule.get(i);
            }
        }

        return fragment4View;
    }


}
