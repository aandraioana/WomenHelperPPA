package com.example.womenhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class RegisterActivity extends AppCompatActivity {
    EditText e4_Email;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e4_Email=(EditText)findViewById(R.id.editTextTextEmailAddress2);
        auth=FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }
    public void  GoToPasswordActivity (View v){
        dialog.setMessage("Checking email adress...");
        dialog.show();
        //verifica daca emailul e deja in baza de date (e deja inregistrat) sau nu
        auth.fetchSignInMethodsForEmail(e4_Email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            boolean check = !task.getResult().getSignInMethods().isEmpty();
                            if (!check) {
                                //emailul nu a mai fost inregistrat
                                Intent myIntent = new Intent(RegisterActivity.this,PasswordActivity.class);
                                startActivity(myIntent);
                                finish();
                            } else {
                                //emailul a ami fost inregistrat
                                dialog.dismiss();;
                                Toast.makeText(getApplicationContext(),"This email is already registeres",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                }
    }
