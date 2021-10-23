package nz.ac.wgtn.ecs.CarbonFootprint;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;


public class MyDbAdapter {
    myDbHelper myhelper;
    private HashMap<String, User> defaultUsers = new HashMap<>();

    public MyDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
        //Crete default users
        defaultUsers.put("lou", new User("rhea", "123", 0));
    }

    public void fillDatabaseWithDefaultUsers() {
        for (Map.Entry<String, User> entry : defaultUsers.entrySet()) {
            insertData(entry.getValue().getUserName(), entry.getValue().getPassword());
        }
    }

    public long insertData(String name, String pass) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.MyPASSWORD};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid + "   " + name + "   " + password + " \n");
        }
        return buffer.toString();
    }

    public int delete(String uname) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {uname};

        int count = db.delete(myDbHelper.TABLE_NAME, myDbHelper.NAME + " = ?", whereArgs);
        return count;
    }

    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, newName);
        String[] whereArgs = {oldName};
        int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.NAME + " = ?", whereArgs);
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        static final String DATABASE_NAME = "myDatabase";    // Database Name
        static final String TABLE_NAME = "myTable";   // Table Name
        static final int DATABASE_Version = 1;    // Database Version
        static final String UID = "_id";     // Column I (Primary Key)
        static final String NAME = "Name";    //Column II
        static final String MyPASSWORD = "Password";    // Column III
        static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + MyPASSWORD + " VARCHAR(225));";
        static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                //fillDatabaseWithData(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}