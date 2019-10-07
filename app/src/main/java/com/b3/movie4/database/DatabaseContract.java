package com.b3.movie4.database;

import android.database.Cursor;
import android.provider.BaseColumns;
import android.net.Uri;

public class DatabaseContract {
    static final String FILM_TABLE = "film_table";
    public static final String AUTHORITY = "com.b3.movie4";
    private static final String SCHEME2 = "content";
    private static final String SCHEME = "content";

    public static String TABLE_NOTE_MOVIE = "note1";
    public static String TABLE_NOTE_TVSHOW = "note2";

    public static final class FilmColumn implements BaseColumns {
        static final String ID = "id";
        static final String TITLE = "title";
        static final String OVERVIEW = "overview";
        public static final String POSTER = "poster";
        static final String RELEASE = "release";
        static final String LANGUAGE = "language";
        static final String VOTE_AVERAGE = "vote_average";
        static final String FILM_TYPE = "film_type";


        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NOTE_MOVIE)
                .build();
        public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme(SCHEME2)
                .authority(AUTHORITY)
                .appendPath(TABLE_NOTE_TVSHOW)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
