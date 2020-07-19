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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kikoteam.mobileschool.MainActivity;
import com.kikoteam.mobileschool.R;

import java.lang.ref.WeakReference;

public class SaveAvatar extends AppCompatActivity implements Animation.AnimationListener {

    Avatar avatar;
    ProgressBar mProgressBar;
    ImageView finalForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_avatar);

        mProgressBar = findViewById(R.id.saveProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        avatar = Avatar.getInstance();

        finalForm = findViewById(R.id.saveAvatarFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());

    }

    public void saveAvatar(View view) {
        Animation animZoomIn;

        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animZoomIn.setAnimationListener(this);
        finalForm.startAnimation(animZoomIn);

        findViewById(R.id.saveAvatarBack).setVisibility(View.GONE);
        view.setVisibility(View.INVISIBLE);
        new BackgroundGateToSavingProcessor(this).execute();
    }

    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    public void backSelector (View view) {
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

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}