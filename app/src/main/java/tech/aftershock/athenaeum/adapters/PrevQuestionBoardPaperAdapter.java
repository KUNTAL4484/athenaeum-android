package tech.aftershock.athenaeum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.models.PreviousQuestionPaper;

public class PrevQuestionBoardPaperAdapter extends RecyclerView.Adapter<PrevQuestionBoardPaperAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.prev_question_cover);
            title = itemView.findViewById(R.id.prev_question_title);
        }
    }

    private List<PreviousQuestionPaper> mPapers;

    public PrevQuestionBoardPaperAdapter(List<PreviousQuestionPaper> papers) {
        mPapers = papers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prev_question_paper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PreviousQuestionPaper paper = mPapers.get(position);

        Picasso.get()
                .load(R.drawable.pdf_icon)
                .into(holder.cover);

        holder.title.setText(paper.getTitle());
    }

    @Override
    public int getItemCount() {
        return mPapers.size();
    }

    public void addPapers(List<PreviousQuestionPaper> papers) {
        mPapers.clear();
        mPapers.addAll(papers);
        notifyDataSetChanged();
    }
}
