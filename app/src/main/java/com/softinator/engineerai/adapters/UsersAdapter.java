package com.softinator.engineerai.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softinator.engineerai.R;
import com.softinator.engineerai.models.User;
import com.softinator.engineerai.viewutils.RecyclerViewItemDecoration;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {

    ArrayList<User> users;
    Context context;

    public UsersAdapter(Context context, ArrayList<User> users){
        this.context = context;
        this.users = users;
    }

    public void addUsers(ArrayList<User> users){
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        UsersHolder holder = new UsersHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {

        Glide.with(context).load(users.get(position).getImage()).into(holder.userProfileImage);
        holder.name.setText(users.get(position).getName());

        holder.totalItemsInRow = users.get(position).getItems().size();
        ItemsAdapter adapter = new ItemsAdapter(context, users.get(position).getItems());
        holder.itemsRV.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class UsersHolder extends RecyclerView.ViewHolder{

        CircleImageView userProfileImage;
        TextView name;
        RecyclerView itemsRV;
        int totalItemsInRow;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);

            userProfileImage = itemView.findViewById(R.id.userImage);
            name = itemView.findViewById(R.id.userName);
            itemsRV = itemView.findViewById(R.id.itemsRV);

            int spacing = 20;
            itemsRV.addItemDecoration(new RecyclerViewItemDecoration(spacing));

            GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(totalItemsInRow%2 !=0 && position == 0){
                        return 2;
                    }else{
                        return 1;
                    }
                }
            });

            this.itemsRV.setLayoutManager(layoutManager);
        }
    }

}
