package com.android.app.ordersdishes.service;

import com.android.app.ordersdishes.model.Dish;
import com.android.app.ordersdishes.model.Order;

import org.json.JSONException;

import java.util.List;

/**
 * Created by gabriel on 10/31/2015.
 */
public interface ApiOrderDishesService {
    public static final String[] ORDER_STATE = {"Pendiente", "Enviada", "Recibida"};

    public static final String BASE_API_URL = "http://foodcourt.gear.host/api/FoodCourtApi";
    public static final String LOGIN_ENDPOINT = "Login";
    public static final String REGISTER_ENDPOINT = "Register";
    public static final String DISHES_LIST_ENDPOINT = "GetDishes";
    public static final String ORDER_ENDPOINT = "Order";
    public static final String ORDER_LIST_ENDPOINT = "GetOrders";
    public static final String ORDER_RECEIVED_ENDPOINT = "ReceiveOrder";

    public boolean login(String username, String password);

    public boolean register(String username, String password);

    public List<Dish> getListDishes();

    public boolean registerOrder(Order order, String username);

    public List<Order> getListOrders(String username);

    public boolean receiveOrder(Order order);
}