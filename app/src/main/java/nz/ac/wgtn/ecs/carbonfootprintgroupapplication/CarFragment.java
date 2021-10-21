package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import android.app.DatePickerDialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CarFragment extends Fragment {
    private Button date;
    private DatePickerDialog datePickerDialog;
    private CarFragment carFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        date = view.findViewById(R.id.date);
        carFragment = this;
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(carFragment);
                newFragment.show(getActivity().getSupportFragmentManager(), "ti" +
                        "mePicker");
            }
        });
        return view;
    }

    public void updateDateTime(int hourOfDay, int minute) {
        date.setText(Integer.toString(hourOfDay));
    }


}

