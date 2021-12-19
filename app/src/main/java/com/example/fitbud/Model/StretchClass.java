package com.example.fitbud.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class StretchClass implements Parcelable {

    private String author;
    private String name;
    private String textDescription;
    private String videoDescription;
    private String imageId;

    public StretchClass(String author, String name, String textDescription, String videoDescription, String imageId){
        this.author = author;
        this.name = name;
        this.textDescription = textDescription;
        this.videoDescription = videoDescription;
        this.imageId = imageId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public StretchClass(){}

    public static final Creator<StretchClass> CREATOR = new Creator<StretchClass>() {
        @Override
        public StretchClass createFromParcel(Parcel in) {
            return new StretchClass(in);
        }

        @Override()
        public StretchClass[] newArray(int size) {
            return new StretchClass[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }


    public StretchClass(Parcel parcel) {
        this.name = parcel.readString();
        this.author = parcel.readString();
        this.imageId = parcel.readString();
        this.textDescription = parcel.readString();
        this.videoDescription = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.author);
        parcel.writeString(this.imageId);
        parcel.writeString(this.textDescription);
        parcel.writeString(this.videoDescription);
    }
}
