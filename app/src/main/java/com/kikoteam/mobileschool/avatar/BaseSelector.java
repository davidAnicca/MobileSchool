package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;

public class BaseSelector extends AppCompatActivity {

    Avatar avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_selector);

        avatar = Avatar.getInstance();
        if ( avatar.getBase() != null) {
            ImageView finalForm = findViewById(R.id.baseSelectorFinalForm);
            finalForm.setImageBitmap(avatar.getFinalForm());
        }

        showOptions();

    }

    private void showOptions(){
        ImageView option1 = findViewById(R.id.baseSelectorOption1);
        option1.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.square_base_388c91));
        ImageView option2 = findViewById(R.id.baseSelectorOption2);
        option2.setImageBitmap(BitmapFactory.decodeResource(BaseSelector.this.getResources(), R.drawable.square_base_e2152d));
    }

    public void nextSelector(View view){
        Intent intent = new Intent(this, EyesSelector.class);
        startActivity(intent);
    }

    public void selectOption(View view){

        avatar.setBase(ImageProcessor.getBitmapFromView(view));
        avatar.rebuild();
        ImageView finalForm = findViewById(R.id.baseSelectorFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());
    }


}