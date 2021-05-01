package com.itb.tmbdmobileapp.Adapters;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itb.tmbdmobileapp.Modelos.TV;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.Support.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder> {

    private final OnItemClickListener itemClickListener;
    private final List<TV> TV;
    private final int layout;

    public interface  OnItemClickListener {
        void onItemClick(TV TV);
    }

    public TvAdapter(List<TV> TV, int layout, OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
        this.TV = TV;
        this.layout = layout;
    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new TvHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, int position) {
        holder.bind(TV.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return TV.size();
    }

    public static class TvHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView puntuationText, title;
        ProgressBar progressBar;

        public TvHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo);
            puntuationText = itemView.findViewById(R.id.progress_bar_num);
            title = itemView.findViewById(R.id.textViewTitle);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        @SuppressLint("SetTextI18n")
        public void bind(TV TV, final OnItemClickListener listener) {

            Picasso.get().load(Common.MOVIEDB_SMALL_POSTER_URL + TV.getPosterPath()).into(image);

            puntuationText.setText(TV.getVote_average()+"");
            title.setText(TV.getName());
            progressBar.setProgress((int) TV.getVote_average() * 10);

            itemView.setOnClickListener(v -> listener.onItemClick(TV));
        }
    }
}
