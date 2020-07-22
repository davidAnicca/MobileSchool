package com.kikoteam.mobileschool.registerLogIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.newAvatar.BaseSelector;

public class AgreeActivity extends AppCompatActivity {

    Switch agreeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree);
        agreeSwitch = (Switch) findViewById(R.id.agreeSwitch);

        agreeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getBaseContext(), getString(R.string.thank), Toast.LENGTH_SHORT).show();
                    openNextActivity();
                }
            }
        });

    }

    public void openNextActivity(){
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
        finish();
    }
}
