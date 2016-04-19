package id.co.okhome.okhome;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import id.co.okhome.okhome.Data.OrderInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpPackageFragment extends Fragment {

    public static final String TAG = "TopUpPackageFragment";

    private RadioGroup topUpPackage;
    private int topUpAmountCash;
    private int topUpAmountPoint;
    private String user_token;
    private String email;


    public TopUpPackageFragment() {
        // Required empty public constructor
    }

    public static TopUpPackageFragment newInstance() {
        TopUpPackageFragment fragment = new TopUpPackageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_top_up_package, container, false);

        topUpPackage = (RadioGroup) fragmentView.findViewById(R.id.rdb_select_topup_pkg);
        topUpPackage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_top_up_10m:
                        topUpAmountCash = 10000000;
                        topUpAmountPoint = 11000000;
                        break;

                    case R.id.btn_top_up_5m:
                        topUpAmountCash = 5000000;
                        topUpAmountPoint = 5400000;
                        break;

                    case R.id.btn_top_up_2500k:
                        topUpAmountCash = 2500000;
                        topUpAmountPoint = 2625000;
                        break;

                    case R.id.btn_top_up_1000k:
                        topUpAmountCash = 1000000;
                        topUpAmountPoint = 1040000;
                        break;

                    case R.id.btn_top_up_800k:
                        topUpAmountCash = 800000;
                        topUpAmountPoint = 800000;
                        break;

                }
            }
        });

        TopUpActivity topUpActivity = (TopUpActivity) getActivity();
        SharedPreferences sharedPreferences = topUpActivity.getSharedPreferences(LoginActivity.KEY_USER_DATA, Context.MODE_PRIVATE);
        user_token = sharedPreferences.getString(LoginActivity.KEY_USER_DATA_TOKEN, "noToken");

        fragmentView.findViewById(R.id.btn_send_transfer_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something for send SMS msg to User
            }
        });

        fragmentView.findViewById(R.id.btn_end_of_top_up_package).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUpActivity activityTopUp = (TopUpActivity)  getActivity();
                OrderInfo.getInstance().AddTopUpExpectedInfo(topUpAmountCash, topUpAmountPoint);
                email = OrderInfo.getInstance().GetUserEmailInfo();
                if(email != null) {
                    activityTopUp.nextFragment(TopUpInfoFragment.newInstance(), TopUpInfoFragment.TAG);
                } else {
                    activityTopUp.nextFragment(SignUpRecommendFragment.newInstance(), SignUpRecommendFragment.TAG);
                }

            }
        });

        return fragmentView;
    }

}
