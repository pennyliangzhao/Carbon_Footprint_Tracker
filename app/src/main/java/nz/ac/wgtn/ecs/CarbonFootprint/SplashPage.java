package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;
@SuppressWarnings("deprecation")
public class SplashPage extends BaseActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Change the language
        setDefaultLanguage();
        setContentView(R.layout.activity_splash_page);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean defaultUsers = preferences.getBoolean("default_users_created", false);

        if(!defaultUsers) {
            MyDbAdapter myDbAdapter = new MyDbAdapter(this);
            myDbAdapter.fillDatabaseWithDefaultUsers();

            //Update the SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("default_users_created", true);
            editor.apply();
        }
    }

    public void getStartedButtonClick(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    //Get the selected language from the SharedPreferences
    private void setDefaultLanguage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String language = preferences.getString("language","");
        if(!language.equalsIgnoreCase("")) {
            Resources resources = getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(new Locale(language));
            resources.updateConfiguration(configuration, metrics);
        }
    }
}