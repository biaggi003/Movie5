package com.b3.movie4;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.b3.movie4.adapter.FavoriteMovieAdapter;
import com.b3.movie4.adapter.FavoriteTVShowAdapter;
import com.b3.movie4.database.Helper;
import com.b3.movie4.model.FavoriteMovie;
import com.b3.movie4.model.FavoriteTVShow;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTVShowFragment extends Fragment {
    private FavoriteTVShowAdapter favoriteTVShowAdapter;
    private Helper helper;
    private ProgressBar progressBar;

    public FavoriteTVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container,false);

        helper = new Helper(getActivity());
        favoriteTVShowAdapter = new FavoriteTVShowAdapter(getActivity());

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerView = view.findViewById(R.id.list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteTVShowAdapter);
        recyclerView.setHasFixedSize(true);
        setData();
        return view;
    }

    private void setData() {
        helper.open();
        ArrayList<FavoriteTVShow> favoriteTVShowArrayList = helper.getFavoriteTVShow();
        helper.close();
        favoriteTVShowAdapter.setListTVShow(favoriteTVShowArrayList);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
}
