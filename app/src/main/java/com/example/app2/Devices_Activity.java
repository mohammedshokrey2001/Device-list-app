package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Devices_Activity extends AppCompatActivity {

    ArrayList<String> Device_name ;
    ArrayList<String> Device_ip ;
    ArrayList<String> Device_mac ;
    public static  final  String user_id_key ="name" ;
    String user_id;
    HelperClassSQL myDB ;
    RecyclerView recyclerView ;
    Devices_Adaptor devices_adaptor ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        Device_name = new ArrayList<>();
        Device_ip = new ArrayList<>();
        Device_mac = new ArrayList<>();
        recyclerView = findViewById(R.id.rec_view);
        Intent intent = getIntent();
        myDB = new HelperClassSQL(Devices_Activity.this);
       user_id = intent.getStringExtra(AddDevices.user_id_key);
       storeDtaArray();
       devices_adaptor = new Devices_Adaptor(Device_name,Device_ip,Device_mac ,Devices_Activity.this);
        recyclerView.setAdapter(devices_adaptor);
        recyclerView.setLayoutManager( new LinearLayoutManager(Devices_Activity.this));



    }



    void  storeDtaArray (){
        Cursor cursor = myDB.getUserDevices(user_id);
      //  Toast.makeText(Devices_Activity.this,user_id +"  put in user Devices",Toast.LENGTH_LONG).show();

        if (cursor.getCount()==0){

             Toast.makeText(Devices_Activity.this, "no devices to show", Toast.LENGTH_SHORT).show();
         }else {

             while (cursor.moveToNext()){
                 Device_name.add(cursor.getString(1));
                 Device_ip.add(cursor.getString(2));
                 Device_mac.add(cursor.getString(3));

             }

         }
    }
}