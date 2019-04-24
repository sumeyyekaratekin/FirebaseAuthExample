package com.nsmyyu.firebaseauthexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button loginBttn, registerBttn;
    private EditText userEmail, userPassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Giriş Yap");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser(); // authenticated user

        if (firebaseUser != null) { // check current user exist

            openHomeActivity();
        }

        loginBttn = findViewById(R.id.loginBttn);
        registerBttn = findViewById(R.id.registerBttn);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        registerBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userEmail.getText().toString().length() > 0 && userPassword.getText().toString().length() > 0) {

                    loginControl();

                } else {

                    Toast.makeText(getApplicationContext(), "Lütfen ilgili alanları giriniz!", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void loginControl() {

        firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    openHomeActivity();

                } else {
                    Toast.makeText(getApplicationContext(), "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void openHomeActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}




    /* textView =findViewById(R.id.txt);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str =sharedPreferences.getString("userName","");
        textView.setText(str);
*/
       /* buton = findViewById(R.id.start);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); //MainActivity.this de olur
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("userName","Sümeyye");
                editor.apply();
                Toast.makeText(getApplicationContext(),"değer kaydedildi.",Toast.LENGTH_LONG).show();*//*

          *//*      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String str =sharedPreferences.getString("userName","");
                Toast.makeText(getApplicationContext(),""+str,Toast.LENGTH_LONG).show();

            }
        });*/
