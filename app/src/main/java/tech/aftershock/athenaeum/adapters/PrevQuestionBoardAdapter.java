package tech.aftershock.athenaeum.adapters;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.libs.Downloader;
import tech.aftershock.athenaeum.libs.RecyclerItemClickListener;
import tech.aftershock.athenaeum.libs.RecyclerItemTouchListener;
import tech.aftershock.athenaeum.libs.StaticConstant;
import tech.aftershock.athenaeum.models.PrevQuestionBoard;
import tech.aftershock.athenaeum.models.PreviousQuestionPaper;

public class PrevQuestionBoardAdapter extends RecyclerView.Adapter<PrevQuestionBoardAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, moreBtn, notAvailable;
        RecyclerView paperList;
        PrevQuestionBoardPaperAdapter adapter;

        WeakReference<RecyclerItemClickListener> weakListener;
        DownloadManager mDownloadManager;

        ViewHolder(@NonNull View itemView, RecyclerItemClickListener clickListener) {
            super(itemView);
            weakListener = new WeakReference<>(clickListener);
            mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

            title = itemView.findViewById(R.id.prev_question_board_title);
            moreBtn = itemView.findViewById(R.id.prev_question_board_more_btn);
            notAvailable = itemView.findViewById(R.id.prev_question_board_not_available);
            paperList = itemView.findViewById(R.id.prev_question_board_paper_list);

            adapter = new PrevQuestionBoardPaperAdapter(new ArrayList<PreviousQuestionPaper>());
            paperList.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
            paperList.setAdapter(adapter);

            paperList.addOnItemTouchListener(new RecyclerItemTouchListener(mContext, paperList, new RecyclerItemClickListener() {
                @Override
                public void onClick(View item, int position) {
                    PreviousQuestionPaper paper = mBoards.get(getAdapterPosition()).getPapers().get(position);
                    mDownloader.startDownload(StaticConstant.ContentType.PREV_QUESTION, paper.getTitle(), paper.getFile());
                }

                @Override
                public void onLongClick(View item, int position) { }
            }));

            paperList.setRecycledViewPool(new RecyclerView.RecycledViewPool());

            moreBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            weakListener.get().onClick(v, getAdapterPosition());
        }

    }

    private Context mContext;
    private List<PrevQuestionBoard> mBoards;
    private RecyclerItemClickListener mClickListener;
    private Downloader mDownloader;

    public PrevQuestionBoardAdapter(Context context, List<PrevQuestionBoard> boards, RecyclerItemClickListener clickListener) {
        mContext = context;
        mBoards = boards;
        mClickListener = clickListener;
        mDownloader = new Downloader((Activity) context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prev_question_board, parent, false);
        return new ViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PrevQuestionBoard board = mBoards.get(position);
        holder.title.setText(String.format(Locale.getDefault(), "Year %s", board.getBoardName()));
        holder.adapter.addPapers(board.getPapers());
    }

    @Override
    public int getItemCount() {
        return mBoards.size();
    }

    public void addBoards(List<PrevQuestionBoard> boards) {
        mBoards.addAll(boards);
        notifyDataSetChanged();
    }

    public PrevQuestionBoard getBoard(int position) {
        return mBoards.get(position);
    }
}