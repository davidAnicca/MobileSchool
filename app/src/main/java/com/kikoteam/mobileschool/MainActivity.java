package com.kikoteam.mobileschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.kikoteam.mobileschool.registerLogIn.RegisterActivity;
import com.kikoteam.mobileschool.avatar.Avatar;
import com.kikoteam.mobileschool.avatar.newAvatar.BaseSelector;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    ImageView seeAvatarImage;
    Button seeAvatarButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(logInIfNotLogged())
            return;
        initializeIfNotInitialized();

        setViews();
        initializeViews();
    }

    private void initializeIfNotInitialized(){
        if (!Application.getInstance().isInitialized())
            initialize();
    }

    private boolean logInIfNotLogged(){
        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
            return true;
        }
        return false;
    }

    private void setViews(){
        seeAvatarButton = findViewById(R.id.mainSeeAvatarButton);
        seeAvatarImage = findViewById(R.id.mainAvatarImage);
    }

    private void initializeViews(){
        seeAvatarImage.setVisibility(View.GONE);
        if (Avatar.getInstance().getFinalForm() != null ) {
            seeAvatarButton.setVisibility(View.VISIBLE);
        }else{
            seeAvatarButton.setVisibility(View.GONE);
        }
    }

    private void initialize() {
        Intent intent = new Intent(this, Initializer.class);
        startActivity(intent);
        finish();
    }

    public void logOut(View view) {
        view.setVisibility(View.GONE);
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        Avatar.resetInstance();
        finish();
    }

    public void createNewAvatar(View view) {
        Avatar.resetInstance();
        seeAvatarImage.setVisibility(View.GONE);
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
    }

    public void seeAvatar(View view) {

        Animation fadeIn;
        Animation smoothFloat;

        smoothFloat = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.smooth_float);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);


        seeAvatarImage.setImageBitmap(Avatar.getInstance().getFinalForm());
        seeAvatarImage.setVisibility(View.VISIBLE);
        seeAvatarImage.startAnimation(fadeIn);
        seeAvatarImage.startAnimation(smoothFloat);
        seeAvatarButton.setVisibility(View.GONE);
    }

}