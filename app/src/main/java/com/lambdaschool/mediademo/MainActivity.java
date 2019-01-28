package com.lambdaschool.mediademo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button      playButton;
    private MediaPlayer mediaPlayer;
    private SeekBar     seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.play_button);
        playButton.setEnabled(false);

        seekBar = findViewById(R.id.media_seekbar);

//        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.the_trail);

        /*final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.setDataSource("http://soundbible.com/grab.php?id=2213&type=mp3");
                    mediaPlayer.prepare();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            playButton.setEnabled(true);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    playButton.setText("Play");
                } else {
                    mediaPlayer.start();
                    playButton.setText("Stop");
                }
            }
        });*/

        /*mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
//            mediaPlayer.setDataSource("http://soundbible.com/grab.php?id=2213&type=mp3");
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.the_trail));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
//                seekBar.setProgress(percent);
            }
        });

        mediaPlayer.prepareAsync();

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                playButton.setEnabled(true);
                seekBar.setMax(mp.getDuration());
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    playButton.setText("Play");
                } else {
                    mediaPlayer.start();
                    playButton.setText("Stop");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (mediaPlayer.isPlaying()) {
                                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                try {
                                    Thread.sleep(mediaPlayer.getDuration() / 1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
            }
        });*/

        final VideoView videoView = findViewById(R.id.video_view);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.time_lapsevideoofightky));
//        videoView.setVideoURI(Uri.parse("https://www.videezy.com/download/20675?download_auth_hash=fd21d181&pro=false"));

        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                playButton.setEnabled(true);
                seekBar.setMax(mp.getDuration());
            }
        });

       playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.stopPlayback();
                    playButton.setText("Play");
                } else {
                    videoView.start();
                    playButton.setText("Stop");
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();

    }
}
