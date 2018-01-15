package com.quaindinteractive.goodslist.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "product")
public class Item {
    @Element(name = "id")
    private int id;
    @Element(name = "name")
    private String name;
    @Element(name = "price")
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
