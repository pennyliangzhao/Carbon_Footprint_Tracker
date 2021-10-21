package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class TravelRecorderPage extends AppCompatActivity {
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