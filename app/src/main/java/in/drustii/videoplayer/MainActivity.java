package in.drustii.videoplayer;

import android.Manifest;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import in.drustii.videoplayer.Folder.FolderAdapter;
import in.drustii.videoplayer.Folder.FolderModel;

// https://youtu.be/VyVUnxUF1kw?t=89

public class MainActivity extends AppCompatActivity {
    private Cursor videocursor;
    RecyclerView homeContainer;
    List<FolderModel> videoFolder;
    TextView showError;
    CardView errorContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeContainer = findViewById(R.id.homeContainer);
        videoFolder = new ArrayList<FolderModel>();

        showError = findViewById(R.id.msg);
        errorContainer = findViewById(R.id.Error_Container);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Dexter.withContext(getApplicationContext())
                .withPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        showError.setVisibility(View.GONE);
                        errorContainer.setVisibility(View.GONE);
                        videoFolder.clear();
                        getAllFolder();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        showError.setText("Please Give Storage Permission to play videos");
                        showError.setVisibility(View.VISIBLE);
                        errorContainer.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


    }

    public void getAllFolder() {

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

//            Log.d("videosFolder1 ", videoFolder.toString());

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