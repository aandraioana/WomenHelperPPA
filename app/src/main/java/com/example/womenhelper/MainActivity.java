package com.example.womenhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karan.churi.PermissionManager.PermissionManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    PermissionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
       if(user == null|| auth== null)
        {
            setContentView(R.layout.activity_main);
           manager = new PermissionManager(){} ;
            manager.checkAndRequestPermissions(this);
           }

        else{
           setContentView(R.layout.activity_main);
           manager = new PermissionManager(){} ;
           manager.checkAndRequestPermissions(this);
        }
    }

    @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        manager.checkResult(requestCode,permissions,grantResults);

        ArrayList<String> denied_permissions = manager.getStatus().get(0).denied;
        if(denied_permissions.isEmpty()){
            Toast.makeText(getApplicationContext(),"Permissions enabled",Toast.LENGTH_SHORT).show();
       }
    }

    //stabilim ca butonul sign in ne duce la pagina de login si butonul de sign up la pagina de register
    public void goToLogin(View v){
        Intent myIntent = new Intent (MainActivity.this,LoginActivity.class);
        startActivity(myIntent);
        finish();
    }
    public void goToRegister(View v){
        Intent myIntent = new Intent (MainActivity.this,RegisterActivity.class);
        startActivity(myIntent);
        finish();
    }
}