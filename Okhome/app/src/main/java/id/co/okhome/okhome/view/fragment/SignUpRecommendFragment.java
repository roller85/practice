package id.co.okhome.okhome.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import id.co.okhome.okhome.R;
import id.co.okhome.okhome.data.OrderInfo;
import id.co.okhome.okhome.view.activity.SignupActivity;
import id.co.okhome.okhome.view.activity.TopUpActivity;
import id.co.okhome.okhome.view.fragment.topup_fragment.TopUpInfoFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpRecommendFragment extends Fragment {

    public static final String TAG = "SignUpRecommendFragment";



    public SignUpRecommendFragment() {
        // Required empty public constructor
    }

    public static SignUpRecommendFragment newInstance() {
        SignUpRecommendFragment fragment = new SignUpRecommendFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView =inflater.inflate(R.layout.fragment_sign_up_recommend, container, false);

        fragmentView.findViewById(R.id.btn_signup_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                Intent intentSignUp = new Intent(topUpActivity, SignupActivity.class);
                intentSignUp.putExtra("requestCode", TopUpActivity.REQ_SIGNUP);
                topUpActivity.startActivityForResult(intentSignUp, TopUpActivity.REQ_SIGNUP_RETURN);
            }
        });

        fragmentView.findViewById(R.id.btn_continue_guest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID uuid = UUID.randomUUID();
                String number = uuid.toString();
                String guestNumber = "g-" + number;
                OrderInfo.getInstance().AddGuestInfo(guestNumber);

                int topUpAmountCash = 800000;
                int topUpAmountPoint = 800000;
                int bonus = 0;
                OrderInfo.getInstance().AddTopUpExpectedInfo(topUpAmountCash, topUpAmountPoint, bonus);

                TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                topUpActivity.nextFragment(TopUpInfoFragment.newInstance(), TopUpInfoFragment.TAG);
            }
        });

        return fragmentView;
    }

}