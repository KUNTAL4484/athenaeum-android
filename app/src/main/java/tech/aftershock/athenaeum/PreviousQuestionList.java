package tech.aftershock.athenaeum;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.aftershock.athenaeum.adapters.PreviousQuestionPaperListAdapter;
import tech.aftershock.athenaeum.libs.Downloader;
import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.libs.NetworkOperations;
import tech.aftershock.athenaeum.libs.RecyclerItemClickListener;
import tech.aftershock.athenaeum.libs.RecyclerItemTouchListener;
import tech.aftershock.athenaeum.libs.StaticConstant;
import tech.aftershock.athenaeum.libs.Stdlib;
import tech.aftershock.athenaeum.models.PreviousQuestionPaper;

public class PreviousQuestionList extends AppCompatActivity {

    private View mRoot;
    private Toolbar mToolbar;
    private AppCompatSpinner mSpinner;
    private RecyclerView mQuestionList;

    private NetworkOperations mOperations;
    private PreviousQuestionPaperListAdapter mAdapter;
    private Downloader mDownloader;

    private Call<List<PreviousQuestionPaper>> mGetQuestionPaperListCall;

    private int mStream, mYear;
    private String[] mStreamItems = {"All Semester", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8"};
    private List<PreviousQuestionPaper> mPapers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_question_list);

        Intent intent = getIntent();
        if(intent != null) {
            mStream = intent.getIntExtra("stream", 0);
            mYear = intent.getIntExtra("year", 2010);
        }
        else {
            mStream = 0;
            mYear = 2010;
        }

        initialize();

        setupStreamTitle();

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        mOperations = NetworkClient.getOperations(this);

        mPapers = new ArrayList<PreviousQuestionPaper>();

        mDownloader = new Downloader(this);

        mAdapter = new PreviousQuestionPaperListAdapter(new ArrayList<PreviousQuestionPaper>());
        mQuestionList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mQuestionList.setAdapter(mAdapter);
        mQuestionList.addOnItemTouchListener(new RecyclerItemTouchListener(this, mQuestionList, new RecyclerItemClickListener() {
            @Override
            public void onClick(View item, int position) {
                PreviousQuestionPaper paper = mAdapter.getPaper(position);
                mDownloader.startDownload(StaticConstant.ContentType.PREV_QUESTION, paper.getTitle(), paper.getFile());
            }

            @Override
            public void onLongClick(View item, int position) { }
        }));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mStreamItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.changeSemester(mPapers, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        loadData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        onBackPressed();
        return true;
    }

    private void initialize() {
        mRoot = findViewById(R.id.previous_question_list_root);
        mToolbar = findViewById(R.id.previous_question_list_toolbar);
        mSpinner = findViewById(R.id.previous_question_list_semester_spinner);
        mQuestionList = findViewById(R.id.previous_question_list_recycler);
    }

    private void setupStreamTitle() {
        String title;

        switch (mStream) {
            case 0: title = "B.Tech " + mYear; break;
            case 1: title = "BHM / MHA " + mYear; break;
            case 2: title = "BMS / MMS " + mYear; break;
            case 3: title = "MBA / BBA " + mYear; break;
            case 4: title = "BCA / MCA " + mYear; break;
            default: title = "Previous Questions";
        }

        mToolbar.setTitle(title);
    }

    private void loadData() {
        mGetQuestionPaperListCall = mOperations.getPreviousYearQuestionPaperList(mStream, mYear);
        mGetQuestionPaperListCall.enqueue(new Callback<List<PreviousQuestionPaper>>() {
            @Override
            public void onResponse(Call<List<PreviousQuestionPaper>> call, Response<List<PreviousQuestionPaper>> response) {
                if(response.isSuccessful()) {
                    List<PreviousQuestionPaper> papers = response.body();
                    if(papers != null) {
                        mAdapter.addPapers(papers);
                        mPapers = papers;
                    }
                    else
                        Stdlib.showErrorSnack(mRoot, "Something went wrong");
                }
                else
                    Stdlib.showErrorSnack(mRoot, "Something went wrong");
            }

            @Override
            public void onFailure(Call<List<PreviousQuestionPaper>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
