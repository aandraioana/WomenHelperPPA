package com.example.womenhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth; //adaugam tool ul de autentificare cu useri de la firebase pe care l-am trecut in dependente ca s ail putem folosi
    EditText e1,e2; //am creat 2 texte pt email si parola
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1= (EditText)findViewById(R.id.editTextTextEmailAddress);//e1 specificam ca coresp email
        e2= (EditText)findViewById((R.id.editTextTextPassword));// e2 parola
       auth = FirebaseAuth.getInstance();
    }
    public void  login(View v)
    {
        auth.signInWithEmailAndPassword(e1.getText().toString(),e2.getText().toString())//functia verifica daca autentificarea e completa si are ca parametrii emailul si parola
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())//daca autentificarea a avut succes
                        {
                            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();//apare mesaul acesta daca a avut succes
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Wrong email or password",Toast.LENGTH_LONG).show();//acesta apare daca nu a avut
                        }
                    }
                });
    }

}