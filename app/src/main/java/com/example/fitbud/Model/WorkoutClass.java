package com.example.fitbud.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Timer;

public class WorkoutClass implements Parcelable {

    private String name;
    private Integer reviews;
    Boolean isSetBased;
    Boolean isTimeBased;
    Boolean isDeletable;
    Integer setsPerExercise;
    ArrayList<ExerciseClass> exercises;
    Integer averageWorkoutTime;
    Integer restLength;
    String author;
    String difficulty;
    ArrayList<StretchClass> stretchesList;
    ArrayList<String> musclesWorked;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public static Creator<WorkoutClass> getCREATOR() {
        return CREATOR;
    }

    public WorkoutClass(
                        String name,
                        Integer reviews,
                        Integer setsPerExercise,
                        Integer averageWorkoutTime,
                        Integer restLength,
                        Boolean isDeletable,
                        Boolean isSetBased,
                        Boolean isTimeBased,
                        String author,
                        String difficulty,
                        ArrayList<ExerciseClass> exercises,
                        ArrayList<StretchClass> stretchesList,
                        ArrayList<String> musclesWorked
                      ){

            this.name = name;
            this.reviews = reviews;
            this.setsPerExercise = setsPerExercise;
            this.averageWorkoutTime = averageWorkoutTime;
            this.restLength = restLength;
            this.isDeletable = isDeletable;
            this.isSetBased = isSetBased;
            this.isTimeBased = isTimeBased;
            this.author = author;
            this.difficulty = difficulty;
            this.exercises = exercises;
            this.stretchesList = stretchesList;
            this.musclesWorked = musclesWorked;

    }

    public WorkoutClass(){

    }

    protected WorkoutClass(Parcel in) {

        this.name = in.readString();
        this.reviews = in.readInt();
        this.setsPerExercise = in.readInt();
        this.averageWorkoutTime = in.readInt();
        this.restLength = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.isDeletable = in.readBoolean();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.isSetBased = in.readBoolean();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.isTimeBased = in.readBoolean();
        }
        this.author = in.readString();
        this.difficulty = in.readString();
        this.exercises = in.readArrayList(ExerciseClass.class.getClassLoader());
        this.stretchesList = in.readArrayList(StretchClass.class.getClassLoader());
        this.musclesWorked = in.readArrayList(ArrayList.class.getClassLoader());

    }

    public static final Creator<WorkoutClass> CREATOR = new Creator<WorkoutClass>() {
        @Override
        public WorkoutClass createFromParcel(Parcel in) {
            return new WorkoutClass(in);
        }

        @Override
        public WorkoutClass[] newArray(int size) {
            return new WorkoutClass[size];
        }
    };


    public Boolean getIsSetBased() {
        return isSetBased;
    }

    public void setIsSetBased(Boolean setBased) {
        isSetBased = setBased;
    }

    public Boolean getIsTimeBased() {
        return isTimeBased;
    }

    public void setIsTimeBased(Boolean timeBased) {
        isTimeBased = timeBased;
    }

    public Boolean getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(Boolean deletable) {
        isDeletable = deletable;
    }

    public Integer getSetsPerExercise() {
        return setsPerExercise;
    }

    public void setSetsPerExercise(Integer setsPerExercise) {
        this.setsPerExercise = setsPerExercise;
    }


    public ArrayList getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ExerciseClass> exercises) {
        this.exercises = exercises;
    }

    public Integer getAverageWorkoutTime() {
        return averageWorkoutTime;
    }

    public void setAverageWorkoutTime(Integer averageWorkoutTime) {
        this.averageWorkoutTime = averageWorkoutTime;
    }

    public Integer getRestLength() {
        return restLength;
    }

    public void setRestLength(Integer restLength) {
        this.restLength = restLength;
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

    public ArrayList getStretchesList() {
        return stretchesList;
    }

    public void setStretches(ArrayList<StretchClass> stretchesList) {
        this.stretchesList = stretchesList;
    }

    public ArrayList getMusclesWorked() {
        return musclesWorked;
    }

    public void setMusclesWorked(ArrayList<String> musclesWorked) {
        this.musclesWorked = musclesWorked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeString(this.name);
        parcel.writeInt(this.reviews);
        parcel.writeInt(this.setsPerExercise);
        parcel.writeInt(this.averageWorkoutTime);
        parcel.writeInt(this.restLength);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.isDeletable);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.isSetBased);
        }

        parcel.writeString(this.author);
        parcel.writeString(this.difficulty);
        parcel.writeList(this.exercises);
        parcel.writeList(this.stretchesList);
        parcel.writeList(this.musclesWorked);
    }
}

