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

    Avatar avatar;
    private View lastSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth_selector);

        lastSelected = null;
        avatar = Avatar.getInstance();

        ImageView finalForm = findViewById(R.id.mouthSelectorFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());

        showOptions();

    }

    private void showOptions() {
        ImageView option1 = findViewById(R.id.mouthSelectorOption1);
        option1.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth_open));
        ImageView option2 = findViewById(R.id.mouthSelectorOption2);
        option2.setImageBitmap(BitmapFactory.decodeResource(MouthSelector.this.getResources(), R.drawable.mouth_almost_closed));
    }

    public void nextSelector(View view) {
        Intent intent = new Intent(this, HairSelector.class);
        startActivity(intent);
    }

    public void selectOption(View view) {

        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;

        avatar.setMouth(ImageProcessor.getBitmapFromView(view));
        avatar.rebuild();
        ImageView finalForm = findViewById(R.id.mouthSelectorFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());
    }

}