package com.nsmyyu.firebaseauthexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button loginbutton,registerbutton;
    private EditText userEmail,userPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth =FirebaseAuth.getInstance();
        loginbutton = findViewById(R.id.login);
        registerbutton = findViewById(R.id.register);
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEmail.getText().toString().length()>0 && userPassword.getText().toString().length()>0){
                    loginControl();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen geçerli alanları doldurunuz..", Toast.LENGTH_LONG).show();
                }
            }
        });

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
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

    }

    private void loginControl() {
        firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "GİRİŞ BAŞARILI", Toast.LENGTH_LONG).show();


                    //giriş yapılacak başka bir activitye yönlendirme
                }else {
                    Toast.makeText(getApplicationContext(), "Lütfen geçerli alanları doldurunuz..", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
