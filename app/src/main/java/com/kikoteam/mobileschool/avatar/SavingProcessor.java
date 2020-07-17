package com.kikoteam.mobileschool.avatar;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SavingProcessor  {

    public static void uploadImage(Bitmap bitmap) {
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

        final StorageReference ref = mStorageRef.child("avatars/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + ".png");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();

        final UploadTask uploadTask = ref.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw Objects.requireNonNull(task.getException());
                        }
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downUri = task.getResult();
                            assert downUri != null;
                            Avatar.getInstance().setUrl(downUri.toString());
                            saveUrl();
                        }
                    }
                });
            }
        });
    }

    private static void saveUrl() {
        Avatar avatar = Avatar.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, String> url = new HashMap<>();
        url.put("userID", FirebaseAuth.getInstance().getCurrentUser().getUid());
        url.put("Avatar url", avatar.getUrl());
        CollectionReference urls = db.collection("avatars");
        urls.document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(url);
    }


}
