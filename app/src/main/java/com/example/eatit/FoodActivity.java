package com.example.eatit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    TextView food;
    String categoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        food=findViewById(R.id.food);
        if(getIntent()!=null){
            categoryId=getIntent().getStringExtra("CategoryId");
            if(!categoryId.isEmpty() && categoryId!=null){
                Log.e("CategoryId",categoryId);
            }
        }


    }
}
