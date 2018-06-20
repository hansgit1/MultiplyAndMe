package com.example.hans.multiplyandme;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static final String USER_NAME = "username";
    public static final String USER_SCORE = "score";
    DatabaseReference rootref, userref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startButton);
        Button buttons = (Button) findViewById(R.id.buttons);
        Button instellingen = (Button) findViewById(R.id.instellingen);

        //start muziek
        MediaPlayer backgroundmusic = MediaPlayer.create(getBaseContext(), R.raw.backgroundmusic);
        backgroundmusic.start();




        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start firebase
                // Write a message to the database
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                rootref = database.getReference();
//                userref = rootref.child("user");

                EditText username = (EditText) findViewById(R.id.username);

              User user1 = new User(username.getText().toString(), 0);
//                userref.push().setValue(user1);
                Intent intent = new Intent(MainActivity.this, SomScherm.class);


                intent.putExtra(USER_NAME, user1.getUsername());
                intent.putExtra(USER_SCORE, user1.getScore());

                startActivity(intent);
            }
        });



//
//        // Read from the database
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Post post = dataSnapshot.getValue(Post.class);
//                myRef.setValue(post);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
//        myRef.addValueEventListener(postListener);
//        //end firebase
    }
//    public void onButtonClick(View v){
//        if(v.getId() == R.id.startButton){
//            Intent i = new Intent(MainActivity.this, SomScherm.class);
//            startActivity(i);
//        }
//    }
}
