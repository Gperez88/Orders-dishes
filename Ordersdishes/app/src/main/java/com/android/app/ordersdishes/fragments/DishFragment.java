package com.android.app.ordersdishes.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.adapters.DishAdapter;
import com.android.app.ordersdishes.service.ApiOrderDishesService;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;
import com.gp89developers.android.commonutils.widget.recyclerview.DividerItemDecoration;
import com.gp89developers.android.commonutils.widget.recyclerview.RecyclerEmptyView;

public class DishFragment extends BaseAppListFragment<DishAdapter> {
    private ApiOrderDishesService apiOrderDishesService;

    public static DishFragment create() {
        return new DishFragment();
    }

    public DishFragment() {
        super(R.layout.fragment_list, R.string.no_data_display, R.mipmap.ic_meal_line);
    }

    @Override
    protected void setupListRecyclerView(View view) {
        initDishesList(view);
    }

    @Override
    protected DishAdapter loadData() {
        return setupAdapter();
    }

    private void initDishesList(View view) {
        listRecyclerView = (RecyclerEmptyView) view.findViewById(R.id.list_recyclerView);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getSupportActivity()));
        listRecyclerView.addItemDecoration(new DividerItemDecoration(getSupportActivity(), DividerItemDecoration.VERTICAL_LIST));
        listRecyclerView.setEmptyView(emptyContainer);
        listRecyclerView.setHasFixedSize(true);

        apiOrderDishesService = new ApiOrderDishesImpl();
    }

    public DishAdapter setupAdapter() {
        return new DishAdapter(apiOrderDishesService.getListDishes());
    }
}
