package nz.ac.wgtn.ecs.carbonfootprintgroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

 class RegisterPage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        setContentView(R.layout.activity_register_page);
    }


    public void loginBTN(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}