package nz.ac.wgtn.ecs.CarbonFootprint;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class RemindersAlertPage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_alert_page);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("current_user","userName" );
        TextView textView = findViewById(R.id.userName);
        textView.setText(userName);
    }

    public void bannerOnClick(View view) {
        openActivity(this, WebViewActivity.class, "https://www.nytimes.com/guides/year-of-living-better/how-to-reduce-your-carbon-footprint");
    }

    public void shopWiselyOnClick(View view) {
        openActivity(this, WebViewActivity.class, "https://greenerideal.com/guides/0327-10-eco-friendly-shopping-tips/");
    }

    public void bikeMoreOnClick(View view) {
        openActivity(this, WebViewActivity.class, "https://www.sustrans.org.uk/our-blog/get-active/2020/in-your-community/how-does-walking-and-cycling-help-to-protect-the-environment/");
    }

    public void conserveEnergyOnClick(View view) {
        openActivity(this, WebViewActivity.class, "https://19january2017snapshot.epa.gov/climatechange/what-you-can-do-home_.html");
    }

    public void plantATreeOnClick(View view) {
        openActivity(this, WebViewActivity.class, "https://psci.princeton.edu/tips/2020/8/15/tree-planting-and-negative-emissions");
    }

    private void openActivity(Activity activity, Class cls, String url) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("URL", url);
        startActivity(intent);
    }
}