package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class CarbonFootprintRecorder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_footprint_recorder);
    }

    public void onRadioButtonClicked(View view){
    // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which RadioButton was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked) {
                    Intent intent = new Intent(this, TravelRecorderPage.class);
                    startActivity(intent);
                }
                break;
            case R.id.radioButton2:
                if (checked) {
                    Intent intent = new Intent(this, FoodRecordPage.class);
                    startActivity(intent);
                }
                break;
            case (R.id.radioButton3):
                if (checked) {
                    Intent intent = new Intent(this, ShopRecordPage.class);
                    startActivity(intent);
                }
                break;
            case (R.id.radioButton4):
                if (checked) {
                    Intent intent = new Intent(this, ActionRecorderPage.class);
                    startActivity(intent);

                }
        }
    }
}