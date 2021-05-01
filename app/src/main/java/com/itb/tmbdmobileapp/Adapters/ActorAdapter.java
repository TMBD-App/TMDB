package com.itb.tmbdmobileapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.itb.tmbdmobileapp.Modelos.People;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.Support.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorTestHolder> {

    private final OnItemClickListener itemClickListener;
    private final List<People> actors;
    private final int layout;

    public interface  OnItemClickListener {
        void onItemClick(People actors);
    }

    public ActorAdapter(List<People> actors, int layout, OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
        this.actors = actors;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ActorAdapter.ActorTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ActorAdapter.ActorTestHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.ActorTestHolder holder, int position) {
        holder.bind(actors.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public static class ActorTestHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView actorJob, title;

        public ActorTestHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo);
            actorJob = itemView.findViewById(R.id.textViewActorType);
            title = itemView.findViewById(R.id.textViewTitle);
        }

        @SuppressLint("SetTextI18n")
        public void bind(People actors, final OnItemClickListener listener) {
            Picasso.get().load(Common.MOVIEDB_SMALL_POSTER_URL + actors.getProfile_path()).into(image);
            title.setText(actors.getName());
            actorJob.setText(actors.getKnown_for_department());

            itemView.setOnClickListener(v -> listener.onItemClick(actors));
        }
    }
}
