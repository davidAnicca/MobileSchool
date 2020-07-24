package com.kikoteam.mobileschool.avatar.newAvatar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.entities.Avatar;
import com.kikoteam.mobileschool.avatar.processors.ImageProcessor;

public class EyesSelector extends AppCompatActivity {


    private View lastSelected;
    private ImageView finalForm;

    private ImageView option1;
    private ImageView option2;
    private ImageView option3;
    private ImageView option4;
    private ImageView option5;
    private ImageView option6;
    private ImageView option7;
    private ImageView option8;
    private ImageView option9;
    private ImageView option10;
    private ImageView option11;
    private ImageView option12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyes_selector);
        setViews();
        lastSelected = null;

        showOptions();
    }

    private void setViews() {
        finalForm = findViewById(R.id.eyesSelectorFinalForm);
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());

        option1 = findViewById(R.id.eyesSelectorOption1);
        option2 = findViewById(R.id.eyesSelectorOption2);
        option3 = findViewById(R.id.eyesSelectorOption3);
        option4 = findViewById(R.id.eyesSelectorOption4);
        option5 = findViewById(R.id.eyesSelectorOption5);
        option6 = findViewById(R.id.eyesSelectorOption6);
        option7 = findViewById(R.id.eyesSelectorOption7);
        option8 = findViewById(R.id.eyesSelectorOption8);
        option9 = findViewById(R.id.eyesSelectorOption9);
        option10 = findViewById(R.id.eyesSelectorOption10);
        option11 = findViewById(R.id.eyesSelectorOption11);
        option12 = findViewById(R.id.eyesSelectorOption12);
    }

    private void showOptions() {
        option1.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes1));
        option2.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes2));
        option3.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes3));
        option4.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes4));
        option5.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes5));
        option6.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes6));
        option7.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes7));
        option8.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes8));
        option9.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes9));
        option10.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes10));
        option11.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes11));
        option12.setImageBitmap(BitmapFactory.decodeResource(EyesSelector.this.getResources(), R.drawable.eyes12));
    }

    public void previousSelector(View view) {
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    public void nextSelector(View view) {
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, NoseSelector.class);
        startActivity(intent);
        finish();
    }

    public void selectOption(View view) {

        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;
        Avatar.getInstance().setEyes(ImageProcessor.getBitmapFromView(view));
        Avatar.getInstance().rebuild();
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
    }

}