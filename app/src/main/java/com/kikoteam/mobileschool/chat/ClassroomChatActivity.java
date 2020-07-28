package com.kikoteam.mobileschool.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kikoteam.mobileschool.R;
import com.kikoteam.mobileschool.User;
import com.kikoteam.mobileschool.avatar.entities.Avatar;

import java.util.Objects;

public class ClassroomChatActivity extends AppCompatActivity {

    private FirebaseListAdapter<Message> adapter;
    private Initializer processor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        processor = new Initializer();
        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);

        displayChat();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.input);
                FirebaseDatabase.getInstance()
                        .getReference("/classrooms/"+User.getInstance().getClassroomCode())
                        .push()
                        .setValue(new Message(input.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getUid())
                        );
                input.setText("");
                hideKeyboard();
            }
        });


    }

    private void hideKeyboard(){
        try {
            InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
            assert inputManager != null;
            inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayChat(){
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<Message>(this, Message.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference("classrooms/"+User.getInstance().getClassroomCode())) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView messageText = (TextView)v.findViewById(R.id.messageText);
                TextView messageUser = (TextView)v.findViewById(R.id.messageUser);
                TextView messageTime = (TextView)v.findViewById(R.id.messageTime);
                ///processor.pasteBitmap("first", (ImageView)v.findViewById(R.id.messageAvatar));

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));

            }
        };
        listOfMessages.setAdapter(adapter);
    }
}