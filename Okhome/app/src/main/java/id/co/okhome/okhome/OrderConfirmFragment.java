package id.co.okhome.okhome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderConfirmFragment extends Fragment {

    public static final String TAG = "OrderConfirmFragment";


    public OrderConfirmFragment() {
        // Required empty public constructor
    }

    public static OrderConfirmFragment newInstance() {
        OrderConfirmFragment fragment = new OrderConfirmFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment8View = inflater.inflate(R.layout.fragment_order_confirm, container, false);

        return fragment8View;
    }

}
