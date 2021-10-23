package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ActionRecorderPage extends BaseActivity {
        private double totalActionPoints = 0;
        private static final double CONSERVE_ENERGY_POINTS = 17.0;
        private static final double RECYCLE_POINTS = 20.0;
        private static final double BIKE_MORE_POINTS = 12.0;
        private static final double LESS_DISPOSABLE_PACKAGING_POINTS = 13.0;
        private static final double PLANT_TREE_POINTS = 30.0;

        private TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_action_recorder_page);
            textView = findViewById(R.id.totalActionPoints);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String userName = preferences.getString("current_username","");
            TextView textView = findViewById(R.id.userName);
            textView.setText(userName);
        }

        public void onCheckboxClicked(View view) {
            boolean checked = ((CheckBox) view).isChecked();
            switch (view.getId()) {
                case R.id.conserve_energy:
                    if (checked) {
                        totalActionPoints += CONSERVE_ENERGY_POINTS;
                    } else {
                        totalActionPoints -= CONSERVE_ENERGY_POINTS;
                    }
                case R.id.recycle:
                    if (checked) {
                        totalActionPoints += RECYCLE_POINTS;
                    } else {
                        totalActionPoints -= RECYCLE_POINTS;
                    }
                case R.id.bike_more:
                    if (checked) {
                        totalActionPoints += BIKE_MORE_POINTS;
                    } else {
                        totalActionPoints -= BIKE_MORE_POINTS;
                    }
                case R.id.less_disposable_packaging:
                    if (checked) {
                        totalActionPoints += LESS_DISPOSABLE_PACKAGING_POINTS;
                    } else {
                        totalActionPoints -= LESS_DISPOSABLE_PACKAGING_POINTS;
                    }
                case R.id.plant_tree:
                    if (checked) {
                        totalActionPoints += PLANT_TREE_POINTS;
                    } else {
                        totalActionPoints -= PLANT_TREE_POINTS;
                    }
                    textView.setText(Double.toString(totalActionPoints));
            }
        }

        public void savePoints(View view) {
            //textView.setText(Double.toString(totalFoodPoints));
        }
    }
