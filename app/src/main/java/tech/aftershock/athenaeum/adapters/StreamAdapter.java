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
import tech.aftershock.athenaeum.models.Stream;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mBanner;
        TextView mTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.stream_banner);
            mTitle = itemView.findViewById(R.id.stream_title);
        }
    }

    private List<Stream> mStreams;

    public StreamAdapter(List<Stream> streams) {
        mStreams = streams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stream_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stream stream = mStreams.get(position);
        Picasso.get()
                .load(stream.getBannerId())
                .error(R.drawable.bba_banner)
                .into(holder.mBanner);

        holder.mTitle.setText(stream.getTitle());
    }

    @Override
    public int getItemCount() {
        return mStreams.size();
    }
}
