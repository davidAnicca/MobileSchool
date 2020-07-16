package com.kikoteam.mobileschool.RegisterLogIn;

import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterProcessor  {
    ///this class has background methods to initialize data-base :)

    public static void processRegister(){
            new InitializeDataBaseFields().execute();
    }

    private static class InitializeDataBaseFields extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Map<String, String> url = new HashMap<>();
            assert firebaseUser != null;
            url.put("userID", firebaseUser.getUid());
            url.put("Avatar url", "empty");
            CollectionReference urls = db.collection("avatars");
            urls.document(firebaseUser.getUid()).set(url);

            return null;
        }
    }

}
