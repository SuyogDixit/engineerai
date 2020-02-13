package com.softinator.engineerai.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    @SerializedName("name") String name;
    @SerializedName("image") String image;
    @SerializedName("items") ArrayList<String> items;

    User(String name, String image, ArrayList<String> items){
        this.name = name;
        this.image = image;
        this.items = items;

    }

}
