package com.example.womenhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.womenhelper.R.layout.activity_password;

public class PasswordActivity extends AppCompatActivity {
    String email;
    EditText e3_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_password);//aici specificam ca layout-ul este setat si schimbat din XML-ul ACTIVITY_PASSWORD
        e3_password= (EditText)findViewById(R.id.editTextTextPassword2);//am luat id-ul la fiecare din XML ca sa poata face corespondenta
        Intent myIntent=getIntent();//cu intent se incepe o activitate
        if(myIntent!=null){
            email=myIntent.getStringExtra("email");//daca myintent nu e null adica daca userul apasa pe buton
        }
    }
    public void goToNamePicActivity(View v){
        if(e3_password.getText().toString().length()>6){
            Intent myIntent = new Intent(PasswordActivity.this,NameActivity.class);//avem constrangerea ca parola sa fie mai mare de 6 litere
            myIntent.putExtra("email",email); //daca parola e buna introducem emailul si parola inn "intent"
            myIntent.putExtra("password",e3_password.getText().toString());
            startActivity(myIntent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Password lenght must be over 6 characters",Toast.LENGTH_SHORT).show();//daca parola e mica afisam un mesaj
        }
    }
}