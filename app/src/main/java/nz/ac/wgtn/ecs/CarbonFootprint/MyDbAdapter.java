package nz.ac.wgtn.ecs.CarbonFootprint;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("Range")
public class MyDbAdapter {
    myDbHelper myhelper;
    private final HashMap<String, User> defaultUsers = new HashMap<>();


    public MyDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
        //context.deleteDatabase("myDatabase");
        //Crete default users
//        defaultUsers.put("rhea", new User("rhea", "567", 50));
//        defaultUsers.put("yuri", new User("yuri", "456", 60));
//        defaultUsers.put("penny", new User("penny", "789", 100));
//        defaultUsers.put("lou", new User("lou", "910", 80));
//
    }

    public void fillDatabaseWithDefaultUsers() {
//        for (Map.Entry<String, User> entry : defaultUsers.entrySet()) {
//            insertData(entry.getValue().getUserName(), entry.getValue().getPassword(), entry.getValue().getPoints());
//        }
        insertData("rhea", "123", 50, 20, 50, 10);
        insertData("yuri", "567", 30, 50, 20, 100);
        insertData("penny", "789", 20, 20, 40, 40);
    }

    public long insertData(String name, String pass, double points) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        contentValues.put(myDbHelper.TRAVEL_POINTS, points);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public long insertData(String name, String pass, int travelPoints, int foodPoints, int shopPoints, int actionPoints) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        contentValues.put(myDbHelper.TRAVEL_POINTS, travelPoints);
        contentValues.put(myDbHelper.FOOD_POINTS, foodPoints);
        contentValues.put(myDbHelper.SHOP_POINTS, shopPoints);
        contentValues.put(myDbHelper.ACTION_POINTS, actionPoints);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.MyPASSWORD, myDbHelper.TRAVEL_POINTS, myDbHelper.FOOD_POINTS, myDbHelper.SHOP_POINTS, myDbHelper.ACTION_POINTS};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            int travelPoints = cursor.getInt(cursor.getColumnIndex(myDbHelper.TRAVEL_POINTS));
            int foodPoints = cursor.getInt(cursor.getColumnIndex(myDbHelper.FOOD_POINTS));
            int shopPoints = cursor.getInt(cursor.getColumnIndex(myDbHelper.SHOP_POINTS));
            int actionPoints = cursor.getInt(cursor.getColumnIndex(myDbHelper.ACTION_POINTS));
            buffer.append(cid + "   " + name + "   " + password + "  " + travelPoints + "  " + foodPoints + "  " + shopPoints + "  " + actionPoints + " \n");
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

    public void updateTravelPoints(int userID, int travelPoints) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TRAVEL_POINTS, getTravelPoints(userID) + travelPoints);

        db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.UID + "=" + userID, null);
    }

    public int getTravelPoints(int userID) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME + " WHERE " + myDbHelper.UID + " = " + userID, null);
        cursor.moveToFirst();

        int points = cursor.getInt(cursor.getColumnIndex(myDbHelper.TRAVEL_POINTS));
        return points;
    }

    public void updateFoodPoints(int userID, int foodPoints) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.FOOD_POINTS, getFoodPoints(userID) + foodPoints);

        db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.UID + "=" + userID, null);
    }

    public int getFoodPoints(int userID) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME + " WHERE " + myDbHelper.UID + " = " + userID, null);
        cursor.moveToFirst();

        int points = cursor.getInt(cursor.getColumnIndex(myDbHelper.FOOD_POINTS));
        return points;
    }

    public void updateShopPoints(int userID, int shopPoints) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.SHOP_POINTS, getShopPoints(userID) + shopPoints);

        db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.UID + "=" + userID, null);
    }

    public int getShopPoints(int userID) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME + " WHERE " + myDbHelper.UID + " = " + userID, null);
        cursor.moveToFirst();

        int points = cursor.getInt(cursor.getColumnIndex(myDbHelper.SHOP_POINTS));
        return points;
    }


    static class myDbHelper extends SQLiteOpenHelper {
        static final String DATABASE_NAME = "myDatabase";    // Database Name
        static final String TABLE_NAME = "myTable";   // Table Name
        static final int DATABASE_Version = 1;    // Database Version
        static final String UID = "_id";     // Column I (Primary Key)
        static final String NAME = "Name";    //Column II
        static final String MyPASSWORD = "Password";    // Column III
        static final String TRAVEL_POINTS = "Travel";
        static final String FOOD_POINTS = "Food";
        static final String SHOP_POINTS = "Shop";
        static final String ACTION_POINTS = "ActionPoints";

        static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + MyPASSWORD +
                " VARCHAR(225) ," + TRAVEL_POINTS + " INTEGER," + FOOD_POINTS + " INTEGER ," + SHOP_POINTS +
                " INTEGER ," + ACTION_POINTS + " INTEGER " + ");";


        static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
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