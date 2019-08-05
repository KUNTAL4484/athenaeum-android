package tech.aftershock.athenaeum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.models.PreviousQuestionPaper;

public class PreviousQuestionPaperListAdapter extends RecyclerView.Adapter<PreviousQuestionPaperListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, semester;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_previous_question_paper_full_title);
            semester = itemView.findViewById(R.id.item_previous_question_paper_full_semester);
        }
    }

    private List<PreviousQuestionPaper> mPapers;
    private int mSemester;
    private int mSize;

    public PreviousQuestionPaperListAdapter(List<PreviousQuestionPaper> papers) {
        mPapers = papers;
        mSemester = 0;
        mSize = mPapers.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_previous_question_paper_full, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PreviousQuestionPaper paper = mPapers.get(position);

        if(mSemester != 0) {
            if (Integer.parseInt(paper.getSemester()) == mSemester) {
                holder.title.setText(paper.getTitle());
                holder.semester.setText(paper.getSemester());
            }
        }
        else {
            holder.title.setText(paper.getTitle());
            holder.semester.setText(paper.getSemester());
        }
    }

    @Override
    public int getItemCount() {
        return mPapers.size();
    }

    public void addPapers(List<PreviousQuestionPaper> papers) {
        mPapers.addAll(papers);
        notifyDataSetChanged();
    }

    public PreviousQuestionPaper getPaper(int position) {
        return mPapers.get(position);
    }

    public void changeSemester(List<PreviousQuestionPaper> papers, int semester) {
        mSemester = semester;
        mPapers.clear();
        if(mSemester != 0) {
            for (PreviousQuestionPaper paper : papers) {
                if (Integer.parseInt(paper.getSemester()) == semester) {
                    mPapers.add(paper);
                }
            }
        }
        else
            mPapers.addAll(papers);

        notifyDataSetChanged();
    }
}
