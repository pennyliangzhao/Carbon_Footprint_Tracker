package nz.ac.wgtn.ecs.CarbonFootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class TravelRecorderPage extends BaseActivity {
    private int travelPoints;
    private Fragment fragmentCar = new CarFragment();
    private Fragment fragmentBike = new BikeFragment();
    private Fragment fragmentPlane = new PlaneFragment();
    private Fragment fragmentTrain = new TrainFragment();
    private Fragment fragmentWalk = new WalkFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_recorder_page);
        switchFragment(R.id.frame_layout, fragmentCar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("current_user","userName" );
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);
    }


    public void GobackMenu(View view){
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }

    public void carButton(View view){
        view.setBackgroundColor(getResources().getColor(R.color.black, null));
        switchFragment(R.id.frame_layout, fragmentCar);
    }

    public void bikeButton(View view){
        switchFragment(R.id.frame_layout, fragmentBike);
    }

    public void planeButton(View view){
        switchFragment(R.id.frame_layout, fragmentPlane);
    }

    public void trainButton(View view){
        switchFragment(R.id.frame_layout, fragmentTrain);
    }

    public void walkButton(View view){
        switchFragment(R.id.frame_layout, fragmentWalk);
    }

    private void switchFragment(int layout, Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(layout, fragment);
        ft.commit();
    }
}