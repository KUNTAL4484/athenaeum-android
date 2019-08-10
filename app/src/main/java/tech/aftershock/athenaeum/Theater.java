package tech.aftershock.athenaeum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Locale;

import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.models.Video;

public class Theater extends AppCompatActivity {

    private View mBannerContainer;
    private ImageView mBanner;
    private TextView mTitle, mViews, mSubject, mDescription;
    private Button mAddToColBtn;

    private Video mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater);

        final Intent intent = getIntent();
        if(intent != null) {
            mVideo = intent.getParcelableExtra("video");
        }

        initialize();

        DecimalFormat formatter = new DecimalFormat("#,###");

        Picasso.get()
                .load(NetworkClient.getVideoThumbBase() + mVideo.getThumbnail())
                .placeholder(R.drawable.ic_baseline_play_circle_outline_24px)
                .error(R.drawable.ic_baseline_play_circle_outline_24px)
                .into(mBanner);

        mTitle.setText(mVideo.getTitle());
        mViews.setText(String.format(Locale.getDefault(), "%s views", formatter.format(mVideo.getViews())));
        mSubject.setText(mVideo.getSubject().getName());
        mDescription.setText(mVideo.getDescription());

        mBannerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoIntent = new Intent(Theater.this, VideoRoom.class);
                videoIntent.putExtra("file", mVideo.getFile());
                startActivity(videoIntent);
            }
        });
    }

    private void initialize() {
        mBannerContainer = findViewById(R.id.theater_banner_container);
        mBanner = findViewById(R.id.theater_banner);
        mTitle = findViewById(R.id.theater_video_title);
        mViews = findViewById(R.id.theater_video_views);
        mSubject = findViewById(R.id.theater_video_subject);
        mDescription = findViewById(R.id.theater_video_description);
        mAddToColBtn = findViewById(R.id.theater_add_to_col_btn);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mDescription.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }
}
