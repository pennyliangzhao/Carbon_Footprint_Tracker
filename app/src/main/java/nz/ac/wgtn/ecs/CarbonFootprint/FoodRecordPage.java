package nz.ac.wgtn.ecs.CarbonFootprint;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class FoodRecordPage extends BaseActivity {
    private double totalFoodPoints = 0;
    private static final double HEAVY_MEAT_POINTS = 27.0;
    private static final double MEDIUM_MEAT_POINTS = 12.0;
    private static final double LIGHT_MEAT_POINTS = 8.0;
    private static final double VEGETARIAN_POINTS = 3.0;
    private static final double VEGAN_POINTS = 2.0;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_record_page);
        textView = findViewById(R.id.totalFoodPoints);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("current_username","");
        TextView textView = findViewById(R.id.userNameMessage);
        textView.setText(userName);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.food_type_heavy_meat:
                if (checked) {
                    totalFoodPoints += HEAVY_MEAT_POINTS;
                } else {
                    totalFoodPoints -= HEAVY_MEAT_POINTS;
                }
            case R.id.food_type_medium_meat:
                if (checked) {
                    totalFoodPoints += MEDIUM_MEAT_POINTS;
                } else {
                    totalFoodPoints -= MEDIUM_MEAT_POINTS;
                }
            case R.id.food_type_light_meat:
                if (checked) {
                    totalFoodPoints += LIGHT_MEAT_POINTS;
                } else {
                    totalFoodPoints -= LIGHT_MEAT_POINTS;
                }
            case R.id.food_type_vegetarian:
                if (checked) {
                    totalFoodPoints += VEGETARIAN_POINTS;
                } else {
                    totalFoodPoints -= VEGETARIAN_POINTS;
                }
            case R.id.food_type_vegan:
                if (checked) {
                    totalFoodPoints += VEGAN_POINTS;
                } else {
                    totalFoodPoints -= VEGAN_POINTS;
                }
                textView.setText(Double.toString(totalFoodPoints));
        }
    }

    public void savePoints(View view) {
        //textView.setText(Double.toString(totalFoodPoints));
    }
}