package com.b3.movie4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.b3.movie4.model.Movie;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = menuItem -> {
        Fragment fragment;
        ActionBar actionBar = getSupportActionBar();
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(R.string.app_name);
        switch (menuItem.getItemId()) {
            case R.id.navigation_movie:
                fragment = new MovieFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                if (actionBar != null) {
                    actionBar.setElevation(4);
                }
                return true;
            case R.id.navigation_tvshow:
                fragment = new TVShowFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                    if (actionBar != null) {
                        actionBar.setElevation(4);
                    }
                    return true;
            case R.id.navigation_favorite:
                fragment = new FavoriteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                if (actionBar != null) {
                    actionBar.setElevation(0);
                }
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocale();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.language_settings) {
            showLanguageSettingsDialog();
        } else if (item.getItemId() == R.id.search_bar) {
            showSearchDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLanguageSettingsDialog() {
        final String[] langItems = {"English", "Indonesia"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.select_language)
                .setSingleChoiceItems(langItems, -1, (dialogInterface, i) -> {
                    if (i == 0) {
                        setLocale("en");
                        recreate();
                    }
                    else if (i == 1) {
                        setLocale("id");
                        recreate();
                    }
                    dialogInterface.dismiss();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("App_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("App_Lang","");
        setLocale(language);
    }
    private void showSearchDialog() {
        final SearchView searchView = new SearchView(MainActivity.this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Observer<ArrayList<Movie>> getSearch = new Observer<ArrayList<Movie>> {
                @Override
                public void onChanged(ArrayList<Movie> movies) {
                    if (movies != null) {
                        searchAdapter.setMovies(movies);
                    }
                }
            };
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
}
