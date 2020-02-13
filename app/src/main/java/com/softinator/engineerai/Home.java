package com.softinator.engineerai;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softinator.engineerai.adapters.UsersAdapter;
import com.softinator.engineerai.models.DataResponse;
import com.softinator.engineerai.models.User;
import com.softinator.engineerai.networklayer.EngineerApi;
import com.softinator.engineerai.networklayer.IEngineerService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    IEngineerService engineerService;
    int dataOffset = 10;
    int dataLimit = 10;
    private String TAG = "HOME";

    UsersAdapter adapter;
    RecyclerView usersRV;
    ProgressBar progressBar;

    private boolean canLoadData = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        engineerService = EngineerApi.getClient().create(IEngineerService.class);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        usersRV = (RecyclerView) findViewById(R.id.usersRV);
        usersRV.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<User> users = new ArrayList<>();
        adapter = new UsersAdapter(this, users);

        usersRV.setAdapter(adapter);

        loadData();
        iniScrollListener();
    }

    private void iniScrollListener() {

        usersRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if(canLoadData){
                    if(linearLayoutManager!=null && linearLayoutManager.findLastVisibleItemPosition() == adapter.getItemCount()-1){
                        dataOffset = dataOffset+10;
                        loadData();
                        Log.d(TAG, "onScrolled: loading more data");
                    }
                }

            }
        });

    }


    void loadData(){
        canLoadData = false;
        progressBar.setVisibility(View.VISIBLE);
        Call<DataResponse> call = engineerService.getUsers(dataOffset, dataLimit);
        call.enqueue(new Callback<DataResponse>() {


            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                Log.d(TAG, "onResponse: We have data!");
                adapter.addUsers(((DataResponse.Data)response.body().getData()).getUsers());
                canLoadData = true;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                canLoadData = true;
                progressBar.setVisibility(View.GONE);
            }
        });

    }

}
