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
import tech.aftershock.athenaeum.fragments.Videos;
import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.models.Video;

public class VideoBoardVideoListAdapter extends RecyclerView.Adapter<VideoBoardVideoListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View container;
        ImageView thumb;
        TextView title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.item_video_container);
            thumb = itemView.findViewById(R.id.item_video_thumb);
            title = itemView.findViewById(R.id.item_video_title);

            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mVideoItemClickListener.onItemClick(mVideos.get(getAdapterPosition()));
        }
    }

    private List<Video> mVideos;
    private Videos.VideoItemClickListener mVideoItemClickListener;

    public VideoBoardVideoListAdapter(List<Video> videos, Videos.VideoItemClickListener listener) {
        mVideos = videos;
        mVideoItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = mVideos.get(position);
        holder.title.setText(video.getTitle());

        Picasso.get()
                .load(NetworkClient.getVideoThumbBase() + video.getThumbnail())
                .placeholder(R.drawable.ic_baseline_play_circle_outline_24px)
                .error(R.drawable.ic_baseline_play_circle_outline_24px)
                .fit()
                .into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public void addVideos(List<Video> videos) {
        mVideos.clear();
        mVideos.addAll(videos);
        notifyDataSetChanged();
    }
}
