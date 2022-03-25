package com.example.projek_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button playpause,stop;
    Button next,prev;
    TextView title;
    MediaPlayer mp;
    int selectedmusic = 0;
    boolean play = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playpause = (Button) findViewById(R.id.idplay);
        stop = (Button) findViewById(R.id.idstop);
        next = (Button) findViewById(R.id.idnext);
        prev = (Button) findViewById(R.id.idprev);
        title = findViewById(R.id.idtitle);

        ArrayList<MediaPlayer> songs = new ArrayList<>();
        songs.add(MediaPlayer.create(this,R.raw.charlie_puth_light_switch_official_music_video));
        songs.add(MediaPlayer.create(this,R.raw.little_mix_touch));
        mp = songs.get(selectedmusic);

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(play == true){
                    mp.start();
                    playpause.setText("Pause");
                    play = false;
                }
                else
                {
                    mp.pause();
                    playpause.setText("Play");
                    play = true;
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mp.stop();
                    play = true;
                    mp.prepare();
                    mp.seekTo(0);
                    playpause.setText("Play");
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    play = false;
                    mp.stop();
                    mp.prepare();
                    setSelectedmusic(songs);
                    mp.start();
                    play = true;
                    playpause.setText("Pause");
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    play = false;
                    mp.stop();
                    mp.prepare();
                    setSelectedmusic(songs);
                    mp.start();
                    play = true;
                    playpause.setText("Pause");
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
    void setSelectedmusic(ArrayList<MediaPlayer> songs) {
        switch (selectedmusic) {
            case 0:
            mp = songs.get(1);
            selectedmusic = 1;
            title.setText("Litte Mix - Touch");
            break;
            case 1:
                mp = songs.get(0);
                selectedmusic = 0;
                title.setText("Charlie Puth - Light Switch");
                break;
        }
    }
}