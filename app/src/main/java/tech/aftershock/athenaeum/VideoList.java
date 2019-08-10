package tech.aftershock.athenaeum;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.aftershock.athenaeum.adapters.VideoListAdapter;
import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.libs.NetworkOperations;
import tech.aftershock.athenaeum.libs.RecyclerItemClickListener;
import tech.aftershock.athenaeum.libs.RecyclerItemTouchListener;
import tech.aftershock.athenaeum.libs.Stdlib;
import tech.aftershock.athenaeum.models.Subject;
import tech.aftershock.athenaeum.models.Video;

public class VideoList extends AppCompatActivity {

    private View mRoot;
    private Toolbar mToolbar;
    private RecyclerView mContentList;

    private NetworkOperations mOperations;
    private VideoListAdapter mAdapter;

    private Call<List<Video>> mGetVideoCall = null;

    private Subject mSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        Intent intent = getIntent();
        if(intent != null) {
            mSubject = intent.getParcelableExtra("subject");
        }

        mRoot = findViewById(R.id.video_list_root);
        mToolbar = findViewById(R.id.video_list_toolbar);
        mContentList = findViewById(R.id.video_list_content_list);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        mToolbar.setTitle(mSubject.getName());

        mAdapter = new VideoListAdapter(new ArrayList<Video>());
        mContentList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mContentList.setAdapter(mAdapter);
        mContentList.addOnItemTouchListener(new RecyclerItemTouchListener(VideoList.this, mContentList, new RecyclerItemClickListener() {
            @Override
            public void onClick(View item, int position) {
                Intent theaterIntent = new Intent(VideoList.this, Theater.class);
                theaterIntent.putExtra("video", mAdapter.getVideo(position));
                startActivity(theaterIntent);
            }

            @Override
            public void onLongClick(View item, int position) { }
        }));

        mOperations = NetworkClient.getOperations(this);

        loadData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        onBackPressed();
        return true;
    }

    private void loadData() {
        mGetVideoCall = mOperations.getSubjectVideos(mSubject.getId());
        mGetVideoCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if(response.isSuccessful()) {
                    List<Video> videos = response.body();
                    if(videos != null)
                        mAdapter.addVideos(videos);
                    else
                        Stdlib.showErrorSnack(mRoot, "Something went wrong");

                }
                else
                    Stdlib.showErrorSnack(mRoot, "Something went wrong");
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                if(!call.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }


}
