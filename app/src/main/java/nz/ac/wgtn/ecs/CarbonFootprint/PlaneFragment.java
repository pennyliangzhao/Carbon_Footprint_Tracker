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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PlaneFragment extends Fragment {
    private Button date;
    private DatePickerDialog datePickerDialog;
    private PlaneFragment planeFragment;

    MyDbAdapter myDbHelper;
    private Button calculatePoints;
    private Button savePoints;
    private Spinner spinner;
    private Spinner spinner2;
    private TextView distance;
    private TextView textview;
    private int userID;
    private int travelPoints;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_plane, container, false);
        date = view.findViewById(R.id.date);
        planeFragment = this;

        calculatePoints = view.findViewById(R.id.calculatePointsBtn);
        savePoints = view.findViewById(R.id.savePointsBtn);
        distance = view.findViewById(R.id.distanceInput);
        textview = view.findViewById(R.id.planePoints);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        userID = preferences.getInt("current_user_id", 0);

        myDbHelper = new MyDbAdapter(view.getContext());
        //Get the initial travelPoints
        travelPoints = myDbHelper.getTravelPoints(userID);

        String [] values =
                {"Domestic", "International"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        String [] values2 =
                {"Economy","Business","First Class",};
        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerPlaneFragment(planeFragment);
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
                Intent i = new Intent(getActivity(), CarbonFootprintRecorder.class);
                startActivity(i);
            }
        }));

        return view;

    }

    public void updateDateTime(int year, int month, int day) {
        date.setText(Integer.toString(day) + Integer.toString(month) +Integer.toString(year) );
    }

    public int computePoints() {
        String flightType = (String) spinner.getSelectedItem();
        String flightClass = (String) spinner2.getSelectedItem();
        int distanceTravelled = Integer.parseInt(distance.getText().toString());

        pointsFromClass(flightClass);

        pointsFromTypeFlight(flightType);

        extracted(distanceTravelled);

        int totalPointsTravel = pointsFromClass(flightType) +
                pointsFromTypeFlight(flightClass) + extracted(distanceTravelled);

        return totalPointsTravel;
    }

    private int extracted(int distanceTravelled) {
        int pointDistanceTravelled;
        if (distanceTravelled <= 500) {
            pointDistanceTravelled = 40;
        } else if (distanceTravelled <= 1000) {
            pointDistanceTravelled = 100;
        } else {
            pointDistanceTravelled = 150;
        }
        return pointDistanceTravelled;
    }

    private int pointsFromTypeFlight(String typeFlight) {
        int pointTypeFlight;
        if (typeFlight.equals("Domestic")) {
            pointTypeFlight = 20;
        } else {
            pointTypeFlight = 40;
        }
        return pointTypeFlight;
    }

    private int pointsFromClass(String flightClass) {
        int pointFlightClass;
        if (flightClass.equals("Economy")) {
            pointFlightClass = 10;
        } else if (flightClass.equals("Business")) {
            pointFlightClass = 50;
        } else {
            pointFlightClass = 100;
        }
        return pointFlightClass;
    }

}

