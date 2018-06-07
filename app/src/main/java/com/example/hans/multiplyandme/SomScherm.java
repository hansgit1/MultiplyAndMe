package com.example.hans.multiplyandme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SomScherm extends AppCompatActivity {

    TextView som;
    Button optie1;
    Button optie2;
    Button optie3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_som_scherm);

        som = (TextView) findViewById(R.id.som);
        optie1 = (Button) findViewById(R.id.optie1);
        optie2 = (Button) findViewById(R.id.optie2);
        optie3 = (Button) findViewById(R.id.optie3);

        calcualteSom((int) (Math.random() * 10));
    }

    //methode om som te berekenen
    public void calcualteSom(int number) {

        // loop tot de tafel van 10
        for (int i = 1; i <= 10; i++) {
            int value = i * number;
            int random1 = (int) (Math.random() * i);
            int random2 = (int) (Math.random() * number);
            if(random1 == 0 || random2 == 0){
                random1 = random1 + 1;
                random2 = random2 + 1;
            }
            som.setText(random1 + " x " + random2 + " = ");
        }

    }

}
