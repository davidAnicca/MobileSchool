package com.kikoteam.mobileschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.kikoteam.mobileschool.RegisterLogIn.RegisterActivity;
import com.kikoteam.mobileschool.avatar.AvatarInitialize;
import com.kikoteam.mobileschool.avatar.BaseSelector;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();


        if (fAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        }

        ///AvatarInitialize.initialize();

    }

 /*/
    public void getAvatarUrl(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if ( documentSnapshot.exists()){
                            Map<String, Object> data = documentSnapshot.getData();
                            assert data != null;
                            String text = (String) data.get("userID");
                            Avatar.getInstance().setUrl(text);
                            texttest = text;
                            Toast toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });

    }

  */



    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        finish();
    }

    public void startAvatarCreator(View view){
        Intent intent = new Intent(this, BaseSelector.class);
        startActivity(intent);
    }

}