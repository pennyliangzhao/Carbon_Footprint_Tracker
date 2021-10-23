package nz.ac.wgtn.ecs.CarbonFootprint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends BaseActivity {

    private EditText emailAddress;
    private EditText password;
    String EmailHolder, PasswordHolder;
    String TempPassword = "NOT_FOUND" ;
    Boolean EditTextEmptyHolder;
    private TextView tvError;
    MyDbAdapter myDbHelper;
    SQLiteDatabase sqLiteDatabaseObj;
    public static final String UserEmail = "";

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
                    if (cursor.isFirst()) {
                        cursor.moveToFirst();

                        //Storing password associated with email
                        TempPassword = cursor.getString(cursor.getColumnIndex(myDbHelper.myhelper.MyPASSWORD));
                        //closing cursor
                        cursor.close();
                    }
                }
                //method to check final result
                CheckFinalResult();
            }
            else {

                //If any of login EditText empty then this block will be executed.
                Toast.makeText(LoginPage.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();
            }
        }

        // Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = emailAddress.getText().toString();
        PasswordHolder = password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;
        }
        else {

            EditTextEmptyHolder = true ;
        }
    }
            // Checking entered password from SQLite database email associated password.
            public void CheckFinalResult() {

                if(TempPassword.equalsIgnoreCase(PasswordHolder))
                {
                    Toast.makeText(LoginPage.this,"Login Successfully",Toast.LENGTH_LONG).show();
                    // Going to Dashboard activity after login success message.
                    Intent intent = new Intent(this, MenuPage.class);

                    // Sending Email to Dashboard Activity using intent.
                    intent.putExtra(UserEmail, EmailHolder);
                    startActivity(intent);

                }else{
            tvError.setText("Your password or username is invalid");
        }
    }
//        if(emailAdd.equals("admin") && pwd.equals("123")){
//            Intent intent = new Intent(this, MenuPage.class);
//            startActivity(intent);
//        }else{
//            tvError.setText("Your password or username is invalid");
//        }



    public void signUpButtonClick(View view){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }
}