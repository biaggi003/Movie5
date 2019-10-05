package com.b3.movie4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.b3.movie4.database.Helper;
import com.b3.movie4.model.FavoriteMovie;
import com.b3.movie4.model.Movie;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class DetailMovie extends AppCompatActivity {
    public static String EXTRA_MOVIE = "extra_movie";
    public static String EXTRA_FAVORITE_MOVIE = "extra_favorite_movie";
    TextView title, release, language, overview, voteAverage;
    ImageView photo;
    Movie movie;
    FavoriteMovie favoriteMovie;
    FloatingActionButton floatingActionButton;
    Helper helper;
    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        helper = new Helper(this);
        helper.open();

        photo = findViewById(R.id.img_mv);
        title = findViewById(R.id.mv_detail_title);
        overview = findViewById(R.id.mv_detail_description);
        release = findViewById(R.id.mv_detail_release);
        language = findViewById(R.id.mv_detail_language);
        voteAverage = findViewById(R.id.mv_detail_voteAverage);
        floatingActionButton = findViewById(R.id.fab_favorite);

        if (getIntent().getParcelableExtra(EXTRA_MOVIE) != null) {
            getMovieDetail();
            favoriteChecker(movie.getId());
        } else {
            getFavoriteMovieDetail();
            favoriteChecker(favoriteMovie.getId());
        }
    }

    private void getMovieDetail() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(movie.getTitle());

        title.setText(Objects.requireNonNull(movie).getTitle());
        Glide.with(this)
                .load(movie.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(photo);
        overview.setText(movie.getOverview());
        release.setText(movie.getReleaseDate());
        language.setText(movie.getOriginalLanguage());
        voteAverage.setText(movie.getVoteAverage());

        floatingActionButton.setOnClickListener(view -> {
            if (isFavorite) {
                favoriteChecker(movie.getId());
                Toast.makeText(getApplicationContext(), R.string.already_on_favorite, Toast.LENGTH_SHORT).show();
            } else {
                helper.addMovieFavorite(movie);
                favoriteChecker(movie.getId());
                Toast.makeText(getApplicationContext(), R.string.add_to_favorite, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFavoriteMovieDetail() {
        favoriteMovie = getIntent().getParcelableExtra(EXTRA_FAVORITE_MOVIE);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(favoriteMovie.getTitle());

        title.setText(Objects.requireNonNull(favoriteMovie).getTitle());
        Glide.with(this)
                .load(favoriteMovie.getPosterPath())
                .into(photo);
        overview.setText(favoriteMovie.getOverview());
        release.setText(favoriteMovie.getRelease());
        language.setText(favoriteMovie.getLanguage());
        voteAverage.setText(favoriteMovie.getVoteAverage());

        floatingActionButton.setOnClickListener(view -> {
            if (isFavorite) {
                helper.deleteFavorite(favoriteMovie.getId());
                favoriteChecker(favoriteMovie.getId());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.success_remove_favorite), Toast.LENGTH_SHORT).show();
            } else {
                favoriteChecker(favoriteMovie.getId());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.already_remove_favorite), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void favoriteChecker(int i) {
        isFavorite = helper.favoriteCheck(i);
        if (isFavorite) {
            floatingActionButton.setImageResource(R.drawable.ic_favorite);
            isFavorite = true;
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_empty);
            isFavorite = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
