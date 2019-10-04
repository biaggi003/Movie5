package com.b3.movie4;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b3.movie4.adapter.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);

        TabLayout tabLayout = v.findViewById(R.id.tablayout_id);
        ViewPager viewPager = v.findViewById(R.id.viewpager_id);
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new FavoriteMovieFragment());
        adapter.AddFragment(new FavoriteTVShowFragment());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setText(R.string.movie);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText(R.string.tvshow);
        tabLayout.setElevation(4);

        return v;
    }
}
