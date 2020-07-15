package com.kikoteam.mobileschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kikoteam.mobileschool.avatar.Avatar;
import com.kikoteam.mobileschool.avatar.AvatarInitialize;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.BitSet;
import java.util.Map;

public class Initializer extends AppCompatActivity {


    /*/
    1. Get avatar image url
    2. Download avatar image and set it to avatar final form
    3. ... others (in future)
     */

    ProgressBar initializeProgressBarr;
    ImageView avatarFinalForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initializer);
        initializeProgressBarr = findViewById(R.id.initializeProgressBar);
        initializeProgressBarr.setVisibility(View.VISIBLE);
        avatarFinalForm = findViewById(R.id.avatarInitialize);


        AvatarInitialize.initialize();
        getAvatarImageUrl();


    }

    private void downloadProcess() {
        AvatarDownloader task = new AvatarDownloader(this);
        task.execute(Avatar.getInstance().getUrl());
    }

    private void exitInitializer(){
        Application.getInstance().setInitialized(true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Map<String, Object> data = documentSnapshot.getData();
                            Avatar avatar = Avatar.getInstance();
                            String url = (String) data.get("Avatar url");
                            avatar.setUrl(url);
                            if (!url.equals("empty"))
                                downloadProcess();
                        } else {
                            Avatar.getInstance().setUrl("empty");
                        }
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Avatar avatar = Avatar.getInstance();
                        avatar.setUrl("empty");

                    }
                });

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}