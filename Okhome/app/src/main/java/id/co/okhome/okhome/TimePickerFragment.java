package id.co.okhome.okhome;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */

/*
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public interface PickTime {
        void returnTime(String value);
    }

    PickTime mCallback;


    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState) {

        TimeSelectionFragment fragment = (TimeSelectionFragment) getParentFragment();
        mCallback = fragment;
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), AlertDialog.BUTTON_NEUTRAL,this,hour,minute, DateFormat.is24HourFormat(getActivity()));
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if(mCallback != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(hourOfDay);
            sb.append(":");
            sb.append(minute);
            mCallback.returnTime(sb.toString());
        }

    }

}
*/