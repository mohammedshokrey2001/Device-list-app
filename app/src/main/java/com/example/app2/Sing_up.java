package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sing_up extends AppCompatActivity {

    EditText nameEdit ;
    EditText mailEdit ;
    EditText passEdit ;
    EditText numberDevEdit ;
    Button submitBT;
    HelperClassSQL myDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        nameEdit = findViewById(R.id.nameEDIT);
        mailEdit = findViewById(R.id.emailEDIT);
        passEdit = (EditText) findViewById(R.id.PASSWORDEDIT);
        numberDevEdit = findViewById(R.id.NUMBERDEVEDIT);
        submitBT  = findViewById(R.id.submit);

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("aaaaa", "onClick: |as");
                //submitData();
                String  name = nameEdit.getText().toString().trim();
                String  mail = mailEdit.getText().toString().trim();
                String  pass = passEdit.getText().toString().trim();
                String  devNum = numberDevEdit.getText().toString().trim();
                Log.i("sub", "submitData: submit1");
                myDB= new HelperClassSQL(Sing_up.this);
                myDB.addNewUser(mail,pass,name,devNum);

                Intent intent = new Intent(Sing_up.this,InterFace.class);
                //Toast.makeText(InterFace.class, "dfg", Toast.LENGTH_SHORT).show();
                 startActivity(intent);
            }
        });

    }

    /*void submitData(){
        String  name = nameEdit.getText().toString();
        String  mail = mailEdit.getText().toString();
        String  pass = passEdit.getText().toString();
        String  devNum = numberDevEdit.getText().toString();
        Log.i("sub", "submitData: submit1");
        HelperClassSQL myDB = new HelperClassSQL(Sing_up.this);


        myDB.addNewUser(mail,pass,name,devNum);

    }

     */
}