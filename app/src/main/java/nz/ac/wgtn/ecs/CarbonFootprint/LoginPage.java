package nz.ac.wgtn.ecs.CarbonFootprint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends BaseActivity {

    public static final String UserEmail = "";
    String EmailHolder, PasswordHolder;
    String TempPassword = "NOT_FOUND";
    Boolean EditTextEmptyHolder;
    MyDbAdapter myDbHelper;
    SQLiteDatabase sqLiteDatabaseObj;
    private EditText emailAddress;
    private EditText password;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        emailAddress = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        myDbHelper = new MyDbAdapter(this);

        sqLiteDatabaseObj = myDbHelper.myhelper.getWritableDatabase();
    }


    public void loginButtonClick(View view) {
//        Intent intent = new Intent(this, MenuPage.class);
//        startActivity(intent);
        CheckEditTextStatus();
        loginFunction();

    }

    @SuppressLint("Range")
    public void loginFunction() {


        if (EditTextEmptyHolder) {
            sqLiteDatabaseObj = myDbHelper.myhelper.getReadableDatabase();
            // Adding search email query to cursor.
            Cursor cursor = sqLiteDatabaseObj.query(myDbHelper.myhelper.TABLE_NAME, null,
                    " " + myDbHelper.myhelper.NAME + "=?", new String[]{EmailHolder},
                    null, null, null);
            while (cursor.moveToNext()) {
                int cid =cursor.getInt(cursor.getColumnIndex(MyDbAdapter.myDbHelper.UID));
                String name = cursor.getString(cursor.getColumnIndex(MyDbAdapter.myDbHelper.NAME));
                String passwordDb = cursor.getString(cursor.getColumnIndex(MyDbAdapter.myDbHelper.MyPASSWORD));

                if (emailAddress.getText().toString().equals(name) && password.getText().toString().equals(passwordDb)) {
                    User user = new User(cid, name, 0);
                    saveCurrentUser(user);
                    TempPassword = cursor.getString(cursor.getColumnIndex(myDbHelper.myhelper.MyPASSWORD));
                }
            }


//                while (cursor.moveToNext()) {
//                    if (cursor.isFirst()) {
//                        cursor.moveToFirst();
//                        //Storing password associated with email
//                        TempPassword = cursor.getString(cursor.getColumnIndex(myDbHelper.myhelper.MyPASSWORD));
//                        //closing cursor
//                        cursor.close();
//                    }
//                }
            //method to check final result
            CheckFinalResult();
            cursor.close();
        } else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(LoginPage.this,R.string.invalid_input, Toast.LENGTH_LONG).show();
        }
    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus() {

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = emailAddress.getText().toString();
        PasswordHolder = password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;
        } else {

            EditTextEmptyHolder = true;
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult() {

        if (TempPassword.equalsIgnoreCase(PasswordHolder)) {
            Toast.makeText(LoginPage.this,R.string.login_successfully, Toast.LENGTH_LONG).show();
            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(this, MenuPage.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);
            startActivity(intent);

        } else {
            tvError.setText(R.string.enter);
        }
    }
//        if(emailAdd.equals("admin") && pwd.equals("123")){
//            Intent intent = new Intent(this, MenuPage.class);
//            startActivity(intent);
//        }else{
//            tvError.setText("Your password or username is invalid");
//        }


    public void signUpButtonClick(View view) {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    private void saveCurrentUser(User user) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("current_user", user.getUserName());
        editor.apply();
    }
}