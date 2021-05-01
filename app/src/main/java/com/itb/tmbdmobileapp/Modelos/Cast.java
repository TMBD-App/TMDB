package com.itb.tmbdmobileapp.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cast implements Parcelable {
    @SerializedName("cast")
    private People[] cast;

    public Cast(People[] cast) {
        this.cast = cast;
    }

    protected Cast(Parcel in) {
        cast = in.createTypedArray(People.CREATOR);
    }

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };

    public People[] getCast() {
        return cast;
    }

    public void setCast(People[] cast) {
        this.cast = cast;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(cast, flags);
    }
}
