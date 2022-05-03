package in.drustii.videoplayer.Folder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import in.drustii.videoplayer.R;

public class FolderActivity extends AppCompatActivity {
    Intent FolderIntent;
    String Folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        FolderIntent = getIntent();
        Folder = FolderIntent.getStringExtra("FolderName");

    }

    public void getVideosFromFolder() {

    }
}