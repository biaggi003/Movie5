package com.b3.movie4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b3.movie4.DetailTVShow;
import com.b3.movie4.R;
import com.b3.movie4.model.TVShow;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.b3.movie4.DetailTVShow.EXTRA_TVSHOW;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    private Context context;
    private ArrayList<TVShow> tvShowArrayList;

    public TVShowAdapter(Context context, ArrayList<TVShow> tvShowArrayList) {
        this.context = context;
        this.tvShowArrayList = tvShowArrayList;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TVShowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TVShowViewHolder holder, int i) {
        TVShow tvShow = tvShowArrayList.get(i);
        holder.title.setText(tvShow.getName());
        holder.description.setText(tvShow.getOverview());
        holder.rating.setText(tvShow.getVoteAverage());
        holder.release.setText(tvShow.getFirstAirDate());
        Glide.with(context)
                .load(tvShow.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(holder.photo);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailTVShow.class);
            intent.putExtra(EXTRA_TVSHOW, tvShow);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvShowArrayList.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, rating, release;
        ImageView photo;

        TVShowViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.item_title);
            description = v.findViewById(R.id.item_desc);
            rating = v.findViewById(R.id.item_rating);
            release = v.findViewById(R.id.item_release);
            photo = v.findViewById(R.id.item_photo);

        }
    }
}
