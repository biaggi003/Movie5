package com.b3.movie4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.b3.movie4.database.Helper;
import com.b3.movie4.model.FavoriteTVShow;
import com.b3.movie4.model.TVShow;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class DetailTVShow extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    public static final String EXTRA_FAVORITE_TVSHOW = "extra_favorite_tvshow";
    TextView title, overview, release, language, voteAverage;
    ImageView photo;
    TVShow tvShow;
    FavoriteTVShow favoriteTVShow;
    FloatingActionButton floatingActionButton;
    Helper helper;
    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);

        helper = new Helper(this);
        helper.open();

        photo = findViewById(R.id.img_tv);
        title = findViewById(R.id.tv_detail_title);
        overview = findViewById(R.id.tv_detail_overview);
        release = findViewById(R.id.tv_detail_firstRelease);
        language = findViewById(R.id.tv_detail_language);
        voteAverage = findViewById(R.id.tv_detail_userscore);
        floatingActionButton = findViewById(R.id.fab_favorite);

        if (getIntent().getParcelableExtra(EXTRA_TVSHOW) != null) {
            getTVShowDetail();
            favoriteCheck(tvShow.getId());
        } else {
            getFavoriteTVShowDetail();
            favoriteCheck(favoriteTVShow.getId());
        }
    }

    private void getTVShowDetail() {
        tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(R.string.tvshow);
        actionBar.setSubtitle(tvShow.getName());

        title.setText(Objects.requireNonNull(tvShow).getName());
        Glide.with(this)
                .load(tvShow.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(photo);
        overview.setText(tvShow.getOverview());
        release.setText(tvShow.getFirstAirDate());
        language.setText(tvShow.getLanguage());
        voteAverage.setText(tvShow.getVoteAverage());
        floatingActionButton.setOnClickListener(v -> {
            if (isFavorite) {
                favoriteCheck(tvShow.getId());
                Toast.makeText(getApplicationContext(), R.string.already_on_favorite, Toast.LENGTH_SHORT).show();
            } else {
                helper.addTVShowFavorite(tvShow);
                favoriteCheck(tvShow.getId());
                Toast.makeText(getApplicationContext(), R.string.add_to_favorite, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFavoriteTVShowDetail() {
        favoriteTVShow = getIntent().getParcelableExtra(EXTRA_FAVORITE_TVSHOW);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(R.string.favorite_tvshow);
        actionBar.setSubtitle(favoriteTVShow.getTitle());

        title.setText(Objects.requireNonNull(favoriteTVShow).getTitle());
        Glide.with(this)
                .load(favoriteTVShow.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(photo);
        overview.setText(favoriteTVShow.getOverview());
        release.setText(favoriteTVShow.getFirstAirDate());
        language.setText(favoriteTVShow.getLanguage());
        voteAverage.setText(favoriteTVShow.getVoteAverage());

        floatingActionButton.setOnClickListener(v -> {
            if (isFavorite) {
                helper.deleteFavorite(favoriteTVShow.getId());
                favoriteCheck(favoriteTVShow.getId());
                Toast.makeText(getApplicationContext(), R.string.success_remove_favorite, Toast.LENGTH_SHORT).show();
            } else {
                favoriteCheck(favoriteTVShow.getId());
                Toast.makeText(getApplicationContext(), R.string.already_remove_favorite, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void favoriteCheck(int id) {
        isFavorite = helper.favoriteCheck(id);
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