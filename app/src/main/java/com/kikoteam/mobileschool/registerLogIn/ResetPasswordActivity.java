package com.kikoteam.mobileschool.registerLogIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.kikoteam.mobileschool.R;

import java.util.Objects;

public class ResetPasswordActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ProgressBar progressBar = findViewById(R.id.resetPassProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
        TextView makeSureYourMailIsGood = findViewById(R.id.makeSureYourMailIsGood);
        makeSureYourMailIsGood.setVisibility(View.INVISIBLE);
        Button registerButton = findViewById(R.id.restePassRegisterButton);
        registerButton.setVisibility(View.GONE);
    }

    public void openLoginActivity(View view){
        Intent intent = new Intent( this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void openRegisterActivity(View view){
        Intent intent = new Intent( this, RegisterActivity.class);
        startActivity(intent);
        finish();
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

    public void sendResetMail(View view){
        final ProgressBar progressBar = findViewById(R.id.resetPassProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        hideKeyboard();
        fAuth = FirebaseAuth.getInstance();
        final Button sendMailButton = findViewById(R.id.resetPasswordSendButton);
        sendMailButton.setVisibility(View.GONE);
        EditText inResetPasswordEmail = findViewById(R.id.resetPasswordEmail);
        String resetEmail = inResetPasswordEmail.getText().toString().trim();
        if (TextUtils.isEmpty(resetEmail)){
            progressBar.setVisibility(View.INVISIBLE);
            sendMailButton.setVisibility(View.VISIBLE);
            inResetPasswordEmail.setError(getString(R.string.empty_field));
            return;
        }
        fAuth.sendPasswordResetEmail(resetEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressBar.setVisibility(View.INVISIBLE);
                TextView successMessage = findViewById(R.id.successMessage);
                TextView makeSureYourMailIsGood = findViewById(R.id.makeSureYourMailIsGood);
                makeSureYourMailIsGood.setVisibility(View.INVISIBLE);
                successMessage.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                TextView makeSureYourMailIsGood = findViewById(R.id.makeSureYourMailIsGood);
                makeSureYourMailIsGood.setVisibility(View.VISIBLE);
                sendMailButton.setVisibility(View.VISIBLE);
                Button registerButton = findViewById(R.id.restePassRegisterButton);
                registerButton.setVisibility(View.VISIBLE);
                Toast.makeText(ResetPasswordActivity.this, getString(R.string.error) + ", " + getString(R.string.try_again), Toast.LENGTH_LONG).show();
            }
        });
    }

}