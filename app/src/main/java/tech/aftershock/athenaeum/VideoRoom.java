package tech.aftershock.athenaeum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import tech.aftershock.athenaeum.libs.NetworkClient;

public class VideoRoom extends AppCompatActivity {

    private VideoView mPlayer;

    private String mVideoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_room);

        Intent intent = getIntent();
        if(intent != null) {
            mVideoFile = intent.getStringExtra("file");
        }

        mPlayer = findViewById(R.id.video_room_player);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mPlayer);

        mPlayer.setVideoURI(Uri.parse(NetworkClient.getVideoBase() + mVideoFile));
        mPlayer.setMediaController(mediaController);

        mPlayer.start();
    }
}
