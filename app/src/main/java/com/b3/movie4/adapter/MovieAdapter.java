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
import com.b3.movie4.model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.b3.movie4.DetailMovie.EXTRA_MOVIE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movie> movieList;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder (MovieViewHolder holder, int i) {
        holder.title.setText(movieList.get(i).getTitle());
        holder.description.setText(movieList.get(i).getOverview());
        holder.rating.setText(movieList.get(i).getVoteAverage());
        holder.release.setText(movieList.get(i).getReleaseDate());
        Glide.with(context)
                .load(movieList.get(i).getPosterPath())
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, rating, release;
        ImageView photo;

        MovieViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.item_title);
            description = view.findViewById(R.id.item_desc);
            rating = view.findViewById(R.id.item_rating);
            release = view.findViewById(R.id.item_release);
            photo = view.findViewById(R.id.item_photo);

            view.setOnClickListener(v -> {
                int q = getAdapterPosition();
                Movie movie = movieList.get(q);
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra(EXTRA_MOVIE, movie);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        }
    }
}