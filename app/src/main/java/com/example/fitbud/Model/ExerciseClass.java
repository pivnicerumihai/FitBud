package com.example.fitbud.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.String;

import java.util.ArrayList;

public class ExerciseClass implements Parcelable{
    private int mData;
    private String imageId;
    private String name;
    private String author;
    private String difficulty;
    private String category;
    private Boolean hasReps;
    private Boolean hasTimer;
    private Boolean isDeletable;
    private Integer reps;
    private Integer sets;
    private String textDescription;
    private String videoDescription;
    private ArrayList<ExerciseRecordsClass> records;

    public ExerciseClass(String name,
                         String category,
                         String author,
                         String difficulty,
                         Boolean hasReps,
                         Boolean isDeletable,
                         String imageId,
                         Integer reps,
                         Integer sets,
                         String textDescription,
                         String videoDescription,
                          ArrayList<ExerciseRecordsClass> records){

        this.name = name;
        this.category = category;
        this.author = author;
        this.difficulty = difficulty;
        this.hasReps = hasReps;
        this.isDeletable = isDeletable;
        this.imageId = imageId;
        this.reps = reps;
        this.sets = sets;
        this.textDescription = textDescription;
        this.videoDescription = videoDescription;
        this.records = records;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ExerciseClass(){}

    public static final Creator<ExerciseClass> CREATOR = new Creator<ExerciseClass>() {
        @Override
        public ExerciseClass createFromParcel(Parcel in) {
            return new ExerciseClass(in);
        }

        @Override()
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

    public int getmData() {
        return mData;
    }

    public void setmData(int mData) {
        this.mData = mData;
    }

    public ArrayList<ExerciseRecordsClass> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<ExerciseRecordsClass> records) {
        this.records = records;
    }

    public static Creator<ExerciseClass> getCREATOR() {
        return CREATOR;
    }

    public ExerciseClass(Parcel parcel) {
        this.name = parcel.readString();
        this.author = parcel.readString();
        this.difficulty = parcel.readString();
        this.category = parcel.readString();
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
        this.records = parcel.readArrayList(ExerciseRecordsClass.class.getClassLoader());
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
        parcel.writeString(this.category);
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
        parcel.writeList(this.records);
    }
}
