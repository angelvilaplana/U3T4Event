package dam.android.angelvilaplana.u3t4event.model;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Activity 4 - Add a dialog in Date & Hour
 */
public class TimePickerFragment extends DialogFragment implements Serializable {

    private int hour;

    private int minute;

    private final boolean is24HourView;

    private TimePickerDialog.OnTimeSetListener listener;

    public TimePickerFragment() {
        super();
        setCurrentTime();
        this.is24HourView = false;
    }

    public TimePickerFragment(boolean is24HourView) {
        setCurrentTime();
        this.is24HourView = is24HourView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), listener, hour, minute, is24HourView);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (TimePickerDialog.OnTimeSetListener) context;
    }

    public void updateTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    private void setCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

}
