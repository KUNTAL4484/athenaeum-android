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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.aftershock.athenaeum.adapters.PrevQuestionBoardAdapter;
import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.libs.NetworkOperations;
import tech.aftershock.athenaeum.libs.RecyclerItemClickListener;
import tech.aftershock.athenaeum.libs.Stdlib;
import tech.aftershock.athenaeum.models.GetPrevQuestionBoardResponse;
import tech.aftershock.athenaeum.models.PrevQuestionBoard;

public class PreviousQuestionBoard extends AppCompatActivity {

    private View mRoot;
    private Toolbar mToolbar;
    private RecyclerView mBoardList;

    private PrevQuestionBoardAdapter mBoardAdapter;
    private NetworkOperations mOperations;

    private Call<GetPrevQuestionBoardResponse> mGetPrevQuestionBoardResponseCall = null;

    private int mStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_question_board);

        mStream = getIntent().getIntExtra("stream", 0);

        initializeView();

        setupStreamTitle();

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        mBoardAdapter = new PrevQuestionBoardAdapter(this, new ArrayList<PrevQuestionBoard>(), new RecyclerItemClickListener() {
            @Override
            public void onClick(View item, int position) {
                Intent intent = new Intent(PreviousQuestionBoard.this, PreviousQuestionList.class);
                intent.putExtra("stream", mStream);
                intent.putExtra("year", mBoardAdapter.getBoard(position).getBoardYear());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View item, int position) { }
        });
        mBoardList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBoardList.setAdapter(mBoardAdapter);

        mOperations = NetworkClient.getOperations(this);

        loadData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        onBackPressed();
        return true;
    }

    private void initializeView() {
        mRoot = findViewById(R.id.previous_question_board_root);
        mToolbar = findViewById(R.id.prev_question_board_toolbar);

        mBoardList = findViewById(R.id.prev_question_board_list);
    }

    private void setupStreamTitle() {
        String title;

        switch (mStream) {
            case 0: title = "B.Tech"; break;
            case 1: title = "BHM / MHA"; break;
            case 2: title = "BMS / MMS"; break;
            case 3: title = "MBA / BBA"; break;
            case 4: title = "BCA / MCA"; break;
            default: title = "Previous Questions";
        }

        mToolbar.setTitle(title);
    }

    private void loadData() {
        mGetPrevQuestionBoardResponseCall = mOperations.getPreviousYearQuestionBoard(mStream);
        mGetPrevQuestionBoardResponseCall.enqueue(new Callback<GetPrevQuestionBoardResponse>() {
            @Override
            public void onResponse(Call<GetPrevQuestionBoardResponse> call, Response<GetPrevQuestionBoardResponse> response) {
                if(response.isSuccessful()) {
                    GetPrevQuestionBoardResponse body = response.body();
                    if(body != null && body.getSuccess()) {
                        mBoardAdapter.addBoards(body.getBoards());
                    }
                    else
                        Stdlib.showErrorSnack(mRoot, "Something went wrong");
                }
                else
                    Stdlib.showErrorSnack(mRoot, "Something went wrong");
            }

            @Override
            public void onFailure(Call<GetPrevQuestionBoardResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
