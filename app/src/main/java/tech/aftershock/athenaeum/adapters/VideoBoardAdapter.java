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

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.models.Video;
import tech.aftershock.athenaeum.models.VideoBoard;

public class VideoBoardAdapter extends RecyclerView.Adapter<VideoBoardAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, moreBtn;
        RecyclerView videoList;

        VideoBoardVideoListAdapter adapter;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_video_board_title);
            moreBtn = itemView.findViewById(R.id.item_video_board_more_btn);
            videoList = itemView.findViewById(R.id.item_video_board_video_list);

            adapter = new VideoBoardVideoListAdapter(new ArrayList<Video>());
            videoList.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
            videoList.setAdapter(adapter);
            videoList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        }
    }

    private Context mContext;
    private List<VideoBoard> mBoards;

    public VideoBoardAdapter(Context context, List<VideoBoard> boards) {
        mContext = context;
        mBoards = boards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoBoard board = mBoards.get(position);

        holder.title.setText(board.getBoardName());

        holder.adapter.addVideos(board.getVideos());
    }

    @Override
    public int getItemCount() {
        return mBoards.size();
    }

    public void addBoards(List<VideoBoard> boards) {
        mBoards.addAll(boards);
        notifyDataSetChanged();
    }
}
