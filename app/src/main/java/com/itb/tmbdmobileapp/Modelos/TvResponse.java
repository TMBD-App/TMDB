package com.itb.tmbdmobileapp.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TvResponse implements Parcelable {
    @SerializedName("results")
    private TV[] results;

    @SerializedName("total_pages")
    private int totalPages;

    public TvResponse(TV[] results, int totalPages) {
        this.results = results;
        this.totalPages = totalPages;
    }

    public TV[] getResults() {
        return results;
    }

    public void setResults(TV[] results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    protected TvResponse(Parcel in) {
        results = in.createTypedArray(TV.CREATOR);
        totalPages = in.readInt();
    }

    public static final Creator<TvResponse> CREATOR = new Creator<TvResponse>() {
        @Override
        public TvResponse createFromParcel(Parcel in) {
            return new TvResponse(in);
        }

        @Override
        public TvResponse[] newArray(int size) {
            return new TvResponse[size];
        }
    };

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
