package nz.ac.wgtn.ecs.CarbonFootprint;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BikeFragment extends Fragment {
    MyDbAdapter myDbHelper;
    private Button date;
    private Button calculatePoints;
    private Button savePoints;
    private DatePickerDialog datePickerDialog;
    private BikeFragment bikeFragment;
    private int travelPoints;
    private TextView distance;
    private TextView textview;
    private int userID;
    private int bikePoints;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bike, container, false);
        date = view.findViewById(R.id.date);
        bikeFragment = this;

        calculatePoints = view.findViewById(R.id.calculatePointsBtn);
        savePoints = view.findViewById(R.id.savePointsBtn);
        distance = view.findViewById(R.id.distanceInput);
        textview = view.findViewById(R.id.bikePoints);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        userID = preferences.getInt("current_user_id", 0);

        myDbHelper = new MyDbAdapter(view.getContext());
        //Get the initial travelPoints
        travelPoints = myDbHelper.getTravelPoints(userID);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerBikeFragment(bikeFragment);
                newFragment.show(getActivity().getSupportFragmentManager(), "ti" +
                        "mePicker");
            }
        });

        calculatePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String points = String.valueOf(computePoints());
                textview.setText(points);
            }
        });

        savePoints.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDbHelper.updateTravelPoints(userID, computePoints());
                String pointsCar = String.valueOf(computePoints());
                Intent i = new Intent(getActivity(), CarbonFootprintRecorder.class);
                startActivity(i);
            }
        }));
        return view;
    }

    public void updateDateTime(int year, int month, int day) {
        date.setText(Integer.toString(day) + Integer.toString(month) +Integer.toString(year) );
    }

    public int computePoints(){
       int distanceTravelled = Integer.parseInt(distance.getText().toString());
        if (distanceTravelled <= 20) {
            bikePoints = 1;
        } else if (distanceTravelled <= 50) {
            bikePoints = 5;
        } else if (distanceTravelled <= 80) {
            bikePoints = 10;
        } else if (distanceTravelled <= 120) {
            bikePoints = 2;
        } else {
            bikePoints = 30;
        }
        return bikePoints;
    }
}