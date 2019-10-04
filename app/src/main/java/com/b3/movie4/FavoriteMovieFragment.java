package com.b3.movie4;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.b3.movie4.adapter.FavoriteMovieAdapter;
import com.b3.movie4.database.Helper;
import com.b3.movie4.model.FavoriteMovie;

import java.util.ArrayList;

public class FavoriteMovieFragment extends Fragment {
    private FavoriteMovieAdapter favoriteMovieAdapter;
    private Helper helper;
    private ProgressBar progressBar;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.list_fragment, container,false);

    helper = new Helper(getActivity());
    favoriteMovieAdapter = new FavoriteMovieAdapter(getActivity());

    progressBar = view.findViewById(R.id.progressBar);
    progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerView = view.findViewById(R.id.list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteMovieAdapter);
        recyclerView.setHasFixedSize(true);
        setData();
        return view;
    }

    private void setData() {
        helper.open();
        ArrayList<FavoriteMovie> favoriteMovieArrayList = helper.getFavoriteMovie();
        helper.close();
        favoriteMovieAdapter.setListMovie(favoriteMovieArrayList);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
}
