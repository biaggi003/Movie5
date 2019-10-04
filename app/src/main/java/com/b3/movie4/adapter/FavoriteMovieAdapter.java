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

import com.b3.movie4.DetailMovie;
import com.b3.movie4.R;
import com.b3.movie4.model.FavoriteMovie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.b3.movie4.DetailMovie.EXTRA_FAVORITE_MOVIE;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.MovieViewHolder> {
    private ArrayList<FavoriteMovie> favoriteMovieArrayList = new ArrayList<>();
    private Context context;

    public FavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    public void setListMovie(ArrayList<FavoriteMovie> movie) {
        favoriteMovieArrayList.clear();
        favoriteMovieArrayList.addAll(movie);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        final FavoriteMovie favoriteMovie = favoriteMovieArrayList.get(i);
        movieViewHolder.title.setText(favoriteMovie.getTitle());
        movieViewHolder.overview.setText(favoriteMovie.getOverview());
        movieViewHolder.rating.setText(favoriteMovie.getVoteAverage());
        movieViewHolder.release.setText(favoriteMovie.getRelease());
        Glide.with(context)
                .load(favoriteMovie.getPosterPath())
                .placeholder(R.color.colorPrimaryDark)
                .into(movieViewHolder.photo);
        movieViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailMovie.class);
            intent.putExtra(EXTRA_FAVORITE_MOVIE, favoriteMovie);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteMovieArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView title, overview, rating, release;
        private ImageView photo;

        MovieViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.item_title);
            overview = v.findViewById(R.id.item_desc);
            photo = v.findViewById(R.id.item_photo);
            rating = v.findViewById(R.id.item_rating);
            release = v.findViewById(R.id.item_release);
        }
    }
}
