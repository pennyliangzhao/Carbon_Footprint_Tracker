package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class ActionRecorderPage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_recorder_page);
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        // Check which RadioButton was clicked
        switch(view.getId()) {
            case R.id.conserveEnergyBtn:
                if (checked) {
                   // Intent intent = new Intent(this, ___.class);
                   // startActivity(intent);
                }
                break;
            case R.id.recycleBtn:
                if (checked) {
                  //  Intent intent = new Intent(this, ___.class);
                   // startActivity(intent);
                }
            case R.id.bikeMoreBtn:
                if (checked) {
                  //  Intent intent = new Intent(this, ___.class);
                   // startActivity(intent);
                }
                break;
            case (R.id.lessDisposablePackagingBtn):
                if (checked) {
                    //Intent intent = new Intent(this, ___.class);
                   // startActivity(intent);
                }
                break;
            case (R.id.plantTreeBtn):
                if (checked) {
                   // Intent intent = new Intent(this, ___.class);
                   // startActivity(intent);

                }
        }
    }
}
