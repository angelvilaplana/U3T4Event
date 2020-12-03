package dam.android.angelvilaplana.u3t4event;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import dam.android.angelvilaplana.u3t4event.model.EventData;

public class EventDataActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private String priority = "Normal";

    private TextView tvEventName;
    // Activity 1.3 - EditText Place
    private EditText edPlaceName;
    private RadioGroup rgPriority;
    private DatePicker dpDate;
    private TimePicker tpTime;
    private Button btAccept;
    private Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        // Get data from Intent that started this activity
        Bundle inputData = getIntent().getExtras();

        // Set eventName from inputData
        tvEventName.setText(inputData.getString("EventName"));

        //Activity 1.3 - Set the last Event Data
        EventData eventData = (EventData) inputData.getSerializable("EventData");
        if (eventData != null) {
            edPlaceName.setText(eventData.getPlace());
            setRadioPriority(eventData.getPriority());
            dpDate.updateDate(eventData.getYear(), eventData.getMonth(), eventData.getDay());
            tpTime.setHour(eventData.getHour());
            tpTime.setMinute(eventData.getMinutes());
        }
    }

    private void setUI() {
        tvEventName = findViewById(R.id.tvEventName);
        // Activity 1.3 - EditText Place
        edPlaceName = findViewById(R.id.etPlaceName);
        rgPriority = findViewById(R.id.rgPriority);
        rgPriority.check(R.id.rbNormal);
        dpDate = findViewById(R.id.dpDate);
        tpTime = findViewById(R.id.tpTime);
        tpTime.setIs24HourView(true);
        btAccept = findViewById(R.id.btAccept);
        btCancel = findViewById(R.id.btCancel);

        // Activity 2 - Modify DatePicker
        setDpDateMode();

        // Set listeners
        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgPriority.setOnCheckedChangeListener(this);
    }

    /**
     * Activity 2 - Modify DatePicker
     * Show calendar depending on orientation
     */
    private void setDpDateMode() {
        int orientation = getResources().getConfiguration().orientation;
        dpDate.setCalendarViewShown(orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    private void setRadioPriority(String priority) {
        switch (priority) {
            case "Low":
                this.priority = "Low";
                rgPriority.check(R.id.rbLow);
                break;
            case "Normal":
                this.priority = "Normal";
                rgPriority.check(R.id.rbNormal);
                break;
            case "High":
                this.priority = "High";
                rgPriority.check(R.id.rbHigh);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        Intent activityResult = new Intent();
        Bundle eventData = new Bundle();

        switch (view.getId()) {
            case R.id.btAccept:
                // Activity 1.3 - Add event data
                String name = tvEventName.getText().toString();
                String place = edPlaceName.getText().toString();
                EventData event = new EventData(name, place, priority, dpDate.getDayOfMonth(), dpDate.getMonth(),
                                                dpDate.getYear(), tpTime.getHour(), tpTime.getMinute());
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbLow:
                priority = "Low";
                break;
            case R.id.rbNormal:
                priority = "Normal";
                break;
            case R.id.rbHigh:
                priority = "High";
                break;
        }
    }
}