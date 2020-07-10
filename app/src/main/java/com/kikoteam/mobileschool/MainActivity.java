package com.kikoteam.mobileschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.kikoteam.mobileschool.RegisterLogIn.RegisterActivity;
import com.kikoteam.mobileschool.RegisterLogIn.ResetPasswordActivity;
import com.kikoteam.mobileschool.avatar.ImageMerger;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        }

    }

    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        finish();
    }

    public void startImageMerger(View view){
        Intent intent = new Intent(this, ImageMerger.class);
        startActivity(intent);
    }

}