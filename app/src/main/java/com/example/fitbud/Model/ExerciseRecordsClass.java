package com.example.fitbud.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ExerciseRecordsClass implements Serializable {
    private String date;
    private HashMap<String,Integer> set;


    public ExerciseRecordsClass(String date, HashMap<String,Integer> set){
        this.date = date;
        this.set = set;
    }

    public ExerciseRecordsClass(){};

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Integer> getSet() {
        return set;
    }

    public void setSet(HashMap<String, Integer> set) {
        this.set = set;
    }
}
