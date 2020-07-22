package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.newAvatar.HairSelector;

import java.lang.ref.WeakReference;

public class SaveAvatar extends AppCompatActivity {


    ProgressBar mProgressBar;
    ImageView finalForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_avatar);
        setViews();
        initializeViews();
    }

    private void setViews(){
        mProgressBar = findViewById(R.id.saveProgressBar);
        finalForm = findViewById(R.id.saveAvatarFinalForm);
    }

    private void initializeViews(){
        mProgressBar.setVisibility(View.INVISIBLE);

        Animation smoothFloat = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.smooth_float);
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
        finalForm.startAnimation(smoothFloat);
    }

    public void saveAvatar(View view) {

        Animation animZoomIn;
        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        finalForm.startAnimation(animZoomIn);

        findViewById(R.id.saveAvatarBack).setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        new BackgroundGateToSavingProcessor(this).execute();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    public void backSelector(View view) {
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, HairSelector.class);
        startActivity(intent);
        finish();
    }


    private static class BackgroundGateToSavingProcessor extends AsyncTask<Void, Void, Void> {
        private WeakReference<SaveAvatar> activityWeakReference;

        BackgroundGateToSavingProcessor(SaveAvatar activity) {
            activityWeakReference = new WeakReference<SaveAvatar>(activity);
        }

        @Override
        protected void onPreExecute() {
            SaveAvatar activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;

            activity.mProgressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            SavingProcessor.uploadImage(Avatar.getInstance().getFinalForm());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            SaveAvatar activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;


            activity.mProgressBar.setVisibility(View.INVISIBLE);
            activity.startMainActivity();

        }
    }

}