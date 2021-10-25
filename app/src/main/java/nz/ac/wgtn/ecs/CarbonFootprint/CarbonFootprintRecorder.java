package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class CarbonFootprintRecorder extends BaseActivity {
    int travelPoints;
    int foodPoints;
    int shopPoints;
    int actionPoints;
    TextView tVC;
    TextView tVF;
    TextView tVS;
    TextView tVA;
    private int userID;
    private MyDbAdapter myDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_footprint_recorder);

        myDbHelper = new MyDbAdapter(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userID = preferences.getInt("current_user_id", 0);
        String userName = preferences.getString("current_user","userName" );
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);

        travelPoints = myDbHelper.getTravelPoints(userID);
        tVC = findViewById(R.id.travelText);
        tVC.setText(String.valueOf(travelPoints));


        foodPoints = myDbHelper.getFoodPoints(userID);
        tVF = findViewById(R.id.foodText);
        tVF.setText(String.valueOf(foodPoints));

        shopPoints = myDbHelper.getShopPoints(userID);
        tVS = findViewById(R.id.shopText);
        tVS.setText(String.valueOf(shopPoints));

        actionPoints = myDbHelper.getActionPoints(userID);
        tVA = findViewById(R.id.actionText);
        tVA.setText(String.valueOf(actionPoints));


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


    public void GobackMenu(View view){
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }

    public void drawPieChart(View view) {
        Intent intent = new Intent(this, PointsPage.class);
        startActivity(intent);
    }


}