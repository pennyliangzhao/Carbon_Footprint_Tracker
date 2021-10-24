package nz.ac.wgtn.ecs.CarbonFootprint;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CarFragment extends Fragment {
    MyDbAdapter myDbHelper;
    SQLiteDatabase sqLiteDatabaseObj;
    double initialPoints, newPoints;
    private Button date;
    private Button calculatePoints;
    private Button savePoints;
    private DatePickerDialog datePickerDialog;
    private CarFragment carFragment;
    private Spinner spinner;
    private Spinner spinner2;
    private TextView distance;
    private TextView textview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        date = view.findViewById(R.id.date);
        calculatePoints = view.findViewById(R.id.calculatePointsBtn);
        savePoints = view.findViewById(R.id.savePointsBtn);
        distance = view.findViewById(R.id.distanceInput);
        textview = view.findViewById(R.id.carPoints);

        carFragment = this;

        myDbHelper = new MyDbAdapter(view.getContext());


        String[] values =
                {"Diesel", "Petrol", "Hybrid", "Full EV",};
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        String[] values2 =
                {"Small", "SUV", "Van",};
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);


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
                String pointsCar = String.valueOf((computePoints()));
                Intent i = new Intent(getActivity(), PointsPage.class);
                i.putExtra("pointsCar", pointsCar);
                startActivity(i);
            }
        }));

        return view;

    }

    public void updateDateTime(int year, int month, int day) {
        date.setText(Integer.toString(day) + Integer.toString(month) + Integer.toString(year));
    }


    public int computePoints() {
        String fuelType = (String) spinner.getSelectedItem();
        String vehicleSize = (String) spinner2.getSelectedItem();
        int distanceTravelled = Integer.parseInt(distance.getText().toString());


        pointsFromFuelType(fuelType);

        pointsFromVehicleSize(vehicleSize);

        extracted(distanceTravelled);

        int totalPointsTravel = pointsFromFuelType(fuelType) +
                pointsFromVehicleSize(vehicleSize) + extracted(distanceTravelled);

        return totalPointsTravel;

    }

    private int extracted(int distanceTravelled) {
        int pointDistanceTravelled;
        if (distanceTravelled <= 20) {
            pointDistanceTravelled = 5;
        } else if (distanceTravelled <= 50) {
            pointDistanceTravelled = 6;
        } else if (distanceTravelled <= 80) {
            pointDistanceTravelled = 7;
        } else if (distanceTravelled <= 120) {
            pointDistanceTravelled = 8;
        } else {
            pointDistanceTravelled = 10;
        }
        return pointDistanceTravelled;
    }

    private int pointsFromVehicleSize(String vehicleSize) {
        int pointVehicleSize;
        if (vehicleSize.equals("Small")) {
            pointVehicleSize = 3;
        } else if (vehicleSize.equals("SUV")) {
            pointVehicleSize = 5;
        } else {
            pointVehicleSize = 8;
        }
        return pointVehicleSize;
    }

    private int pointsFromFuelType(String fuelType) {
        int pointFuelType;
        if (fuelType.equals("Diesel")) {
            pointFuelType = 10;
        } else if (fuelType.equals("Petrol")) {
            pointFuelType = 8;
        } else if (fuelType.equals("Hybrid")) {
            pointFuelType = 6;
        } else {
            pointFuelType = 4;
        }
        return pointFuelType;
    }

    @SuppressLint("Range")
    public void updatePoints(View view) {

        SQLiteDatabase db = myDbHelper.myhelper.getWritableDatabase();
        Cursor cursor = db.query(MyDbAdapter.myDbHelper.TABLE_NAME, null, null, null, null, null, null);
        initialPoints = cursor.getInt(cursor.getColumnIndex(MyDbAdapter.myDbHelper.MyPoints));

        int a = myDbHelper.updatePoints(initialPoints, newPoints);
        newPoints = initialPoints + computePoints();
        }

    }


