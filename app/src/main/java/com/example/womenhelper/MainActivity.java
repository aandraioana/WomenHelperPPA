package com.example.womenhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    //stabilim ca butonul sign in ne duce la pagina de login si butonul de sign up la pagina de register
    public void goToLogin(View v){
        Intent myIntent = new Intent (MainActivity.this,LoginActivity.class);
        startActivity(myIntent);
    }
    public void goToRegister(View v){
        Intent myIntent = new Intent (MainActivity.this,RegisterActivity.class);
        startActivity(myIntent);
    }
}