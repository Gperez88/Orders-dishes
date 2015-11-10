package com.android.app.ordersdishes.fragments;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.app.ordersdishes.R;
import com.gp89developers.android.commonutils.utils.CustomAsyncTask;
import com.gp89developers.android.commonutils.widget.recyclerview.RecyclerEmptyView;

/**
 * Created by gabriel on 11/1/2015.
 */
public abstract class BaseAppListFragment<T extends RecyclerView.Adapter> extends BaseAppFragment {
    protected RelativeLayout emptyContainer;
    protected ImageView infoEmptyImg;
    protected TextView infoEmptyText;
    protected RecyclerEmptyView listRecyclerView;
    protected ProgressBar loadingProgressBar;

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
        setupListRecyclerView(view);

        LoadDataTask loadDataTask = new LoadDataTask();
        loadDataTask.execute();
    }

    private void initEmptyContainer(View view) {
        emptyContainer = (RelativeLayout) view.findViewById(R.id.empty_view);
        infoEmptyImg = (ImageView) view.findViewById(R.id.info_img);
        infoEmptyText = (TextView) view.findViewById(R.id.info_text);
        loadingProgressBar = (ProgressBar) view.findViewById(R.id.loadingProgressBar);

        infoEmptyImg.setImageResource(emptyImageRes);
        infoEmptyImg.setColorFilter(R.color.colorDivider, PorterDuff.Mode.MULTIPLY);
        infoEmptyText.setText(getString(emptyTextRes));
    }

    private class LoadDataTask extends CustomAsyncTask<Void, Void, T> {
        public LoadDataTask() {
            super(getSupportActivity());
        }

        @Override
        protected T doInBackground(Void... params) {
            return loadData();
        }

        @Override
        protected void onPostExecute(T adapter) {
            if (adapter != null)
                listRecyclerView.setAdapter(adapter);

            loadingProgressBar.setVisibility(View.GONE);
            listRecyclerView.setVisibility(View.VISIBLE);

            super.onPostExecute(adapter);
        }
    }

    protected abstract void setupListRecyclerView(View view);

    protected abstract T loadData();
}
