package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserProfile extends AppCompatActivity {

    TextView firstnameTV ;
    TextView lastnameTV ;
    TextView emailTV ;
    TextView num_dv_TV ;
    FloatingActionButton add_devices  ;

    public static  final  String fname_key ="name" ;
    public static  final  String lname_key ="name" ;
    public static  final  String email_key ="name" ;
    public static  final  String dev_key ="name" ;
    public static  final  String user_id_key ="name" ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firstnameTV = findViewById(R.id.et_first_name);
        lastnameTV = findViewById(R.id.et_last_name);
        emailTV = findViewById(R.id.et_email);
        num_dv_TV = findViewById(R.id.et_number_devices);
        add_devices = findViewById(R.id.floatingActionButton);


        Intent intent = getIntent();

        String fname = intent.getStringExtra(InterFace.fname_key);
        String lname = intent.getStringExtra(InterFace.lname_key);
        String mail = intent.getStringExtra(InterFace.email_key);
        String dev = intent.getStringExtra("a");
        String user_id = intent.getStringExtra(InterFace.user_id_key);
        Log.i("abc", "onCreate:  dev = " +dev + user_id );
        firstnameTV.setText(fname);
        lastnameTV.setText(lname);
        emailTV.setText(mail);
        num_dv_TV.setText(dev);

        add_devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(UserProfile.this,AddDevices.class) ;
                intent2.putExtra(user_id_key,user_id);
               // Toast.makeText(UserProfile.this,user_id +"  put in user profile",Toast.LENGTH_LONG).show();

                startActivity(intent2);

            }
        });

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}