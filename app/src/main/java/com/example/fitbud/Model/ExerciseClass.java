package com.example.fitbud.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.String;

import java.lang.reflect.Array;

public class ExerciseClass implements Parcelable{
    private int mData;
    String imageId;
    String name;
    String author;
    String difficulty;
    Boolean hasReps;
    Boolean hasTimer;
    Boolean isDeletable;
    Integer reps;
    Integer sets;
    String textDescription;
    String videoDescription;

    public ExerciseClass(String name,
                         String author,
                         String difficulty,
                         Boolean hasReps,
                         Boolean isDeletable,
                         String imageId,
                         Integer reps,
                         Integer sets,
                         String textDescription,
                         String videoDescription){

        this.name = name;
        this.author = author;
        this.difficulty = difficulty;
        this.hasReps = hasReps;
        this.isDeletable = isDeletable;
        this.imageId = imageId;
        this.reps = reps;
        this.sets = sets;
        this.textDescription = textDescription;
        this.videoDescription = videoDescription;
    }

    public ExerciseClass(){}

    public static final Creator<ExerciseClass> CREATOR = new Creator<ExerciseClass>() {
        @Override
        public ExerciseClass createFromParcel(Parcel in) {
            return new ExerciseClass(in);
        }

        @Override
        public ExerciseClass[] newArray(int size) {
            return new ExerciseClass[size];
        }
    };

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getHasReps() {
        return hasReps;
    }

    public void setHasReps(Boolean hasReps) {
        this.hasReps = hasReps;
    }

    public Boolean getHasTimer() {
        return hasTimer;
    }

    public void setHasTimer(Boolean hasTimer) {
        this.hasTimer = hasTimer;
    }

    public Boolean getDeletable() {
        return isDeletable;
    }

    public void setDeletable(Boolean deletable) {
        isDeletable = deletable;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
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

    public String getImageId(){ return  imageId;}
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public ExerciseClass(Parcel parcel) {
        this.name = parcel.readString();
        this.author = parcel.readString();
        this.difficulty = parcel.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.hasReps = parcel.readBoolean();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.isDeletable = parcel.readBoolean();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.hasTimer = parcel.readBoolean();
        }
        this.imageId = parcel.readString();
        this.reps = parcel.readInt();
        this.sets = parcel.readInt();
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
        parcel.writeString(this.difficulty);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.hasReps);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.hasTimer);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.isDeletable);
        }
        parcel.writeString(this.imageId);
        parcel.writeInt(this.sets);
        parcel.writeInt(this.reps);
        parcel.writeString(this.textDescription);
        parcel.writeString(this.videoDescription);
    }
}
