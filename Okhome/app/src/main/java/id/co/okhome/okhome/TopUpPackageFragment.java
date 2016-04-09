package id.co.okhome.okhome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpPackageFragment extends Fragment {

    public static final String TAG = "TopUpPackageFragment";


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
        return inflater.inflate(R.layout.fragment_top_up_package, container, false);
    }

}
