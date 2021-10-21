package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProfilePage extends AppCompatActivity {
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        avatar = findViewById(R.id.imageViewAvatar);
    }

    @Override
    public void onResume() {
        super.onResume();
        Object object = getIntent().getExtras().get("profile_picture");
        if(object instanceof Bitmap) {
            avatar.setImageBitmap((Bitmap) object);
        }
    }

    public void avatarBTN(View view){
        Intent intent = new Intent(this, MyCameraActivity.class);
        startActivity(intent);
    }
}