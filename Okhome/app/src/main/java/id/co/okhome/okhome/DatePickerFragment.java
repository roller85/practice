package id.co.okhome.okhome;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {


    public DatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        TimeSelectionFragment timeSelectionFragment = (TimeSelectionFragment) getParentFragment();
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Calendar cal = Calendar.getInstance();
        int setDay = ((TimeSelectionFragment) getParentFragment()).getStartDate();
        cal.add(Calendar.DAY_OF_MONTH, 7);

        /*
        try {
            String dateString = String.valueOf(setDay)+"/"+String.valueOf(month)+"/"+ String.valueOf(year);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dateString);
            setDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), timeSelectionFragment , year, month, setDay);
        dialog.getDatePicker().setMinDate(cal.getTimeInMillis());


        return dialog;
    }


}
