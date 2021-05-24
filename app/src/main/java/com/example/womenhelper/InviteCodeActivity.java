package com.example.womenhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class InviteCodeActivity extends AppCompatActivity {
    String name,email,password,date,issharing,code;
    Uri imageUri;

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
        Intent myIntent = getIntent();
        t1 = (TextView)findViewById(R.id.textView);
        if (myIntent != null) {
            name = myIntent.getStringExtra("name");
            email = myIntent.getStringExtra("email");
            password = myIntent.getStringExtra("password");//in acest fisier punem un nume unic pt fiecare utilizator, acestuia corespunzandu-i un email si o parola, de asemenea fiecare utilizator are un id
            code = myIntent.getStringExtra("code");
            issharing = myIntent.getStringExtra("issharing");
            imageUri = myIntent.getParcelableExtra("imageUri");
        }
        t1.setText(code);
    }
}