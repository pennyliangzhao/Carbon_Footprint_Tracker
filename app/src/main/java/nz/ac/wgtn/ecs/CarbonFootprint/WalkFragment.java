package nz.ac.wgtn.ecs.CarbonFootprint;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WalkFragment extends Fragment {
    private Button date;
    private DatePickerDialog datePickerDialog;
    private WalkFragment walkFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bike, container, false);
        date = view.findViewById(R.id.date);
        walkFragment = this;

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerWalkFragment(walkFragment);
                newFragment.show(getActivity().getSupportFragmentManager(), "ti" +
                        "mePicker");
            }
        });
        return view;

    }

    public void updateDateTime(int year, int month, int day) {
        date.setText(Integer.toString(day) + Integer.toString(month) +Integer.toString(year) );
    }
}