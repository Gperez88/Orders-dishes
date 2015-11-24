package com.android.app.ordersdishes.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.model.Dish;
import com.android.app.ordersdishes.model.Order;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gabriel on 11/9/2015.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orders;
    private OrderItemViewListener orderItemViewListener;
    private static final String DATE_FORMAT = "dd/MM/yyyy hh:mm a";

    public OrderAdapter(List<Order> orders,OrderItemViewListener orderItemViewListener ) {
        this.orders = orders;
        this.orderItemViewListener = orderItemViewListener;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {


        private TextView nameDishTextView;
        private TextView stateTextView;
        private TextView dateTextView;
        private Button receiveOrderButton;

        public OrderViewHolder(View itemView) {
            super(itemView);

            nameDishTextView = (TextView) itemView.findViewById(R.id.name_dish);
            stateTextView = (TextView) itemView.findViewById(R.id.state);
            dateTextView = (TextView) itemView.findViewById(R.id.date);
            receiveOrderButton = (Button) itemView.findViewById(R.id.receive_order_button);
        }

    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row_layout, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        bindOrder(holder, orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void bindOrder(final OrderViewHolder orderViewHolder, final Order order) {
        if (order == null)
            return;

        orderViewHolder.nameDishTextView.setText(order.getDish().getName());
        orderViewHolder.stateTextView.setText(ApiOrderDishesImpl.ORDER_STATE[order.getState()]);
        orderViewHolder.dateTextView.setText(new SimpleDateFormat(DATE_FORMAT).format(order.getDate()));

        orderViewHolder.receiveOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderItemViewListener.onOrderReceived(order);
                orderViewHolder.receiveOrderButton.setEnabled(false);
            }
        });
    }

    public interface OrderItemViewListener {
        void onOrderReceived(Order order);
    }

}
