package com.android.app.ordersdishes.activities;

import android.os.Bundle;

import com.android.app.ordersdishes.OrderDishesApplication;
import com.gp89developers.android.commonutils.activity.BaseActivity;

/**
 * Created by gabriel on 10/31/2015.
 */
public abstract class BaseAppActivity extends BaseActivity<OrderDishesApplication> {
    private int layoutId;

    public BaseAppActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);

        initComponents();
    }

    protected abstract void initComponents();
}
