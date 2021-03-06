package com.example.eatit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        navigationView=findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }
//Remove inter-activity transition to avoid screen tossing on tapping bottom nav items
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(()->{
            int itemId = item.getItemId();
            if(itemId== R.id.nav_home){
                startActivity(new Intent(this, MainActivity.class));
            }
            else if(itemId == R.id.nav_cart){
                startActivity(new Intent(this, CartActivity.class));
            }
            else if(itemId == R.id.nav_account){
                startActivity(new Intent(this, AccountActivity.class));
            }

            finish();
        },10);
        return true;
    }
    private void updateNavigationBarState() {
        int actionId = getBottomNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }
    abstract int getLayoutId(); // this is to return which layout(activity) needs to display when clicked on tabs.

    abstract int getBottomNavigationMenuItemId();//Which menu item selected and change the state of that menu item
}
