package com.example.fitbud.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.versionedparcelable.VersionedParcelize;

import java.util.ArrayList;

public class UserClass implements Parcelable {

    private String username;
    private String email;
    private String height;
    private String weight;
    private String profilePhoto;
    private String goal;
    private ArrayList<String> availableEquipment;
    private ArrayList posts;
    private ArrayList workouts;
    private ArrayList exercises;
    private ArrayList mealRecipes;
    private Boolean firstTimeLogIn;
    private String heightMeasurement;
    private String weightMeasurement;
    private String userType;


    public UserClass(String username, String email, String height, String weight, String profilePhoto, String goal, ArrayList<String> availableEquipment, ArrayList posts, ArrayList workouts, ArrayList exercises, ArrayList mealRecipes, Boolean firstTimeLogIn, String weightMeasurement, String heightMeasurement, String userType) {
        this.username = username;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.profilePhoto = profilePhoto;
        this.goal = goal;
        this.availableEquipment = availableEquipment;
        this.posts = posts;
        this.workouts = workouts;
        this.exercises = exercises;
        this.mealRecipes = mealRecipes;
        this.firstTimeLogIn = firstTimeLogIn;
        this.weightMeasurement = weightMeasurement;
        this.heightMeasurement = heightMeasurement;
        this.userType = userType;
    }

    public UserClass(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public ArrayList<String> getAvailableEquipment() {
        return availableEquipment;
    }

    public void setAvailableEquipment(ArrayList<String> availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public ArrayList getPosts() {
        return posts;
    }

    public void setPosts(ArrayList posts) {
        this.posts = posts;
    }

    public ArrayList getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList workouts) {
        this.workouts = workouts;
    }

    public ArrayList getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList exercises) {
        this.exercises = exercises;
    }

    public ArrayList getMealRecipes() {
        return mealRecipes;
    }

    public void setMealRecipes(ArrayList mealRecipes) {
        this.mealRecipes = mealRecipes;
    }

    public Boolean getFirstTimeLogIn() {
        return firstTimeLogIn;
    }

    public void setFirstTimeLogIn(Boolean firstTimeLogIn) {
        this.firstTimeLogIn = firstTimeLogIn;
    }

    public String getHeightMeasurement() {
        return heightMeasurement;
    }

    public void setHeightMeasurement(String heightMeasurement) {
        this.heightMeasurement = heightMeasurement;
    }

    public String getWeightMeasurement() {
        return weightMeasurement;
    }

    public void setWeightMeasurement(String weightMeasurement) {
        this.weightMeasurement = weightMeasurement;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(userType);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(height);
        parcel.writeString(weight);
        parcel.writeString(profilePhoto);
        parcel.writeString(goal);
        parcel.writeStringList(availableEquipment);
        parcel.writeStringList(posts);
        parcel.writeStringList(workouts);
        parcel.writeStringList(exercises);
        parcel.writeStringList(mealRecipes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(firstTimeLogIn);
        }
        parcel.writeString(weightMeasurement);
        parcel.writeString(heightMeasurement);

    }
    protected UserClass(Parcel in){

        userType = in.readString();
        username = in.readString();
        email = in.readString();
        height = in.readString();
        weight = in.readString();
        profilePhoto = in.readString();
        goal = in.readString();
        availableEquipment = new ArrayList<>();
        posts = new ArrayList<>();
        workouts = new ArrayList<>();
        exercises = new ArrayList<>();
        mealRecipes = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            firstTimeLogIn = in.readBoolean();
        }
        weightMeasurement = in.readString();
        heightMeasurement = in.readString();


    }
@Override
    public int describeContents() {
        return 0;
    }


    public static final Creator<UserClass> CREATOR = new Creator<UserClass>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public UserClass createFromParcel(Parcel in) {
            return new UserClass(in);
        }

        @Override
        public UserClass[] newArray(int size) {
            return new UserClass[size];
        }
    };

}
