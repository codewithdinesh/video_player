package in.drustii.videoplayer;

import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.drustii.videoplayer.Folder.FolderAdapter;
import in.drustii.videoplayer.Folder.FolderModel;

// https://youtu.be/VyVUnxUF1kw?t=89

public class MainActivity extends AppCompatActivity {
    private Cursor videocursor;
    RecyclerView homeContainer;
    List<FolderModel> videoFolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeContainer = findViewById(R.id.homeContainer);
        videoFolder = new ArrayList<FolderModel>();
        getAllFolder();
    }

    public void getAllFolder() {

        // HashSet<String> videoItemHashSet = new HashSet<>();

        String[] projection = {MediaStore.Video.VideoColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME};

        Cursor cursor = this.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Video.Media.DATE_TAKEN + " DESC");

        int column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);

        ArrayList<String> Folders = new ArrayList<>();


        try {
            cursor.moveToFirst();

            do {
                String FolderName = cursor.getString(column_index_folder_name);
                if (!Folders.contains(FolderName)) {
                    FolderModel folderModel = new FolderModel();
                    folderModel.setFolderName(FolderName);
                    videoFolder.add(folderModel);
                    Folders.add(FolderName);

                }

            } while (cursor.moveToNext());

            Log.d("videosFolder1 ", videoFolder.toString());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FolderAdapter folderAdapter = new FolderAdapter(getApplicationContext(), videoFolder);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            homeContainer.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        } else {
            homeContainer.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5));
        }
        homeContainer.setAdapter(folderAdapter);
    }
}