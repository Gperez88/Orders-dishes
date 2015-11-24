
package com.android.app.ordersdishes.service.impl;

        import android.net.Uri;
        import android.text.TextUtils;

        import com.android.app.ordersdishes.model.Dish;
        import com.android.app.ordersdishes.model.Order;
        import com.android.app.ordersdishes.service.ApiOrderDishesService;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

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

        Uri builtUri = Uri.parse(ApiOrderDishesService.BASE_API_URL).buildUpon()
                .appendEncodedPath(ApiOrderDishesService.LOGIN_ENDPOINT)
                .appendQueryParameter("email", username)
                .appendQueryParameter("password",password).build();

        Boolean result = false;
        try {
            JSONObject response = new JSONObject(callBaseApiService(builtUri));
            result = response.getBoolean("Response");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean register(String username, String password) {
        Uri builtUri = Uri.parse(ApiOrderDishesService.BASE_API_URL).buildUpon()
                .appendEncodedPath(ApiOrderDishesService.REGISTER_ENDPOINT)
                .appendQueryParameter("email", username)
                .appendQueryParameter("password",password).build();
        Boolean result = false;
        try {
            JSONObject response = new JSONObject(callBaseApiService(builtUri));
            result = response.getBoolean("Response");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Dish> getListDishes(){
        Uri builtUri = Uri.parse(ApiOrderDishesService.BASE_API_URL).buildUpon()
                .appendEncodedPath(ApiOrderDishesService.DISHES_LIST_ENDPOINT)
                .build();

        JSONArray dishesJsonArray = null;
        List<Dish> dishes = new ArrayList<>();
        try {
            dishesJsonArray = new JSONArray(callBaseApiService(builtUri));

            for (int i = 0; i < dishesJsonArray.length(); i++) {
                JSONObject dishJsonObject = dishesJsonArray.getJSONObject(i);
                String description = dishJsonObject.getString("Description");
                Dish dish = new Dish();
                dish.setId(dishJsonObject.getInt("Id"));
                dish.setName(dishJsonObject.getString("Name"));
                dish.setDescription(description);
                dish.setPrice(Float.parseFloat(dishJsonObject.getString("Price")));

                dishes.add(dish);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dishes;
    }

    @Override
    public boolean registerOrder(Order order) {
        Uri builtUri = Uri.parse(ApiOrderDishesService.BASE_API_URL).buildUpon()
                .appendEncodedPath(ApiOrderDishesService.ORDER_ENDPOINT)
                .appendQueryParameter("dishId", String.valueOf(order.getDish().getId()))
                .appendQueryParameter("username",order.getUsername())
                .build();
        Boolean result = false;
        try {
            JSONObject response = new JSONObject(callBaseApiService(builtUri));
            result = response.getBoolean("Response");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Order> getListOrders(String username) {

        List<Order> orders = new ArrayList<>();
        if (username == null){
            return orders;
        }

        JSONArray ordersJsonArray = null;
        Uri builtUri = Uri.parse(ApiOrderDishesService.BASE_API_URL).buildUpon()
                .appendEncodedPath(ApiOrderDishesService.ORDER_LIST_ENDPOINT)
                .appendQueryParameter("username", username)
                .build();

        try {
            ordersJsonArray = new JSONArray(callBaseApiService(builtUri));

            for (int i = 0; i < ordersJsonArray.length(); i++) {
                JSONObject orderJsonObject = ordersJsonArray.getJSONObject(i);
                JSONObject dishJsonObject = orderJsonObject.getJSONObject("Dish");

                Dish dish = new Dish();
                dish.setId(dishJsonObject.getInt("Id"));
                dish.setName(dishJsonObject.getString("Name"));
                String description = dishJsonObject.getString("Description");
                dish.setDescription(description);
                dish.setPrice(Float.parseFloat(dishJsonObject.getString("Price")));

                Order order = new Order();
                order.setId(orderJsonObject.getInt("Id"));
                order.setDish(dish);
                order.setDate(new Date());
                order.setState(orderJsonObject.getInt("State"));

                orders.add(order);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public boolean receiveOrder(Order order) {
        Uri builtUri = Uri.parse(ApiOrderDishesService.BASE_API_URL).buildUpon()
                .appendEncodedPath(ApiOrderDishesService.ORDER_LIST_ENDPOINT)
                .appendQueryParameter("id", String.valueOf(order.getId()))
                .build();

        Boolean result = false;
        try {
            JSONObject response = new JSONObject(callBaseApiService(builtUri));
            result = response.getBoolean("Response");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
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
