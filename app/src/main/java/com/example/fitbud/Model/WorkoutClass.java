package com.example.fitbud.Model;

import java.util.ArrayList;
import java.util.Timer;

public class WorkoutClass {

    Integer id;
    Boolean isSetBased;
    Boolean isTimeBased;
    Boolean isDeletable;
    Integer setsPerExercise;
    Timer timer;
    ArrayList exercises;
    Integer averageWorkoutTime;
    Integer restLength;
    String author;
    String difficulty;
    ArrayList stretches;
    ArrayList musclesWorked;


    public WorkoutClass(Integer id,
                        Integer setsPerExercise,
                        Integer averageWorkoutTime,
                        Integer restLength,
                        Boolean isDeletable,
                        Boolean isSetBased,
                        Boolean isTimeBased,
                        String author,
                        String difficulty,
                        ArrayList exercises,
                        ArrayList stretches,
                        ArrayList musclesWorked,
                        Timer timer){
            this.id = id;
            this.setsPerExercise = setsPerExercise;
            this.averageWorkoutTime = averageWorkoutTime;
            this.restLength = restLength;
            this.isDeletable = isDeletable;
            this.isSetBased = isSetBased;
            this.isTimeBased = isTimeBased;
            this.author = author;
            this.difficulty = difficulty;
            this.exercises = exercises;
            this.stretches = stretches;
            this.musclesWorked = musclesWorked;
            this.timer = timer;

    }

    public WorkoutClass(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSetBased() {
        return isSetBased;
    }

    public void setSetBased(Boolean setBased) {
        isSetBased = setBased;
    }

    public Boolean getTimeBased() {
        return isTimeBased;
    }

    public void setTimeBased(Boolean timeBased) {
        isTimeBased = timeBased;
    }

    public Boolean getDeletable() {
        return isDeletable;
    }

    public void setDeletable(Boolean deletable) {
        isDeletable = deletable;
    }

    public Integer getSetsPerExercise() {
        return setsPerExercise;
    }

    public void setSetsPerExercise(Integer setsPerExercise) {
        this.setsPerExercise = setsPerExercise;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public ArrayList getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList exercises) {
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

    public ArrayList getStretches() {
        return stretches;
    }

    public void setStretches(ArrayList stretches) {
        this.stretches = stretches;
    }

    public ArrayList getMusclesWorked() {
        return musclesWorked;
    }

    public void setMusclesWorked(ArrayList musclesWorked) {
        this.musclesWorked = musclesWorked;
    }

}

