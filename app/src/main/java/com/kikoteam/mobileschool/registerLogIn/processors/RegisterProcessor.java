package com.kikoteam.mobileschool.registerLogIn.processors;

import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterProcessor  {
    ///this class has background methods to initialize data-base :)

    private static String classroomCode;

    public static String getClassroomCode() {
        return classroomCode;
    }

    public static void setClassroomCode(String newClassroomCode) {
        classroomCode = newClassroomCode;
    }

    public static void processRegister(){
            new InitializeDataBaseFields().execute();
    }

    private static class InitializeDataBaseFields extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Map<String, String> avatarUrl = new HashMap<>();
            assert firebaseUser != null;
            avatarUrl.put("userID", firebaseUser.getUid());
            avatarUrl.put("Avatar url", "empty");
            CollectionReference AvatarUrlLocation = db.collection("avatars");
            AvatarUrlLocation.document(firebaseUser.getUid()).set(avatarUrl);


            Map<String, String> classroom = new HashMap<>();
            classroom.put("classroom", classroomCode);
            CollectionReference users = db.collection("users");
            users.document(firebaseUser.getUid()).set(classroom);

            return null;
        }
    }

}
