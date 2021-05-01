package com.itb.tmbdmobileapp.Modelos;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class People implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("known_for_department")
    private String known_for_department;
    @SerializedName("name")
    private String name;
    @SerializedName("profile_path")
    private String profile_path;

    public People(int id, String known_for_department, String name, String profile_path) {
        this.id = id;
        this.known_for_department = known_for_department;
        this.name = name;
        this.profile_path = profile_path;
    }

    protected People(Parcel in) {
        id = in.readInt();
        known_for_department = in.readString();
        name = in.readString();
        profile_path = in.readString();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(known_for_department);
        dest.writeString(name);
        dest.writeString(profile_path);
    }
}
