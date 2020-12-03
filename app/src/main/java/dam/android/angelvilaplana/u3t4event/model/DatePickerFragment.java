package dam.android.angelvilaplana.u3t4event.model;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Activity 4 - Add a dialog in Date & Hour
 */
public class DatePickerFragment extends DialogFragment implements Serializable {

    private int year;

    private int month;

    private int day;

    private DatePickerDialog.OnDateSetListener listener;

    public DatePickerFragment() {
        super();
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DatePickerDialog.OnDateSetListener) context;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    public void updateDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

}
