package com.b3.movie4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.b3.movie4.model.FavoriteMovie;
import com.b3.movie4.model.FavoriteTVShow;
import com.b3.movie4.model.Movie;
import com.b3.movie4.model.TVShow;

import java.util.ArrayList;

import static com.b3.movie4.database.DatabaseContract.FILM_TABLE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.FILM_TYPE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.ID;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.LANGUAGE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.OVERVIEW;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.POSTER;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.RELEASE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.TITLE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.VOTE_AVERAGE;

public class Helper {
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;

    public Helper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public ArrayList<FavoriteMovie> getFavoriteMovie() {
        ArrayList<FavoriteMovie> favoriteMovieArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(FILM_TABLE, null,
                "FILM_TYPE='0'",
                null,
                null,
                null,
                ID + " DESC",
                null);
        cursor.moveToFirst();
        FavoriteMovie favoriteMovie;
        if (cursor.getCount() > 0) {
            do {
                favoriteMovie = new FavoriteMovie();
                favoriteMovie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                favoriteMovie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                favoriteMovie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                favoriteMovie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                favoriteMovie.setRelease(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                favoriteMovie.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                favoriteMovie.setVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));

                favoriteMovieArrayList.add(favoriteMovie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return favoriteMovieArrayList;
    }

    public ArrayList<FavoriteTVShow> getFavoriteTVShow() {
        ArrayList<FavoriteTVShow> favoriteTVShowArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(FILM_TABLE, null,
                "FILM_TYPE='1'",
                null,
                null,
                null,
                ID + " DESC",
                null);
        cursor.moveToFirst();
        FavoriteTVShow favoriteTVShow;
        if (cursor.getCount() > 0) {
            do {
                favoriteTVShow = new FavoriteTVShow();
                favoriteTVShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                favoriteTVShow.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                favoriteTVShow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                favoriteTVShow.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                favoriteTVShow.setFirstAirDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                favoriteTVShow.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                favoriteTVShow.setVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));

                favoriteTVShowArrayList.add(favoriteTVShow);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return favoriteTVShowArrayList;
    }

    public void addMovieFavorite(Movie movie) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ID, movie.getId());
        args.put(TITLE, movie.getTitle());
        args.put(OVERVIEW, movie.getOverview());
        args.put(POSTER, movie.getPosterPath());
        args.put(RELEASE, movie.getReleaseDate());
        args.put(LANGUAGE, movie.getOriginalLanguage());
        args.put(VOTE_AVERAGE, movie.getVoteAverage());
        args.put(FILM_TYPE, "0");
        sqLiteDatabase.insert(FILM_TABLE, null, args);
    }

    public void addTVShowFavorite(TVShow tv) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ID, tv.getId());
        args.put(TITLE, tv.getName());
        args.put(OVERVIEW, tv.getOverview());
        args.put(POSTER, tv.getPoster());
        args.put(RELEASE, tv.getFirstAirDate());
        args.put(LANGUAGE, tv.getLanguage());
        args.put(VOTE_AVERAGE, tv.getVoteAverage());
        args.put(FILM_TYPE, "1");
        sqLiteDatabase.insert(FILM_TABLE, null, args);
    }

    public void deleteFavorite(int id) {
        sqLiteDatabase.delete(FILM_TABLE, ID + " = '" + id + "'", null);
    }

    public boolean favoriteCheck(int id) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM film_table WHERE id = " + id + "", null);
        cursor.moveToFirst();
        if (cursor.getCount() <= 0) {
            return false;
        }
        cursor.close();
        return true;
    }
}