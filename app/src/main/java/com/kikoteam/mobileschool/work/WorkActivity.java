package com.kikoteam.mobileschool.work;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.User;

public class WorkActivity extends AppCompatActivity {

    TextView testInfo;
    private String lessonTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        setViews();
        lessonTitle = "firstLesson";
        loadLessons();

    }

    private void loadLessons(){
        FirebaseFirestore  db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("classrooms").document(User.getInstance().getClassroomCode()).collection("lessons").document(lessonTitle);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String lessonURL = (String) documentSnapshot.getData().get("URL");
                            testInfo.setText(lessonURL);
                    }
                });
    }

    private void setViews(){
        testInfo = findViewById(R.id.testInfo);
    }

}