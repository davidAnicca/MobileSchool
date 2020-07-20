package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;

public class MouthSelector extends AppCompatActivity {


    private View lastSelected;

    private ImageView finalForm;

    private ImageView option1;
    private ImageView option2;
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
        ///
    }

    private void showOptions() {
        option1.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth_open));
        option2.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth_almost_closed));
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
        Intent intent = new Intent(this, EyesSelector.class);
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
        ImageView finalForm = findViewById(R.id.mouthSelectorFinalForm);
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
    }

}