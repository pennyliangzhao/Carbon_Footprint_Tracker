package nz.ac.wgtn.ecs.CarbonFootprint;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class FoodRecordPage extends BaseActivity {
    private int totalFoodPoints = 0;
    private static final int HEAVY_MEAT_POINTS = 27;
    private static final int MEDIUM_MEAT_POINTS = 12;
    private static final int LIGHT_MEAT_POINTS = 8;
    private static final int VEGETARIAN_POINTS = 3;
    private static final int VEGAN_POINTS = 2;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_record_page);
        textView = findViewById(R.id.totalFoodPoints);
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

    public void calculatePoints(View view) {
        textView.setText(Double.toString(totalFoodPoints));
    }

    public void savePoints(View view) {
        String foodPoints = String.valueOf(totalFoodPoints);
        Intent i = new Intent(this, PointsPage.class);
        i.putExtra("pointsFood", foodPoints);
        startActivity(i);
    }
}