package com.kikoteam.mobileschool.avatar.newAvatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.entities.Avatar;
import com.kikoteam.mobileschool.avatar.processors.ImageProcessor;

public class OtherAccessorySelector extends AppCompatActivity {


    private View lastSelected;
    private ImageView finalForm;

    private ImageView option1;
    private ImageView option2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_accessory_selector);
        setViews();

        lastSelected = null;
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());

        showOptions();
    }

    private void setViews(){
        finalForm = findViewById(R.id.otherAccessorySelectorFinalForm);

        option1 = findViewById(R.id.otherAccessorySelectorOption1);
        option2 = findViewById(R.id.otherAccessorySelectorOption2);
    }

    private void showOptions() {
        ///no opt yet
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
        Intent intent = new Intent(this, EyesAccessorySelector.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    public void selectOption(View view) {
        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;
        Avatar.getInstance().setOther_accessory(ImageProcessor.getBitmapFromView(view));
        Avatar.getInstance().rebuild();
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
    }
}