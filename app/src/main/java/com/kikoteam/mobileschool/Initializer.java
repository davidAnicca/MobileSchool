package com.kikoteam.mobileschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kikoteam.mobileschool.avatar.Avatar;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Initializer extends AppCompatActivity {


    /*/
    1. Get avatar image url
    2. Download avatar image and set it to avatar final form
    3. ... others (in future)
     */

    ProgressBar initializeProgressBarr;
    ImageView logoInitialize;

    String finalFormUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initializer);
        setViews();

        initializeProgressBarr.setVisibility(View.VISIBLE);

        logoAnimate();

        Avatar.resetInstance();
        Avatar.getInstance();

        getAvatarImageUrl();

    }
    private void setViews(){
        initializeProgressBarr = findViewById(R.id.initializeProgressBar);
        logoInitialize = findViewById(R.id.logoInitialize);
    }

    private void logoAnimate(){
        Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.smooth_float);
        logoInitialize.startAnimation(zoomIn);
    }

    private void downloadProcess() {
        AvatarDownloader task = new AvatarDownloader(this);
        task.execute(finalFormUrl);
    }

    private void exitInitializer() {
        Application.getInstance().setInitialized(true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    private static class AvatarDownloader extends AsyncTask<String, Integer, Bitmap> {
        private WeakReference<Initializer> activityWeakReference;

        AvatarDownloader(Initializer activity) {
            activityWeakReference = new WeakReference<Initializer>(activity);
        }

        @Override
        protected void onPreExecute() {
            Initializer activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;

            activity.initializeProgressBarr.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String src = strings[0];
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            Avatar.getInstance().setFinalForm(bitmap);
            Initializer activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;

            activity.exitInitializer();
        }


    }

    private void getAvatarImageUrl() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("avatars").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Map<String, Object> data = documentSnapshot.getData();
                            String url = (String) data.get("Avatar url");
                            finalFormUrl = url;
                            if (!url.equals("empty"))
                                downloadProcess();
                            else exitInitializer();
                        }else{
                            exitInitializer();
                        }
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        finalFormUrl = "empty";

                    }
                });

    }

}