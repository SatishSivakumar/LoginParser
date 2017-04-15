package com.example.logintrail.parser_satish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userName;
    EditText Password;
    Button loginButton;

    int count =0;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName=(EditText) findViewById(R.id.editText);
        Password=(EditText) findViewById(R.id.editText2);
        loginButton=(Button) findViewById(R.id.login);




        loginButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if ( count >= 2){
                    loginButton.setEnabled(false);

                }

                if  ( userName.getText().toString().equals("")&& Password.getText().toString().equals("") ){

                    Toast.makeText(getApplicationContext(), "Please Enter Valid credentials", Toast.LENGTH_LONG).show();
                }

                else if ( userName.getText().toString().equals("admin")&& Password.getText().toString().equals("admin") ){

                    Toast.makeText(getApplicationContext(), "Redirecting…", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);

                }
                else if( !userName.getText().toString().equals("admin") || !Password.getText().toString().equals("admin") ){

                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                    count++;

                }

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
