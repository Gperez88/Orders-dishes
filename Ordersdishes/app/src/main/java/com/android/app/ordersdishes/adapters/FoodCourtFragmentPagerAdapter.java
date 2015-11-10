package com.android.app.ordersdishes.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.fragments.DishFragment;
import com.android.app.ordersdishes.fragments.OrderFragment;

/**
 * Created by gabriel on 11/9/2015.
 */
public class FoodCourtFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUMBER_PAGE = 2;
    private static final int POSITION_DISH = 0;
    private static final int POSITION_ORDER = 1;

    private Context mContext;

    public FoodCourtFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POSITION_DISH: {
                return DishFragment.create();
            }
            case POSITION_ORDER: {
                return OrderFragment.create();
            }
            default: {
                return DishFragment.create();
            }
        }
    }

    /**
     * el verdadero forze 1.2 --> esto para que siempre elimine el fragment anterior al pasar al
     * siguiente por eso hice una instancia del FragmentStatePagerAdapter y no FragmentPagerAdapter.
     **/
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case POSITION_DISH: {
                return mContext.getString(R.string.dish);
            }
            case POSITION_ORDER: {
                return mContext.getString(R.string.order);
            }
            default: {
                return mContext.getString(R.string.dish);
            }
        }
    }

    @Override
    public int getCount() {
        return NUMBER_PAGE;
    }
}
