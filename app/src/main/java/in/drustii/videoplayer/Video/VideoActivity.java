package in.drustii.videoplayer.Video;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import in.drustii.videoplayer.R;

public class VideoActivity extends AppCompatActivity {
    String videoPath, videoTitle;
    VideoView videoController;
    Intent FolderActivity;
    LinearLayout videoContainer;
    LinearLayout videoLayoutContainer;
    //    SeekBar volumeSeekbar;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoController = findViewById(R.id.videoController);
        videoContainer = findViewById(R.id.videoContainer);
        videoLayoutContainer = findViewById(R.id.videoLayoutContainer);
//        volumeSeekbar = findViewById(R.id.volumeSeekbar);

        FolderActivity = getIntent();
        videoPath = FolderActivity.getStringExtra("videoPath");
        videoTitle = FolderActivity.getStringExtra("videoTitle");


        try {
            videoController.setVideoPath(videoPath);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoLayoutContainer);
            videoController.start();
            videoController.setMediaController(mediaController);

            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


//            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
//
//            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
//
//            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int newVolume, boolean b) {
//
//                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0);
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//                }
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//                }
//            });

        } catch (Exception e) {
            Log.d("error", "Something err");
            e.printStackTrace();
        }


    }
}