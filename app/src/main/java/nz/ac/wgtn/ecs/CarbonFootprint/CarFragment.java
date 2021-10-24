package nz.ac.wgtn.ecs.CarbonFootprint;

import android.app.DatePickerDialog;
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


public class CarFragment extends Fragment {
    private Button date;
    private DatePickerDialog datePickerDialog;
    private CarFragment carFragment;
    private Spinner spinner;
    private Spinner spinner2;
    private TextView distance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        date = view.findViewById(R.id.date);
        distance = view.findViewById(R.id.distanceInput);

        carFragment = this;

        String [] values =
                {"Diesel","Petrol","Hybrid","Full EV",};
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        String [] values2 =
                {"Small", "SUV","Van",};
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
        return view;

    }

    public void updateDateTime(int year, int month, int day) {
        date.setText(Integer.toString(day) + Integer.toString(month) +Integer.toString(year) );
    }

    public int computePoints(){
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
        if(distanceTravelled <= 20){
            pointDistanceTravelled = 5;
        }else if (distanceTravelled <= 50){
            pointDistanceTravelled = 6;
        }else if(distanceTravelled <= 80){
            pointDistanceTravelled = 7;
        }else if(distanceTravelled <= 120){
            pointDistanceTravelled = 8;
        }else {
            pointDistanceTravelled = 10;
        }
        return pointDistanceTravelled;
    }

    private int pointsFromVehicleSize(String vehicleSize) {
        int pointVehicleSize;
        if(vehicleSize.equals("Small")){
            pointVehicleSize = 3;
        }else if (vehicleSize.equals("SUV")){
            pointVehicleSize = 5;
        }else{
            pointVehicleSize = 8;
        }
        return pointVehicleSize;
    }

    private int pointsFromFuelType(String fuelType) {
        int pointFuelType;
        if(fuelType.equals("Diesel")){
            pointFuelType = 10;
        } else if(fuelType.equals("Petrol")){
            pointFuelType = 8;
        } else if (fuelType.equals("Hybrid")){
            pointFuelType = 6;
        }else {
            pointFuelType = 4;
        }
        return pointFuelType;
    }

}
