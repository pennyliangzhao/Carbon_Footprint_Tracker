package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class CarbonFootprintRecorder extends BaseActivity {
    String pointsCar;
    String pointsFood;
    String pointsShop;
    String pointsAction;
    TextView tVC;
    TextView tVF;
    TextView tVS;
    TextView tVA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_footprint_recorder);

        Intent travelIntent = getIntent();
        pointsCar = (travelIntent.getStringExtra("pointsCar"));
        tVC = findViewById(R.id.travelText);
        tVC.setText(pointsCar);


        Intent foodIntent = getIntent();
        pointsFood = (foodIntent.getStringExtra("pointsFood"));
        tVF = findViewById(R.id.foodText);
        tVF.setText(pointsFood);


        Intent shopIntent = getIntent();
        pointsShop = (shopIntent.getStringExtra("pointsShop"));
        tVS = findViewById(R.id.shopText);
        tVS.setText(pointsShop);

        Intent actionIntent = getIntent();
        pointsAction = (actionIntent.getStringExtra("pointsAction"));
        tVA = findViewById(R.id.actionText);
        tVA.setText(pointsAction);


        //Get the user name from the SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("current_username", "userName");
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);
    }

    public void onRadioButtonClicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which RadioButton was clicked
        switch (view.getId()) {
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

    public void drawPieChart(View view) {
        Intent intent = new Intent(this, PointsPage.class);
        intent.putExtra("carPoints", String.valueOf(tVC));
        intent.putExtra("foodPoints", String.valueOf(tVF));
        intent.putExtra("foodPoints", String.valueOf(tVS));
        intent.putExtra("foodPoints", String.valueOf(tVA));
        startActivity(intent);
    }


}