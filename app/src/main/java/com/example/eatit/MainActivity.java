package com.example.eatit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eatit.Interface.onItemClickListener;
import com.example.eatit.models.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends BaseActivity{
    TextView userTV;
    FirebaseRecyclerAdapter<Category, FoodViewHolder> adapter;
    private LinearLayoutManager linearLayoutManager;
    Parcelable state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        LoadData();
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        linearLayoutManager.onRestoreInstanceState(state);
    }
    private void LoadData() {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference category = database.getReference("Category");




        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(category, Category.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Category, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Category model) {

                holder.HomeCookName.setText(model.getHomeCookName());
                holder.FoodType.setText(model.getFoodType());
                Glide.with(getApplicationContext()).load(model.getImageUrl())
                        .fallback(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(holder.image);
                holder.setOnItemClickListener(new onItemClickListener() {
                    @Override
                    public void onItemClick(View view, int positon) {
                        Intent intent=new Intent(MainActivity.this, FoodActivity.class);

                        intent.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivityForResult(intent,1234);

                    }
                });

            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);


                return new FoodViewHolder(view);

            }
        };


    }
    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.nav_home;
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        state=linearLayoutManager.onSaveInstanceState();
    }
}
