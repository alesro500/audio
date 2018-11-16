package com.example.alfonso.audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class practicaAudio extends AppCompatActivity {

    Button play, stop, pause;
    TextView texto;
    MediaPlayer musica,video;
    MediaController control;
    VideoView vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica_audio);

        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        pause = (Button) findViewById(R.id.pause);
        texto = (TextView) findViewById(R.id.texto);
        // musica = MediaPlayer.create(this,R.raw.can);
        musica = new MediaPlayer();
        musica.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            musica.setDataSource(getApplicationContext(), Uri.parse("http://www.tannerhelland.com/dmusic/AMemoryAway.ogg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
            musica.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        video = MediaPlayer.create(this,R.raw.celda);
        vid = findViewById(R.id.vid);
        control = new MediaController(this);

        vid.setMediaController(control);
        Uri my = Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.celda);
        vid.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.celda));

        vid.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                control.show();
                vid.start();
                mp.setLooping(true);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musica.isPlaying())
                {
                    texto.setText("SONANDO!!! :D");
                }
                else
                {
                    musica.start();
                    texto.setText("ESCUCHANDO :D");
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musica.isPlaying())
                {
                    musica.pause();
                    texto.setText("PAUSADO :X");
                }
                else
                {
                    texto.setText("NO ESTÁ SONANDO NADA :D");
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musica.isPlaying())
                {
                    musica.stop();
                    try
                    {
                        musica.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    texto.setText("PARADA :X");
                }
                else
                {
                    texto.setText("NO ESTÁ SONANDO NADA :x");
                }
            }
        });
    }
}
