package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteDevice extends AppCompatActivity {

    public static  final  String user_id_key ="name" ;

    Button button_delte ;
    Button button_back_profile ;
    EditText ip_input ;

    String user_id;
    HelperClassSQL myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_device);

        button_delte = findViewById(R.id.delete_with_ip);
        button_back_profile = findViewById(R.id.back_to_profile);
        ip_input = findViewById(R.id.editTextDelteIP);

        myDb = new HelperClassSQL(DeleteDevice.this);

        Intent intent = getIntent();
        user_id = intent.getStringExtra(AddDevices.user_id_key);



        button_delte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ip_to_delte = ip_input.getText().toString();
                myDb.deleteDeviceWithIp(ip_to_delte,user_id);
            }
        });





        button_back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(DeleteDevice.this,UserProfile.class);
                startActivity(intent2);
            }
        });


    }
}