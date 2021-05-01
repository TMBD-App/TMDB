package com.itb.tmbdmobileapp.Modelos;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Evert Moreno on 22-01-2018.
 */
public class MoviesResponse implements Parcelable {
    @SerializedName("results")
    private Movie[] results;

    @SerializedName("total_pages")
    private int totalPages;

    public MoviesResponse(Movie[] results, int totalPages) {
        this.results = results;
        this.totalPages = totalPages;
    }

    protected MoviesResponse(Parcel in) {
        results = in.createTypedArray(Movie.CREATOR);
        totalPages = in.readInt();
    }

    public static final Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {
        @Override
        public MoviesResponse createFromParcel(Parcel in) {
            return new MoviesResponse(in);
        }

        @Override
        public MoviesResponse[] newArray(int size) {
            return new MoviesResponse[size];
        }
    };

    public Movie[] getResults() {
        return results;
    }

    public void setResults(Movie[] results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(results, flags);
        dest.writeInt(totalPages);
    }
}