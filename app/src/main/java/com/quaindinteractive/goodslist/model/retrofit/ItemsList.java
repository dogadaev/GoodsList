package com.quaindinteractive.goodslist.model.retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "products")
public class ItemsList {

    @ElementList(inline = true)
    List<Item> itemsList;

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }
}
