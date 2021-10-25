package nz.ac.wgtn.ecs.CarbonFootprint;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ActionRecorderPage extends BaseActivity {
    private static final double CONSERVE_ENERGY_POINTS = 17.0;
    private static final double RECYCLE_POINTS = 20.0;
    private static final double BIKE_MORE_POINTS = 12.0;
    private static final double LESS_DISPOSABLE_PACKAGING_POINTS = 10.0;
    private static final double PLANT_TREE_POINTS = 10.0;

    MyDbAdapter myDbHelper;
    private double totalActionPoints = 0;
    private TextView textView;
    private TextView text;
    private int actionPoints;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_recorder_page);
        textView = findViewById(R.id.totalActionPoints);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userID = preferences.getInt("current_user_id", 0);

        myDbHelper = new MyDbAdapter(this);
        //Get the initial foodPoints
        actionPoints = myDbHelper.getActionPoints(userID);

        String userName = preferences.getString("current_user","userName" );
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.conserve_energy:
                if (checked) {
                    totalActionPoints += CONSERVE_ENERGY_POINTS;
                } else {
                    totalActionPoints -= CONSERVE_ENERGY_POINTS;
                }
            case R.id.recycle:
                if (checked) {
                    totalActionPoints += RECYCLE_POINTS;
                } else {
                    totalActionPoints -= RECYCLE_POINTS;
                }
            case R.id.bike_more:
                if (checked) {
                    totalActionPoints += BIKE_MORE_POINTS;
                } else {
                    totalActionPoints -= BIKE_MORE_POINTS;
                }
            case R.id.less_disposable_packaging:
                if (checked) {
                    totalActionPoints += LESS_DISPOSABLE_PACKAGING_POINTS;
                } else {
                    totalActionPoints -= LESS_DISPOSABLE_PACKAGING_POINTS;
                }
            case R.id.plant_tree:
                if (checked) {
                    totalActionPoints += PLANT_TREE_POINTS;
                } else {
                    totalActionPoints -= PLANT_TREE_POINTS;
                }
                textView.setText(Double.toString(totalActionPoints));
        }
    }

    public void savePoints(View view) {
        myDbHelper.updateActionPoints(userID, (int) totalActionPoints);
        Intent i = new Intent(this, CarbonFootprintRecorder.class);
        startActivity(i);
    }


}
