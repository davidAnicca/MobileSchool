package com.kikoteam.mobileschool.avatar.newAvatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.Avatar;
import com.kikoteam.mobileschool.avatar.ImageProcessor;
import com.kikoteam.mobileschool.avatar.SaveAvatar;

public class HairSelector extends AppCompatActivity {


    private View lastSelected;
    private ImageView finalForm;

    private ImageView option1;
    private ImageView option2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_selector);
        setViews();

        lastSelected = null;
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());

        showOptions();
    }

    private void setViews(){
        finalForm = findViewById(R.id.hairSelectorFinalForm);

        option1 = findViewById(R.id.hairSelectorOption1);
        option2 = findViewById(R.id.hairSelectorOption2);
    }

    private void showOptions() {
        option1.setImageBitmap(BitmapFactory.decodeResource(HairSelector.this.getResources(), R.drawable.hair_1));
        option2.setImageBitmap(BitmapFactory.decodeResource(HairSelector.this.getResources(), R.drawable.hair_2));
    }

    public void nextSelector(View view) {
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, SaveAvatar.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    public void previousSelector(View view){
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, MouthSelector.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    public void selectOption(View view) {
        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;
        Avatar.getInstance().setHair(ImageProcessor.getBitmapFromView(view));
        Avatar.getInstance().rebuild();
        ImageView finalForm = findViewById(R.id.hairSelectorFinalForm);
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
    }
}