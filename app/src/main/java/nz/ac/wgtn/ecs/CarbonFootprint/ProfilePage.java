package nz.ac.wgtn.ecs.CarbonFootprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePage extends BaseActivity {
    private ImageView avatar;

    EditText Name, Pass, updateOldName, updateNewName, updateOldPassword, updateNewPassword;
    MyDbAdapter helper;
    double points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        helper = new MyDbAdapter(this);
        updateOldName = (EditText) findViewById(R.id.oldusername);
        updateNewName = (EditText) findViewById(R.id.newusername);
        updateOldPassword = (EditText) findViewById(R.id.oldpassword);
        updateNewPassword = (EditText) findViewById(R.id.newpassword);

        avatar = findViewById(R.id.imageViewAvatar);


//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String userName = preferences.getString("current_username","");
//        TextView textView = findViewById(R.id.welcome2);
//        TextView textView1 = findViewById(R.id.userName);
//        textView1.setText(userName);
//        textView.setText(userName);


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
//        Intent intent = new Intent(this, SettingsActivity.class);
//        startActivity(intent);
    }

    public void avatarBTN(View view){
        Intent intent = new Intent(this, MyCameraActivity.class);
        startActivity(intent);
    }

    public void updateUserName(View view) {
        String u1 = updateOldName.getText().toString();
        String u2 = updateNewName.getText().toString();


        if (u1.isEmpty() || u2.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = helper.updateName(u1, u2);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
            } else {
                Message.message(getApplicationContext(), "Updated");
            }
            updateOldName.setText("");
            updateNewName.setText("");
        }
    }

    public void updatePassword(View view){
        String p1 = updateOldPassword.getText().toString();
        String p2 = updateNewPassword.getText().toString();
        if (p1.isEmpty() || p2.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = helper.updatePassword(p1, p2);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
            } else {
                Message.message(getApplicationContext(), "Updated");
            }
            updateOldPassword.setText("");
            updateNewPassword.setText("");
        }
    }


}