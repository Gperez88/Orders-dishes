package com.android.app.ordersdishes.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.model.Dish;
import com.android.app.ordersdishes.model.Order;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by gabriel on 11/1/2015.
 */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    private List<Dish> dishes;
    private DishItemViewListener dishItemViewListener;

    public DishAdapter(List<Dish> dishes,  DishItemViewListener dishItemViewListener) {
        this.dishes = dishes;
        this.dishItemViewListener = dishItemViewListener;
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView priceTextView;

        public DishViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            priceTextView = (TextView) itemView.findViewById(R.id.price);
        }

        public void bind(Dish dish) {
            if (dish == null)
                return;

            nameTextView.setText(dish.getName());
            descriptionTextView.setText(TextUtils.isEmpty(dish.getDescription()) ? "" : dish.getDescription());
            priceTextView.setText(NumberFormat.getCurrencyInstance().format(dish.getPrice()));
        }
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_row_layout, parent, false);
        return new DishViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.bind(dish);
        Order order = new Order();
        order.setDish(dish);
        bindDishOrder(holder, order);

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    private void bindDishOrder(final DishViewHolder dishViewHolder, final Order order) {
        if (order == null || order.getId() == -1)
            return;


        dishViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishItemViewListener.onOrderPlaced(order);

            }
        });

    }
    public interface DishItemViewListener{
        void onOrderPlaced(Order order);
    }
}
