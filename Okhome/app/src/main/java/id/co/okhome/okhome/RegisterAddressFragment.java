package id.co.okhome.okhome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterAddressFragment extends Fragment {

    public static final String TAG = "RegisterAddressFragment";

    private String host_city;
    private String host_region;
    private String host_district;
    private String host_detailed_address;
    private String host_location_detail;
    private String host_name;
    private String host_phone_number;

    public RegisterAddressFragment() {
        // Required empty public constructor
    }

    public static RegisterAddressFragment newInstance() {
        RegisterAddressFragment fragment = new RegisterAddressFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment7View = inflater.inflate(R.layout.fragment_register_address, container, false);

        host_region = getText(R.string.service_area_region).toString();
        host_city = getText(R.string.service_area_city).toString();

        Spinner district_spinner = (Spinner) fragment7View.findViewById(R.id.spn_district);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.district_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        district_spinner.setAdapter(adapter);

        host_district = district_spinner.getSelectedItem().toString();

        EditText detailed_address = (EditText) fragment7View.findViewById(R.id.etx_detailed_address);
        host_detailed_address = detailed_address.getText().toString();

        EditText location_detail = (EditText) fragment7View.findViewById(R.id.etx_location_detail);
        host_location_detail = location_detail.getText().toString();

        EditText hName = (EditText) fragment7View.findViewById(R.id.etx_host_name);
        host_name = hName.getText().toString();

        EditText hPhoneNumber = (EditText) fragment7View.findViewById(R.id.etx_host_phone_number);
        host_phone_number = hPhoneNumber.getText().toString();

        fragment7View.findViewById(R.id.btn_end_of_address1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity activity = (OrderActivity) getActivity();
                activity.GetUserOrder().AddAddressInfo(host_region, host_city, host_district, host_detailed_address,
                        host_location_detail, host_name, host_phone_number);
                activity.nextFragment(OrderConfirmFragment.newInstance(), OrderConfirmFragment.TAG);
            }
        });

        return fragment7View;
    }

}
