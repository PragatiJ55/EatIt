package com.example.eatit;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatit.Interface.onItemClickListener;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    onItemClickListener onItemClickListener;
    TextView HomeCookName,FoodType;
    ImageView image;
    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        HomeCookName=itemView.findViewById(R.id.HomeCookName);
        FoodType=itemView.findViewById(R.id.FoodType);
        image=itemView.findViewById(R.id.imageView);
    }


    @Override
    public void onClick(View view) {
        onItemClickListener.onItemClick(view, getAdapterPosition());
    }
    public void setOnItemClickListener(onItemClickListener itemClickListener){
        this.onItemClickListener=itemClickListener;
    }
}
