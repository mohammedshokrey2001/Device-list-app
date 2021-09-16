package com.example.app2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InterFace extends AppCompatActivity {
    public static  final  String fname_key ="name" ;
    public static  final  String lname_key ="asd" ;
    public static  final  String email_key ="df" ;
    public static  final  String dev_key ="f" ;
    public static  final  String user_id_key ="f" ;

    private Button singIn ;
    private Button singUp ;
    private EditText emailEDIT;
    private TextView passswordEDIT;
    HelperClassSQL myDB ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        singIn = findViewById(R.id.sign_in);
        singUp = findViewById(R.id.sign_up);
        emailEDIT = findViewById(R.id.emailEDIT);
        passswordEDIT = (TextView) findViewById(R.id.passEDIT);


        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_in_wok();
            }
        });
//
        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(InterFace.this,Sing_up.class);
                startActivity(intent);
            }
        });
    }


    protected  void sign_in_wok(){

        String mailText =  emailEDIT.getText().toString();
        String passText =  passswordEDIT.getText().toString().trim();


        if (mailText.equals("") || passText.equals("")){
            Toast.makeText(InterFace.this, "please enter email and password to sign in", Toast.LENGTH_LONG).show();

        }
        else {
            myDB = new HelperClassSQL(InterFace.this);

            String [] data = myDB.searchUserData(mailText,passText);

            if (data ==null){
                Toast.makeText(InterFace.this," email or password not correct",Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(InterFace.this, " you signesd succsfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(InterFace.this, UserProfile.class);

                String[] name = data[0].split(" ");
                String mail = data[1];
                String dev = data[2];
                String user_id = data[3];
                intent.putExtra(fname_key, name[0]);
                intent.putExtra(lname_key, name[1]);
                intent.putExtra(email_key, mail);
                intent.putExtra("a", dev);
                intent.putExtra(user_id_key, user_id);

                Log.i("a2", "searchUserData:  dev =  " + dev);

                // Toast.makeText(InterFace.this,user_id +"  put in interface",Toast.LENGTH_LONG).show();
                startActivity(intent);
                emailEDIT.setText("");
                passswordEDIT.setText("");
            }
        }
        }


    protected  void sign_up_wok(){



    }

}
