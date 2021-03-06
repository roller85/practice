package id.co.okhome.okhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import id.co.okhome.okhome.Server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SelectDayFragment extends Fragment {

    public static final String TAG = "SelectDayFragment";

    private int visitDay1;
    private int visitDay2;
    private int visitDay3;
    private String email;
    private int cleaning_period;
    private int btnLimit;
    private int btnPressed = 0;
    private ArrayList<Integer> mSchedule;




    public SelectDayFragment() {
        // Required empty public constructor
    }

    public static SelectDayFragment newInstance() {
        SelectDayFragment fragment = new SelectDayFragment();
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

        Toast.makeText(getActivity(),"Cleaning period is "+cleaning_period, Toast.LENGTH_LONG).show();

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

        mSchedule = new ArrayList<>();

            ToggleButton tog_monday = (ToggleButton) fragment4View.findViewById(R.id.btn_monday);
            tog_monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSchedule.add(1);
                        btnPressed = btnPressed + 1;
                    } else {
                        for (int i = 0; i < mSchedule.size(); i++) {
                            if (mSchedule.get(i) == 1) {
                                mSchedule.remove(i);
                                btnPressed = btnPressed - 1;
                            }
                        }
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
                        for (int i = 0; i < mSchedule.size(); i++) {
                            if (mSchedule.get(i) == 2) {
                                mSchedule.remove(i);
                                btnPressed = btnPressed - 1;
                            }
                        }
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
                        for (int i = 0; i < mSchedule.size(); i++) {
                            if (mSchedule.get(i) == 3) {
                                mSchedule.remove(i);
                                btnPressed = btnPressed - 1;
                            }
                        }
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
                        for (int i = 0; i < mSchedule.size(); i++) {
                            if (mSchedule.get(i) == 4) {
                                mSchedule.remove(i);
                                btnPressed = btnPressed - 1;
                            }
                        }
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
                        for (int i = 0; i < mSchedule.size(); i++) {
                            if (mSchedule.get(i) == 5) {
                                mSchedule.remove(i);
                                btnPressed = btnPressed - 1;
                            }
                        }
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
                        for (int i = 0; i < mSchedule.size(); i++) {
                            if (mSchedule.get(i) == 6) {
                                mSchedule.remove(i);
                                btnPressed = btnPressed - 1;
                            }
                        }
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
                        for (int i = 0; i < mSchedule.size(); i++) {
                            if (mSchedule.get(i) == 7) {
                                mSchedule.remove(i);
                                btnPressed = btnPressed - 1;
                            }
                        }
                    }
                }
            });

        fragment4View.findViewById(R.id.btn_end_of_select_day).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (btnLimit == mSchedule.size()) {
                    allocateSchedule();

                    SharedPreferences shared1 = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = shared1.edit();
                    editor1.putInt(OrderActivity.EXTRA_MESSAGE4, visitDay1);
                    editor1.commit();

                    SharedPreferences shared2 = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = shared2.edit();
                    editor2.putInt(OrderActivity.EXTRA_MESSAGE5, visitDay2);
                    editor2.commit();

                    SharedPreferences shared3 = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor3 = shared3.edit();
                    editor3.putInt(OrderActivity.EXTRA_MESSAGE6, visitDay3);
                    editor3.commit();

                    Toast.makeText(getActivity(),"VisitDay1 ="+visitDay1+"VisitDay2 ="+visitDay2+"VisitDay3 ="+visitDay3,Toast.LENGTH_LONG).show();
                    attmpAddCleanigPackage();
                }
                else {
                    Toast.makeText(getActivity(), "You need to choose more or less days", Toast.LENGTH_SHORT).show();
                }
            }
        });






        return fragment4View;
    }

    public void allocateSchedule() {
        Collections.sort(mSchedule);
        for(int i = 0; i <btnLimit; i++)
        {
            if (i == 0) {
                visitDay1 = mSchedule.get(i);
            } else if (i == 1) {
                visitDay2 = mSchedule.get(i);
            } else {
                visitDay3 = mSchedule.get(i);
            }
        }
    }

    public void attmpAddCleanigPackage() {

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email)
                .appendQueryParameter("period", String.valueOf(cleaning_period))
                .appendQueryParameter("visitDay1", String.valueOf(visitDay1))
                .appendQueryParameter("visitDay2", String.valueOf(visitDay2))
                .appendQueryParameter("visitDay3", String.valueOf(visitDay3));
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.CLEANINGPACKAGE)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(getActivity().findViewById(R.id.fragment_select_day), "Okhttp : " + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Snackbar.make(getActivity().findViewById(R.id.fragment_select_day), "Okhttp : " + response.body().string(), Snackbar.LENGTH_LONG).show();
                OrderActivity activity = (OrderActivity) getActivity();
                activity.GetUserOrder().AddVisitDayInfo(visitDay1, visitDay2, visitDay3);
                activity.nextFragment(TimeSelectionFragment.newInstance(), TimeSelectionFragment.TAG);

            }
        });
    }


}
