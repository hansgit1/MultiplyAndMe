package com.example.hans.multiplyandme;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Resultaat extends AppCompatActivity {

    private PieChart mChart;
    public static final int MAX_ECTS = 10;
    public static int currentEcts = 0;
    public static int score;
    public static String naam;
    Button buttonTerug;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultaat);

        mChart = (PieChart) findViewById(R.id.chart);
//        Description description = new Description();
//        description.setText("description");
//        mChart.setDescription(description);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(false);
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        buttonTerug = (Button) findViewById(R.id.terugButton);

        buttonTerug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(Resultaat.this, MainActivity.class);
                startActivity(intentHome);
            }
        });

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference().child("user");


//        Intent intent = getIntent();
//        String score = intent.getStringExtra(SomScherm.USER_SCORE);
//        String username = intent.getStringExtra(SomScherm.USER_NAME);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseReference.child("user").orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    //Username wordt hier opgehaald
                    System.out.println("username = " + child.child("username").getValue().toString());
                    //Score wordt hier opgehaald
                    System.out.println("score = " + child.child("score").getValue().toString());
                    //alvast in variable vorm
                    String username = child.child("username").getValue().toString();
                    // om er mee te rekenen moeten omzetten naar int
                    int score = Integer.parseInt(child.child("score").getValue().toString());
                    setData(score);
                    //testen
                    System.out.println("score = " + score);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Handle possible errors.
            }
        });
//        ValueEventListener postListener = new ValueEventListener() {
//            //intialiseer het pad
//
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                DatabaseReference username = myRef.child("username");
//                DatabaseReference score = myRef.child("score");
//
//                    // Get Post object and use the values to update the UI
////                    String score = dataSnapshot.child("score").getValue().toString();
////                    userRef.setValue(user);
//                    System.out.println("username = " + username);
//                    System.out.println("score = " + score);
////                    System.out.println("score = " + user.score);
////                    System.out.println("username = " + user.username);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w( "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
//        myRef.addValueEventListener(postListener);

//        Button fab = (Button) findViewById(R.id.plusTweeTest);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (currentEcts < MAX_ECTS) {
//                    setData(currentEcts += 2);
//                } else {
//                    setData(currentEcts = 0);
//                }
//            }
//        });


    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference("users").push();
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
//                    User user = userSnapshot.getValue(User.class);
//                    System.out.println();
//                    score = user.score;
//                    naam = user.username;
//                    System.out.println("score = " + score);
//                    System.out.println("naam = " + naam);
//                }
//            }

//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    private void setData(int aantal) {
        currentEcts = aantal;
        ArrayList<PieEntry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();


        yValues.add(new PieEntry(10 - currentEcts, "Fout"));
        xValues.add("Behaalde ECTS");

        yValues.add(new PieEntry(currentEcts, "Goed"));
        xValues.add("Resterende ECTS");


        //  http://www.materialui.co/colors
        ArrayList<Integer> colors = new ArrayList<>();
        if (currentEcts > 1 || currentEcts <= 10) {
            colors.add(Color.rgb(255,0,0));
        } else {
            colors.add(Color.rgb(255,0,0));
        }
        colors.add(Color.rgb(0,255,0));

        PieDataSet dataSet = new PieDataSet(yValues, "ECTS");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        mChart.setData(data); // bind dataset aan chart.
        mChart.invalidate();  // Aanroepen van een redraw
        Log.d("aantal =", ""+currentEcts);
    }
}
