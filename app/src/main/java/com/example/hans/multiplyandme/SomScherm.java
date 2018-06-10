package com.example.hans.multiplyandme;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SomScherm extends AppCompatActivity {
    // initialize de ui componenten
    TextView som;
    Button optie1;
    Button optie2;
    Button optie3;
    Button next;
    //iniatileze variabelen
    int aantalgoed = 0;
    int aantalfout = 0;
    int value;
    int antwoord;
    int currentNumber = 1;
    int teller = 1;
    TextView somteller;

    //oncreate functie
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_som_scherm);
        // iniatilize id's
        som = (TextView) findViewById(R.id.som);
        somteller = (TextView) findViewById(R.id.somteller);
        optie1 = (Button) findViewById(R.id.optie1);
        optie2 = (Button) findViewById(R.id.optie2);
        optie3 = (Button) findViewById(R.id.optie3);
        next = (Button) findViewById(R.id.next);

        //roep methode aan voor berekening
        calcualteSom((int) (Math.random() * 10));
        //volgende knop, deze is alleen zichtbaar na invullen antwoord
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // blijf tot 10 keer doorgaan
                if(currentNumber <= 10){
                    currentNumber++;
                    //reset de oude variabelen
                    optie1.setEnabled(true);
                    optie2.setEnabled(true);
                    optie3.setEnabled(true);
                    // hoog ieder cijfer met 1 op zodra
                    // de gebruiker op volgende tapt
                    teller++;
                    somteller.setText(""+teller+"/10");

                    // verander de tekst van de button
                    // zodra cuurentNumber bij de 10e som
                    if (currentNumber == 10){
                        next.setText("Resultaat");
                    }
                    // hieronder een if statement die ervoor
                    // zorgt dat de onderstaande objecten niet
                    // worden weergegeven zodra currentNumber op 11 beland
                    if (currentNumber == 11){
                        optie1.setVisibility(View.GONE);
                        optie2.setVisibility(View.GONE);
                        optie3.setVisibility(View.GONE);
                        somteller.setVisibility(View.GONE);
                        next.setVisibility(View.GONE);
                        som.setVisibility(View.GONE);
                    }
                    // zodra de gebruiker in het somscherm komt kan de
                    // de gebruiker de 'volgende' knop niet zien
                    // pas als de gebruiker op een antwoord tapt
                    // komt de 'volgende' knop in beeld
                    next.setVisibility(View.INVISIBLE);
                    optie1.setBackgroundResource(android.R.drawable.btn_default);
                    optie2.setBackgroundResource(android.R.drawable.btn_default);
                    optie3.setBackgroundResource(android.R.drawable.btn_default);
                    //blijf de methode aanroepe zolang currentNumber geen 10 is vanwege 10 sommen
                    calcualteSom((int) (Math.random() * 10));
                    if (currentNumber == 11) {
                        // als de 10de som bereikt is start resultaat activity
                        startActivity(new Intent(SomScherm.this, Resultaat.class));
                    }
                }

            }

        });
    }

    //methode om som te berekenen
    public void calcualteSom(int number) {


        // loop tot de tafel van 10
        for (int i = 1; i <= 10; i++) {

            // bereken de som
            value = i * number;
            // bepaal random waardes om een random som te generen
            int random1 = (int) (Math.random() * i);
            int random2 = (int) (Math.random() * number);
            //geef random waardes aan de buttons 10 = max, 1 = min
            int max = 10;
            int min = 1;
            int randombutton1 = ((int) (Math.random() * (max - min))) + min;
            int randombutton2 = ((int) (Math.random() * (max - min))) + min;


            //random kan geen 0 zijn, dus tel er 1 bij op als er 0 uitkomt
            if(random1 == 0 || random2 == 0){
                random1 = random1 + 1;
                random2 = random2 + 1;
            }
            // voeg de som toe aan de TextView
            som.setText(random1 + " x " + random2);
            // bereken het antwoord
            antwoord = random1 * random2;
            // zet de antwoorden in een array
            int[] opties = {antwoord, randombutton1, randombutton2};
            // haal een random waard euit de array
            int rnd = new Random().nextInt(opties.length);
            // hieronde om 3 checks te generen zodat het antwoord per button verschilt en voeg de waardes toe aan de buttons
            if (rnd == antwoord) {
                // zorg hiermee voor unieke waardes
                if(randombutton1 == randombutton2 || randombutton1 == antwoord || randombutton2 == antwoord){
                    //counters aangepas om waardes beter te laten verschillen
                    randombutton1 += 3;
                    randombutton2 += 4;
                }
                optie1.setText(""+rnd);
                optie2.setText(""+randombutton1);
                optie3.setText(""+randombutton2);
                // check om antwoord vaker op ander plek te genereren
            }else if(rnd == randombutton1 || rnd == randombutton2) {
                // zorg hiermee voor unieke waardes
                if(randombutton1 == randombutton2 || randombutton1 == antwoord || randombutton2 == antwoord){
                    randombutton1 += 2;
                    randombutton2 += 3;
                }
                optie1.setText(""+randombutton1);
                optie2.setText(""+antwoord);
                optie3.setText(""+randombutton2);
            }else {
                // zorg hiermee voor unieke waardes
                if(randombutton1 == randombutton2 || randombutton1 == antwoord || randombutton2 == antwoord){
                    randombutton1 += 6;
                    randombutton2 += 7;
                }
                optie1.setText(""+randombutton2);
                optie2.setText(""+randombutton1);
                optie3.setText(""+antwoord);
            }

            // click listener voor de buttons zodat iemand er op klikt
            optie1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // check het goede antwoord
                    if(Integer.parseInt((String) optie1.getText()) == antwoord){
                        // in dat geval wordt de button groen en wordt er 1 bij aantalgoed opgeteld
                        optie1.setBackgroundColor(Color.GREEN);
                        next.setVisibility(View.VISIBLE);
                        aantalgoed++;
                    }else{
                        //als het antwoord niet goed is maak button rood en voeg 1 toe bij aantalfout
                        optie1.setBackgroundColor(Color.RED);
                        next.setVisibility(View.VISIBLE);
                        aantalfout++;
                    }
                    // disable de overige buttons en maak de next button visible
                    optie2.setEnabled(false);
                    optie3.setEnabled(false);
                }
            });
            optie2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt((String) optie2.getText()) == antwoord) {
                        optie2.setBackgroundColor(Color.GREEN);
                        next.setVisibility(View.VISIBLE);
                        aantalgoed++;
                    }else{
                        optie2.setBackgroundColor(Color.RED);
                        next.setVisibility(View.VISIBLE);
                        aantalfout++;
                    }
                    optie1.setEnabled(false);
                    optie3.setEnabled(false);
                }
            });
            optie3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt((String) optie3.getText()) == antwoord){
                        optie3.setBackgroundColor(Color.GREEN);
                        next.setVisibility(View.VISIBLE);
                        aantalgoed++;
                    }else{
                        optie3.setBackgroundColor(Color.RED);
                        next.setVisibility(View.VISIBLE);
                        aantalfout++;
                    }
                    optie1.setEnabled(false);
                    optie2.setEnabled(false);
                }
            });

        }

    }

}
