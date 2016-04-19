package id.co.okhome.okhome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import id.co.okhome.okhome.Data.OrderInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpInfoFragment extends Fragment {

    public static final String TAG = "TopUpInfoFragment";

    private String acount_holder_name;
    private String user_bank_name;
    private String user_amount_transfer;
    private Button end_of_top_up_info;

    public TopUpInfoFragment() {
        // Required empty public constructor
    }

    public static TopUpInfoFragment newInstance() {
        TopUpInfoFragment fragment = new TopUpInfoFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_top_up_info, container, false);

        EditText holder_name = (EditText) fragmentView.findViewById(R.id.etx_account_holder_name);
        acount_holder_name = holder_name.getText().toString();

        EditText bank_name = (EditText) fragmentView.findViewById(R.id.etx_user_bank_name);
        user_bank_name = bank_name.getText().toString();

        EditText amount_transfer = (EditText) fragmentView.findViewById(R.id.etx_user_amount_transfer);
        user_amount_transfer = amount_transfer.getText().toString();

        end_of_top_up_info = (Button) fragmentView.findViewById(R.id.btn_end_of_top_up_info);

        end_of_top_up_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUpActivity activityTopUp = (TopUpActivity) getActivity();
                OrderInfo.getInstance().AddTopUpUserInfo(acount_holder_name, user_bank_name, user_amount_transfer);
                activityTopUp.nextFragment(TopUpConfirmFragment.newInstance(), TopUpConfirmFragment.TAG);

            }
        });


        return fragmentView;
    }

}
