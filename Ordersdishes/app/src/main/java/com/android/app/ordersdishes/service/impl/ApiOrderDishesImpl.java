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
import java.util.List;

/**
 * Created by gabriel on 10/31/2015.
 */
public class ApiOrderDishesImpl implements ApiOrderDishesService {
    @Override
    public boolean login(String username, String password) {
        //TODO: implement
        return false;
    }

    @Override
    public boolean register(String username, String password, String confirmationPassword) {
        //TODO: implement
        return false;
    }

    @Override
    public List<Dish> getListDishes() {
        //TODO: implement
        return null;
    }

    @Override
    public boolean registerOrder(Order order, String username) {
        //TODO: implement
        return false;
    }

    @Override
    public List<Order> getListOrders(int userId) {
        //TODO: implement
        return null;
    }

    private String callBaseApiService(Uri builtUri) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String jsonStr = null;

        if(builtUri == null)
            return null;

        try {

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
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
