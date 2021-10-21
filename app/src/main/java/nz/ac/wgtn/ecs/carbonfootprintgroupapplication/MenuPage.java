package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
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