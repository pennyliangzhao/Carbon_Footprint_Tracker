package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class PointsPage extends BaseActivity {


    private MyDbAdapter myDbHelper;
    private PieChart chart;

    private int travelPoints;
    private int foodPoints;
    private int shopPoints;
    private int actionPoints;
    private int i1;
    private int i2;
    private int i3;
    private int i4;
    private int userID;

    TextView tVC;
    TextView tVF;
    TextView tVS;
    TextView tVA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_page);

        myDbHelper = new MyDbAdapter(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userID = preferences.getInt("current_user_id", 0);
        String userName = preferences.getString("current_user","userName" );
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);

        travelPoints = myDbHelper.getTravelPoints(userID);
        foodPoints = myDbHelper.getFoodPoints(userID);
        shopPoints = myDbHelper.getShopPoints(userID);
        actionPoints = myDbHelper.getActionPoints(userID);

        int total = travelPoints + foodPoints + shopPoints + actionPoints;

        if(total == 0) {
            travelPoints = 1;
            foodPoints =1;
            shopPoints = 1;
            actionPoints = 1;
        }



        tVC = findViewById(R.id.textTravel);
        tVC.setText(String.valueOf(travelPoints));

        tVF = findViewById(R.id.textFood);
        tVF.setText(String.valueOf(foodPoints));

        tVS = findViewById(R.id.textShop);
        tVS.setText(String.valueOf(shopPoints));

        tVA = findViewById(R.id.textAction);
        tVA.setText(String.valueOf(actionPoints));



        i1 = travelPoints * 100 / total;
        i2 = foodPoints * 100 / total;
        i3 = shopPoints * 100 / total;
        i4 = actionPoints * 100 / total;



        chart = findViewById(R.id.pie_chart);
        addToPieChart();


    }

    public void GobackMenu(View view){
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }

    private void addToPieChart() {
        // add to pie chart

        chart.addPieSlice(new PieModel("Integer 1", i1, Color.parseColor("#FFA726")));
        chart.addPieSlice(new PieModel("Integer 2", i2, Color.parseColor("#66BB6A")));
        chart.addPieSlice(new PieModel("Integer 3", i3, Color.parseColor("#EF5350")));
        chart.addPieSlice(new PieModel("Integer 4", i4, Color.parseColor("#2986F6")));

        chart.startAnimation();

    }

    public void recordPoints(View view){
        Intent i = new Intent(this, CarbonFootprintRecorder.class);
        startActivity(i);
    }

}