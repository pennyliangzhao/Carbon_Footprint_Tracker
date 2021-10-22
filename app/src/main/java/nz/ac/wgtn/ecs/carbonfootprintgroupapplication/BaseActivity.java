package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

@SuppressLint("RestrictedApi")
@SuppressWarnings("deprecation")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_language) {
            MenuBuilder menuBuilder = new MenuBuilder(this);
            MenuInflater inflater = new MenuInflater(this);
            inflater.inflate(R.menu.popup_menu, menuBuilder);
            MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, findViewById(R.id.action_language));
            optionsMenu.setForceShowIcon(true);

            menuBuilder.setCallback(new MenuBuilder.Callback() {
                @Override
                public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.popup_chinese:
                            changeLanguage("zh");
                            return true;

                        case R.id.popup_english:
                            changeLanguage("en");
                            return true;

                        default:
                            changeLanguage("en");
                            return false;
                    }
                }

                @Override
                public void onMenuModeChange(@NonNull MenuBuilder menu) {
                }
            });

            optionsMenu.show();

        }

        return super.onOptionsItemSelected(item);
    }

    public void changeLanguage(String language) {
        Locale myLocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);

        finish();
        startActivity(getIntent());
    }
}