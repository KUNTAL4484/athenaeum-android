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
import java.util.Locale;

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.models.Video;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title, views, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.video_full_thumbnail);
            title = itemView.findViewById(R.id.video_full_title);
            views = itemView.findViewById(R.id.video_full_views);
            time = itemView.findViewById(R.id.video_full_time);
        }
    }

    private List<Video> mVideos;

    public VideoListAdapter(List<Video> videos) {
        mVideos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_full_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = mVideos.get(position);

        Picasso.get()
                .load(NetworkClient.getVideoThumbBase() + video.getThumbnail())
                .placeholder(R.drawable.ic_baseline_play_circle_outline_24px)
                .error(R.drawable.ic_baseline_play_circle_outline_24px)
                .into(holder.thumbnail);

        holder.title.setText(video.getTitle());
        holder.views.setText(String.format(Locale.getDefault(), "%s views", video.getViews()));
        holder.time.setText(getBeautifiedTime(video.getTime()));
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public void addVideos(List<Video> videos) {
        mVideos.addAll(videos);
        notifyDataSetChanged();
    }

    public Video getVideo(int position) {
        return mVideos.get(position);
    }

    private String getBeautifiedTime(int second) {
        int hours = second / 3600;
        int minutes = (second % 3600) / 60;
        int seconds = second % 60;

        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }
}
