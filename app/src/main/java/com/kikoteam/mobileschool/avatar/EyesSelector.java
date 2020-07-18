package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;

import java.lang.ref.WeakReference;

public class EyesSelector extends AppCompatActivity {

    Avatar avatar;
    private View lastSelected;
    private ImageView eyesSelectorOption1;
    private ImageView eyesSelectorOption2;
    private ImageView eyesSelectorFinalForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyes_selector);
        eyesSelectorFinalForm = findViewById(R.id.eyesSelectorFinalForm);
        eyesSelectorOption1 = findViewById(R.id.eyesSelectorOption1);
        eyesSelectorOption2 = findViewById(R.id.eyesSelectorOption2);

        avatar = Avatar.getInstance();
        lastSelected = null;

        new LoadData(this, EyesSelector.this).execute();
    }

    private static class LoadData extends AsyncTask<Void, Void, Void> {
        private WeakReference<EyesSelector> activityWeakReference;


        private Bitmap option1;
        private Bitmap option2;

        private Bitmap finalForm;

        @SuppressLint("StaticFieldLeak")
        private Context mContext;

        LoadData(EyesSelector activity, Context context) {
            activityWeakReference = new WeakReference<EyesSelector>(activity);
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            option1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eyes_60901b);
            option2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eyes_384f90);
            finalForm = Avatar.getInstance().getFinalForm();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            EyesSelector activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return null;


            activity.eyesSelectorOption1.setImageBitmap(option1);
            activity.eyesSelectorOption2.setImageBitmap(option2);
            activity.eyesSelectorFinalForm.setImageBitmap(finalForm);
            return null;
        }
    }

    public void previousSelector(View view){
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
        finish();
    }


    public void nextSelector(View view) {
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, MouthSelector.class);
        startActivity(intent);
        finish();
    }

    public void selectOption(View view) {

        if (lastSelected != null)
            lastSelected.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.LTGRAY);
        lastSelected = view;
        avatar.setEyes(ImageProcessor.getBitmapFromView(view));
        avatar.rebuild();
        ImageView finalForm = findViewById(R.id.eyesSelectorFinalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());
    }

}