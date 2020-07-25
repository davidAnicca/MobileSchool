package com.kikoteam.mobileschool.work;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.User;

public class WorkActivity extends AppCompatActivity {

    LinearLayout info;
    private int numOfLessons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        setViews();
        loadLessons();

    }

    private void loadLessons() {
        for (int index = 1; index <= User.getInstance().getNumOfLessons(); index++) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference documentReference = db.collection("classrooms").document(User.getInstance().getClassroomCode()).collection("lessons").document(String.valueOf(index));
            documentReference.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String lessonURL = (String) documentSnapshot.getData().get("URL");
                            TextView newInfo = new TextView(getApplicationContext());
                            newInfo.setText(lessonURL);
                            info.addView(newInfo);
                        }
                    });
        }
    }

    private void setViews() {
        info = findViewById(R.id.info);
    }

}