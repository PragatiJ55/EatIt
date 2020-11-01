package com.example.eatit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AccountActivity extends BaseActivity {



    @Override
    int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.nav_account;
    }
}
