package com.kikoteam.mobileschool.RegisterLogIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText inEmail, inPassword;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ProgressBar progressBar = findViewById(R.id.loginProgressBar);
        progressBar.setVisibility(View.INVISIBLE);


        inEmail = findViewById(R.id.loginMail);
        inPassword = findViewById(R.id.loginPassword);
        fAuth = FirebaseAuth.getInstance();
    }

    private boolean validateCredentials() {
        String email = inEmail.getText().toString().trim();
        String pass = inPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inEmail.setError(getString(R.string.empty_field));
            return false;
        }
        if (TextUtils.isEmpty(pass)) {
            inPassword.setError(getString(R.string.empty_field));
            return false;
        }
        return true;
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

    public void logIn(View view) {
        if (validateCredentials()) {
            final ProgressBar progressBar = findViewById(R.id.loginProgressBar);
            hideKeyboard();
            progressBar.setVisibility(View.VISIBLE);
            fAuth.signInWithEmailAndPassword(inEmail.getText().toString().trim(), inPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, getString(R.string.logged_in_successfully), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, getString(R.string.error) + "\n" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void openResetPasswordActivity(View view){
         Intent intent = new Intent(this, ResetPasswordActivity.class);
         startActivity(intent);
    }

}