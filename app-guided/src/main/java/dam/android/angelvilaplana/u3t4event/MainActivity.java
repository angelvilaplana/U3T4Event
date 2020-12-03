package dam.android.angelvilaplana.u3t4event;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST = 0;

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
        // Add bundle to intent
        intent.putExtras(bundle);

        startActivityForResult(intent, REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST && resultCode == RESULT_OK) {
            tvCurrentData.setText(data.getStringExtra("EventData"));
        }
    }

}