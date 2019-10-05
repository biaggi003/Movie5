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
import com.b3.movie4.model.FavoriteTVShow;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.b3.movie4.DetailTVShow.EXTRA_FAVORITE_TVSHOW;

public class FavoriteTVShowAdapter extends RecyclerView.Adapter<FavoriteTVShowAdapter.TVShowViewHolder> {
    private ArrayList<FavoriteTVShow> favoriteTVShowArrayList = new ArrayList<>();
    private Context context;

    public FavoriteTVShowAdapter(Context context) {
        this.context = context;
    }

    public void setListTVShow(ArrayList<FavoriteTVShow> tvShow) {
        favoriteTVShowArrayList.clear();
        favoriteTVShowArrayList.addAll(tvShow);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TVShowViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int i) {
        final FavoriteTVShow tvShow = favoriteTVShowArrayList.get(i);
        holder.title.setText(tvShow.getTitle());
        holder.overview.setText(tvShow.getOverview());
        holder.rating.setText(tvShow.getVoteAverage());
        holder.release.setText(tvShow.getFirstAirDate());
        Glide.with(context)
                .load(tvShow.getPoster())
                .into(holder.poster);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailTVShow.class);
            intent.putExtra(EXTRA_FAVORITE_TVSHOW, tvShow);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteTVShowArrayList.size();
    }
    class TVShowViewHolder extends RecyclerView.ViewHolder {
        private TextView title, overview, rating ,release;
        private ImageView poster;

        TVShowViewHolder(@NonNull View view) {
            super(view);
            title = itemView.findViewById(R.id.item_title);
            overview = itemView.findViewById(R.id.item_desc);
            poster = itemView.findViewById(R.id.item_photo);
            rating = view.findViewById(R.id.item_rating);
            release = view.findViewById(R.id.item_release);
        }
    }
}

