package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RemindersAlertPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_alert_page);
    }

    public void bannerOnClick(View view) {
        openActivity(this, WebViewActivity.class, "https://www.rnz.co.nz");
    }

    public void shopWiselyOnClick(View view) {
        openActivity(this, WebViewActivity.class, "https://www.google.com");
    }

    private void openActivity(Activity activity, Class cls, String url) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("URL",url);
        startActivity(intent);
    }
}