package tech.aftershock.athenaeum.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.fragments.Videos;
import tech.aftershock.athenaeum.libs.RecyclerItemClickListener;
import tech.aftershock.athenaeum.models.Subject;
import tech.aftershock.athenaeum.models.VideoBoard;

public class VideoBoardAdapter extends RecyclerView.Adapter<VideoBoardAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, moreBtn;
        RecyclerView videoList;

        WeakReference<RecyclerItemClickListener> moreClickListener;

        ViewHolder(@NonNull View itemView, RecyclerItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.item_video_board_title);
            moreBtn = itemView.findViewById(R.id.item_video_board_more_btn);
            videoList = itemView.findViewById(R.id.item_video_board_video_list);

            moreClickListener = new WeakReference<>(listener);

            moreBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            moreClickListener.get().onClick(v, getAdapterPosition());
        }
    }

    private Activity mContext;
    private List<VideoBoard> mBoards;
    private Videos.VideoItemClickListener mVideoItemClickListener;
    private RecyclerItemClickListener mMoreClickListener;

    public VideoBoardAdapter(Activity context, List<VideoBoard> boards, Videos.VideoItemClickListener listener, RecyclerItemClickListener moreClickListener) {
        mContext = context;
        mBoards = boards;
        mVideoItemClickListener = listener;
        mMoreClickListener = moreClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_board, parent, false);
        return new ViewHolder(view, mMoreClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        VideoBoard board = mBoards.get(position);

        holder.title.setText(board.getBoardName());

        VideoBoardVideoListAdapter adapter = new VideoBoardVideoListAdapter(board.getVideos(), mVideoItemClickListener);
        holder.videoList.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        holder.videoList.setHasFixedSize(true);
        holder.videoList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        holder.videoList.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mBoards.size();
    }

    public void addBoards(List<VideoBoard> boards) {
        mBoards.addAll(boards);
        notifyDataSetChanged();
    }

    public Subject getSubject(int position) {
        VideoBoard board = mBoards.get(position);
        Subject subject = new Subject(String.valueOf(board.getBoardId()), board.getBoardName());
        return subject;
    }
}
