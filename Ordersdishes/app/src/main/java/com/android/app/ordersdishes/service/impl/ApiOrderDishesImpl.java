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
import java.util.List;

/**
 * Created by gabriel on 10/31/2015.
 */
public class ApiOrderDishesImpl implements ApiOrderDishesService {
    @Override
    public boolean login(String username, String password) {
        //TODO: implement
        return true;
    }

    @Override
    public boolean register(String username, String password, String confirmationPassword) {
        //TODO: implement real method
        return false;
    }

    @Override
    public List<Dish> getListDishes() {
        //TODO: implement real method

        List<Dish> dishes = new ArrayList<>();
        for (int index = 1; index < 100; index++) {
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
        //TODO: implement real method
        return false;
    }

    @Override
    public List<Order> getListOrders(int userId) {
        //TODO: implement real method
        return null;
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
