package com.example.mhts.hp.MainActivities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.compiler.GlideAnnotationProcessor;
import com.bumptech.glide.request.RequestOptions;
import com.example.mhts.hp.MainActivities.AnimeActivity;
import com.example.mhts.hp.MainActivities.Model.Anime;
import com.example.mhts.hp.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mcontext;
    private List<Anime> mData;

    public RecyclerViewAdapter(Context mcontext, List<Anime> mData){
        this.mcontext = mcontext;
        this.mData = mData;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int i) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from(mcontext);
        view = inflater.inflate(R.layout.anime_row_items,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,AnimeActivity.class);
                intent.putExtra("CrimeType",mData.get(viewHolder.getAdapterPosition()).getCrimeType());
                intent.putExtra("CrimeSection",mData.get(viewHolder.getAdapterPosition()).getCrimeSection());
                intent.putExtra("PlateNumber",mData.get(viewHolder.getAdapterPosition()).getPlateNumber());
                intent.putExtra("Code",mData.get(viewHolder.getAdapterPosition()).getCode());
                intent.putExtra("Region",mData.get(viewHolder.getAdapterPosition()).getRegion());
                intent.putExtra("Priority",mData.get(viewHolder.getAdapterPosition()).getPriority());
            mcontext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.plateNumberr.setText(mData.get(position).getPlateNumber());
        holder.codde.setText(mData.get(position).getCode());
        holder.Regiion.setText(mData.get(position).getRegion());
        holder.CrimeTtype.setText(mData.get(position).getCrimeType());
        holder.CrimeSecttion.setText(mData.get(position).getCrimeSection());
        holder.priority.setText(mData.get(position).getPriority());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView plateNumberr;
        TextView codde;
        TextView Regiion;
        TextView CrimeTtype;
        TextView CrimeSecttion;
        LinearLayout container;
        TextView priority;
        ImageView ImageUrl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            plateNumberr = itemView.findViewById(R.id.platenumber67);
            codde = itemView.findViewById(R.id.codej);
            Regiion = itemView.findViewById(R.id.Region12);
            CrimeTtype = itemView.findViewById(R.id.CrimeTyper);
            CrimeSecttion = itemView.findViewById(R.id.crimeSectionasd);
            container = itemView.findViewById(R.id.container121);
            priority = itemView.findViewById(R.id.priority12);
            ImageUrl = itemView.findViewById(R.id.image_url1);
        }
    }
}
