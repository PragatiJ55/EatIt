package com.example.eatit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CartActivity extends BaseActivity {


    @Override
    int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.nav_cart;
    }
}
