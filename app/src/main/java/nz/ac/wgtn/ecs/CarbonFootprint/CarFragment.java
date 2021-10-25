package nz.ac.wgtn.ecs.CarbonFootprint;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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


public class CarFragment extends Fragment {
    MyDbAdapter myDbHelper;
    private int travelPoints;
    private Button date;
    private Button calculatePoints;
    private Button savePoints;
    private DatePickerDialog datePickerDialog;
    private CarFragment carFragment;
    private Spinner spinner;
    private Spinner spinner2;
    private TextView distance;
    private TextView textview;
    private int userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        date = view.findViewById(R.id.date);
        carFragment = this;

        calculatePoints = view.findViewById(R.id.calculatePointsBtn);
        savePoints = view.findViewById(R.id.savePointsBtn);
        distance = view.findViewById(R.id.distanceInput);
        textview = view.findViewById(R.id.carPoints);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        userID = preferences.getInt("current_user_id", 0);

        myDbHelper = new MyDbAdapter(view.getContext());
        //Get the initial travelPoints
        travelPoints = myDbHelper.getTravelPoints(userID);

        distance(view);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerCarFragment(carFragment);
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

    private void distance(View view) {
        String[] values =
                {getString(R.string.diesel),getString(R.string.petrol),getString(R.string.hybrid),getString(R.string.full_ev)};
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        String[] values2 =
                {getString(R.string.small), getString(R.string.suv), getString(R.string.van)};
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);
    }

    public void updateDateTime(int year, int month, int day) {
        date.setText(Integer.toString(day) + Integer.toString(month) + Integer.toString(year));
    }


    public int computePoints() {
        String fuelType = (String) spinner.getSelectedItem();
        String vehicleSize = (String) spinner2.getSelectedItem();
        int vehicleSizeIndex = spinner2.getSelectedItemPosition();
        int distanceTravelled = Integer.parseInt(distance.getText().toString());


        pointsFromFuelType(fuelType);

        pointsFromVehicleSize(vehicleSize);

        distance(distanceTravelled);

        int totalPointsTravel = pointsFromFuelType(fuelType) +
                pointsFromVehicleSize(vehicleSize) + distance(distanceTravelled);


        return totalPointsTravel;

    }

    private int distance(int distanceTravelled) {
        int pointDistanceTravelled;
        if (distanceTravelled <= 20) {
            pointDistanceTravelled = 10;
        } else if (distanceTravelled <= 50) {
            pointDistanceTravelled = 15;
        } else if (distanceTravelled <= 80) {
            pointDistanceTravelled = 20;
        } else if (distanceTravelled <= 120) {
            pointDistanceTravelled = 25;
        } else {
            pointDistanceTravelled = 30;
        }
        return pointDistanceTravelled;
    }

    private int pointsFromVehicleSize(String vehicleSize) {
        int pointVehicleSize;
        if (vehicleSize.equals("Small")) {
            pointVehicleSize = 10;
        } else if (vehicleSize.equals("SUV")) {
            pointVehicleSize = 15;
        } else {
            pointVehicleSize = 20;
        }
        return pointVehicleSize;
    }

    private int pointsFromFuelType(String fuelType) {
        int pointFuelType;
        if (fuelType.equals("Diesel")) {
            pointFuelType = 30;
        } else if (fuelType.equals("Petrol")) {
            pointFuelType = 25;
        } else if (fuelType.equals("Hybrid")) {
            pointFuelType = 15;
        } else {
            pointFuelType = 20;
        }
        return pointFuelType;
    }

}


