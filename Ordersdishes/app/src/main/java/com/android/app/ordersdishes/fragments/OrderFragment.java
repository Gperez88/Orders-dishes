package com.android.app.ordersdishes.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.adapters.OrderAdapter;
import com.android.app.ordersdishes.model.Order;
import com.android.app.ordersdishes.service.ApiOrderDishesService;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;
import com.gp89developers.android.commonutils.utils.CustomAsyncTask;
import com.gp89developers.android.commonutils.widget.recyclerview.DividerItemDecoration;
import com.gp89developers.android.commonutils.widget.recyclerview.RecyclerEmptyView;

import java.io.Serializable;

public class OrderFragment extends BaseAppListFragment<OrderAdapter> implements OrderAdapter.OrderItemViewListener {
    public static final String PREFERENCES_ARG = "preferences_args";
    private ApiOrderDishesService apiOrderDishesService;

    public static OrderFragment create() {
        return new OrderFragment();
    }

    public OrderFragment() {
        super(R.layout.fragment_list, R.string.no_data_display, R.mipmap.ic_order_line);
    }

    @Override
    protected void setupListRecyclerView(View view) {
        initOrderList(view);
    }

    @Override
    protected OrderAdapter loadData() {
        return setupAdapter();
    }

    private void initOrderList(View view) {
        listRecyclerView = (RecyclerEmptyView) view.findViewById(R.id.list_recyclerView);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getSupportActivity()));
        listRecyclerView.addItemDecoration(new DividerItemDecoration(getSupportActivity(), DividerItemDecoration.VERTICAL_LIST));
        listRecyclerView.setEmptyView(emptyContainer);
        listRecyclerView.setHasFixedSize(true);
        apiOrderDishesService = new ApiOrderDishesImpl();
    }

    public OrderAdapter setupAdapter() {
        String username = null;

        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getSupportActivity());
            username = preferences.getString(getString(R.string.prompt_username), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new OrderAdapter(apiOrderDishesService.getListOrders(username), this);
    }

    @Override
    public void onOrderReceived(Order order) {
        ReceiveOrderTask receiveOrderTask = new ReceiveOrderTask();;
        receiveOrderTask.execute(order);
        Toast.makeText(getSupportActivity(), "Order received", Toast.LENGTH_LONG).show();
    }

    private class ReceiveOrderTask extends CustomAsyncTask<Order, Void, Boolean> {
        private static final int ORDER_INDEX_PARAM = 0;
        private ApiOrderDishesService apiOrderDishesService;

        public ReceiveOrderTask() {
            super(getSupportActivity());
            apiOrderDishesService = new ApiOrderDishesImpl();
        }

        @Override
        protected Boolean doInBackground(Order... params) {
            return apiOrderDishesService.receiveOrder(params[ORDER_INDEX_PARAM]);
        }

    }
}
