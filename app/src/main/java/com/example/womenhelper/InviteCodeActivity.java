package com.example.womenhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InviteCodeActivity extends AppCompatActivity {
    String name,email,password,issharing,code;
    Uri imageUri;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener mFirebaseAuthStateListnener;
    DatabaseReference reference;
    String userId;
    ProgressDialog progressDialog;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);

        t1 = (TextView)findViewById(R.id.textView);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        Intent myIntent = getIntent();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");//arata catre (pointer) root-ul din firebase, in Users valorile vor fi afisate

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


    public void registerUser(View v){
        progressDialog.setMessage("Please wait while we are creating your account");
        progressDialog.show();

        //avem nev de o clasa pt un utilizator
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //inseram valorile in baza de date firebase
                            //cream obiectul din clasa creata
                            //default is sharing e false pt ca intial nu isi partajeaza locatia, de aceea si urm sunt na
                            CreateUser createUser=new CreateUser(name,email,password,code,"false","na","na","na");
                            //trebuie sa avem id-ul unic al userului
                            user= auth.getCurrentUser();

                            userId = user.getUid();

                            reference.child(userId).setValue(createUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progressDialog.dismiss();

                                                Toast.makeText(getApplicationContext(),"User registered succesfully", Toast.LENGTH_SHORT).show();
                                                auth.signOut();
                                                finish();
                                                Intent myIntent = new Intent(InviteCodeActivity.this, UserLocationMainActivity.class);
                                                startActivity(myIntent);

                                            }
                                            else{
                                                progressDialog.dismiss();

                                                Toast.makeText(getApplicationContext(),"User not registered succesfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}