package com.b3.movie4;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.b3.movie4.adapter.MovieAdapter;
import com.b3.movie4.model.Movie;
import com.b3.movie4.model.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieFragment extends Fragment {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        recyclerView = view.findViewById(R.id.list_recyclerview);
        progressBar = view.findViewById(R.id.progressBar);

        MovieViewModel movieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MovieViewModel.class);
        movieViewModel.init();
        movieViewModel.getMovieRepo().observe(this, movieResponse -> {
            List<Movie> movies = movieResponse.getResults();
            movieList.addAll(movies);
            movieAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        });
        setRecyclerView();

        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void setRecyclerView() {
        if (movieAdapter == null) {
            progressBar.setVisibility(View.VISIBLE);
            movieAdapter = new MovieAdapter(getContext(), movieList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            movieAdapter.notifyDataSetChanged();
        }
    }
}

