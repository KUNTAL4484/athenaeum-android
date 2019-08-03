package tech.aftershock.athenaeum.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.models.PrevQuestionBoard;
import tech.aftershock.athenaeum.models.PreviousQuestionPaper;

public class PrevQuestionBoardAdapter extends RecyclerView.Adapter<PrevQuestionBoardAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, moreBtn, notAvailable;
        RecyclerView paperList;
        PrevQuestionBoardPaperAdapter adapter;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.prev_question_board_title);
            moreBtn = itemView.findViewById(R.id.prev_question_board_more_btn);
            notAvailable = itemView.findViewById(R.id.prev_question_board_not_available);
            paperList = itemView.findViewById(R.id.prev_question_board_paper_list);

            adapter = new PrevQuestionBoardPaperAdapter(new ArrayList<PreviousQuestionPaper>());
            paperList.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
            paperList.setAdapter(adapter);

            paperList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        }
    }

    private Context mContext;
    private List<PrevQuestionBoard> mBoards;

    public PrevQuestionBoardAdapter(Context context, List<PrevQuestionBoard> boards) {
        mContext = context;
        mBoards = boards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prev_question_board, parent, false);
        return new ViewHolder(view);
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
}