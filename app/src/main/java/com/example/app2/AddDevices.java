package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDevices extends AppCompatActivity {

    public static  final  String user_id_key ="name" ;

    Button add_dev ;
    Button go_to_devices;
    Button delete_devices_bt;

    EditText DEVname;
    EditText ip;
    EditText mac;
    HelperClassSQL myDB ;

    String user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_devices);

        add_dev = findViewById(R.id.submit_data);
        go_to_devices = findViewById(R.id.back);
        delete_devices_bt = findViewById(R.id.delete_device_from_database);
        ip = findViewById(R.id.ip_add);
        mac = findViewById(R.id.mac_add);
        DEVname = findViewById(R.id.dev_name);


        Intent intent_2_get_profile_data = getIntent();


        user_id = intent_2_get_profile_data.getStringExtra(UserProfile.user_id_key);

        add_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  devicename = DEVname.getText().toString();
                String  ip_str  = ip.getText().toString();
                String  mac_str = mac.getText().toString();


               //  Toast.makeText(AddDevices.this,user_id +"  put in user add devices",Toast.LENGTH_LONG).show();


                myDB = new HelperClassSQL(AddDevices.this);
                myDB.addNewDevice(devicename,ip_str,mac_str,user_id);


                DEVname.setText("");
                ip.setText("");
                mac.setText("");


                //add_device();

            }
        });


        go_to_devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent   intent = new Intent(AddDevices.this,Devices_Activity.class);
                intent.putExtra(user_id_key,user_id);
           //     Toast.makeText(AddDevices.this,user_id +"  put in user add devices",Toast.LENGTH_LONG).show();

                startActivity(intent);

            }
        });

        delete_devices_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDevices.this,DeleteDevice.class);
                intent.putExtra(user_id_key,user_id);
                startActivity(intent);
            }
        });


    }




    private void add_device() {





    }


}