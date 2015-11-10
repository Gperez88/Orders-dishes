package com.android.app.ordersdishes.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.adapters.FoodCourtFragmentPagerAdapter;

public class MainActivity extends BaseAppActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FoodCourtFragmentPagerAdapter foodCourtPagerAdapter;

    public MainActivity() {
        super(R.layout.activity_dish);
    }

    @Override
    protected void initComponents() {
        setupToolbar();
        initViewPager();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        foodCourtPagerAdapter = new FoodCourtFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(foodCourtPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        /**
         *  el verdadero forze 1.1 --> desabilito el swipe del viewpager, esto porque mas abajo estoy
         *  haciendo una notification ya que quiero que siempre se elimine el fragment anterior al
         *  pasar al siguiente.
         **/
        viewPager .setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        // notifico para que elimine el fragment anterior.
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                foodCourtPagerAdapter.notifyDataSetChanged();
            }
        });
    }
}
