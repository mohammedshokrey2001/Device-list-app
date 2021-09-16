package com.example.app2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class HelperClassSQL extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "mysqldb.db";
    private static final int DATABASE_VERISON = 1;

    //table user data
    private static final String TABLE_NAME = "USERDATA";
    private static final String NAME = "NAME";
    private static final String UID = "_id";
    private static final String PASSWORD = "Password";
    private static final String EMAIL = "Email";
    private static final String NUMER_OF_DEVICES = "DevNumber";


    //table devices
    private static final String TABLE_NAME2 = "DEVICESDATA";
    private static final String DEVICES_NAME = "DEVICE_NAME";
    private static final String ID = "_id";
    private static final String IP = "IP_ADDRESS";
    private static final String MAC = "MAC_ADDRESS";
    private static final String USER_ID = "USER_ID";
    private static final String LAT = "LAT";
    private static final String LNG = "LNG";



    private static final String DATABASE_ALTER_TEAM_1 = "ALTER TABLE "
            + TABLE_NAME2 + " ADD COLUMN " + LAT + " string;";

    private static final String DATABASE_ALTER_TEAM_2 = "ALTER TABLE "
            + TABLE_NAME2 + " ADD COLUMN " + LNG + " string;";

    HelperClassSQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERISON);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // USER DATA TABLE
        String query = " Create Table  " + TABLE_NAME +
                " ( " + UID + " INTEGER  PRIMARY  KEY  AUTOINCREMENT , " +
                NAME + "  TEXT , " +
                EMAIL + " TEXT , " +
                NUMER_OF_DEVICES + " TEXT , " +
                PASSWORD + " TEXT ) ; ";


        //DEVICES DATA TABLE
        String query2 = "  Create Table  " + TABLE_NAME2 +
                "  (  " + ID + "  INTEGER  PRIMARY  KEY  AUTOINCREMENT , " +
                DEVICES_NAME + " TEXT ," +
                IP + " TEXT, " +
                MAC + " TEXT, " +
                USER_ID + " TEXT ) ; ";
        db.execSQL(query);
        db.execSQL(query2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        switch (i) {
            case 1:
                break;

            case 2:
                db.execSQL(DATABASE_ALTER_TEAM_1);
                db.execSQL(DATABASE_ALTER_TEAM_2);

        }


    }
    void addNewUser(String email, String password, String name, String number_of_devices) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(EMAIL, email);
        cv.put(NUMER_OF_DEVICES, number_of_devices);
        cv.put(PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "failed to add", Toast.LENGTH_LONG).show();
            Log.i("aaaa", "addNewUser: run");
        } else {
            Toast.makeText(context, "added successfully", Toast.LENGTH_LONG).show();
            Toast.makeText(context, "you can now sign in ", Toast.LENGTH_LONG).show();

            Log.i("aah", "addNewUser: run2");

        }
    }

    void addNewDevice(String name, String ip, String mac, String custID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DEVICES_NAME, name);
        cv.put(IP, ip);
        cv.put(MAC, mac);
        cv.put(USER_ID, custID);


        long result = db.insert(TABLE_NAME2, null, cv);

        if (result == -1) {
            Toast.makeText(context, "failed to add new device", Toast.LENGTH_LONG).show();
            Log.i("aaaa", "addNewUser: run");
        } else {
            Toast.makeText(context, "added successfully", Toast.LENGTH_LONG).show();

            Log.i("aah", "addNewUser: run2");

        }


    }

    /*
     */
    String[] searchUserData(String mail, String pass) {

       //onUpgrade(this.getWritableDatabase(),2,3);
        SQLiteDatabase db = this.getWritableDatabase();
        String[] buffer = new String[4];

        Cursor cursor = db.rawQuery("select * from " +
                        TABLE_NAME + "  where  " + EMAIL + " = ? AND " + PASSWORD +
                        " = ?",
                new String[] { mail,pass });



        //String query =;
     //   Cursor cursor = db.rawQuery("select * from USERDATA  where  Email = '" + mail + "'", null);
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "noooo", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            while (cursor.moveToNext()) {

                int a = cursor.getInt(0);
                //   Toast.makeText(context ,a+"",Toast.LENGTH_LONG).show();

                buffer[3] = a + "";
                buffer[0] = cursor.getString(1);
                buffer[1] = cursor.getString(2);
                buffer[2] = cursor.getString(3);
                Log.i("a1", "searchUserData:  dev =  "+buffer[2]);
            }

            return buffer;

        }


    }


    Cursor getUserDevices(String user_id) {
       // Toast.makeText(context,"hey  "+user_id,Toast.LENGTH_LONG).show();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from DEVICESDATA  where  USER_ID = '" + user_id + "'", null);

                 return cursor;

        }



        void deleteDeviceWithIp(String ip, String user_id){

            Log.i("iaa", "deleteDeviceWithIp: "+ ip +"   " +user_id );
           SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME2,
                      IP+ "=? AND " + USER_ID + "=?  "
                            ,
                    new String[] {ip, user_id});


            //db.delete(TABLE_NAME2, "DEVICE_NAME =?", new String[]{name});
            db.close();

        }










    }
