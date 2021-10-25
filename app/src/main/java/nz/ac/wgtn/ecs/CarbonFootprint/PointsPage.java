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

    private String pointsCar;
    private MyDbAdapter myDbHelper;
    String pointsFood;
    private Button click;
    private PieChart chart;
    // private TextView pointsCar;
    private int travel;
    private int food =50;
    private int shop = 30;
    private int action = 10;
    private int i1;
    private int i2;
    private int i3;
    private int i4;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_page);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("current_user", "userName");
        userID = preferences.getInt("current_user_id", 0);
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);

        //Get DB instance
        myDbHelper = new MyDbAdapter(this);


        travel = myDbHelper.getTravelPoints(userID);

        //get username
       // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);



//        Intent carIntent = getIntent();
//        pointsCar = carIntent.getStringExtra("pointsCar");
//        travel = Integer.parseInt(pointsCar);
//
//        Intent foodIntent = getIntent();
//        pointsFood = foodIntent.getStringExtra("pointsFood");
//        food = Integer.parseInt(pointsFood);


        int total = travel + food + shop + action;

        i1 = travel * 100 / total;
        i2 = food * 100 / total;
        i3 = shop * 100 / total;
        i4 = shop * 100 / total;
//
        click = findViewById(R.id.btn_click);
        chart = findViewById(R.id.pie_chart);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToPieChart();
                Toast.makeText(PointsPage.this, pointsCar, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void addToPieChart() {
        // add to pie chart

        chart.addPieSlice(new PieModel("Integer 1", i1, Color.parseColor("#FFA726")));
        chart.addPieSlice(new PieModel("Integer 2", i2, Color.parseColor("#66BB6A")));
        chart.addPieSlice(new PieModel("Integer 3", i3, Color.parseColor("#EF5350")));
        chart.addPieSlice(new PieModel("Integer 4", i4, Color.parseColor("#2986F6")));

        chart.startAnimation();
        click.setClickable(false);
    }
}