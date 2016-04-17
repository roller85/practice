package id.co.okhome.okhome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

            }
        });
        return fragmentView;
    }

}
