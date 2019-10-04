package com.b3.movie4.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TVShow implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("original_language")
    private String language;
    @SerializedName("overview")
    private String overview;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPoster() {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }
    public String getFirstAirDate() {
        return firstAirDate;
    }
    public String getVoteAverage() {
        return voteAverage;
    }
    public String getLanguage() {
        return language;
    }
    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(posterPath);
        parcel.writeString(firstAirDate);
        parcel.writeString(voteAverage);
        parcel.writeString(language);
        parcel.writeString(overview);
    }

    private TVShow(Parcel in) {
        id = in.readInt();
        name = in.readString();
        posterPath = in.readString();
        firstAirDate = in.readString();
        voteAverage = in.readString();
        language = in.readString();
        overview = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
