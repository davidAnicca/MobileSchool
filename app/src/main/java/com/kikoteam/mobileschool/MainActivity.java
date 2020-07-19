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
import com.kikoteam.mobileschool.RegisterLogIn.RegisterActivity;
import com.kikoteam.mobileschool.avatar.Avatar;
import com.kikoteam.mobileschool.avatar.BaseSelector;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    ImageView seeAvatarImage;
    Button seeAvatarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Application.getInstance().isInitialized())
            initialize();

        seeAvatarButton = findViewById(R.id.mainSeeAvatarButton);
        seeAvatarImage = findViewById(R.id.mainAvatarImage);

        seeAvatarImage.setVisibility(View.GONE);
        seeAvatarButton.setVisibility(View.GONE);

        if (Avatar.getInstance().getFinalForm() != null && !Avatar.getInstance().getUrl().equals("empty")) {
            seeAvatarImage.setVisibility(View.VISIBLE);
            seeAvatarButton.setVisibility(View.VISIBLE);
        }

        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        }

    }

    public void initialize() {
        Intent intent = new Intent(this, Initializer.class);
        startActivity(intent);
        finish();
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        Avatar.getInstance().setFinalForm(null);
        Avatar.getInstance().setUrl("empty");
        finish();
    }

    public void startAvatarCreator(View view) {
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
    }

    public void seeAvatar(View view) {

        Animation fadeIn;


        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);


        seeAvatarImage.setImageBitmap(Avatar.getInstance().getFinalForm());

        seeAvatarImage.startAnimation(fadeIn);

        seeAvatarButton.setVisibility(View.GONE);
    }

}