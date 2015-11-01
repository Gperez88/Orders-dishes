package com.android.app.ordersdishes.fragments;

import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.app.ordersdishes.R;

/**
 * Created by gabriel on 11/1/2015.
 */
public abstract class BaseAppListFragment extends BaseAppFragment {
    protected RelativeLayout emptyContainer;
    protected ImageView infoEmptyImg;
    protected TextView infoEmptyText;

    private int emptyImageRes;
    private int emptyTextRes;

    public BaseAppListFragment(int layoutId, int emptyTextRes, int emptyImageRes) {
        super(layoutId);
        this.emptyTextRes = emptyTextRes;
        this.emptyImageRes = emptyImageRes;
    }

    @Override
    protected void initComponents(View view) {
        initEmptyContainer(view);
    }

    private void initEmptyContainer(View view) {
        emptyContainer = (RelativeLayout) view.findViewById(R.id.empty_view);
        infoEmptyImg = (ImageView) view.findViewById(R.id.info_img);
        infoEmptyText = (TextView) view.findViewById(R.id.info_text);

        infoEmptyImg.setImageResource(emptyImageRes);
        infoEmptyImg.setColorFilter(R.color.colorDivider, PorterDuff.Mode.MULTIPLY);
        infoEmptyText.setText(getString(emptyTextRes));
    }
}
