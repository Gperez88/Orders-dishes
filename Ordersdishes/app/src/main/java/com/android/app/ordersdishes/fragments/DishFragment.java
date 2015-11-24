package com.android.app.ordersdishes.fragments;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.adapters.DishAdapter;
import com.android.app.ordersdishes.model.Order;
import com.android.app.ordersdishes.service.ApiOrderDishesService;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;
import com.gp89developers.android.commonutils.utils.CustomAsyncTask;
import com.gp89developers.android.commonutils.widget.recyclerview.DividerItemDecoration;
import com.gp89developers.android.commonutils.widget.recyclerview.RecyclerEmptyView;

public class DishFragment extends BaseAppListFragment<DishAdapter> implements DishAdapter.DishItemViewListener {
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
        return new DishAdapter(apiOrderDishesService.getListDishes(), this);
    }

    private String getUsername(){
        String username = null;

        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getSupportActivity());
            username = preferences.getString(getString(R.string.prompt_username), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return username;
    }

    @Override
    public void onOrderPlaced(Order order) {
        order.setUsername(getUsername());
        OrderDishTask orderDishTask = new OrderDishTask();
        orderDishTask.execute(order);
        Toast.makeText(getSupportActivity(), "Order placed", Toast.LENGTH_LONG).show();
    }

    private class OrderDishTask extends CustomAsyncTask<Order, Void, Boolean> {
        private static final int ORDER_INDEX_PARAM = 0;
        private ApiOrderDishesService apiOrderDishesService;

        public OrderDishTask() {
            super(getSupportActivity());
            apiOrderDishesService = new ApiOrderDishesImpl();
        }

        @Override
        protected void onPostExecute(Boolean orderDone) {
            if(!orderDone){
                Toast.makeText(getSupportActivity(), "There was a problem trying to register the order. Try again later", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Boolean doInBackground(Order... params) {
            return apiOrderDishesService.registerOrder(params[ORDER_INDEX_PARAM]);
        }

    }
}
