package com.kikoteam.mobileschool;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText inEmail, inPassword, inPassword2;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null ){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        inEmail = findViewById(R.id.mail);
        inPassword = findViewById(R.id.pass);
        inPassword2 = findViewById(R.id.repeatPass);

        Button registerButton = (Button) findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inEmail.getText().toString().trim();
                String pass = inPassword.getText().toString().trim();
                String pass2 = inPassword2.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    inEmail.setError("Acest câmp este gol");
                    return;
                }
                if ( TextUtils.isEmpty(pass)){
                    inPassword.setError("Acest câmp este gol");
                    return;
                }
                if ( TextUtils.isEmpty(pass2)){
                    inPassword2.setError("Acest câmp este gol");
                    return;
                }
                if ( !pass.equals(pass2) ){
                    inPassword.setError("Parolele diferă");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "înregistrat cu succes", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AgreeActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Eroare "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
