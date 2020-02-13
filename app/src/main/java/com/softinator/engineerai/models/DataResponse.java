package com.softinator.engineerai.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResponse {


    @SerializedName("status") boolean status;
    @SerializedName("message") String message;
    @SerializedName("data") Data data;


    DataResponse(boolean status, String message, Data data){
        this.status  = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        public ArrayList<User> getUsers() {
            return users;
        }

        public void setUsers(ArrayList<User> users) {
            this.users = users;
        }

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        @SerializedName("users") ArrayList<User> users;
        @SerializedName("has_more") boolean hasMore;

        Data(ArrayList<User> users, boolean hasMore){
            this.users = users;
            this.hasMore = hasMore;
        }
    }

}
