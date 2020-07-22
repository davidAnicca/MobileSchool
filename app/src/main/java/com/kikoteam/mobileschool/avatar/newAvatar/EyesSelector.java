package com.kikoteam.mobileschool.avatar.newAvatar;

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

import androidx.appcompat.app.AppCompatActivity;

import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.avatar.Avatar;
import com.kikoteam.mobileschool.avatar.ImageProcessor;

import java.lang.ref.WeakReference;

public class EyesSelector extends AppCompatActivity {


    private View lastSelected;
    private ImageView finalForm;
    private Button nextSelector;


    private ImageView option1;
    private ImageView option2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyes_selector);
        setViews();
        lastSelected = null;

        new LoadData(this, EyesSelector.this).execute();
    }

    private void setViews(){
        finalForm = findViewById(R.id.eyesSelectorFinalForm);

        option1 = findViewById(R.id.eyesSelectorOption1);
        option2 = findViewById(R.id.eyesSelectorOption2);
    }

    private static class LoadData extends AsyncTask<Void, Void, Void> {
        private WeakReference<EyesSelector> activityWeakReference;


        private Bitmap option1Bitmap;
        private Bitmap option2Bitmap;

        private Bitmap finalFormBitmap;

        @SuppressLint("StaticFieldLeak")
        private Context mContext;

        LoadData(EyesSelector activity, Context context) {
            activityWeakReference = new WeakReference<EyesSelector>(activity);
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            option1Bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eyes_60901b);
            option2Bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eyes_384f90);

            finalFormBitmap = Avatar.getInstance().getFinalForm();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            EyesSelector activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return null;

            activity.option1.setImageBitmap(option1Bitmap);
            activity.option2.setImageBitmap(option2Bitmap);

            activity.finalForm.setImageBitmap(finalFormBitmap);
            return null;
        }
    }

    public void previousSelector(View view){
        view.setVisibility(View.GONE);
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
        Avatar.getInstance().setEyes(ImageProcessor.getBitmapFromView(view));
        Avatar.getInstance().rebuild();
        finalForm.setImageBitmap(Avatar.getInstance().getFinalForm());
    }

}