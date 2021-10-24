package nz.ac.wgtn.ecs.CarbonFootprint;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;


public class MyDbAdapter {
    myDbHelper myhelper;
    private HashMap<String, User> defaultUsers = new HashMap<>();


    public MyDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
        //Crete default users
        defaultUsers.put("rhea", new User("rhea", "567", 50));
        defaultUsers.put("yuri", new User("yuri", "456", 60));
        defaultUsers.put("penny", new User("penny", "789", 100));
        defaultUsers.put("lou", new User("lou", "910", 80));

    }

    public void fillDatabaseWithDefaultUsers() {
        for (Map.Entry<String, User> entry : defaultUsers.entrySet()) {
            insertData(entry.getValue().getUserName(), entry.getValue().getPassword(), entry.getValue().getPoints());
        }
    }

    public long insertData(String name, String pass, double points) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        contentValues.put(myDbHelper.MyPoints, points);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    @SuppressLint("Range")
    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.MyPASSWORD, myDbHelper.MyPoints};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
             int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
             String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
             String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
             String points = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPoints));
            buffer.append(cid + "   " + name + "   " + password + " " + points + " \n");
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

    public int updatePassword(String oldPass, String newPass) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.MyPASSWORD, newPass);
        String[] whereArgs = {oldPass};
        int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.MyPASSWORD + " = ?", whereArgs);
        return count;
    }


    public int updatePoints(double initialPoints, double newPoints) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.MyPoints, newPoints);
        String[] whereArgs = {String.valueOf(initialPoints)};
        int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.MyPoints + " = ?", whereArgs);
        return count;
    }


    static class myDbHelper extends SQLiteOpenHelper {
        static final String DATABASE_NAME = "myDatabase";    // Database Name
        static final String TABLE_NAME = "myTable";   // Table Name
        static final int DATABASE_Version = 1;    // Database Version
        static final String UID = "_id";     // Column I (Primary Key)
        static final String NAME = "Name";    //Column II
        static final String MyPASSWORD = "Password";    // Column III
        static final String MyPoints = "Points";

        static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + MyPASSWORD + " VARCHAR(225) ," + MyPoints + ");";

        static final String TABLE_NAME_2 = "Points";   // Table Name
        static final String TRAVEL_POINTS = "Travel";
        static final String FOOD_POINTS = "Food";
        static final String SHOP_POINTS = "Shop";
        static final String ACTION_POINTS = "Action";
        static final String CREATE_TABLE_POINTS = "CREATE TABLE " + TABLE_NAME_2 +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TRAVEL_POINTS  + FOOD_POINTS + SHOP_POINTS+ ACTION_POINTS + ");";
        static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_2;

        Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_TABLE_POINTS);
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

    public long insertDataPoints(double travelPoint, double foodPoint, double shopPoint, double actionPoint) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TRAVEL_POINTS, travelPoint);
        contentValues.put(myDbHelper.FOOD_POINTS, foodPoint);
        contentValues.put(myDbHelper.SHOP_POINTS, shopPoint);
        contentValues.put(myDbHelper.ACTION_POINTS, actionPoint);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

}