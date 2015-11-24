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

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by gabriel on 11/1/2015.
 */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    private List<Dish> dishes;
    private DishItemViewListener dishItemViewListener;

    public DishAdapter(List<Dish> dishes, DishItemViewListener dishItemViewListener) {
        this.dishes = dishes;
        this.dishItemViewListener = dishItemViewListener;
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView priceTextView;
        private Button registerOrderButton;

        public DishViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            priceTextView = (TextView) itemView.findViewById(R.id.price);
            registerOrderButton = (Button) itemView.findViewById(R.id.register_order_button);
        }
    }

    public void bindDish(DishViewHolder dishViewHolder, final Dish dish) {
        if (dish == null)
            return;

        dishViewHolder.nameTextView.setText(dish.getName());
        dishViewHolder.descriptionTextView.setText(TextUtils.isEmpty(dish.getDescription()) ? "" : dish.getDescription());
        dishViewHolder.priceTextView.setText(NumberFormat.getCurrencyInstance().format(dish.getPrice()));

        dishViewHolder.registerOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order();
                order.setDish(dish);
                dishItemViewListener.onOrderPlaced(order);
            }
        });
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_row_layout, parent, false);
        return new DishViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DishViewHolder dishViewHolder, int position) {
        Dish dish = dishes.get(position);
        bindDish(dishViewHolder, dish);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public interface DishItemViewListener {
        void onOrderPlaced(Order order);
    }
}
