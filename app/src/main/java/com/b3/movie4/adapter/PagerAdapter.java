package com.b3.movie4.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentArrayList = new ArrayList<>();
    private final List<String> stringArrayList = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }

    public void AddFragment(Fragment fragment) {
        fragmentArrayList.add(fragment);
        stringArrayList.add("");
    }

}

