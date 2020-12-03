package dam.android.angelvilaplana.u3t4event;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import dam.android.angelvilaplana.u3t4event.model.EventData;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST = 0;

    // Activity 1.3 - Get event data
    private EventData eventData = null;

    private EditText etEventName;
    private TextView tvCurrentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI() {
        etEventName = findViewById(R.id.etEventName);
        tvCurrentData = findViewById(R.id.tvCurrentData);
        // Clean text view
        tvCurrentData.setText("");
    }

    public void editEventData(View view) {
        Intent intent = new Intent(this, EventDataActivity.class);
        Bundle bundle = new Bundle();

        // Set info data to bundle
        bundle.putString("EventName", etEventName.getText().toString());

        // Activity 1.3 - Set event info data to bundle if eventData is not null
        if (eventData != null) {
            bundle.putSerializable("EventData", eventData);
        }

        // Add bundle to intent
        intent.putExtras(bundle);

        startActivityForResult(intent, REQUEST);
    }

    /**
     * Activity 1.3
     * Save the event data
     * @param outState Where save the data
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("EventData", eventData);
    }

    /**
     * Activity 1.3
     * Restore the event data
     * @param savedInstanceState Where restore the data
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        eventData = (EventData) savedInstanceState.getSerializable("EventData");
        if (eventData != null) {
            tvCurrentData.setText(eventData.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Activity 1.1 - Keep data
        EventData eventData = (EventData) data.getSerializableExtra("EventData");
        if (requestCode == REQUEST && resultCode == RESULT_OK && eventData != null) {
            // Activity 1.3 - Get values
            this.eventData = eventData;
            tvCurrentData.setText(eventData.toString());
        }
    }

}