package dam.android.angelvilaplana.u3t4event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import dam.android.angelvilaplana.u3t4event.model.DatePickerFragment;
import dam.android.angelvilaplana.u3t4event.model.EventData;
import dam.android.angelvilaplana.u3t4event.model.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class EventDataActivity extends AppCompatActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private String priority;

    // Extra - Get position scroll
    protected ScrollView scrollView;

    // Activity 4 - Save event data
    private TextView tvEventName;

    // Activity 1.3 - EditText Place
    private EditText edPlaceName;
    private RadioGroup rgPriority;

    // Activity 4 - Buttons display a dialog with date & another with time
    private Button btSelectDate;
    private Button btSelectTime;

    // Activity 4 - Dialogs Date & Time
    private DatePickerFragment datePickerFragment;
    private TimePickerFragment timePickerFragment;

    // Activity 4 - EditText Date & Time
    private EditText etDate;
    private EditText etTime;

    private Button btAccept;
    private Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        // Set default priority
        priority = getString(R.string.rbNormal);

        // Get data from Intent that started this activity
        Bundle inputData = getIntent().getExtras();

        // Set eventName from inputData
        tvEventName.setText(inputData.getString("EventName"));

        // Activity 1.3 - Set the last Event Data
        EventData eventData = (EventData) inputData.getSerializable("EventData");

        // Activity 4 - Set Date & Time dialogs
        // Check if screen rotate or not
        if (savedInstanceState == null) {
            datePickerFragment = new DatePickerFragment();
            timePickerFragment = new TimePickerFragment(true);
        } else {
            datePickerFragment = (DatePickerFragment) savedInstanceState.getSerializable("datePicker");
            timePickerFragment = (TimePickerFragment) savedInstanceState.getSerializable("timePicker");
        }

        // Activity 4 - Check if not change the screen orientation
        // and has the last event
        if (savedInstanceState == null && eventData != null) {
            edPlaceName.setText(eventData.getPlace());
            setRadioPriority(eventData.getPriority());
            // Activity 4 - Enter data to dialog with date & time
            datePickerFragment.updateDate(eventData.getYear(), eventData.getMonth(), eventData.getDay());
            timePickerFragment.updateTime(eventData.getHour(), eventData.getMinutes());
        }

        // Activity 4 - Set text in EditText Date & Time
        setDateInEditText(datePickerFragment.getYear(), datePickerFragment.getMonth(), datePickerFragment.getDay());
        setTimeInEditText(timePickerFragment.getHour(), timePickerFragment.getMinute());

        // Activity 4 - Set listenere DatePicker & TimePicker
        datePickerFragment.setListener(this);
        timePickerFragment.setListener(this);
    }

    private void setUI() {
        // Extra - Get scroll
        scrollView = findViewById(R.id.scrollView);

        tvEventName = findViewById(R.id.tvEventName);
        // Activity 1.3 - EditText Place
        edPlaceName = findViewById(R.id.etPlaceName);
        rgPriority = findViewById(R.id.rgPriority);
        rgPriority.check(R.id.rbNormal);

        // Activity 4 - Buttons Date & Time dialogs
        btSelectDate = findViewById(R.id.btSelectDate);
        btSelectTime = findViewById(R.id.btSelectTime);

        // Activity 4 - EditText Date & Time
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);

        btAccept = findViewById(R.id.btAccept);
        btCancel = findViewById(R.id.btCancel);

        // Set listeners
        btSelectDate.setOnClickListener(this);
        btSelectTime.setOnClickListener(this);
        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgPriority.setOnCheckedChangeListener(this);
    }

    /**
     * Translate priorities
     *
     * @param priority name Priority
     */
    private void setRadioPriority(String priority) {
        if (priority.equals(getString(R.string.rbLow))) {
            rgPriority.check(R.id.rbLow);
        } else if (priority.equals(getString(R.string.rbNormal))) {
            rgPriority.check(R.id.rbNormal);
        } else if (priority.equals(getString(R.string.rbHigh))) {
            rgPriority.check(R.id.rbHigh);
        }
    }

    /**
     * Activity 4 - Restore the position y on scroll
     *
     * @param savedInstanceState Where the data is stored
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final int positionY = savedInstanceState.getInt("positionY");
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.scrollTo(0, positionY);
            }
        });
    }

    /**
     * Activity 4 - Save the date & time picker
     *              & save the position y on scroll
     *
     * @param outState Where save the data
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("positionY", scrollView.getVerticalScrollbarPosition());
        outState.putSerializable("datePicker", datePickerFragment);
        outState.putSerializable("timePicker", timePickerFragment);
    }

    @Override
    public void onClick(View view) {
        // Activity 4 - Button display a dialog with date & time
        if (view.getId() == R.id.btSelectDate) {
            datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            return;
        } else if (view.getId() == R.id.btSelectTime) {
            timePickerFragment.show(getSupportFragmentManager(), "timePicker");
            return;
        }

        Intent activityResult = new Intent();
        Bundle eventData = new Bundle();

        switch (view.getId()) {
            case R.id.btAccept:
                // Activity 1.3 - Add event data
                String name = tvEventName.getText().toString();
                String place = edPlaceName.getText().toString();
                EventData event = new EventData(name, place, priority, datePickerFragment.getDay(), datePickerFragment.getMonth(),
                        datePickerFragment.getYear(), timePickerFragment.getHour(), timePickerFragment.getMinute());
                eventData.putSerializable("EventData", event);
                break;
            case R.id.btCancel:
                eventData.putSerializable("EventData", null);
                break;
        }
        activityResult.putExtras(eventData);
        setResult(RESULT_OK, activityResult);

        finish();
    }

    /**
     * Translate priorities
     *
     * @param group RadioGroup
     * @param checkedId Radio checked
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbLow:
                priority = getString(R.string.rbLow);
                break;
            case R.id.rbNormal:
                priority = getString(R.string.rbNormal);
                break;
            case R.id.rbHigh:
                priority = getString(R.string.rbHigh);
                break;
        }
    }

    /**
     * Activity 4 - Listener DatePickerDialog
     *              when accept changes
     *
     * @param view View
     * @param year Years select in calendar
     * @param month Month select in calendar
     * @param dayOfMonth Day of month select in calendar
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        datePickerFragment.updateDate(year, month, dayOfMonth);
        setDateInEditText(year, month, dayOfMonth);
    }

    /**
     * Activity 4 - Set in EditText Date the date
     *
     * @param year Year
     * @param month Month
     * @param dayOfMonth Day
     */
    private void setDateInEditText(int year, int month, int dayOfMonth) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, dayOfMonth);
        String text = DateFormat.getDateInstance(DateFormat.LONG).format(date.getTime());
        etDate.setText(text);
    }

    /**
     * Activity 4 - Listener TimePickerDialog
     *              when accept changes
     *
     * @param view View
     * @param hourOfDay Hour select in clcok
     * @param minute Minute select in clock
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timePickerFragment.updateTime(hourOfDay, minute);
        setTimeInEditText(hourOfDay, minute);
    }

    /**
     * Activity 4 - Set in EditText Time the time
     *
     * @param hourOfDay Hour of day
     * @param minute Minute
     */
    private void setTimeInEditText(int hourOfDay, int minute) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, hourOfDay);
        time.set(Calendar.MINUTE, minute);
        String text = DateFormat.getTimeInstance(DateFormat.SHORT).format(time.getTime());
        etTime.setText(text);
    }

}
