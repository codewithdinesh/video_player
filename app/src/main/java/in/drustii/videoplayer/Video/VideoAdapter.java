package in.drustii.videoplayer.Video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import in.drustii.videoplayer.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    Context context;
    List<VideoModel> videoList = Collections.emptyList();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title;
        ImageView videoThumbnail;
        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.VideoTitle);
            videoThumbnail = itemView.findViewById(R.id.videoThumbnail);
            view = itemView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_view, parent, false);
        VideoAdapter.ViewHolder viewHolder = new VideoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Title.setSelected(true);
        holder.Title.setText(videoList.get(position).getVideoName());
//        holder.videoThumbnail.setImageURI(videoList.get(position).getVideoThumbnail());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
