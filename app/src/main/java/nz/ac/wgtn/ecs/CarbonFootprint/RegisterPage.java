package nz.ac.wgtn.ecs.CarbonFootprint;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity {
    EditText Name, Pass, updateold, updatenew, delete;
    MyDbAdapter helper;
   double points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Name = (EditText) findViewById(R.id.editName);
        Pass = (EditText) findViewById(R.id.editPass);
        updateold = (EditText) findViewById(R.id.editText3);
        updatenew = (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new MyDbAdapter(this);
    }

    public void addUser(View view) {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        double t3 = points;
        if (t1.isEmpty() || t2.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Both Name and Password");
        } else {
            long id = helper.insertData(t1, t2, t3);
            if (id <= 0) {
                Message.message(getApplicationContext(), "Insertion Unsuccessful");
                Name.setText("");
                Pass.setText("");
            } else {
                Message.message(getApplicationContext(), "Insertion Successful");
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void viewdata(View view) {
        String data = helper.getData();
        Message.message(this, data);
    }

    public void update(View view) {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if (u1.isEmpty() || u2.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = helper.updateName(u1, u2);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                Message.message(getApplicationContext(), "Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }

    }

    public void delete(View view) {
        String uname = delete.getText().toString();
        if (uname.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = helper.delete(uname);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
            } else {
                Message.message(this, "DELETED");
            }
            delete.setText("");
        }
    }
}

