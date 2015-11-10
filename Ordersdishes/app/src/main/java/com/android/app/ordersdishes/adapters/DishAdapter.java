package com.android.app.ordersdishes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.model.Dish;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by gabriel on 11/1/2015.
 */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    List<Dish> dishes;

    public DishAdapter(List<Dish> dishes) {
        this.dishes = dishes;
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
            descriptionTextView.setText(dish.getDescription());
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
        holder.bind(dishes.get(position));
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
