package com.android.app.ordersdishes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.model.Order;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gabriel on 11/9/2015.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private static final String DATE_FORMAT = "dd/MM/yyyy hh:mm a";

        private TextView nameDishTextView;
        private TextView stateTextView;
        private TextView dateTextView;

        public OrderViewHolder(View itemView) {
            super(itemView);

            nameDishTextView = (TextView) itemView.findViewById(R.id.name_dish);
            stateTextView = (TextView) itemView.findViewById(R.id.state);
            dateTextView = (TextView) itemView.findViewById(R.id.date);
        }

        public void bind(Order order) {
            if (order == null)
                return;

            nameDishTextView.setText(order.getDish().getName());
            stateTextView.setText(ApiOrderDishesImpl.ORDER_STATE[order.getState()]);
            dateTextView.setText(new SimpleDateFormat(DATE_FORMAT).format(order.getDate()));
        }
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row_layout, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.bind(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


}
