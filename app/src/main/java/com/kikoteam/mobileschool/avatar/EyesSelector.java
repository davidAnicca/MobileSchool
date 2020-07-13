package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;

public class EyesSelector extends AppCompatActivity {

    Avatar avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyes_selector);

        avatar = Avatar.getInstance();

        ImageView finalForm = findViewById(R.id.eyesSelectorFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());

        showOptions();

    }

    private void showOptions(){
        ImageView option1 = findViewById(R.id.eyesSelectorOption1);
        option1.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes_60901b));
        ImageView option2 = findViewById(R.id.eyesSelectorOption2);
        option2.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes_384f90));
    }

    public void nextSelector(View view){
        Intent intent = new Intent(this, MouthSelector.class);
        startActivity(intent);
    }

    public void selectOption(View view){

        avatar.setEyes(ImageProcessor.getBitmapFromView(view));
        avatar.rebuild();
        ImageView finalForm = findViewById(R.id.eyesSelectorFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());
    }

}