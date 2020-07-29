package com.kikoteam.mobileschool.registerLogIn;

import androidx.appcompat.app.AppCompatActivity;
import pl.droidsonroids.gif.GifImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.newAvatar.BaseSelector;

public class AgreeActivity extends AppCompatActivity {

    Switch agreeSwitch;
    GifImageView bubbles1;
    GifImageView bubbles2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree);
        setViews();
        initializeViews();

        agreeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getBaseContext(), getString(R.string.thank), Toast.LENGTH_SHORT).show();

                    bubbles1.setVisibility(View.VISIBLE);
                    bubbles2.setVisibility(View.VISIBLE);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            openNextActivity();
                        }
                    }, 3000);   //5 seconds

                }
            }
        });

    }

    private void setViews() {
        bubbles1 = findViewById(R.id.agreeBubbles);
        bubbles2 = findViewById(R.id.agreeBubbles2);
        agreeSwitch = (Switch) findViewById(R.id.agreeSwitch);
    }

    private void initializeViews() {
        bubbles1.setVisibility(View.INVISIBLE);
        bubbles2.setVisibility(View.INVISIBLE);
    }

    private void openNextActivity() {
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
        finish();
    }
}
