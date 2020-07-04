package com.kikoteam.mobileschool.RegisterLogIn;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText inEmail, inPassword, inRepeatedPassword;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        fAuth = FirebaseAuth.getInstance();
        stopIfUserIsLoggedId();
        tryToLogIn();
        ProgressBar progressBar = findViewById(R.id.registerProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        inEmail = findViewById(R.id.registerMail);
        inPassword = findViewById(R.id.registerPassword);
        inRepeatedPassword = findViewById(R.id.registerRepeatedPassword);

        Button registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar progressBar = findViewById(R.id.registerProgressBar);
                if (validateCredentials()) {
                    hideKeyboard();
                    progressBar.setVisibility(View.VISIBLE);
                    addUser(inEmail.getText().toString().trim(), inPassword.getText().toString().trim());
                }
            }
        });
    }

    private void hideKeyboard(){
        try {
            InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
            assert inputManager != null;
            inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateCredentials() {
        String email = inEmail.getText().toString().trim();
        String pass = inPassword.getText().toString().trim();
        String repeatedPassword = inRepeatedPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inEmail.setError(getString(R.string.emptyField));
            return false;
        }
        if (TextUtils.isEmpty(pass)) {
            inPassword.setError(getString(R.string.emptyField));
            return false;
        }
        if (TextUtils.isEmpty(repeatedPassword)) {
            inRepeatedPassword.setError(getString(R.string.emptyField));
            return false;
        }
        if (!pass.equals(repeatedPassword)) {
            inRepeatedPassword.setError(getString(R.string.notSamePassword));
            return false;
        }
        return true;
    }

    private void addUser(String email, String pass) {
        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.singedUpSuccessfully), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AgreeActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.error) + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void stopIfUserIsLoggedId() {
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void tryToLogIn() {
        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }
}
