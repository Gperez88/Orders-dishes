package com.android.app.ordersdishes.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gabriel on 10/31/2015.
 */
public class Order implements Serializable {
    private int id;
    private String username;
    private Dish dish;
    private int state;
    private Date date;

    public Order() {
    }

    public Order(Date date, int id, String username, Dish dish, int state) {
        this.date = date;
        this.id = id;
        this.username = username;
        this.dish = dish;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
