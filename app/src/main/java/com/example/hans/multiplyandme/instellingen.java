package com.example.hans.multiplyandme;


import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class instellingen extends AppCompatActivity {

    Switch soundswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instellingen);

        soundswitch = (Switch) findViewById(R.id.soundSwitch);



        soundswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //state of switch
                Boolean switchState = soundswitch.isChecked();
                AudioManager volumeControl = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                if(!switchState) {
                    volumeControl.setStreamMute(AudioManager.STREAM_MUSIC, true);
                }else{
                    volumeControl.setStreamMute(AudioManager.STREAM_MUSIC, false);

                }
            }
        });


    }
}
