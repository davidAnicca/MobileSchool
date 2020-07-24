package com.kikoteam.mobileschool.avatar.newAvatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.entities.Avatar;
import com.kikoteam.mobileschool.avatar.processors.ImageProcessor;

public class MouthSelector extends AppCompatActivity {


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

    ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth_selector);
        setViews();

        lastSelected = null;

        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());

        showOptions();

    }

    private void setViews() {
        finalForm = findViewById(R.id.mouthSelectorFinalForm);

        option1 = findViewById(R.id.mouthSelectorOption1);
        option2 = findViewById(R.id.mouthSelectorOption2);
        option3 = findViewById(R.id.mouthSelectorOption3);
        option4= findViewById(R.id.mouthSelectorOption4);
        option5 = findViewById(R.id.mouthSelectorOption5);
        option6 = findViewById(R.id.mouthSelectorOption6);
        option7 = findViewById(R.id.mouthSelectorOption7);
        option8 = findViewById(R.id.mouthSelectorOption8);
        ///
    }

    private void showOptions() {
        option1.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth1));
        option2.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth2));
        option3.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth3));
        option4.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth4));
        option5.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth5));
        option6.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth6));
        option7.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth7));
        option8.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth8));
        ///
    }

    public void nextSelector(View view) {
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, HairSelector.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    public void previousSelector(View view) {
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, NoseSelector.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    public void selectOption(View view) {

        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;

        Avatar.getInstance().setMouth(ImageProcessor.getBitmapFromView(view));
        Avatar.getInstance().rebuild();
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
    }

}