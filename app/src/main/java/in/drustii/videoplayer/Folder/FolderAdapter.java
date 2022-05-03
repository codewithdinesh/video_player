package in.drustii.videoplayer.Folder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import in.drustii.videoplayer.R;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    List<FolderModel> Folders = Collections.emptyList();
    Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView FolderName;
        LinearLayout folderBtn;
        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FolderName = itemView.findViewById(R.id.FolderName);
            folderBtn = itemView.findViewById(R.id.folderBtn);
            view = itemView;

        }
    }

    public FolderAdapter(Context c, List<FolderModel> folders) {
        this.context = c;
        this.Folders = folders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.folder_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.FolderName.setText(Folders.get(position).getFolderName());
        holder.FolderName.setSelected(true);

        holder.folderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent openFolder = new Intent(context.getApplicationContext(), FolderActivity.class);
                openFolder.putExtra("FolderName", Folders.get(position).getFolderName());
                activity.startActivity(openFolder);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Folders.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
