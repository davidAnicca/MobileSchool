package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;

public class BaseSelector extends AppCompatActivity {

    Avatar avatar;
    private View lastSelected;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_selector);

        nextButton = findViewById(R.id.baseSelectorNext);
        nextButton.setVisibility(View.GONE);
        lastSelected = null;
        avatar = Avatar.getInstance();
        if (avatar.getBase() != null) {
            ImageView finalForm = findViewById(R.id.baseSelectorFinalForm);
            finalForm.setImageBitmap(avatar.getFinalForm());
        }

        showOptions();

    }

    public void skip (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showOptions() {
        ImageView option1 = findViewById(R.id.baseSelectorOption1);
        option1.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.square_base_388c91));
        ImageView option2 = findViewById(R.id.baseSelectorOption2);
        option2.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.square_base_e2152d));
    }



    public void nextSelector(View view) {
        view.setVisibility(View.GONE);
        finish();
        Intent intent = new Intent(this, EyesSelector.class);
        startActivity(intent);

    }

    public void selectOption(View view) {

        nextButton.setVisibility(View.VISIBLE);
        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;
        avatar.setBase(ImageProcessor.getBitmapFromView(view));
        avatar.rebuild();
        ImageView finalForm = findViewById(R.id.baseSelectorFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());
    }


}