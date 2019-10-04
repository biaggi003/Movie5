package com.b3.movie4;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.b3.movie4.adapter.TVShowAdapter;
import com.b3.movie4.model.TVShow;
import com.b3.movie4.model.TVShowResponse;
import com.b3.movie4.model.TVShowViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {
    private ArrayList<TVShow> tvShowArrayList = new ArrayList<>();
    private TVShowAdapter tvShowAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        recyclerView = view.findViewById(R.id.list_recyclerview);
        progressBar = view.findViewById(R.id.progressBar);

        TVShowViewModel tvShowViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TVShowViewModel.class);
        tvShowViewModel.init();
        tvShowViewModel.getTVRepo().observe(this, TVShowResponse -> {
            List<TVShow> tvShow = TVShowResponse.getResults();
            tvShowArrayList.addAll(tvShow);
            tvShowAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        });
        setRecyclerView();
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void setRecyclerView() {
        if (tvShowAdapter == null) {
            progressBar.setVisibility(View.VISIBLE);
            tvShowAdapter = new TVShowAdapter(getContext(), tvShowArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(tvShowAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            tvShowAdapter.notifyDataSetChanged();
        }
    }
}
