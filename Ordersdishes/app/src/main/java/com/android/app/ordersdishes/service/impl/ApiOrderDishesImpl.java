package com.android.app.ordersdishes.service.impl;

import android.net.Uri;

import com.android.app.ordersdishes.model.Dish;
import com.android.app.ordersdishes.model.Order;
import com.android.app.ordersdishes.service.ApiOrderDishesService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gabriel on 10/31/2015.
 */
public class ApiOrderDishesImpl implements ApiOrderDishesService {
    @Override
    public boolean login(String username, String password) {
        //TODO: implement actual method

        //simulates a process
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean register(String username, String password, String confirmationPassword) {
        //TODO: implement actual method
        return true;
    }

    @Override
    public List<Dish> getListDishes() {
        //TODO: implement actual method

        //simulates a process
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<Dish> dishes = new ArrayList<>();

        for (int index = 1; index < 50; index++) {
            Dish dish = new Dish();
            dish.setId(index);
            dish.setName("Dish #" + index);
            dish.setDescription("Description dish #" + index);
            dish.setPrice(199.99f);

            dishes.add(dish);
        }

        return dishes;
    }

    @Override
    public boolean registerOrder(Order order, String username) {
        //TODO: implement actual method
        return false;
    }

    @Override
    public List<Order> getListOrders(String username) {
        //TODO: implement actual method

        //simulates a process
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<Order> orders = new ArrayList<>();

        if (username == null)
            return orders;

        Dish dish = new Dish();
        dish.setId(1);
        dish.setName("Dish #" + 1);
        dish.setDescription("Description dish #" + 1);
        dish.setPrice(199.99f);

        for (int index = 1; index < 10; index++) {
            Order order = new Order();
            order.setId(index);
            order.setDish(dish);
            order.setDate(new Date());
            order.setState(0);

            orders.add(order);
        }

        return orders;
    }

    private String callBaseApiService(Uri builtUri) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String jsonStr = null;

        if (builtUri == null)
            return null;

        try {

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            jsonStr = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonStr;
    }
}
