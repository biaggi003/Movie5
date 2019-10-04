package com.b3.movie4.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    static final String FILM_TABLE = "film_table";

    static final class FilmColumn implements BaseColumns {
        static final String ID = "id";
        static final String TITLE = "title";
        static final String OVERVIEW = "overview";
        static final String POSTER = "poster";
        static final String RELEASE = "release";
        static final String LANGUAGE = "language";
        static final String VOTE_AVERAGE = "vote_average";
        static final String FILM_TYPE = "film_type";
    }
}
