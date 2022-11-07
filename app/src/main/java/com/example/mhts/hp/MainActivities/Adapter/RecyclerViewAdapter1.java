package com.example.mhts.hp.MainActivities.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mhts.hp.MainActivities.Model.Anime;
import com.example.mhts.hp.MainActivities.Model.Anime2;
import com.example.mhts.hp.R;

import java.util.List;

public class RecyclerViewAdapter1 extends RecyclerView.Adapter<RecyclerViewAdapter1.MyViewHolder>{
    private Context mcontext;
    private List<Anime2> mData;

    public RecyclerViewAdapter1(Context mcontext, List<Anime2> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        view = inflater.inflate(R.layout.anim_row_items2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.Announcement_title.setText(mData.get(i).getAnnouncement_title());
        myViewHolder.Announcement_details.setText(mData.get(i).getAnnouncement_details());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Announcement_title;
        TextView Announcement_details;
        LinearLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Announcement_title = itemView.findViewById(R.id.Announcement_title);
            Announcement_details = itemView.findViewById(R.id.Anouncement_details);
            container = itemView.findViewById(R.id.container123);
        }
    }
}
