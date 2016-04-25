package id.co.okhome.okhome;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpConfirmFragment extends Fragment {

    public static final String TAG = "TopUpConfirmFragment";


    public TopUpConfirmFragment() {
        // Required empty public constructor
    }

    public static TopUpConfirmFragment newInstance() {
        TopUpConfirmFragment fragment = new TopUpConfirmFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_top_up_confirm, container, false);

        fragmentView.findViewById(R.id.btn_end_of_top_up_confirmation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUpActivity topUpActivity = (TopUpActivity) getActivity();
                int requestCode = topUpActivity.getIntent().getIntExtra("requestCode", -1);
                if (requestCode == MainActivity.REQ_TOPUP) {
                    //Need to do: send Top UP info to the server only
                    topUpActivity.setResult(Activity.RESULT_OK);
                    topUpActivity.finish();
                } else if (requestCode == OrderActivity.REQ_TOPUP) {
                    //Need to do: send Order info + Top up Info to server
                    topUpActivity.setResult(Activity.RESULT_OK);
                    topUpActivity.finish();

                } else {
                    TopUpActivity activity = (TopUpActivity) getActivity();
                    Intent intent = new Intent(activity, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
        return fragmentView;
    }

}
