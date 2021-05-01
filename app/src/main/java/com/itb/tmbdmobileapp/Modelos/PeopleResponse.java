package com.itb.tmbdmobileapp.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PeopleResponse implements Parcelable {

    @SerializedName("results")
    private People[] results;
    @SerializedName("total_pages")
    private int totalPages;

    protected PeopleResponse(Parcel in) {
        totalPages = in.readInt();
    }

    public PeopleResponse(People[] results, int totalPages) {
        this.results = results;
        this.totalPages = totalPages;
    }

    public People[] getResults() {
        return results;
    }

    public void setResults(People[] results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public static final Creator<PeopleResponse> CREATOR = new Creator<PeopleResponse>() {
        @Override
        public PeopleResponse createFromParcel(Parcel in) {
            return new PeopleResponse(in);
        }

        @Override
        public PeopleResponse[] newArray(int size) {
            return new PeopleResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalPages);
    }
}
