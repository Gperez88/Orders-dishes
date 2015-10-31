package com.android.app.ordersdishes.service;

import com.android.app.ordersdishes.model.Dish;
import com.android.app.ordersdishes.model.Order;

import java.util.List;

/**
 * Created by gabriel on 10/31/2015.
 */
public interface ApiOrderDishesService {
    public static final String BASE_API_URL = "http://sample.com/orderdishes/api";
    public static final String LOGIN_ENDPOINT = "/v1/login/{email}/{password}";
    public static final String REGISTER_ENDPOINT = "/v1/register/{email}/{password}/{confirmation}";
    public static final String DISHES_LIST_ENDPOINT = "/v1/dishes/";
    public static final String ORDER_ENDPOINT = "/v1/order/{dish_id}/{user}}";
    public static final String ORDER_LIST_ENDPOINT = "/v1/orders/{user}}";

    public boolean login(String username, String password);
    public boolean register(String username, String password, String confirmationPassword);
    public List<Dish> getListDishes();
    public boolean registerOrder(Order order, String username);
    public List<Order> getListOrders(int userId);
}
