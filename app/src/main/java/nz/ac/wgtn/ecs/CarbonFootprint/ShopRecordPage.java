package nz.ac.wgtn.ecs.CarbonFootprint;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ShopRecordPage extends BaseActivity {
    private double totalShopPoints = 0;
    private static final double SUPERMARKET_POINTS = 13.0;
    private static final double CLOTHING_RETAILER_POINTS = 20.0;
    private static final double PERSONAL_CARE_POINTS = 7.0;
    private static final double HOMEWARES_POINTS = 10.0;
    private static final double GARDENING_RETAILER_POINTS = 12.0;

    private TextView textView;

    MyDbAdapter myDbHelper;
    private TextView text;
    private int shopPoints;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_record_page);
        textView = findViewById(R.id.total_shop_points);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userID = preferences.getInt("current_user_id", 0);

        myDbHelper = new MyDbAdapter(this);
        //Get the initial foodPoints
        shopPoints = myDbHelper.getShopPoints(userID);

        String userName = preferences.getString("current_user","userName" );
        TextView textView = findViewById(R.id.userNameMessage);
        textView.setText(userName);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.shop_supermarket_retailers:
                if (checked) {
                    totalShopPoints += SUPERMARKET_POINTS;
                } else {
                    totalShopPoints -= SUPERMARKET_POINTS;
                }
            case R.id.shop_clothing_retailers:
                if (checked) {
                    totalShopPoints += CLOTHING_RETAILER_POINTS;
                } else {
                    totalShopPoints -= CLOTHING_RETAILER_POINTS;
                }
            case R.id.shop_cosmetics_retailers:
                if (checked) {
                    totalShopPoints += PERSONAL_CARE_POINTS;
                } else {
                    totalShopPoints -= PERSONAL_CARE_POINTS;
                }
            case R.id.shop_homeware_retailers:
                if (checked) {
                    totalShopPoints += HOMEWARES_POINTS;
                } else {
                    totalShopPoints -= HOMEWARES_POINTS;
                }
            case R.id.shop_outdoor_retailers:
                if (checked) {
                    totalShopPoints += GARDENING_RETAILER_POINTS;
                } else {
                    totalShopPoints -= GARDENING_RETAILER_POINTS;
                }
                textView.setText(Double.toString(totalShopPoints));
        }
    }

    public void savePoints(View view) {
        myDbHelper.updateShopPoints(userID, (int) totalShopPoints);
        Intent i = new Intent(this, CarbonFootprintRecorder.class);
        startActivity(i);
    }

}