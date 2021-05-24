package com.example.womenhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class NameActivity extends AppCompatActivity {
    String email,password;
    EditText e5_name;
    CircleImageView circleImageView;
    Uri resultUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        e5_name = (EditText) findViewById(R.id.editTextTextPersonName);
        circleImageView = (CircleImageView) findViewById(R.id.circleImageView);
        Intent myIntent = getIntent();
        if (myIntent != null) {
            email = myIntent.getStringExtra("email");
            password = myIntent.getStringExtra("password");//in acest fisier punem un nume unic pt fiecare utilizator, acestuia corespunzandu-i un email si o parola, de asemenea fiecare utilizator are un id

        }
    }
        //spunem util ca trb sa selecteze o poza de profil
        public void generateCode(View v)
        {
            Date myDate = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
            String date = format1.format(myDate);
            Random r = new Random(); // generam un numar random
            int n = 100000 + r.nextInt(900000);
            String code = String.valueOf(n); // folosim nr random pt generarea unui id propriu utilizatorului
            if (resultUri!=null){
                Intent myIntent = new Intent(NameActivity.this, InviteCodeActivity.class);//adaugam date intentului nostru si anume numele parola email etc
                myIntent.putExtra("name",e5_name.getText().toString());
                myIntent.putExtra("email",email);
                myIntent.putExtra("password",password);
                myIntent.putExtra("isSharing","false");
                myIntent.putExtra("code",code);
                myIntent.putExtra("imageUrl",resultUri);
                startActivity(myIntent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please choose a profile picture", Toast.LENGTH_SHORT).show();
            }
        }
        //metoda noua lasa utilizatorul sa aleaga o poza de profil din galerie
    public void selectImage (View v){
        Intent i= new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,12);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12 && resultCode == RESULT_OK && data != null) {
            // lasa utlizatorul sa cropeze imaginea aleasa in forma prestabilita de patrat
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }
        //primim poza rezultanta dupa cropare
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                 resultUri = result.getUri();
                 circleImageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
//cand utilizatorul apasa pe imagine se va deschide un image viewer care il va lasa sa isi aleaga si cropeze poza de profil


    }
    }
