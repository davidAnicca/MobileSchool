package com.kikoteam.mobileschool.registerLogIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.registerLogIn.processors.RegisterProcessor;

public class ClassroomCodeRoom extends AppCompatActivity {

    private EditText classroomCode;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_code_room);
        setViews();



    }

    private void setViews(){
        classroomCode = (EditText) findViewById(R.id.classroomCodeEdit);
        progressBar = (ProgressBar) findViewById(R.id.classroomCodeCheckProgress);

        progressBar.setVisibility(View.INVISIBLE);
    }

    public void check(View view){
        progressBar.setVisibility(View.VISIBLE);

        final String strClassCode = classroomCode.getText().toString().trim();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("classrooms").document(strClassCode);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        Intent intent = new Intent(getApplicationContext(), AgreeActivity.class);
                        RegisterProcessor.setClassroomCode(strClassCode);
                        RegisterProcessor.processRegister();

                        startActivity(intent);
                        finish();
                    }else{
                        classroomCode.setError(getString(R.string.classroom_do_not_exists));
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

    }



}