package nz.ac.wgtn.ecs.CarbonFootprint;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogoutPage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_page);
    }

    public void goBackToApp(View view) {
        Intent intent = new Intent(this, SplashPage.class);
        startActivity(intent);
    }
}