package nz.ac.wgtn.ecs.CarbonFootprint;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private CarFragment carFragment;
    private BikeFragment bikeFragment;
    private TrainFragment trainFragment;
    private PlaneFragment planeFragment;

    public DatePickerFragment(CarFragment carFragment) {
        this.carFragment = carFragment;
    }
    public DatePickerFragment(BikeFragment bikeFragment) {
        this.bikeFragment = bikeFragment;
    }
    public DatePickerFragment(TrainFragment trainFragment) { this.trainFragment = trainFragment; }
    public DatePickerFragment(PlaneFragment planeFragment) { this.planeFragment = planeFragment; }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        carFragment.updateDateTime(year, month + 1, day);
        //       bikeFragment.updateDateTime(year, month+1, day);
//        trainFragment.updateDateTime(year, month+1, day);
//        planeFragment.updateDateTime(year, month+1, day);
    }
}