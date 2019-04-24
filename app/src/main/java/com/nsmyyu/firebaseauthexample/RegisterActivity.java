package com.nsmyyu.firebaseauthexample;

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

public class RegisterActivity extends AppCompatActivity {
    private Button registerbutton;
    private EditText userEmail,userPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Kayıt Ol Sayfası");
        firebaseAuth =FirebaseAuth.getInstance();

        registerbutton = findViewById(R.id.register);
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEmail.getText().toString().length()>0 && userPassword.getText().toString().length()>0){
                    registerControl();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen geçerli alanları doldurunuz..", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void registerControl() {
        firebaseAuth.createUserWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString()).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Kullanıcı başarılı şekilde kayıt oldu.", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), ""+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }


        });
    }
}
