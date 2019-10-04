package com.b3.movie4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.b3.movie4.database.DatabaseContract.FILM_TABLE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.FILM_TYPE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.ID;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.LANGUAGE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.OVERVIEW;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.POSTER;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.RELEASE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.TITLE;
import static com.b3.movie4.database.DatabaseContract.FilmColumn.VOTE_AVERAGE;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbMovie";
    private static int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            FILM_TABLE,
            ID,
            TITLE,
            OVERVIEW,
            POSTER,
            RELEASE,
            LANGUAGE,
            VOTE_AVERAGE,
            FILM_TYPE
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int n) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FILM_TABLE);
        onCreate(sqLiteDatabase);
    }
}
