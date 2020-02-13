package com.softinator.engineerai.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softinator.engineerai.R;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> {

    Context context;
    ArrayList<String> items;

    public ItemsAdapter(Context context, ArrayList<String> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ItemsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        Glide.with(context).load(items.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemsHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
        }
    }

}
