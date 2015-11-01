package com.android.app.ordersdishes.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.adapters.DishAdapter;
import com.android.app.ordersdishes.service.ApiOrderDishesService;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;
import com.gp89developers.android.commonutils.widget.recyclerview.DividerItemDecoration;
import com.gp89developers.android.commonutils.widget.recyclerview.RecyclerEmptyView;

public class DishFragment extends BaseAppListFragment {

    private RecyclerEmptyView dishesList;
    private DishAdapter dishAdapter;

    private ApiOrderDishesService apiOrderDishesService;

    public static DishFragment create(){
        return new DishFragment();
    }

    public DishFragment() {
        super(R.layout.fragment_dish, R.string.no_data_display, R.mipmap.ic_meal_line);
    }

    @Override
    protected void initComponents(View view) {
        super.initComponents(view);

        initDishesList(view);
        setupAdapter();
    }

    private void initDishesList(View view) {
        dishesList = (RecyclerEmptyView) view.findViewById(R.id.dishes_list);
        dishesList.setLayoutManager(new LinearLayoutManager(getSupportActivity()));
        dishesList.addItemDecoration(new DividerItemDecoration(getSupportActivity(), DividerItemDecoration.VERTICAL_LIST));
        dishesList.setHasFixedSize(true);

        apiOrderDishesService = new ApiOrderDishesImpl();
    }

    public void setupAdapter() {
        dishAdapter = new DishAdapter(apiOrderDishesService.getListDishes());
        dishesList.setAdapter(dishAdapter);
        dishesList.setEmptyView(emptyContainer);
    }
}
