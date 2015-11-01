package com.android.app.ordersdishes.activities;

import android.support.v7.widget.Toolbar;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.fragments.DishFragment;

public class DishActivity extends BaseAppActivity {
    private static final String DISH_FRAGMENT_TAG = "dish_fragment_tag";

    public DishActivity() {
        super(R.layout.activity_dish);
    }

    @Override
    protected void initComponents() {
        setupToolbar();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, DishFragment.create(), DISH_FRAGMENT_TAG)
                .commit();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
