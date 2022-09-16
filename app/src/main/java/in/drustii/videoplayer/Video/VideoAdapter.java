package in.drustii.videoplayer.Video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Collections;
import java.util.List;

import in.drustii.videoplayer.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    Context context;
    List<VideoModel> videoList = Collections.emptyList();


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title;
        ImageView videoThumbnail;
        LinearLayout videoBtn;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.VideoTitle);
            videoThumbnail = itemView.findViewById(R.id.videoThumbnail);
            videoBtn = itemView.findViewById(R.id.videoBtn);
            view = itemView;
        }
    }

    public VideoAdapter(Context c, List<VideoModel> videoList) {
        this.context = c;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Title.setSelected(true);
        holder.Title.setText(videoList.get(position).getVideoName());
        File file = new File(videoList.get(position).getVideoPath());
        Glide.with(context).asBitmap().load(Uri.fromFile(file)).into(holder.videoThumbnail);


        holder.videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent i = new Intent(context.getApplicationContext(), VideoActivity.class);
                i.putExtra("videoPath", videoList.get(position).getVideoPath());
                i.putExtra("videoTitle", videoList.get(position).getVideoName());
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
