package id.co.okhome.okhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;



public class PackageSelectionFragment extends Fragment {

    public static final String TAG = "PackageSelectionFragment";

    private int cleaning_period;
    private String email;
    private TextView weekly_charge;
    private TextView weekly_charge_equation;
    private RadioGroup cleaningPackage;
    private int charge_per_hour;
    private int cleaning_hours;
    private int cleaning_days;
    private int charge_per_week;

    public PackageSelectionFragment() {
        // Required empty public constructor
    }

    public static PackageSelectionFragment newInstance() {
        PackageSelectionFragment fragment = new PackageSelectionFragment();
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment3View = inflater.inflate(R.layout.fragment_package_selection, container, false);

        email = getActivity().getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE2);
        weekly_charge = (TextView) fragment3View.findViewById(R.id.weeklyCharge);
        weekly_charge_equation = (TextView) fragment3View.findViewById(R.id.weeklyChargeEquation);

        //selection of package
        cleaningPackage = (RadioGroup) fragment3View.findViewById(R.id.cleaningPackageRegular);
        cleaningPackage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btn_triple:
                        cleaning_period = 3;
                        calculationOfWeeklyCharge(100000, 3, 3);
                        displayWeeklyCharge(charge_per_week, weekly_charge);
                        displayWeeklyChargeEquation(charge_per_hour, cleaning_hours, cleaning_days, weekly_charge_equation);
                        break;
                    case R.id.btn_double:
                        cleaning_period = 2;
                        calculationOfWeeklyCharge(100000, 4, 2);
                        displayWeeklyCharge(charge_per_week, weekly_charge);
                        displayWeeklyChargeEquation(charge_per_hour, cleaning_hours, cleaning_days, weekly_charge_equation);
                        break;
                    case R.id.btn_single:
                        cleaning_period = 1;
                        calculationOfWeeklyCharge(100000, 8, 1);
                        displayWeeklyCharge(charge_per_week, weekly_charge);
                        displayWeeklyChargeEquation(charge_per_hour, cleaning_hours, cleaning_days, weekly_charge_equation);
                        break;
                    case R.id.btn_onetime:
                        cleaning_period = 0;
                        calculationOfWeeklyCharge(150000, 8, 1);
                        displayWeeklyCharge(charge_per_week, weekly_charge);
                        displayWeeklyChargeEquation(charge_per_hour, cleaning_hours, cleaning_days, weekly_charge_equation);
                        break;
                }
            }
        });

        fragment3View.findViewById(R.id.btn_end_of_package_selection).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences shared = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putInt(OrderActivity.EXTRA_MESSAGE3, cleaning_period);
                editor.commit();
                OrderActivity activity = (OrderActivity) getActivity();
                activity.GetUserOrder().AddPeriodInfo(cleaning_period);
                activity.nextFragment(SelectDateFragment.newInstance(), SelectDateFragment.TAG);
            }
        });


        return fragment3View;
    }

    public void calculationOfWeeklyCharge(int perHour, int cleaningHours, int cleaningDays) {
        charge_per_hour = perHour;
        cleaning_hours = cleaningHours;
        cleaning_days = cleaningDays;

        charge_per_week = charge_per_hour*cleaning_hours*cleaning_days;
    }

    private void displayWeeklyCharge(int price, TextView tv) {
        this.weekly_charge = tv;
        weekly_charge.setText(String.valueOf(price) + getContext().getString(R.string.weekly_unit));
    }

    private void displayWeeklyChargeEquation(int price_per_hour, int working_hours, int working_days, TextView tv) {
        this.weekly_charge_equation = tv;
        weekly_charge_equation.setText(String.valueOf(price_per_hour) + getContext().getString(R.string.rupia_unit)
        + getContext().getString(R.string.multiply_unit) + String.valueOf(working_hours) + getContext().getString(R.string.hour_unit)
        + getContext().getString(R.string.multiply_unit) + String.valueOf(working_days) + getContext().getString(R.string.day_unit));
    }



}
