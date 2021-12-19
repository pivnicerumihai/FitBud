package com.example.fitbud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitbud.Model.StretchClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StretchesAdapter extends RecyclerView.Adapter<StretchesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<StretchClass> stretchClassArrayList;

    public StretchesAdapter(Context context, ArrayList<StretchClass> stretchClassArrayList) {
        this.context = context;
        this.stretchClassArrayList = stretchClassArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_exercises, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StretchClass stretchClass = stretchClassArrayList.get(position);
        holder.listName.setText(stretchClass.getName());
        Picasso.get().load(stretchClass.getImageId()).into(holder.listImage);
        holder.listDifficulty.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return stretchClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button listName;
        private ImageView listImage;
        private LinearLayoutCompat listDifficulty;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            listName = itemView.findViewById(R.id.list_name);
            listImage = itemView.findViewById(R.id.list_image);
            listDifficulty = itemView.findViewById(R.id.layout_difficulty);
        }
    }
}
