package com.quaindinteractive.goodslist.model.retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class XmlList {

    @ElementList(inline = true)
    List<ItemsList> products;

    public List<ItemsList> getProducts() {
        return products;
    }

    public void setProducts(List<ItemsList> products) {
        this.products = products;
    }
}
