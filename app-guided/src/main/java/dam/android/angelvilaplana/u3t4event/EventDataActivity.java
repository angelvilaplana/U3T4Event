package dam.android.angelvilaplana.u3t4event;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class EventDataActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private String priority = "Normal";

    private TextView tvEventName;
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
    }

    private void setUI() {
        tvEventName = findViewById(R.id.tvEventName);
        rgPriority = findViewById(R.id.rgPriority);
        rgPriority.check(R.id.rbNormal);
        dpDate = findViewById(R.id.dpDate);
        tpTime = findViewById(R.id.tpTime);
        tpTime.setIs24HourView(true);
        btAccept = findViewById(R.id.btAccept);
        btCancel = findViewById(R.id.btCancel);

        // Set listeners
        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgPriority.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent activityResult = new Intent();
        Bundle eventData = new Bundle();

        switch (view.getId()) {
            case R.id.btAccept:
                String[] month = {"January", "February", "March", "April", "May", "June", "July", "August",
                        "September", "October", "November", "December"};
                eventData.putString("EventData", "Priority: " + priority + "\n" +
                                                  "Month: " + month[dpDate.getMonth()] + "\n" +
                                                  "Day: " + dpDate.getDayOfMonth() + "\n" +
                                                  "Year: " + dpDate.getYear());
                break;
            case R.id.btCancel:
                eventData.putString("EventData", "");
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