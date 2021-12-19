package com.example.fitbud.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RecipeClass implements Parcelable {

    private String name;
    private Integer calories;
    private Integer proteins;
    private Integer carbohydrates;
    private Integer fats;
    private ArrayList<String> tags;
    private ArrayList<String> ingredients;
    private String cookingTime;
    private String author;
    private String imageId;
    private String cookingInstructions;

    public RecipeClass(String name, Integer calories, Integer proteins, Integer carbohydrates, Integer fats, ArrayList<String> tags, ArrayList<String> ingredients, String cookingTime, String author, String imageId, String cookingInstructions) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.tags = tags;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
        this.author = author;
        this.imageId = imageId;
        this.cookingInstructions = cookingInstructions;
    }

    public RecipeClass(){}

    protected RecipeClass(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            calories = null;
        } else {
            calories = in.readInt();
        }
        if (in.readByte() == 0) {
            proteins = null;
        } else {
            proteins = in.readInt();
        }
        if (in.readByte() == 0) {
            carbohydrates = null;
        } else {
            carbohydrates = in.readInt();
        }
        if (in.readByte() == 0) {
            fats = null;
        } else {
            fats = in.readInt();
        }
        tags = in.createStringArrayList();
        ingredients = in.createStringArrayList();
        cookingTime = in.readString();
        author = in.readString();
        imageId = in.readString();
        cookingInstructions = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (calories == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(calories);
        }
        if (proteins == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(proteins);
        }
        if (carbohydrates == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(carbohydrates);
        }
        if (fats == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fats);
        }
        dest.writeStringList(tags);
        dest.writeStringList(ingredients);
        dest.writeString(cookingTime);
        dest.writeString(author);
        dest.writeString(imageId);
        dest.writeString(cookingInstructions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeClass> CREATOR = new Creator<RecipeClass>() {
        @Override
        public RecipeClass createFromParcel(Parcel in) {
            return new RecipeClass(in);
        }

        @Override
        public RecipeClass[] newArray(int size) {
            return new RecipeClass[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }



}
