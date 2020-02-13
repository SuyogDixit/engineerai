package com.softinator.engineerai.networklayer;

import com.softinator.engineerai.models.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IEngineerService {

    @GET("users")
    Call<DataResponse> getUsers(@Query("offset") int offset, @Query("limit") int limit);


}
