package in.drustii.videoplayer.Folder;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.drustii.videoplayer.R;
import in.drustii.videoplayer.Video.VideoAdapter;
import in.drustii.videoplayer.Video.VideoModel;

public class FolderActivity extends AppCompatActivity {
    Intent FolderIntent;
    String Folder;
    List<VideoModel> videoModelList;
    RecyclerView videoContainer;
    TextView FolderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        FolderIntent = getIntent();
        Folder = FolderIntent.getStringExtra("FolderName");
        FolderName = findViewById(R.id.FolderName);
        videoModelList = new ArrayList<VideoModel>();
        videoContainer = findViewById(R.id.videoContainer);
        getVideosFromFolder();
        FolderName.setText(Folder + " (" + videoModelList.size() + ") ");
    }

    public void getVideosFromFolder() {

        int video_column_index;
        int video_thumbnail_index;
        int video_title_index;

        try {
            String[] projection = {MediaStore.Video.Media._ID, MediaStore.Video.Media.DISPLAY_NAME};

            String selection = MediaStore.Video.Media.DATA + " like?";
            String[] selectionArgs = new String[]{"%" + Folder + "%"};

            Cursor cursor = this.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, MediaStore.Video.Media.DATE_TAKEN + " DESC");

            video_column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATA);

            video_title_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);

            while (cursor.moveToNext()) {
                String path = cursor.getString(video_column_index);
                String title = cursor.getString(video_title_index);

                Log.d("title ", title);

                VideoModel v = new VideoModel();
                v.setVideoPath(path);
                v.setVideoName(title);
                videoModelList.add(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        VideoAdapter videoAdapter = new VideoAdapter(getApplicationContext(), videoModelList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            videoContainer.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        } else {
            videoContainer.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        }

        videoContainer.setAdapter(videoAdapter);


    }
}