package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class MenuPage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        //Get the user name from the SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("current_user","userName" );
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);
    }

    public void profilePageButton(View view){
        Intent intent = new Intent(this,ProfilePage.class);
        intent.putExtra("profile_picture","IMG");
        startActivity(intent);
    }

    public void recordFootprintButton(View view){
        Intent intent = new Intent(this, CarbonFootprintRecorder.class);
        startActivity(intent);
    }

    public void checkPointsButton(View view){
        Intent intent = new Intent(this, PointsPage.class);
        startActivity(intent);
    }

    public void remindersButton(View view){
        Intent intent = new Intent(this, RemindersAlertPage.class);
        startActivity(intent);
    }

    public void shareButton(View view){
        Intent intent = new Intent(this, SharePage.class);
        startActivity(intent);
    }

    public void logoutButton(View view){
        Intent intent = new Intent(this, LogoutPage.class);
        startActivity(intent);
    }


}