package com.kikoteam.mobileschool.avatar.newAvatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.entities.Avatar;
import com.kikoteam.mobileschool.avatar.processors.ImageProcessor;

public class BaseSelector extends AppCompatActivity {


    private View lastSelected;
    private Button nextButton;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_selector);

        setViews();
        nextButton.setVisibility(View.GONE);

        lastSelected = null;

        if (Avatar.getInstance().getBase() != null) {
            finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
        }

        showOptions();

    }

    private void setViews() {
        nextButton = findViewById(R.id.baseSelectorNext);
        finalForm = findViewById(R.id.baseSelectorFinalForm);

        option1 = findViewById(R.id.baseSelectorOption1);
        option2 = findViewById(R.id.baseSelectorOption2);
        option3 = findViewById(R.id.baseSelectorOption3);
        option4 = findViewById(R.id.baseSelectorOption4);
        option5 = findViewById(R.id.baseSelectorOption5);
        option6 = findViewById(R.id.baseSelectorOption6);
        option7 = findViewById(R.id.baseSelectorOption7);
        option8 = findViewById(R.id.baseSelectorOption8);
        option9 = findViewById(R.id.baseSelectorOption9);
        option10 = findViewById(R.id.baseSelectorOption10);
        option11 = findViewById(R.id.baseSelectorOption11);
    }

    public void skip(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void showOptions() {
        option1.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base1));
        option2.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base2));
        option3.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base3));
        option4.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base4));
        option5.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base5));
        option6.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base6));
        option7.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base7));
        option8.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base8));
        option9.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base9));
        option10.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base10));
        option11.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.base11));
    }


    public void nextSelector(View view) {
        view.setVisibility(View.GONE);
        finish();
        Intent intent = new Intent(this, EyesSelector.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void selectOption(View view) {

        nextButton.setVisibility(View.VISIBLE);
        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;
        Avatar.getInstance().setBase(ImageProcessor.getBitmapFromView(view));
        Avatar.getInstance().rebuild();
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
    }


}