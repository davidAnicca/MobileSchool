package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;

public class BaseSelector extends AppCompatActivity {


    private View lastSelected;
    private Button nextButton;
    private ImageView finalForm;

    private ImageView option1;
    private ImageView option2;


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

    private void setViews(){
        nextButton = findViewById(R.id.baseSelectorNext);
        finalForm = findViewById(R.id.baseSelectorFinalForm);

        option1 = findViewById(R.id.baseSelectorOption1);
        option2 = findViewById(R.id.baseSelectorOption2);
    }

    public void skip (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void showOptions() {
        option1.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.square_base_388c91));
        option2.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.square_base_e2152d));
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