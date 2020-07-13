package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;

public class SaveAvatar extends AppCompatActivity {

    Avatar avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_avatar);

        avatar = Avatar.getInstance();

        ImageView finalForm = findViewById(R.id.saveAvatarFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());

    }

    public void saveAvatar(View view){
        ///save avatar entity on database
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}