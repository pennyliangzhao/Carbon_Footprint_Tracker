package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePage extends BaseActivity {
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        avatar = findViewById(R.id.imageViewAvatar);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("current_username","userName");
        TextView textView = findViewById(R.id.welcome2);
        textView.setText(userName);
    }

    @Override
    public void onResume() {
        super.onResume();
        Object object = getIntent().getExtras().get("profile_picture");
        if(object instanceof Bitmap) {
            avatar.setImageBitmap((Bitmap) object);
        }
    }

    public void settingsBtnClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void avatarBTN(View view){
        Intent intent = new Intent(this, MyCameraActivity.class);
        startActivity(intent);
    }
}